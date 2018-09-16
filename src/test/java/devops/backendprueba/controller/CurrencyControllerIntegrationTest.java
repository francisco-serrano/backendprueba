package devops.backendprueba.controller;

import devops.backendprueba.controller.CurrencyController;
import devops.backendprueba.service.DolarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyController.class)
public class CurrencyControllerIntegrationTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    DolarService dolarService;

    @Test
    public void checkCotizacion() throws Exception {
        when(dolarService.getCotizacion()).thenReturn("40p");

        mvc.perform(get("http://localhost:8080/get_cotizacion_dolar"))
                .andExpect(status().isOk())
                .andExpect(content().string("40p"));
    }
}
