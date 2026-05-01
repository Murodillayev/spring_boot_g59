package uz.pdp.todo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.pdp.todo.config.AppConfig;
import uz.pdp.todo.model.CurrencyResponse;

@Slf4j
@Service
public class RemoteApiService {

    private final AppConfig appConfig;
    private final RestTemplate restTemplate;

    public RemoteApiService(AppConfig appConfig, RestTemplate restTemplate) {
        this.appConfig = appConfig;
        this.restTemplate = restTemplate;
    }

    public CurrencyResponse retrieveCurrency(String currency) {
        ResponseEntity<CurrencyResponse[]> response = restTemplate.getForEntity(appConfig.getCenteralBankApi() + currency + "/", CurrencyResponse[].class);
        CurrencyResponse[] body = response.getBody();
        if (response.getStatusCode().is2xxSuccessful() && body != null && body.length > 0) {
            return body[0];
        } else {
            log.error("Markaziy bank apisiga sorov qilishda xatolik boldi");
            throw new RuntimeException("Markaziy bank ishlamayapti");
        }
    }
}
