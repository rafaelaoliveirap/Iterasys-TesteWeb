package homepage;

import org.junit.jupiter.api.Test; 
import static org.junit.jupiter.api.Assertions.assertTrue;

import base.BaseTests;
import pages.CarrinhoPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ModalProdutoPage;
import pages.ProdutoPage;
import util.Funcoes;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


import java.util.List;

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
	
	ProdutoPage produtoPage;
	
	String nomeProduto_ProdutoPage;
	
	@Test
	public void testValidarDescricaoEValorProdutos_DescricaoEValorIguais() {
	 int indiceProduto = 0;
	 String nomeProduto_HomePage = homePage.obterNomeProduto(indiceProduto);
	 String precoProduto_HomePage = homePage.obterPrecoProduto(indiceProduto);
	 
	 System.out.println(nomeProduto_HomePage);
	 System.out.println(precoProduto_HomePage);
	 
	 produtoPage = homePage.aoClicarNoProduto(indiceProduto);
	 
	 nomeProduto_ProdutoPage = produtoPage.obterNomeProduto();
	 String precoProduto_ProdutoPage = produtoPage.obterPrecoProduto();
	 
	 System.out.println(nomeProduto_ProdutoPage);
	 System.out.println(precoProduto_ProdutoPage);
	 
	 assertThat(nomeProduto_HomePage.toUpperCase(), is (nomeProduto_ProdutoPage.toUpperCase()));
	 assertThat(precoProduto_HomePage, is(precoProduto_ProdutoPage));
	}
	
	LoginPage loginPage;
	
	@Test
	public void testLoginComSucesso_UsuarioEstaLogado() {
		// clicar no botão signIn (homePage)
		
		loginPage = homePage.clicarNoBotaoSignInNavBar();
		
		//Preencher usuario e senha
		
		loginPage.preencherEmail("rafaela@teste.com");
		loginPage.preencherSenha("rafateste");
		
		// clicar no botao SignIn para logar
		
		loginPage.clicarBotaoSignInFormulario();
		
		//validar se o usuario conseguiu se logar
		assertThat(homePage.usuarioEstaLogado("Rafaela Oliveira"), is (true));
		
		carregarPaginaInicial();
	
	}
	
	@Test
	public void adicionarProdutoNoCarrinho_ProdutoAdicionadocomSucesso() {
		if(!homePage.usuarioEstaLogado("Rafaela Oliveira")) {
			testLoginComSucesso_UsuarioEstaLogado();
		}
	}
	
	ModalProdutoPage modalProdutoPage;
	
	@Test
	public void incluirProdutoNocarrinho_ProdutoInclusoComSucesso() {
		
		String tamanhoProduto = "M";
		String corProduto = "Black";
		int quantidadeProduto = 2;
		
		//pré-çondição : usuario logado
		
		if(!homePage.usuarioEstaLogado("Rafaela Oliveira")){
			testLoginComSucesso_UsuarioEstaLogado();
		}
		
		//Teste - selecionando produto
		testValidarDescricaoEValorProdutos_DescricaoEValorIguais();
		
		//Teste - selecionando tamanho
		List<String> listaOpcoes = produtoPage.obterOpcoesSelecionadas();
		
		System.out.println(listaOpcoes.get(0));
		System.out.println("Tamanho da lista: " + listaOpcoes.size());
		produtoPage.selecionarOpcaoDropDown("M");
		
		listaOpcoes = produtoPage.obterOpcoesSelecionadas();
		System.out.println(listaOpcoes.get(0));
		System.out.println("Tamanho da lista: " + listaOpcoes.size());
		
		//Teste - selecionando cor
		
		produtoPage.selecionarCorPreta();
		
		//Teste - selecionando quantidade
		produtoPage.selecionarQuantidade(quantidadeProduto);
		
		// Adicionar no carrinho
		modalProdutoPage = produtoPage.clicarBotaoAddToCart();
		
		//validações
		
		assertTrue(modalProdutoPage.obterMensagemProdutoAdicionado().endsWith("Product successfully added to your shopping cart"));
		
		String precoProdutoString = modalProdutoPage.obterPrecoProduto();
		precoProdutoString = precoProdutoString.replace("$","");
		Double precoProduto = Double.parseDouble(precoProdutoString);

		assertThat(modalProdutoPage.obterDescricaoProduto().toUpperCase(), is(nomeProduto_ProdutoPage.toUpperCase()));
		
		assertThat(modalProdutoPage.obterCorProduto(), is(corProduto));
		assertThat(modalProdutoPage.obterTamanhoProduto(), is(tamanhoProduto));
		assertThat(modalProdutoPage.obterQuantidadeProduto(), is(Integer.toString(quantidadeProduto)));
		
		String subtotalProdutosString = modalProdutoPage.obterSubtotalProdutos();
		subtotalProdutosString = subtotalProdutosString.replace("$","");
		Double subtotalProdutos = Double.parseDouble(subtotalProdutosString);
		
		Double subtotalCalculado = quantidadeProduto * precoProduto;
	
	}
	
	//valores esperados
	
	String esperado_nomeProduto = "Hummingbird printed t-shirt"; 
	Double esperado_precoProduto = 19.12;
	String esperado_tamanhoProduto = "M";
	String esperado_corProduto = "Black";
	int esperado_inputComQuantidadeProdutos = 2;
	Double esperado_subtotalProdutos = esperado_precoProduto * esperado_inputComQuantidadeProdutos;
	
	int esperado_numeroItensLateral = esperado_inputComQuantidadeProdutos;
	Double esperado_subtotalLateral = esperado_subtotalProdutos;
	Double esperado_shippingLateral = 7.00;
	Double esperado_TotalSemTaxasLateral = esperado_subtotalLateral + esperado_shippingLateral;
	Double esperado_TotalTaxasInclusasLateral = esperado_TotalSemTaxasLateral;
	Double esperado_taxasLateral = 0.00;	
	
	Double esperado_valorShipping = 7.00;
	String esperado_nomeCliente = "Rafaela Oliveira";
	
	CarrinhoPage carrinhoPage;
	
	@Test
	public void irParaCarrinho_InformacoesPersistidas() {
		// pre-condiçoes
		incluirProdutoNocarrinho_ProdutoInclusoComSucesso();

		carrinhoPage= modalProdutoPage.clicarBotaoProceedToCheckout();

		// Teste

		// Validando todos os elementos da tela
		System.out.println("-----TELA DO CARRINHO-----");
		
		System.out.println(carrinhoPage.obterNomeProduto());
		System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obterPrecoProduto()));
		System.out.println(carrinhoPage.obterTamanhoProduto());
		System.out.println(carrinhoPage.obterCorProduto());
		System.out.println(carrinhoPage.inputComQuantidadeProdutos());
		System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obterSubtotalProdutos()));
		
		System.out.println("-----ITENS DA LATERAL-----");
		
		System.out.println(Funcoes.removeTextoItemsDevolveInt(carrinhoPage.obterNumeroItensLateral()));
		System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obterSubtotalLateral()));
		System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obterShippingLateral()));
		System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obterTotalSemTaxasLateral()));
		System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obterTotalTaxasInclusasLateral()));
		System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obterTaxasLateral()));
		
		
		//Asserções Hamcrest
		assertThat(carrinhoPage.obterNomeProduto(), is(esperado_nomeProduto));
		assertThat(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obterPrecoProduto()), is(esperado_precoProduto));
		assertThat(carrinhoPage.obterTamanhoProduto(),is(esperado_tamanhoProduto));
		assertThat(carrinhoPage.obterCorProduto(), is(esperado_corProduto));
		assertThat(Integer.parseInt(carrinhoPage.inputComQuantidadeProdutos()), is(esperado_inputComQuantidadeProdutos));
		assertThat(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obterSubtotalProdutos()),
				is(esperado_subtotalProdutos));
		
		assertThat(Funcoes.removeTextoItemsDevolveInt(carrinhoPage.obterNumeroItensLateral()), is (esperado_numeroItensLateral));
		assertThat(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obterSubtotalLateral()), is (esperado_subtotalLateral));
		assertThat(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obterShippingLateral()), is(esperado_shippingLateral));
		assertThat(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obterTotalSemTaxasLateral()), is(esperado_TotalSemTaxasLateral));
		assertThat(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obterTotalTaxasInclusasLateral()), is(esperado_TotalTaxasInclusasLateral));
		assertThat(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obterTaxasLateral()), is(esperado_taxasLateral));
		
				                                
	}
	
	CheckoutPage checkoutPage;
	
	@Test
	public void irParaCheckout_FretePagamentoEnderecoListadoOk() {
		irParaCarrinho_InformacoesPersistidas();
		
		checkoutPage = carrinhoPage.clicarBotaoProceedToCheckout();
		
		assertThat(Funcoes.removeCifraoDevolveDouble(checkoutPage.obterTotalTaxasInclusasLateral()), is(esperado_TotalTaxasInclusasLateral));
		
		assertTrue(checkoutPage.obterNomeCliente().startsWith(esperado_nomeCliente));
		
		checkoutPage.clicarBotaoContinueAddress();
		
		String valorShipping = checkoutPage.obterValorShipping();
		
		valorShipping = Funcoes.removeTexto(valorShipping, " tax excl.");
		
		Double valorShippingDouble = Funcoes.removeCifraoDevolveDouble(valorShipping);
		
		assertThat(valorShippingDouble, is(esperado_valorShipping));
		
		checkoutPage.clicarBotaoContinueShipping();
		
	
	
	
	}
	
	
	
}
