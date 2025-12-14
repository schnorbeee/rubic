package hu.sch.rubicchip.configurations;

import hu.sch.rubicchip.RubicChipApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@CucumberContextConfiguration
@SpringBootTest(classes = RubicChipApplication.class, webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@ComponentScan(basePackages = "hu.sch.rubicchip")
public class CucumberSpringConfiguration {

}
