package devops.backendprueba.service;

import devops.backendprueba.Configuration;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CurrencyServiceTest {

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

    @Autowired
    private CurrencyService dolarService;

    @Test
    public void checkFormatoCotizacion() throws JSONException {
        JSONObject json = dolarService.getCotizacion();

        // Chequeo formato de la fecha
        assertTrue(((String) json.get("d")).matches("([0-9]+-[0-9]+-[0-9]+)"));

        // Chequeo formato de la cotización
        assertTrue(String.valueOf((double) json.get("v")).matches("[0-9.]+"));
    }

    @Test
    public void checkAnalisisValor() throws Exception {
        // Preguntar si se debieran testear los métodos privados
        Method method = CurrencyService.class.getDeclaredMethod("analizarValor", JSONObject.class);
        method.setAccessible(true);

        JSONObject jsonPrueba = new JSONObject();

        jsonPrueba.put("v", 39.46);
        jsonPrueba.put("d", "2018-09-19");
        assertEquals("Valor elevado", method.invoke(dolarService, jsonPrueba));

        jsonPrueba.put("v", 29.46);
        jsonPrueba.put("d", "2018-09-19");
        assertEquals("Valor \"normal\"", method.invoke(dolarService, jsonPrueba));
    }
}
