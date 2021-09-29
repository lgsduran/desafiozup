package pages;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utils.TestBase;

public class AbstractPageObject extends TestBase {
	
	protected AbstractPageObject() {
        PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 15), this);
    }

}
