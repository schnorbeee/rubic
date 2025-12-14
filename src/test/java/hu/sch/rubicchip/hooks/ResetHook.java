package hu.sch.rubicchip.hooks;

import hu.sch.rubicchip.services.impl.ChipServiceImpl;
import hu.sch.rubicchip.sharing.Response;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class ResetHook {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private Response response;

    @Autowired
    private ChipServiceImpl chipService;

    @Before
    public void beforeScenario() {
        log.info("Setting RestAssured port  to {}", port);
        RestAssured.port = port;

        log.info("Reset concurent map.");
        chipService.clearMemory();

        log.info("Reset the shared object. Set NULL");
        response.setValue(null);
    }
}
