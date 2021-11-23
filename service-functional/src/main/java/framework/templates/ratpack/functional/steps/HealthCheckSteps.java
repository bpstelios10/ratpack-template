package framework.templates.ratpack.functional.steps;

import framework.templates.ratpack.functional.config.LocalStartupManager;
import framework.templates.ratpack.functional.model.ServiceEndpoints;
import io.cucumber.java8.En;
import ratpack.http.client.ReceivedResponse;
import ratpack.test.http.TestHttpClient;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class HealthCheckSteps implements En {

    private TestHttpClient httpClient;
    private ReceivedResponse receivedResponse;

    @Inject
    public HealthCheckSteps(LocalStartupManager localStartupManager) {

        When("^the client intends to call (.*) endpoint$", (String endpoint) -> {
            httpClient = localStartupManager.getApplicationUnderTest().getHttpClient();
            receivedResponse = httpClient.get(ServiceEndpoints.valueOf(endpoint).getPath());
        });

        Then("^a response with code (\\d+) is returned$", (Integer responseStatus) ->
                assertThat(receivedResponse.getStatusCode()).isEqualTo(responseStatus)
        );

        Then("^the response body is \"([^\"]*)\"$", (String expectedResponseBody) -> {
            switch (expectedResponseBody) {
                case "empty":
                    assertEquals(receivedResponse.getBody().getText(), "");
                    break;
                case "not empty":
                    assertNotEquals(receivedResponse.getBody().getText(), "");
                    break;
                default:
                    assertEquals(receivedResponse.getBody().getText(), expectedResponseBody);
                    break;
            }
        });
    }
}
