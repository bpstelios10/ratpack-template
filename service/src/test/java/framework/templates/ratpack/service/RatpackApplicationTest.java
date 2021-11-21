package framework.templates.ratpack.service;

import org.junit.Test;
import ratpack.http.client.ReceivedResponse;
import ratpack.test.MainClassApplicationUnderTest;

import static org.junit.Assert.assertEquals;
import static ratpack.http.Status.NOT_FOUND;
import static ratpack.http.Status.OK;

public class RatpackApplicationTest {

    static MainClassApplicationUnderTest ratpackApplication = new MainClassApplicationUnderTest(RatpackApplication.class);

    @Test
    public void givenStatusUrl_getSuccessResponse() {
        ReceivedResponse response = ratpackApplication.getHttpClient().get("/private/status");

        assertEquals("OK", response.getBody().getText());
        assertEquals(OK, response.getStatus());
    }

    @Test
    public void givenInvalidUrl_get404Response() {
        ReceivedResponse response = ratpackApplication.getHttpClient().get("/not-valid-url");

        assertEquals("Client error 404", response.getBody().getText());
        assertEquals(NOT_FOUND, response.getStatus());
    }
}
