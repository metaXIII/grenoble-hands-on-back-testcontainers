
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.testcontainers.containers.BrowserWebDriverContainer
import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.containers.Network
import java.io.File
import java.util.concurrent.TimeUnit

class InfrastructureTest {

  companion object {
    val containers = DockerComposeContainer(File(javaClass.getResource("docker-compose.yml").file))
      .withExposedService("front_1", 80)

    val chrome = BrowserWebDriverContainer()
      .withCapabilities(ChromeOptions())
      .withNetwork(ContainersNetwork())

    val rootUrl: String
    val driver: WebDriver

    init {
      containers.start()
      chrome.start()

      rootUrl = "http://front"
      driver = chrome.webDriver.apply {
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

class ContainersNetwork : Network {
  override fun close() {
  }

  override fun apply(p0: Statement?, p1: Description?): Statement? {
    return null;
  }

  override fun getId(): String {
    return "weather"
  }

}
