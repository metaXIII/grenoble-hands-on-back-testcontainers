
import io.github.bonigarcia.wdm.WebDriverManager
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.containers.Network
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy
import java.util.concurrent.TimeUnit

class InfrastructureTest {

  companion object {
    val network = Network.newNetwork()

    val mysql = MySQLContainer("mysql:8")
      .withDatabaseName("zenika-weather")
      .withUsername("zenika")
      .withPassword("zenika-password")
      .withNetwork(network)
      .withNetworkAliases("db")
    val weatherApi = GenericContainer("7timer:0.0.1-SNAPSHOT")
      .withNetwork(network)
      .withNetworkAliases("7timer")
    val api = GenericContainer("zenika-weather-back:0.0.1-SNAPSHOT")
      .withEnv("SPRING_DATASOURCE_URL", "jdbc:mysql://db:3306/zenika-weather")
      .withEnv("SPRING_DATASOURCE_USERNAME", "zenika")
      .withEnv("SPRING_DATASOURCE_PASSWORD", "zenika-password")
      .withEnv("WEATHER_URL", "http://7timer:1080")
      .withNetwork(network)
      .withNetworkAliases("api")
    val front = GenericContainer("zenika-weather-front:0.0.1-SNAPSHOT")
      .withEnv("API_URL", "http://api:8080")
      .withNetwork(network)
      .withNetworkAliases("front")
      .waitingFor(HttpWaitStrategy().forPort(80).forPath("/api/actuator/health"))
      .withExposedPorts(80)

    val rootUrl: String
    val driver: WebDriver

    init {
      WebDriverManager.chromedriver().setup()

      mysql.start()
      weatherApi.start()
      api.start()
      front.start()

      rootUrl  = "http://localhost:${front.firstMappedPort}"
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
