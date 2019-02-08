package devops.backendprueba.database;

import devops.backendprueba.Configuration;
import devops.backendprueba.service.CurrencyService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.junit.Assert.*;

public class JDBCServiceTestTest {
    @TestConfiguration
    static class CurrencyServiceTestContextConfiguration {

        @Bean
        public Configuration configuration() {
            return new Configuration();
        }

        @Bean
        public CurrencyService currencyService() {
            return new CurrencyService();
        }
    }
}