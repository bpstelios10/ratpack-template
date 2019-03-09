package framework.templates.ratpack.functional;

public class CucumberTest {

    public static void main(String... args) {
        String[] newArgs = new String[]{
                "--strict",
                "--tags", "~@wip",
                "--plugin", "pretty",
                "--plugin", "html:build/cucumber-report",
                "--plugin", "junit:build/junit-test-report.xml",
                "--plugin", "json:build/cucumber-report.json",
                "--glue", "framework.templates.ratpack.functional.steps",
                "service-functional/src/test/resources/features/"
        };

        cucumber.api.cli.Main.main(newArgs);
    }
}