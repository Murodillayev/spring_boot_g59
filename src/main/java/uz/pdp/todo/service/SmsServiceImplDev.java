package uz.pdp.todo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Profile("dev")
public class SmsServiceImplDev implements SmSService {


    @Override
    public void send(String message, String phone) {
        log.info(" send confirm code to " + phone);
        log.info("withdraw 0 uzs");
        log.info("code : " + message);
    }
}
