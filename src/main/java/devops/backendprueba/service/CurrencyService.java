package devops.backendprueba.service;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import devops.backendprueba.Configuration;
import devops.backendprueba.database.JDBCService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

    @Autowired
    private Configuration configuration;

    public void addValues(){
        JDBCService db = new JDBCService();
        db.makeConnection();
        JsonNode response = getJsonDolar();
        JSONArray jsons = response.getArray();
        int i = jsons.length()-1;
        while (i>0){
            JSONObject j = jsons.getJSONObject(i);
            String date = j.getString("d");
            double val = j.getDouble("v");
            if (!db.existDate(date)){
                db.insertValue(date,val);
            }
            i--;
        }
        db.closeConnection();
        System.out.println("Carga de datos finalizada");
    }

    private JsonNode getJsonDolar(){
        JsonNode response = null;
        try {
            response = Unirest.get("http://api.estadisticasbcra.com/usd_of")
                    .header("Authorization", "Bearer " + configuration.getBcraAuthToken())
                    .asJson().getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        assert response != null;
        return response;
    }

    public JSONObject getCotizacion() {
        JDBCService db = new JDBCService();
        db.makeConnection();
        JSONObject last = db.getLastValue();
        last.put("m", analizarValor(last));
        db.closeConnection();
        return last;
    }

    private String analizarValor(JSONObject json) {
        double valor = json.getDouble("v");

        if (valor >= 30.0d)
            return "Valor elevado";

        return "Valor \"normal\"";
    }
}
