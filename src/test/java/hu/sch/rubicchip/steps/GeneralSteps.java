package hu.sch.rubicchip.steps;

import hu.sch.rubicchip.restassured.RestAssuredEntity;
import hu.sch.rubicchip.restassured.RestAssuredService;
import hu.sch.rubicchip.sharing.Response;
import hu.sch.rubicchip.utils.FileUtil;
import hu.sch.rubicchip.utils.InitFullState;
import hu.sch.rubicchip.utils.ResponseJsonComparator;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ResponseOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GeneralSteps {

    @Autowired
    private Response response;

    @Autowired
    private RestAssuredService restAssuredService;

    private static final String REQUESTS_PATH = "/requests/";

    @When("Send POST request to {string} url and {string} content")
    public void sendPostRequestToUrlAndContent(String url, String content) {
        RestAssuredEntity restAssuredEntity = RestAssuredEntity.builder(HttpMethod.POST.name(), url)
                .headers(InitFullState.getHeaders())
                .body(FileUtil.readFile(REQUESTS_PATH + content))
                .build();

        calling(restAssuredEntity);
    }

    @When("Send PUT request to {string} url")
    public void sendPutRequestToUrlAndContent(String url) {
        RestAssuredEntity restAssuredEntity = RestAssuredEntity.builder(HttpMethod.PUT.name(), url)
                .headers(InitFullState.getHeaders())
                .build();

        calling(restAssuredEntity);
    }

    private void calling(RestAssuredEntity restAssuredEntity) {
        // Calling
        ResponseOptions<io.restassured.response.Response> rp = restAssuredService.executeApi(restAssuredEntity);
        response.setValue(rp);
    }

    @Then("Validate response with result {string} and the response code is {int}")
    public void validateResponseWithResultAndTheResponseCodeIs(String file, int statusCode) throws IOException {
        assertThat(response.getValue().getStatusCode(), equalTo(statusCode));

        ResponseJsonComparator.compareResponseWithStaticFile(
                response.getValue().getBody().asString(),
                file);
    }

    @Then("Validate response code is {int}")
    public void validateResponseCodeIs(int statusCode) {
        assertThat(response.getValue().getStatusCode(), equalTo(statusCode));
    }
}
