package homepage;

import org.junit.jupiter.api.Test; 

import base.BaseTests;
import pages.LoginPage;
import pages.ProdutoPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HomePageTests extends BaseTests{
	@Test
	
	public void testContarProdutos_oitoProdutosDiferentes() {
		carregarPaginaInicial();
		assertThat(homePage.contarProdutos(), is (8));
	}
	
	@Test
	public void testCarrinhoVazio_zeroItensNoCarrinnho() {
		int quantidadeItensCarrinho = homePage.verificarQuantidadeItensCarrinho();
		assertThat(quantidadeItensCarrinho, is(0));
	}
	
	@Test
	public void testValidarDescricaoEValorProdutos_DescricaoEValorIguais() {
	 int indiceProduto = 0;
	 String nomeProduto_HomePage = homePage.obterNomeProduto(indiceProduto);
	 String precoProduto_HomePage = homePage.obterPrecoProduto(indiceProduto);
	 
	 System.out.println(nomeProduto_HomePage);
	 System.out.println(precoProduto_HomePage);
	 
	 ProdutoPage produtoPage = homePage.aoClicarNoProduto(indiceProduto);
	 
	 String nomeProduto_ProdutoPage = produtoPage.obterNomeProduto();
	 String precoProduto_ProdutoPage = produtoPage.obterPrecoProduto();
	 
	 System.out.println(nomeProduto_ProdutoPage);
	 System.out.println(precoProduto_ProdutoPage);
	 
	 assertThat(nomeProduto_HomePage.toUpperCase(), is (nomeProduto_ProdutoPage.toUpperCase()));
	 assertThat(precoProduto_HomePage, is(precoProduto_ProdutoPage));
	}
	
	@Test
	public void testLoginComSucesso_UsuarioEstaLogado() {
		// clicar no botão signIn (homePage)
		
		LoginPage loginPage = homePage.clicarNoBotaoSignInNavBar();
		
		//Preencher usuario e senha
		
		loginPage.preencherEmail("rafaela@teste.com");
		loginPage.preencherSenha("rafateste");
		
		// clicar no botao SignIn para logar
		
		loginPage.clicarBotaoSignInFormulario();
		
		//validar se o usuario conseguiu se logar
		assertThat(homePage.usuarioEstaLogado("Rafaela Oliveira"), is (true));
	
	}
	
}
