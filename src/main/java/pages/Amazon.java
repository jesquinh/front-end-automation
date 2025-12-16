package pages;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import utilities.Constants;
import utilities.FrontEndOperation;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.PageFactory.initElements;
import static utilities.DriverConfiguration.getDriver;

public class Amazon extends FrontEndOperation {
  private WebDriver driver;

  @FindBy(id = "sp-cc-accept")
  WebElement aceptarCookies;

  @FindBy(id = "twotabsearchtextbox")
  WebElement barraDeBusqueda;

  @FindBy(id = "nav-cart")
  WebElement carrito;

  @FindBy(id = "nav-logo-sprites")
  WebElement inicio;

  public Amazon() {
    driver = getDriver();
    driver.manage().window().maximize();
    initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(Constants.LOW_TIMEOUT)), this);
  }

  public void aceptarCookies() {
    waitForVisibility(aceptarCookies);
    aceptarCookies.click();
    waitSeconds(1);
  }

  public void busquedaArticulo(String art) {
    barraDeBusqueda.sendKeys(art);
    barraDeBusqueda.sendKeys(Keys.ENTER);
    waitSeconds(2);
  }

  public void busquedaPrecio(Double precioLimiteInf, Double precioLimiteSup) {
    List<WebElement> productos = driver.findElements(By.className("s-result-item"));

    for (WebElement p : productos) {
      String asin = p.getAttribute("data-asin");
      if (asin != null && !asin.isEmpty()) {
        WebElement precioEntero = p.findElement(By.cssSelector("span.a-price-whole"));
        WebElement precioDecimal = p.findElement(By.cssSelector("span.a-price-fraction"));
        String precioStr = precioEntero.getText() + "." + precioDecimal.getText();

        Double precioReal = Double.parseDouble(precioStr);

        if (precioReal >= precioLimiteInf && precioReal <= precioLimiteSup) {
          WebElement link = p.findElement(By.cssSelector("a.a-link-normal.a-text-normal"));
          link.click();
          break;
        }
      }
    }
    waitSeconds(2);
  }

  public void anadirACesta() {
    WebElement botonCesta = driver.findElement(By.id("add-to-cart-button"));
    botonCesta.click();
    waitSeconds(2);
  }

  public void volverAInicio() {
    waitForVisibility(inicio);
    inicio.click();
    waitSeconds(2);
  }

  public void verCesta() {
    waitForVisibility(carrito);
    carrito.click();
    waitSeconds(2);
  }

}
