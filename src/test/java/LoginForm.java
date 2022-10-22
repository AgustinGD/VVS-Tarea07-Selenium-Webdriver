import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginForm extends PageObject {
    public static final String USERNAME = "dumbridge";
    public static final String PASSWORD = "tomriddle";
    public static final String WRONG_PASSWORD = "1234";
    public static final String EMPTY = "";

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(css = "button")
    private WebElement loginButton;

    @FindBy(id = "estado")
    private WebElement stateSpan;

    public LoginForm(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        this.username.sendKeys(username);
    }

    public void enterPassword(String password) {
        this.password.sendKeys(password);
    }

    public void pressLoginButton() {
        this.loginButton.click();
    }

    public String getStateText() {
        return stateSpan.getText();
    }
}
