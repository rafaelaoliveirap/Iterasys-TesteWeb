package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	private WebDriver driver;
	
	private By emailLogin = By.name("email");
	
	private By senhaLogin = By.name("password");
	
	private By botaoSignInFormulario = By.id("submit-login");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver; 
	}
	
	public void preencherEmail(String textoEmail) {
		driver.findElement(emailLogin).sendKeys(textoEmail);
	}
	
	public void preencherSenha(String textoSenha) {
		driver.findElement(senhaLogin).sendKeys(textoSenha);
	}
	
	public void clicarBotaoSignInFormulario() {
		driver.findElement(botaoSignInFormulario).click();
	}
	
	
	

}
