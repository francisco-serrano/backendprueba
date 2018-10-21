package devops.backendprueba;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@PropertySource("classpath:configuration.properties")
public class Configuration {

    @Value("${bcra_auth_token}")
    private String bcraAuthToken;

    @Value("${mensaje_prueba}")
    private String mensajePrueba;

    public String getBcraAuthToken() {
        return bcraAuthToken;
    }

    public String getMensajePrueba() {
        return mensajePrueba;
    }
}

