package devops.backendprueba.controller;

import devops.backendprueba.service.DolarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        mvc.perform(get("http://localhost:8080/get_cotizacion_dolar"))
                .andExpect(status().isOk());
    }
}
