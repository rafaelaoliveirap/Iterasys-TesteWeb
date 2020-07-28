package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	private WebDriver driver;
	
	List<WebElement> listaProdutos = new ArrayList();
	
	private By produtos = By.className("product-description");
	
	private By itensNoCarrinho = By.className("cart-products-count");
	
	private By descricaoDoProduto = By.cssSelector(".product-description a");
	
	private By precoDoProduto = By.className("price");
	
	public HomePage (WebDriver driver) {
		this.driver = driver;
		
	}
	
	public int contarProdutos() {
		carregarListaProdutos();
		return listaProdutos.size(); 
	}
	
	private void carregarListaProdutos(){
		listaProdutos = driver.findElements(produtos);
	}
	
	public int verificarQuantidadeItensCarrinho() {
		String quantidadeItensCarrinho = driver.findElement(itensNoCarrinho).getText();
		quantidadeItensCarrinho = quantidadeItensCarrinho.replace("(", "");
		quantidadeItensCarrinho = quantidadeItensCarrinho.replace(")", "");
		
		int qtdItensCarrinho = Integer.parseInt(quantidadeItensCarrinho);
		return qtdItensCarrinho;
	}
	
	public String obterNomeProduto(int indiceProduto) {
		return driver.findElements(descricaoDoProduto).get(indiceProduto).getText();
		
	}
	
	public String obterPrecoProduto(int indiceProduto) {
		return driver.findElements(precoDoProduto).get(indiceProduto).getText();
		
	}
	
	public ProdutoPage aoClicarNoProduto(int indiceProduto) {
		driver.findElements(descricaoDoProduto).get(indiceProduto).click();
		return new ProdutoPage(driver);
	}
}
