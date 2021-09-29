package utils;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	private static WebDriver driver = null;
	private Properties p = new Properties();
	private String path = System.getProperty("user.dir");

	public void initBrowser() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("disable-infobars");
		options.setPageLoadStrategy(PageLoadStrategy.NONE);
		driver = new ChromeDriver(options);
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().implicitlyWait(10, SECONDS);

	}

	public WebDriver getDriver() {
		return (driver == null) ? throwException("Driver object is null") : driver;
	}

	public void openPage() {
		loadArquivoProperties();
		toUrl(p.getProperty("url"));
	}

	public void quitBrowser() {
		if (driver != null) getDriver().quit();

		driver = null;
	}

	public void captureScreenshot(String fileName) {
		LocalDateTime agora = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");

		String rootPath = null;
		String destinationPath = null;

		try {

			rootPath = this.path + File.separator + "screenshot" + File.separator;
			destinationPath = rootPath + fileName + "_" + agora.format(format) + ".png";
			TakesScreenshot screenshot = (TakesScreenshot) getDriver();
			File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(sourceFile, new File(destinationPath));
			System.out.println("Screenshot path: " + rootPath);

		} catch (Exception e) {
			System.out.println("Erro no método captureScreenshot : " + e.getMessage());
		}
	}

	private void toUrl(String url) {
		getDriver().get(url);
	}

	private void loadArquivoProperties() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.path);
		sb.append(File.separator);
		sb.append("src");
		sb.append(File.separator);
		sb.append("test");
		sb.append(File.separator);
		sb.append("resources");
		sb.append(File.separator);
		sb.append("prop.properties");

		File f = new File(sb.toString());
		try {
			FileInputStream fis = new FileInputStream(f);
			p.load(fis);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private <T> T throwException(String mensagem) {
		throw new RuntimeException(mensagem);
	}
}
