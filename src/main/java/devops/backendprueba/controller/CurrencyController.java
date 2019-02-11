package devops.backendprueba.controller;

import devops.backendprueba.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {
        "http://localhost:4200",
        "http://localhost:9876"
})
public class CurrencyController {

    private final CurrencyService dolarService;

    @Autowired
    public CurrencyController(CurrencyService dolarService) {
        this.dolarService = dolarService;
    }

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @GetMapping(value = "/get_cotizacion_dolar", produces = "application/json")
    public String getCotizacionDolar() {
        return dolarService.getCotizacion().toString();
    }

    @PutMapping(value = "/update_database")
    public void updateDatabase()
    {
        dolarService.addValues();
    }
}
