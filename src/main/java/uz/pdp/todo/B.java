package uz.pdp.todo;

import org.springframework.stereotype.Service;

@Service
public class B {
    public String m1(String data){
        System.out.println("B m1");
        // logic
        return data.toLowerCase();
    }
}
