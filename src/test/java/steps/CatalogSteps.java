package steps;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.Assert.assertThat;

import java.util.List;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.CatalogPage;

public class CatalogSteps {
	
	CatalogPage catalogPage = new CatalogPage();
	
	@When("^confirmo a busca do produto$")
	public void confirmoABuscaDoProduto() throws Throwable {
		catalogPage.confirmarBusca();
	}
	
	@When("^busco por produto$")
	public void buscoPorProduto(DataTable dados) throws Throwable {
		catalogPage.escreverProduto(dados.asList(String.class).get(0));
	}
	
	@When("^acesso \"([^\"]*)\" > \"([^\"]*)\"$")
	public void acesso(String menu, String item) throws Throwable {
	    catalogPage.encontrarProduto(menu, item);
	}
	
	@When("^busco por produto para adicionar ao carrinho$")
	public void buscoPorProdutoParaAdicionarAoCarrinho(DataTable dados) throws Throwable {
		catalogPage.adicionarProdutoAoCarrinho(dados.asList(String.class).get(0));
	}
		
	@When("^adiciono produto ao carrinho$")
	public void adicionoProdutoAoCarrinho(DataTable dados) throws Throwable {
		catalogPage.adicionarProdutoAoCarrinhoEmLojas(dados.asList(String.class).get(0));
	}
	
	@Then("^sistema deve exibir o produto$")
	public void sistemaDeveExibirOProduto(DataTable dados) throws Throwable {
		List<String> list = dados.asList(String.class);
		assertThat(catalogPage.validarProduto(list.get(0).toString()), equalToIgnoringCase(list.get(0).toString()));
	}

	@Then("^sistema deve adicionar produto ao carrinho$")
	public void sistemaDeveAdicionarProdutoAoCarrinho(DataTable dados) throws Throwable {
		assertThat(catalogPage.validarProdutoNoCarrinho(), equalToIgnoringCase(dados.asList(String.class).get(0)));
	}
	
}
