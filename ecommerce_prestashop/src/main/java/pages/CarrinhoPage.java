package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CarrinhoPage {

	private WebDriver driver;

	private By nomeProduto = By.cssSelector("div.product-line-info a");

	private By precoProduto = By.cssSelector("span.price");

	private By tamanhoProduto = By
			.xpath("//div[contains(@class,'product-line-grid-body')]//div[3]/span[contains(@class,'value')]");

	private By corProduto = By
			.xpath("//div[contains(@class,'product-line-grid-body')]//div[4]/span[contains(@class,'value')]");

	private By quantidadeProdutos = By.cssSelector("input.js-cart-line-product-quantity");

	private By subtotalProdutos = By.cssSelector("span.product-price strong");

	private By numeroItensLateral = By.cssSelector("span.js-subtotal");

	private By subtotalLateral = By.cssSelector("#cart-subtotal-products span.value");

	private By shippingLateral = By.cssSelector("#cart-subtotal-shipping span.value");

	private By totalSemTaxasLateral = By
			.cssSelector("div.cart-summary-totals div.cart-summary-line:nth-child(1) span.value");

	private By totalTaxasInclusasLateral = By
			.cssSelector("div.cart-summary-totals div.cart-summary-line:nth-child(2) span.value");

	private By taxasLateral = By.cssSelector("div.cart-summary-totals div.cart-summary-line:nth-child(3) span.value");

	private By botaoProceedToCheckout = By.cssSelector("a.btn-primary");

	public CarrinhoPage(WebDriver driver) {
		this.driver = driver;
	}

	public String obterNomeProduto() {
		return driver.findElement(nomeProduto).getText();
	}

	public String obterPrecoProduto() {
		return driver.findElement(precoProduto).getText();
	}

	public String obterTamanhoProduto() {
		return driver.findElement(tamanhoProduto).getText();
	}

	public String obterCorProduto() {
		return driver.findElement(corProduto).getText();
	}

	public String inputComQuantidadeProdutos() {
		return driver.findElement(quantidadeProdutos).getAttribute("value");
	}

	public String obterSubtotalProdutos() {
		return driver.findElement(subtotalProdutos).getText();
	}

	public String obterNumeroItensLateral() {
		return driver.findElement(numeroItensLateral).getText();
	}

	public String obterSubtotalLateral() {
		return driver.findElement(subtotalLateral).getText();
	}

	public String obterShippingLateral() {
		return driver.findElement(shippingLateral).getText();
	}

	public String obterTotalSemTaxasLateral() {
		return driver.findElement(totalSemTaxasLateral).getText();
	}

	public String obterTotalTaxasInclusasLateral() {
		return driver.findElement(totalTaxasInclusasLateral).getText();
	}

	public String obterTaxasLateral() {
		return driver.findElement(taxasLateral).getText();
	}

	public String obterBotaoProceedToCheckout() {
		return driver.findElement(botaoProceedToCheckout).getText();
	}

}
