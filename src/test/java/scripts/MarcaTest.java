package scripts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pages.Marca;

import static org.hamcrest.Matchers.is;
import static utilities.FrontEndOperation.checkThat;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class MarcaTest extends GenericTest<Marca> {

    @BeforeEach
    public void setup() {
        controller.acceptCookies();
    }

    @Test
    public void checkLoginErrorMessage() {
        controller.fillLogin();
        checkThat("Comparing visible message", controller.isVisible(), is(true));
    }

    @Test
    public void checkErrorMessage() {
        controller.acceptCookies();
        controller.fillLogin();
        controller.compareVisibleMessage();
    }
}
