package devops.backendprueba.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

@Service
public class DolarService {

    public String getCotizacion() {
        HttpResponse<String> response = null;

        try {
            response = Unirest.get("http://ws.geeklab.com.ar/dolar/get-dolar-json.php").asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        assert response != null;

        return response.getBody();
    }
}
