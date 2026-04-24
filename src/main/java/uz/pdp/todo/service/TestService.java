package uz.pdp.todo.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    private final SmSService smSService;

    public TestService(SmSService smSService) {
        this.smSService = smSService;
    }

    public void register(String data) {
        // save db
        // send sms code
        smSService.send("12321", data);
    }

    public void confirmRegister(String code) {
        // check code and activate user
    }


}
