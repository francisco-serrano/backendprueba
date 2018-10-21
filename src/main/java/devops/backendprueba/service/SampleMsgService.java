package devops.backendprueba.service;

import devops.backendprueba.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleMsgService {

    @Autowired
    private Configuration configuration;

    public String getSampleMessage() {
        return configuration.getMensajePrueba();
    }
}
