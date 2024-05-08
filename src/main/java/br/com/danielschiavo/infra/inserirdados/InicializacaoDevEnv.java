package br.com.danielschiavo.infra.inserirdados;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.danielschiavo.repository.cliente.ClienteRepository;
import br.com.danielschiavo.repository.pedido.PedidoRepository;
import br.com.danielschiavo.repository.produto.CategoriaRepository;
import br.com.danielschiavo.repository.produto.ProdutoRepository;
import br.com.danielschiavo.shop.model.cliente.Cliente;
import br.com.danielschiavo.shop.model.cliente.Cliente.ClienteBuilder;
import br.com.danielschiavo.shop.model.cliente.cartao.Cartao;
import br.com.danielschiavo.shop.model.cliente.cartao.Cartao.CartaoBuilder;
import br.com.danielschiavo.shop.model.cliente.cartao.TipoCartao;
import br.com.danielschiavo.shop.model.cliente.endereco.Endereco;
import br.com.danielschiavo.shop.model.cliente.endereco.Endereco.EnderecoBuilder;
import br.com.danielschiavo.shop.model.cliente.role.NomeRole;
import br.com.danielschiavo.shop.model.cliente.role.Role;
import br.com.danielschiavo.shop.model.cliente.role.Role.RoleBuilder;
import br.com.danielschiavo.shop.model.pedido.Pedido;
import br.com.danielschiavo.shop.model.pedido.Pedido.PedidoBuilder;
import br.com.danielschiavo.shop.model.pedido.TipoEntrega;
import br.com.danielschiavo.shop.model.pedido.pagamento.MetodoPagamento;
import br.com.danielschiavo.shop.model.produto.Produto;
import br.com.danielschiavo.shop.model.produto.Produto.ProdutoBuilder;
import br.com.danielschiavo.shop.model.produto.categoria.Categoria;
import br.com.danielschiavo.shop.model.produto.categoria.Categoria.CategoriaBuilder;

@Profile("dev")
@Component
public class InicializacaoDevEnv implements CommandLineRunner {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private LimpadorBancoDeDados limpadorBancoDeDados;
	
	private ProdutoBuilder produtoBuilder = Produto.builder();
	private CategoriaBuilder categoriaBuilder = Categoria.builder();
	
	private EnderecoBuilder enderecoBuilder = Endereco.builder();
	private CartaoBuilder cartaoBuilder = Cartao.builder();
	private ClienteBuilder clienteBuilder = Cliente.builder();
	private RoleBuilder roleBuilder = Role.builder();
	
	private PedidoBuilder pedidoBuilder = Pedido.builder();
	
	@Override
	@Transactional
	public void run(String... args) throws Exception {
		limpadorBancoDeDados.limpar();
		
		inserirDados();
	}

	public void inserirDados() {
		
		List<Categoria> categorias = 
				categoriaBuilder
				.categoria(null, "Computadores")
							.comSubCategoria(null, "Teclado")
							.comSubCategoria(null, "Mouse")
							.comSubCategoria(null, "SSD")
							.comSubCategoria(null, "Placa de Video")
				.categoria(null, "Softwares")
							.comSubCategoria(null, "Sistema Administrativo")
							.comSubCategoria(null, "Automacao")
				.getCategorias();
		categoriaRepository.saveAll(categorias);
		
		List<Produto> produtos = produtoBuilder
					.id(null)
					 	  .nome("Teclado RedDragon switch vermelho")
						  .descricao("Teclado reddragon, switch vermelho, sem teclado numérico pt-br, com leds, teclas macro, switch óptico, teclas anti-desgaste")
						  .preco(200.00)
						  .quantidade(999)
						  .ativo(true)
						  .tipoEntregaIdTipo(null, TipoEntrega.RETIRADA_NA_LOJA)
						  .arquivoProdutoIdNomePosicao(null, "Padrao.jpeg", (byte) 0)
						  .subCategoria(categorias.get(0).getSubCategorias().get(0))
					.id(null)
					 	  .nome("Mouse RedDragon")
						  .descricao("Descricao mouse reddragon")
						  .preco(200.00)
						  .quantidade(999)
						  .ativo(true)
						  .tipoEntregaIdTipo(null, TipoEntrega.RETIRADA_NA_LOJA)
						  .arquivoProdutoIdNomePosicao(null, "Padrao.jpeg", (byte) 0)
						  .subCategoria(categorias.get(0).getSubCategorias().get(0))
						  .getProdutos();
						  
		produtoRepository.saveAll(produtos);

		List<Cliente> clientes = clienteBuilder
				.id(1L)
						.cpf("12345678912")
						.nome("Daniel")
						.sobrenome("Schiavo Rosseto")
						.dataNascimento(LocalDate.of(2000, 3, 3))
						.dataCriacaoConta(LocalDate.now())
						.email("daniel.schiavo35@gmail.com")
						.senha("$2a$12$g/401MRFl.y7b4x5jOPjeu5d31oI9a.uI9WL1pWXR.0ocFj9J/DNu")
						.celular("27996121255")
						.fotoPerfil("Padrao.jpeg")
						.adicionarRole(roleBuilder.id(null)
												  .role(NomeRole.ADMIN).build())
						.carrinho(true)
						.comItemCarrinhoIdQuantidadeProduto(null, 3, produtos.get(0).getId())
						.adicionarEndereco(enderecoBuilder.id(null)
														  .cep("29142298")
														  .rua("NaoSeiONome")
														  .numero("15")
														  .complemento(null)
														  .bairro("Itapua")
														  .cidade("Vila Velha")
														  .estado("ES")
														  .enderecoPadrao(true).build())
						.adicionarCartao(cartaoBuilder.id(null)
													  .nomeBanco("Santander")
													  .numeroCartao("1123444255591132")
													  .nomeNoCartao("Daniel Schiavo Rosseto")
													  .validadeCartao("03/25")
													  .cartaoPadrao(true)
													  .tipoCartao(TipoCartao.CREDITO).build())
				.id(null)
						.cpf("12345678994")
						.nome("Silvana")
						.sobrenome("Pereira da silva")
						.dataNascimento(LocalDate.of(2000, 5, 3))
						.dataCriacaoConta(LocalDate.now())
						.email("silvana.dasilva@gmail.com")
						.senha("$2a$12$g/401MRFl.y7b4x5jOPjeu5d31oI9a.uI9WL1pWXR.0ocFj9J/DNu")
						.celular("27999833653")
						.fotoPerfil("Padrao.jpeg")
						.adicionarEndereco(enderecoBuilder.id(null)
														  .cep("29142298")
														  .rua("Avenida luciano das neves")
														  .numero("3233")
														  .complemento("Apartamento 302")
														  .bairro("Praia de itaparica")
														  .cidade("Vila Velha")
														  .estado("ES")
														  .enderecoPadrao(true).build())
						.adicionarCartao(cartaoBuilder.id(null)
													  .nomeBanco("Santander")
													  .numeroCartao("1111222244445555")
													  .nomeNoCartao("Silvana pereira da silva")
													  .validadeCartao("03/28")
													  .cartaoPadrao(true)
													  .tipoCartao(TipoCartao.CREDITO).build())
				.getClientes();
		
		clienteRepository.saveAll(clientes);
		
		List<Pedido> pedidos = pedidoBuilder
				.cliente(clientes.get(0))
					 .comItemPedidoIdQuantidadeProduto(null, 2, produtos.get(0))
					 .pagamentoIdMetodo(null, MetodoPagamento.PIX)
					 .entregaIdTipo(null, TipoEntrega.ENTREGA_DIGITAL)
				.cliente(clientes.get(1))
					 .comItemPedidoIdQuantidadeProduto(null, 2, produtos.get(1))
					 .pagamentoIdMetodo(null, MetodoPagamento.PIX)
					 .entregaIdTipo(null, TipoEntrega.ENTREGA_DIGITAL)
					 .getPedidos();
		
			
		pedidoRepository.saveAll(pedidos);
		
	}
}
