package utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SeleniumFunctions extends TestBase {

	private WaitHelper waitHelper;

	public void click(WebElement element) {
		getAction().click(element).build().perform();
	}

	public String getText(WebElement element) {
		return element.getText();
	}

	public WaitHelper getWaitHelper() {
		return (waitHelper == null) ? new WaitHelper(getDriver()) : waitHelper;
	}
	
	public void scrollToElement(WebElement element) {
		getAction().moveToElement(element).perform();

	}
	
	private Actions getAction() {
		return new Actions(getDriver());
	}
	

}
