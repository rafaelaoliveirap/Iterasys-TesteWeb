package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProdutoPage {
	
	private WebDriver driver;
	
	private By nomeProduto = By.className("h1");
	
	private By precoProduto = By.cssSelector(".current-price span:nth-child(1)");
	
	private By tamanhoProduto = By.id("group_1");
	
	private By produtoCorPreta = By.xpath("//ul[@id='group_2']//input[@value='11']");
	
	private By quantidadeProduto = By.id("quantity_wanted");
	
	private By addProdutoNoCarrinho = By.className("add-to-cart");
	
	public ProdutoPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String obterNomeProduto() {
		return driver.findElement(nomeProduto).getText();
	}
	
	public String obterPrecoProduto() {
		return driver.findElement(precoProduto).getText();
	}
	
	
	public void selecionarOpcaoDropDown(String opcaoSelecionada) {
		encontrarDropDown().selectByVisibleText(opcaoSelecionada);
	}
	
	public List<String> obterOpcoesSelecionadas(){
		List<WebElement> elementoSelecionado = 
				encontrarDropDown().getAllSelectedOptions();
		
		List<String> listaDeOpcoesSelecionadas = new ArrayList<String>();
		
		for(WebElement elementos: elementoSelecionado) {
			listaDeOpcoesSelecionadas.add(elementos.getText());
		} 
		
		return listaDeOpcoesSelecionadas;
		
		
	}
	
	public Select encontrarDropDown() {
		return new Select(driver.findElement(tamanhoProduto));
	}
	
	public void selecionarCorPreta() {
		driver.findElement(produtoCorPreta).click();
	}

	public void selecionarQuantidade(int quantidade) {
		driver.findElement(quantidadeProduto).clear();
		driver.findElement(quantidadeProduto).sendKeys(Integer.toString(quantidade));
	}
	
	public ModalProdutoPage clicarBotaoAddToCart() {
		driver.findElement(addProdutoNoCarrinho).click();
		return new ModalProdutoPage(driver);
	}
}
