package devops.backendprueba.dolar;

import com.mashape.unirest.http.JsonNode;
import devops.backendprueba.service.DolarService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class DolarServiceTest {

    @InjectMocks
    DolarService dolarService;

    @Test
    public void checkFormatoCotizacion() throws JSONException {
        JSONObject json = new JsonNode(dolarService.getCotizacion()).getObject();

        List<String> nombres = Arrays.asList("blue", "libre");

        JSONArray jsonNames = json.names();
        for (int i = 0; i < jsonNames.length(); i++)
            assertTrue(nombres.contains(jsonNames.get(i)));

        assertTrue(((String)json.get("blue")).matches("[0-9.]+"));
        assertTrue(((String)json.get("libre")).matches("[0-9.]+"));
    }
}
