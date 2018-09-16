package devops.backendprueba.controller;

import devops.backendprueba.service.DolarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {

    private final DolarService dolarService;

    @Autowired
    public CurrencyController(DolarService dolarService) {
        this.dolarService = dolarService;
    }

    @GetMapping("/get_cotizacion_dolar")
    public String getCotizacionDolar() {
        return dolarService.getCotizacion();
    }
}
