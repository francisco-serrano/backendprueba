package devops.backendprueba;

import devops.backendprueba.service.DolarService;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class DolarServiceTestConfig {

    @Bean
    Configuration configuration() {
        return new Configuration();
    }

    @Bean
    DolarService dolarService() {
        return new DolarService(configuration());
    }
}
