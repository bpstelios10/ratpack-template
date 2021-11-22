package framework.templates.ratpack.functional.config;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;
import framework.templates.ratpack.service.RatpackApplication;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ratpack.test.MainClassApplicationUnderTest;

import static framework.templates.ratpack.functional.model.ServiceEndpoints.PRIVATE_STATUS;
import static ratpack.http.Status.OK;

@Slf4j
public class LocalApplicationInstanceManager {

    @Getter
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
            return applicationUnderTest.getHttpClient().get(PRIVATE_STATUS.getPath()).getStatusCode() == OK.getCode();
        } catch (Exception e) {
            log.error("Exception while calling [{}]", PRIVATE_STATUS.getPath(), e);
            return false;
        }
    }
}
