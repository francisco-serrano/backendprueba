package devops.backendprueba;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest {

    @GetMapping("/")
    public String responseTest() {
        return "que onda bigote";
    }
}
