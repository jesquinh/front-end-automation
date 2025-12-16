package scripts;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.Amazon;
import utilities.*;

import static helpers.Runners.runAccessibilityCopy;
import static helpers.Runners.runAllureReport;

@ExtendWith(TestErrorHandler.class)
public class AmazonTest {

  private static Amazon controller;

  @BeforeAll
  public static void clean_reports_logs() {
    JSExecutor.runCommand(
        LocalEnviroment.isWindows()
            ? Constants.ALLURE_CLEAN_COMMAND_WIN
            : Constants.ALLURE_CLEAN_COMMAND_MAC);
    AllureReport.fillReportInfo();
  }

  @BeforeEach
  public void iAmOnAmazonWebpage() {
    controller = new Amazon();
  }

  @Test
  public void buscaPiscinasYVemosCesta() {
    controller.aceptarCookies();
    controller.busquedaArticulo("Piscina infantil");
    // Buscamos una de menos de 20 y volvemos al inicio
    controller.busquedaPrecio(0., 20.);
    controller.anadirACesta();
    controller.volverAInicio();
    // Buscamos otra de entre 20 y 30 y miramos la cesta
    controller.busquedaArticulo("Piscina infantil");
    controller.busquedaPrecio(20., 30.);
    controller.anadirACesta();
    controller.verCesta();
  }

  @Test
  public void buscaMesaMayor400() {
    controller.aceptarCookies();
    controller.busquedaArticulo("Mesa de comedor");
    controller.busquedaPrecio(400., Double.MAX_VALUE);
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
