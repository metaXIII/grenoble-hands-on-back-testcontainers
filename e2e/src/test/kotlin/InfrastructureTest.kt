
import io.github.bonigarcia.wdm.WebDriverManager
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testcontainers.containers.DockerComposeContainer
import java.io.File
import java.util.concurrent.TimeUnit

class InfrastructureTest {

  companion object {
    val containers = DockerComposeContainer(File(javaClass.getResource("docker-compose.yml").file))
      .withExposedService("front_1", 80)

    val rootUrl: String
    val driver: WebDriver

    init {
      WebDriverManager.chromedriver().setup()

      containers.start()

      rootUrl  = "http://localhost:${containers.getServicePort("front_1", 80)}"
      driver = ChromeDriver().apply {
        manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
      }
    }
  }

  @Test
  fun `should display cities`() {
    driver.get(rootUrl)
    val cities = driver.findElements(By.cssSelector(".section h2")).map { it.text }
    assertThat(cities).contains("GRENOBLE")
    assertThat(cities).contains("LYON")
  }

  @Test
  fun `should display GRENOBLE weather`() {
    driver.get("$rootUrl/city/GRENOBLE")
    val weather = driver.findElements(By.cssSelector(".section table tr")).map { it.text }
    assertThat(weather).hasSizeGreaterThan(0)
  }
}
