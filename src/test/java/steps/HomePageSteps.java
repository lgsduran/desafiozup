package steps;

import cucumber.api.java.en.Given;
import utils.TestBase;

public class HomePageSteps extends TestBase {
	
	@Given("^que estou em um grande portal de comercio online$")
	public void que_estou_em_um_grande_portal_de_comercio_online() throws Throwable {
		openPage();
		
	}

}
