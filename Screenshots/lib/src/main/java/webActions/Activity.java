package webActions;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Activity {

	WebDriver driver = null;

	/**
	 * use this method to initialize the browser.
	 */
	public WebDriver startBrowser() throws MalformedURLException {
		// Code to Launch Browser using Zalenium in Crio workspace
		final DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(BrowserType.CHROME);

		// TODO - Milestone 5 Activity - Add configuration for headless mode
		// Configure driver to start as headless
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);

		return driver;
	}

	public static void takeScreenshot(WebDriver driver, String screenshotType, String description) {
		try {
			String localDir = System.getProperty("user.dir");
			File theDir = new File(localDir + "/screenshots");
			if (!theDir.exists()) {
				theDir.mkdirs();
			}

			String timestamp = String.valueOf(java.time.LocalDateTime.now());
			String fileName = String.format("screenshot_%s_%s_%s.png", timestamp, screenshotType, description);

			TakesScreenshot scrShot = ((TakesScreenshot) driver);
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

			File DestFile = new File("screenshots/" + fileName);
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void snippet_4_1(WebDriver driver) throws InterruptedException {
		// TODO
		for (int i = 0; i < 5; i++) {
			driver.get(
					"https://www.howtocodeschool.com/2021/10/demo-display-current-date-and-time-html-javascript.html");
			takeScreenshot(driver, "info", "screenshot_number_" + Integer.toString(i));
			Thread.sleep(5000);
		}

	}

	public static void snippet_5_1(WebDriver driver) {
		// TODO
		driver.get("https://crio-qkart-frontend-qa.vercel.app/");
		takeScreenshot(driver, "homePage", "screenshot");

		driver.get("https://crio-qkart-frontend-qa.vercel.app/login");
		takeScreenshot(driver, "login", "screenshot");

		driver.get("https://crio-qkart-frontend-qa.vercel.app/register");
		takeScreenshot(driver, "register", "screenshot");
	}

	public static void main(String[] args) throws InterruptedException, MalformedURLException, ParseException {

		// Create the object of Wait class
		Activity activity = new Activity();

		// Start the browser
		WebDriver driver = activity.startBrowser();

		// Uncomment for Milestone 4 Activity 1
		snippet_4_1(driver);

		// Uncomment for Milestone 5 Activity 1
		snippet_5_1(driver);

	}

}
