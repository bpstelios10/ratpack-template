package framework.templates.ratpack.functional.steps;

import framework.templates.ratpack.functional.config.LocalStartupManager;
import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java8.En;

import javax.inject.Inject;

@ScenarioScoped
public class Hooks implements En {

    private static boolean dunit = false;

    @Inject
    public Hooks(LocalStartupManager localStartupManager) {
        Before(() -> {
            if (!dunit) {
                Runtime.getRuntime().addShutdownHook(new Thread(() -> doAfterAllTestsFinish(localStartupManager)));
                doBeforeAnyTestStart(localStartupManager);
                dunit = true;
            }
        });
    }

    private void doBeforeAnyTestStart(LocalStartupManager localStartupManager) {
        localStartupManager.startMocks();
        localStartupManager.startApp();
    }

    private void doAfterAllTestsFinish(LocalStartupManager localStartupManager) {
        localStartupManager.stopApp();
        localStartupManager.stopMocks();
    }
}
