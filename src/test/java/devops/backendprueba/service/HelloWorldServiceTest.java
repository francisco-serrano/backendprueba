package devops.backendprueba.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class HelloWorldServiceTest {

    @TestConfiguration
    static class HelloWorldServiceTestContextConfiguration {

        @Bean
        public HelloWorldService helloWorldService() {
            return new HelloWorldService();
        }
    }

    @Autowired
    HelloWorldService helloWorldService;

    @Test
    public void checkHelloWorld() {
        assertEquals("Hello World!", helloWorldService.helloWorld());
    }

}
