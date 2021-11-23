package framework.templates.ratpack.functional.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ClasspathFileSource;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.standalone.JsonFileMappingsSource;
import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;
import framework.templates.ratpack.service.RatpackApplication;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ratpack.test.MainClassApplicationUnderTest;

import static framework.templates.ratpack.functional.model.ServiceEndpoints.PRIVATE_STATUS;
import static ratpack.http.Status.OK;

@Slf4j
public class LocalStartupManager {

    @Getter
    private MainClassApplicationUnderTest applicationUnderTest;
    private WireMockServer mockServer;

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

    public void startMocks() {
        if (mockServer == null) {
            mockServer = new WireMockServer(WireMockConfiguration
                    .wireMockConfig()
                    .notifier(new ConsoleNotifier(true))
                    .port(9090)
                    .mappingSource(new JsonFileMappingsSource(new ClasspathFileSource("mappings")))
            );
        }
        if (!mockServer.isRunning()) {
            mockServer.start();
        }
    }

    public void stopMocks() {
        if (mockServer != null) {
            mockServer.stop();
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
