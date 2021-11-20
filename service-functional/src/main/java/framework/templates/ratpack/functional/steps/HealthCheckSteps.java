package framework.templates.ratpack.functional.steps;

import framework.templates.ratpack.functional.config.LocalApplicationInstanceManager;
import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java8.En;
import ratpack.http.client.ReceivedResponse;
import ratpack.test.http.TestHttpClient;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static ratpack.http.Status.OK;

@ScenarioScoped
public class HealthCheckSteps implements En {

    private TestHttpClient httpClient;
    private ReceivedResponse receivedResponse;

    @Inject
    public HealthCheckSteps(LocalApplicationInstanceManager localApplicationInstanceManager) {

        When("^the client intends to call healthcheck (.*) endpoint$", (String endpoint) -> {
            httpClient = localApplicationInstanceManager.getApplicationUnderTest().getHttpClient();
            receivedResponse = httpClient.get("/status");
        });

        Then("^a success response is returned$", () ->
                assertEquals(receivedResponse.getStatusCode(), OK.getCode())
        );

        Then("^the response body is \"([^\"]*)\"$", (String expectedResponseBody) ->
                assertEquals(receivedResponse.getBody().getText(), expectedResponseBody)
        );
    }
}
