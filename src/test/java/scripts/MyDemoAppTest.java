package scripts;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.MyDemoApp;
import utilities.*;

import static helpers.Runners.runAccessibilityCopy;
import static helpers.Runners.runAllureReport;

@ExtendWith(TestErrorHandler.class)
public class MyDemoAppTest {

  private static MyDemoApp controller;

  @BeforeAll
  public static void clean_reports_logs() {
    JSExecutor.runCommand(
        LocalEnviroment.isWindows()
            ? Constants.ALLURE_CLEAN_COMMAND_WIN
            : Constants.ALLURE_CLEAN_COMMAND_MAC);
    AllureReport.fillReportInfo();
  }

  @BeforeEach
  public void iAmOnTheMdaApp() {
    controller = new MyDemoApp();
  }

  @Test
  public void pruebaInicial() {
    controller.getClass();
  }

  @AfterEach
  public void closeDriver() {
    Accessibility.checkAccessibility();
    NetworkLogs.getNetworkLogs();
    AllureReport.fillReportInfo();
  }

  @AfterAll
  public static void runReports() {
    runAllureReport();
    runAccessibilityCopy();
  }
}
