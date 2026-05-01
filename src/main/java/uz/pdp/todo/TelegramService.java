package uz.pdp.todo;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.pdp.todo.model.Currency;
import uz.pdp.todo.model.CurrencyResponse;
import uz.pdp.todo.model.Member;

import java.time.LocalDateTime;

@Service
public class TelegramService {
    private final MemberRepository memberRepository;
    private final ConverterBot converterBot;
    private final RemoteApiService remoteApiService;
    private final ButtonMaker buttonMaker;

    public TelegramService(MemberRepository memberRepository, @Lazy ConverterBot converterBot, RemoteApiService remoteApiService, ButtonMaker buttonMaker) {
        this.memberRepository = memberRepository;
        this.converterBot = converterBot;
        this.remoteApiService = remoteApiService;
        this.buttonMaker = buttonMaker;
    }

    public void sendWelcome(Message message) {
        String chatId = message.getChatId().toString();
        Member member = createOrGetMember(chatId, message.getFrom().getUserName());
        String welcome = """
                👋 Salom %s!
                Ushbu bot valutlarni konvertatsiya qilib beradi.
                Kerakli qiymatni yuboring.
                ---------
                ⚙️ Sozlamani o'zgartirish: /settings
                """.formatted(member.getName());

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(welcome);
        converterBot.sendMessage(sendMessage);
    }

    public void deleteMessage(Integer messageId, String chatId) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(chatId);
        deleteMessage.setMessageId(messageId);
        converterBot.deleteMessage(deleteMessage);
    }

    public void sendConvertResult(String text, String chatId) {
        Member member = currentMember(chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        Double toRate = convert(text, member);
        sendMessage.setText("%s %s -> %s %s".formatted(text, member.getFrom(), toRate, member.getTo()));
        converterBot.sendMessage(sendMessage);
    }

    private Double convert(String text, Member member) {

        Double val = Double.parseDouble(text);
        if (member.getTo().equals(member.getFrom())) {
            return val;

        } else {
            CurrencyResponse toCurrency = remoteApiService.retrieveCurrency((member.getFrom().equals(Currency.UZS)) ? member.getTo().name() : member.getFrom().name()); // 1 -> 12000

            Double rate = Double.parseDouble(toCurrency.getRate());
            if (member.getFrom().equals(Currency.UZS)) {
                return val / rate;
            } else if (member.getTo().equals(Currency.UZS)) {
                return val * rate;
            } else {
                CurrencyResponse fromCurrency = remoteApiService.retrieveCurrency(member.getFrom().name()); // 1 -> 160
                Double rateFrom = Double.parseDouble(fromCurrency.getRate());
                Double fromSum = val * rateFrom;
                return fromSum / rate;
            }


        }
    }


    public Member currentMember(String chatId) {
        return createOrGetMember(chatId, null);
    }

    public Member createOrGetMember(String chatId, String name) {
        return memberRepository.findByChatId(chatId)
                .orElseGet(() -> {
                    Member newMem = new Member();
                    newMem.setCreatedAt(LocalDateTime.now());
                    newMem.setUpdatedAt(LocalDateTime.now());
                    newMem.setName(name);
                    newMem.setChatId(chatId);
                    return memberRepository.save(newMem);
                });
    }

    public void sendSettings(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Konverterni sozlang👇");
        sendMessage.setReplyMarkup(buttonMaker.settingsButton(currentMember(chatId)));
        converterBot.sendMessage(sendMessage);
    }

    public void updateFrom(String chatId, String from, Integer messageId) {
        Member member = currentMember(chatId);
        member.setFrom(Currency.valueOf(from));
        memberRepository.save(member);
        refreshSettings(messageId, chatId);
    }


    public void updateTo(String chatId, String to, Integer messageId) {
        Member member = currentMember(chatId);
        member.setTo(Currency.valueOf(to));
        memberRepository.save(member);
        refreshSettings(messageId, chatId);
    }

    private void refreshSettings(Integer messageId, String chatId) {

        EditMessageText  editMessageText = new EditMessageText();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(messageId);
        editMessageText.setText("Konverterni sozlang👇");
        editMessageText.setReplyMarkup(buttonMaker.settingsButton(currentMember(chatId)));

        converterBot.editMessage(editMessageText);
    }

}
