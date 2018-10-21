package devops.backendprueba.service;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import devops.backendprueba.Configuration;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

    @Autowired
    private Configuration configuration;

    public JSONObject getCotizacion() {
        JsonNode response = null;

        try {
            response = Unirest.get("http://api.estadisticasbcra.com/usd_of")
                    .header("Authorization", "Bearer " + configuration.getBcraAuthToken())
                    .asJson().getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        assert response != null;

        // Obtengo la última de todas las cotizaciones que se muestra en el JSON
        JSONObject jsonRetornar = getLastCotizacion(response);
        jsonRetornar.put("mensaje", analizarValor(jsonRetornar));

        return jsonRetornar;
    }

    private JSONObject getLastCotizacion(JsonNode json) {
        int cantidadCotizaciones = json.getArray().length();

        return json.getArray().getJSONObject(cantidadCotizaciones - 1);
    }

    private String analizarValor(JSONObject json) {
        double valor = json.getDouble("v");

        if (valor >= 30.0d)
            return "Valor elevado";

        return "Valor \"normal\"";
    }
}
