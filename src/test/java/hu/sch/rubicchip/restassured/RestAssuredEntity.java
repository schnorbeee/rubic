package hu.sch.rubicchip.restassured;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "internalBuilder")
public class RestAssuredEntity {

    @NonNull
    private final String uri;
    @NonNull
    private final String httpMethod;
    private Map<String, String> headers;
    private Object body;

    public static RestAssuredEntityBuilder builder(String httpMethod, String uri) {
        return internalBuilder().httpMethod(httpMethod).uri(uri);
    }
}