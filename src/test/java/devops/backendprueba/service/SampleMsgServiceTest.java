package devops.backendprueba.service;

import devops.backendprueba.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SampleMsgServiceTest {

    @TestConfiguration
    static class SampleMsgServiceTestContextConfiguration {

        @Bean
        public Configuration configuration() {
            return new Configuration();
        }

        @Bean
        public SampleMsgService sampleMsgService() {
            return new SampleMsgService();
        }
    }

    @Autowired
    private SampleMsgService sampleMsgService;

    @Test
    public void checkSampleMsg() {
        assertEquals("que onda bigote", sampleMsgService.getSampleMessage());
    }
}
