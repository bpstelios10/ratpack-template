package framework.templates.ratpack.functional.config;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;
import framework.templates.ratpack.service.RatpackApplication;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ratpack.test.MainClassApplicationUnderTest;

import javax.inject.Singleton;

import static ratpack.http.Status.OK;

@Slf4j
@Singleton
@Getter
public class LocalApplicationInstanceManager {

    private MainClassApplicationUnderTest applicationUnderTest;

    public void startApp() {
        applicationUnderTest = new MainClassApplicationUnderTest(RatpackApplication.class);

        try {
            Awaitility.await().atMost(Duration.ONE_MINUTE).until(this::appIsReady);
        } catch (Exception e) {
            log.error("Application did not start successfully", e);
            System.exit(1);
        }
    }

    public void stopApp() {
        if (applicationUnderTest != null) {
            applicationUnderTest.stop();
        }
    }

    private Boolean appIsReady() {
        try {
            return applicationUnderTest.getHttpClient().get("/status").getStatusCode() == OK.getCode();
        } catch (Exception e) {
            log.error("Exception while calling /status", e);
            return false;
        }
    }
}