package hu.sch.rubicchip.sharing;

import io.restassured.response.ResponseOptions;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Setter
@Getter
@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class Response {

    ResponseOptions<io.restassured.response.Response> value;
}
