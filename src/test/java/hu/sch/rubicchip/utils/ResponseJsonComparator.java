package hu.sch.rubicchip.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.flipkart.zjsonpatch.JsonDiff;
import org.openapitools.jackson.nullable.JsonNullableModule;

import java.io.IOException;

import static org.junit.Assert.fail;

public class ResponseJsonComparator {

    private static final String RESPONSE_PATH = "/responses/";

    public static void compareResponseWithStaticFile(String body, String testFileName) {
        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new JsonNullableModule());

        String content = FileUtil.readFile(RESPONSE_PATH + testFileName);
        try {
            JsonNode sourceObj = mapper.readTree(content);
            JsonNode responseObj = mapper.readTree(body);

            JsonNode patch = JsonDiff.asJson(sourceObj, responseObj);
            checkJsonPatch(patch, sourceObj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void checkJsonPatch(JsonNode patch, JsonNode sourceObj) {
        if (patch.isEmpty()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("There are ").append(patch.size())
                .append(" diffs in the response compared to the JSON response!\n");

        for (JsonNode diff : patch) {
            String operation = diff.get("op").asText();
            String path = diff.get("path").asText();
            JsonNode valueNode = diff.get("value");
            String expected = sourceObj.at(path).asText();
            String actual = valueNode != null ? valueNode.asText() : "null";

            sb.append("operation: ").append(operation)
                    .append(" | path: ").append(path)
                    .append(" | expected: ").append(expected)
                    .append(" | actual: ").append(actual)
                    .append('\n');
        }

        fail(sb.toString());
    }
}
