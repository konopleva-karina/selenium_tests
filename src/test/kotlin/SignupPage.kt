import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class SignupPage(private val driver: WebDriver) {

    private val pageUrl = "https://teamcity.jetbrains.com"

    init {
        PageFactory.initElements(driver, this)
    }

    @FindBy(className = "auth-button-federated")
    lateinit var authButton: WebElement

    fun open() = driver.get(pageUrl)
}
