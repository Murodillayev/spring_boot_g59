package uz.pdp.todo.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class YmlData {
    @Value("${application.sync-db:true}")
    private Boolean userniDbDanOlibYasasinmi;
}
