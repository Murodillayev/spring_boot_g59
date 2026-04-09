package uz.pdp.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import tools.jackson.databind.ObjectMapper;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public DataSource  dataSource1() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl()
//        dataSource.setUrl()
//        dataSource.setUrl()
//        dataSource.setUrl();
        return dataSource;
    }

    @Bean
    public DataSource  dataSource2() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl()
//        dataSource.setUrl()
//        dataSource.setUrl()
//        dataSource.setUrl();
        return dataSource;
    }


    @Bean
    public JdbcTemplate jdbcTemplate2() {
        return new JdbcTemplate(dataSource1());
    }

    @Bean
    public JdbcTemplate jdbcTemplate1() {
        return new JdbcTemplate(dataSource2());
    }
}
