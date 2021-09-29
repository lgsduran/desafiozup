package pages;

import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;
import static org.apache.commons.lang3.StringUtils.trim;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utils.SeleniumFunctions;

public class CatalogPage extends AbstractPageObject {

	private SeleniumFunctions seleniumFunctions = new SeleniumFunctions();

	@FindBy(id = "h_search-input")
	private WebElement conteudo;

	@FindBy(id = "h_search-btn")
	private WebElement btnBusca;

	@FindBy(css = "#content-middle h2")
	private List<WebElement> produtos;

	@FindBy(xpath = "//h3[normalize-space(text())='Vem ver as lojas']")
	private WebElement linkDesktop;

	@FindBy(xpath = "//*[text()='Comprar']")
	private WebElement btnComprar;

	@FindBy(xpath = "//*[text()='Continuar']")
	private WebElement btnContinuar;

	@FindBy(css = "h2.basket-productTitle")
	private WebElement meuCarrinho;

	public void escreverProduto(String produto) {
		seleniumFunctions.getWaitHelper().esperaPaginaCarregar();
		conteudo.sendKeys(produto);
	}

	public void confirmarBusca() {
		btnBusca.click();
	}

	public String validarProduto(String strProduto) {
		return seleniumFunctions.getText(
				getProdutos(produtos, produto -> equalsIgnoreCase(trim(seleniumFunctions.getText(produto)), strProduto))
						.get());
	}

	public void encontrarProduto(String menu, String item) {
		seleniumFunctions.getWaitHelper().esperaPaginaCarregar();
		linkDesktop.click();

		String menuDrop = "(//*[normalize-space(text())='" + menu + "'])[1]";
		seleniumFunctions.getWaitHelper().esperarElemento(By.xpath(menuDrop));
		seleniumFunctions.click(getDriver().findElement(By.xpath(menuDrop)));

		String itemDrop = "//a[normalize-space(.)='" + item + "']";
		seleniumFunctions.getWaitHelper().esperarElemento(By.xpath(itemDrop));
		seleniumFunctions.click(getDriver().findElement(By.xpath(itemDrop)));
	}

	public void adicionarProdutoAoCarrinho(String produto) {
		escreverProduto(produto);
		confirmarBusca();
		meuCarrinho(produto);
	}

	public void selecionarProduto(String strProduto) {
		seleniumFunctions.click(
				getProdutos(produtos, produto -> equalsIgnoreCase(trim(seleniumFunctions.getText(produto)), strProduto))
						.get());
	}

	public String validarProdutoNoCarrinho() {
		seleniumFunctions.getWaitHelper().esperarElemento(meuCarrinho);
		return meuCarrinho.getText();
	}

	public void adicionarProdutoAoCarrinhoEmLojas(String produto) {
		meuCarrinho(produto);
	}

	private void meuCarrinho(String produto) {
		selecionarProduto(produto);

		seleniumFunctions.getWaitHelper().esperarElemento(btnBusca);
		seleniumFunctions.scrollToElement(btnBusca);
		btnComprar.click();

		seleniumFunctions.getWaitHelper().esperarElemento(btnContinuar);
		seleniumFunctions.scrollToElement(btnContinuar);
		btnContinuar.click();
	}

// getProducts(collect, s -> valueOf(s)));
//	private <T, U> List<U> getProducts(List<T> produtos, Function<T, U> function) {
//		return produtos.stream().map(function).collect(Collectors.toList());
//	}

	private <T> Optional<T> getProdutos(List<T> list, Predicate<? super T> predicate) {
		return list.stream().filter(predicate).findFirst();
	}
}
