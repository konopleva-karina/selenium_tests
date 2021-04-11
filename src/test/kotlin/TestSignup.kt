import io.kotlintest.specs.StringSpec
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.concurrent.TimeUnit


class TestSignup : StringSpec() {
    private val driver: WebDriver = autoClose(WebDriverCloseable(ChromeDriver(
        ChromeOptions().addArguments("--headless")
    )))
    private val signupPage = SignupPage(driver)
    private val wait = WebDriverWait(driver, 10)

    init {
        driver.manage()?.timeouts()?.implicitlyWait(10, TimeUnit.SECONDS)
        driver.manage()?.window()?.maximize()

        "Check availability" {
            signupPage.run {
                signupPage.open()
                assert(
                    authButton.text.equals("Log in as guest")
                )
            }
        }

        "Check auth" {
            signupPage.run {
                signupPage.open()
                authButton.click()
                wait.until {
                    driver.currentUrl.contains("teamcity.jetbrains.com")
                }
                assert(
                    driver.title.equals("Projects — TeamCity")
                )
            }
        }

    }
}