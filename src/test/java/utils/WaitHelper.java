package utils;

import static org.junit.Assert.fail;

import java.time.Duration;
import java.time.LocalDateTime;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class WaitHelper extends TestBase {

	private Wait<WebDriver> wait;

	public WaitHelper(WebDriver driver) {
		this.wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
	}

	public boolean esperaPaginaCarregar() {

		ExpectedCondition<Boolean> jQueryLoad = null, jsLoad = null;

		try {
			jQueryLoad = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					try {
						return ((Long) ((JavascriptExecutor) getDriver()).executeScript("return jQuery.active") == 0);
					} catch (Exception e) {
						// no jQuery present
						return true;
					}
				}
			};

			jsLoad = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) getDriver()).executeScript("return document.readyState").toString()
							.equals("complete");
				}
			};

		} catch (TimeoutException e) {
			fail(LocalDateTime.now() + " Tempo excedido para carregar elementos na página");
		}

		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}

	public void esperarElemento(By by) {
		wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(by)));
	}

	public void esperarElemento(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
}
