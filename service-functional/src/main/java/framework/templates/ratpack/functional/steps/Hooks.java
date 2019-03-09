package framework.templates.ratpack.functional.steps;

import cucumber.api.java.Before;
import cucumber.runtime.java.guice.ScenarioScoped;
import framework.templates.ratpack.functional.config.LocalApplicationInstanceManager;
import lombok.Getter;

import javax.inject.Inject;

@ScenarioScoped
@Getter
public class Hooks {

    private static boolean dunit = false;

    private LocalApplicationInstanceManager localApplicationInstanceManager;

    @Inject
    public Hooks(LocalApplicationInstanceManager localApplicationInstanceManager) {
        this.localApplicationInstanceManager = localApplicationInstanceManager;
    }

    @Before
    public void beforeAll() {
        if (!dunit) {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                //After all commands can go here
                localApplicationInstanceManager.stopApp();
            }));
            //Before all commands can go here
            localApplicationInstanceManager.startApp();
            dunit = true;
        }
    }
}
