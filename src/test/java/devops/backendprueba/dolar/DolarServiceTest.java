package devops.backendprueba.dolar;

import devops.backendprueba.service.DolarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class DolarServiceTest {

    @InjectMocks
    DolarService dolarService;

    @Test
    public void checkCotizacion() {
        String resultado = dolarService.getCotizacion();

        assertEquals("40p", resultado);
    }
}
