package pages;

import static org.openqa.selenium.support.PageFactory.initElements;
import static utilities.DriverConfiguration.getDriver;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import utilities.Constants;
import utilities.FrontEndOperation;

public class GenericPage extends FrontEndOperation{

    public GenericPage() {
        WebDriver driver = getDriver();
        //driver.manage().window().maximize();
        initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(Constants.LOW_TIMEOUT)), this);
    }
}
