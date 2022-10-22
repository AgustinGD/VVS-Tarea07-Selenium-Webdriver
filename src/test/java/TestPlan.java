import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestPlan {
    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    @Test(testName = "Empty info login")
    public static void emptyInfoLogin() {
        String expectedState = "Atención: Debe ingresar un nombre de usuario";

        driver.get(Utils.BASE_URL);
        LoginForm loginForm = new LoginForm(driver);

        loginForm.enterUsername(LoginForm.EMPTY);
        loginForm.enterPassword(LoginForm.EMPTY);
        loginForm.pressLoginButton();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        String stateResult = loginForm.getStateText();
        Assert.assertEquals(stateResult, expectedState);
    }

    @Test(testName = "Empty password login")
    public static void emptyPasswordLogin() {
        String expectedSpan = "Atención: El password no puede estar vacío";

        driver.get(Utils.BASE_URL);
        LoginForm loginForm = new LoginForm(driver);

        loginForm.enterUsername(LoginForm.USERNAME);
        loginForm.enterPassword(LoginForm.EMPTY);
        loginForm.pressLoginButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String stateResult = loginForm.getStateText();

        Assert.assertEquals(stateResult, expectedSpan);
    }

    @Test(testName = "Successful login")
    public static void successfulLogin() {
        String expectedWelcomeMessage = "Bienvenido a OSTH On-Line";

        driver.get(Utils.BASE_URL);
        LoginForm loginForm = new LoginForm(driver);
        HomePage homePage = new HomePage(driver);

        loginForm.enterUsername(LoginForm.USERNAME);
        loginForm.enterPassword(LoginForm.PASSWORD);
        loginForm.pressLoginButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String welcomeMessageResult = homePage.getWelcomeMessage();

        Assert.assertEquals(welcomeMessageResult, expectedWelcomeMessage);
    }

    @Test(testName = "Wrong password login")
    public static void wrongPasswordLogin() {
        String expectedState = "Atención: El password ingresado no es válido";

        driver.get(Utils.BASE_URL);
        LoginForm loginForm = new LoginForm(driver);

        loginForm.enterUsername(LoginForm.USERNAME);
        loginForm.enterPassword(LoginForm.WRONG_PASSWORD);
        loginForm.pressLoginButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String stateResult = loginForm.getStateText();

        Assert.assertEquals(stateResult, expectedState);
    }
}
