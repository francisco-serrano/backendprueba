package devops.backendprueba.controller;

import devops.backendprueba.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {

    private final CurrencyService dolarService;

    @Autowired
    public CurrencyController(CurrencyService dolarService) {
        this.dolarService = dolarService;
    }

    @GetMapping(value = "/get_cotizacion_dolar", produces = "application/json")
    public String getCotizacionDolar() {
        return dolarService.getCotizacion().toString();
    }
}
