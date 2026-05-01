package uz.pdp.todo;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.pdp.todo.model.Member;

import java.util.ArrayList;
import java.util.List;

@Component
public class ButtonMaker {

    public InlineKeyboardMarkup settingsButton(Member member) {
        String memberFrom = member.getFrom().name();
        String memberTo = member.getTo().name();

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton uzFrom = new InlineKeyboardButton();
        InlineKeyboardButton uzTo = new InlineKeyboardButton();
        InlineKeyboardButton usdFrom = new InlineKeyboardButton();
        InlineKeyboardButton usdTo = new InlineKeyboardButton();

        uzFrom.setText(memberFrom.equals("UZS") ? "UZS ✅" : "UZS");
        uzFrom.setCallbackData("from_uzs");

        uzTo.setText(memberTo.equals("UZS") ? "UZS ✅" : "UZS");
        uzTo.setCallbackData("to_uzs");

        usdFrom.setText(memberFrom.equals("USD") ? "USD ✅" : "USD");
        usdFrom.setCallbackData("from_usd");

        usdTo.setText(memberTo.equals("USD") ? "USD ✅" : "USD");
        usdTo.setCallbackData("to_usd");

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row1.add(uzFrom);
        row1.add(uzTo);
        row2.add(usdFrom);
        row2.add(usdTo);

        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        rows.add(row1);
        rows.add(row2);

        keyboardMarkup.setKeyboard(rows);

        return keyboardMarkup;


    }
}
