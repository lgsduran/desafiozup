package execution;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import utils.TestBase;

public class DesafioHooks {
	TestBase testBase;

	@Before
	public void setUp() {
		testBase = new TestBase();
		testBase.initBrowser();
	}

	@After(order = 0)
	public void tearDown() {
		testBase.quitBrowser();
	}

	@After(order = 1)
	public void screenshot(Scenario cenario) {
		if (cenario.isFailed()) {
			String fileName = cenario.getName();
			/*
			 * A evidência, gerada em caso de falha, é salva na pasta screenshot na raiz do
			 * projeto que só estará disponível se a pasta for atualizada.
			 */
			testBase.captureScreenshot(fileName);
		}
	}
}
