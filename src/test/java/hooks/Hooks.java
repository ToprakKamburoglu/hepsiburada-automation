package hooks;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.AfterStep;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.BeforeStep;
import com.thoughtworks.gauge.ExecutionContext;
import driver.Driver;

public class Hooks {

    private long stepStartTime;
    private int stepCounter = 0;

    @BeforeScenario
    public void setUp() {
        stepCounter = 0;
        System.out.println("\n========================================");
        System.out.println("SENARYO BAŞLIYOR");
        System.out.println("========================================");
        Driver.init();
    }

    @BeforeStep
    public void logStepStart(ExecutionContext context) {
        stepCounter++;
        stepStartTime = System.currentTimeMillis();
        System.out.println("\n[ADIM " + stepCounter + "] >>> " + context.getCurrentStep().getText());
    }

    @AfterStep
    public void logStepEnd(ExecutionContext context) {
        long duration = System.currentTimeMillis() - stepStartTime;
        System.out.println("[ADIM " + stepCounter + "] <<< tamamlandi (" + duration + " ms)");
    }

    @AfterScenario
    public void tearDown() {
        System.out.println("\n========================================");
        System.out.println("SENARYO TAMAMLANDI");
        System.out.println("========================================");
        Driver.quit();
    }
}