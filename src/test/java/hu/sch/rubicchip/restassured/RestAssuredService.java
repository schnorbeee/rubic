package hu.sch.rubicchip.restassured;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Slf4j
@Component
public class RestAssuredService {

    public ResponseOptions<Response> executeApi(RestAssuredEntity restAssuredEntity) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setConfig(getRestAssuredConfig());
        RequestSpecification requestSpecification = builder.build();
        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.spec(requestSpecification);

        if (restAssuredEntity.getBody() != null) {
            request.body(restAssuredEntity.getBody());
        }
        if (restAssuredEntity.getHeaders() != null) {
            request.headers(restAssuredEntity.getHeaders());
        }

        ResponseOptions<Response> response = sendRequest(
                restAssuredEntity.getUri(),
                restAssuredEntity.getHttpMethod(),
                request);

        log.info("Response Time [" + restAssuredEntity.getUri() + "]: " + LocalTime.ofSecondOfDay(response.getTime())
                .toString());
        return response;
    }

    private ResponseOptions<Response> sendRequest(String uri, String httpMethod, RequestSpecification request) {
        String url = uri;
        return switch (httpMethod) {
            case "POST" -> request.post(url);
            case "PUT" -> request.put(url);
            default -> throw new RuntimeException("HttpMethod is not exist! HttpMethod:" + httpMethod);
        };
    }

    private RestAssuredConfig getRestAssuredConfig() {
        return RestAssuredConfig.config().objectMapperConfig(
                ObjectMapperConfig.objectMapperConfig()
                        .jackson2ObjectMapperFactory((type, s) ->
                                new ObjectMapper()
                                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                                        .registerModule(new JavaTimeModule())
                                        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                                        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                                        .enable(SerializationFeature.INDENT_OUTPUT))
        );
    }
}