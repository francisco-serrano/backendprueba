package devops.backendprueba.service;

import devops.backendprueba.DolarServiceTestConfig;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = "classpath:configuration.properties")
@ContextConfiguration(classes = DolarServiceTestConfig.class)
public class DolarServiceTest {

    private DolarService dolarService;

    @Autowired
    public void setDolarService(DolarService dolarService) {
        this.dolarService = dolarService;
    }

    @Test
    public void checkFormatoCotizacion() throws JSONException {
        JSONObject json = dolarService.getCotizacion();

        assertTrue(((String) json.get("d")).matches("([0-9]+-[0-9]+-[0-9]+)"));
        assertTrue(String.valueOf((double) json.get("v")).matches("[0-9.]+"));
    }

    @Test
    public void checkAnalisisValor() throws Exception {
        // Preguntar si se debieran testear los métodos privados
        Method method = DolarService.class.getDeclaredMethod("analizarValor", JSONObject.class);
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
