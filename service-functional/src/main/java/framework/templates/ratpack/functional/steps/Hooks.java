package framework.templates.ratpack.functional.steps;

import framework.templates.ratpack.functional.config.LocalApplicationInstanceManager;
import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java8.En;

import javax.inject.Inject;

@ScenarioScoped
public class Hooks implements En {

    private static boolean dunit = false;

    @Inject
    public Hooks(LocalApplicationInstanceManager localApplicationInstanceManager) {
        Before(() -> {
            if (!dunit) {
                //After all commands can go in addShutdownHook
                Runtime.getRuntime().addShutdownHook(new Thread(localApplicationInstanceManager::stopApp));
                //Before all commands can go here
                localApplicationInstanceManager.startApp();
                dunit = true;
            }
        });
    }
}
