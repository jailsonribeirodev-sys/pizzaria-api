package com.pizzaria.api.TestConfig;

import com.pizzaria.api.dto_created.ClienteDTO;
import com.pizzaria.api.entities.*;
import com.pizzaria.api.enums.StatusPedido;
import com.pizzaria.api.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;

@Component
@Transactional
public class testconfig implements CommandLineRunner {
    private ItemPedidoRepository itemPedidoRepository;
    private ProdutoRepository produtoRepository;
    private ClienteRepository clienteRepository;
    private PedidoRepository pedidoRepository;
    private CategoriaRepository categoriaRepository;

    public testconfig(ItemPedidoRepository itemPedidoRepository, ProdutoRepository produtoRepository,
                      ClienteRepository clienteRepository, PedidoRepository pedidoRepository,
                      CategoriaRepository categoriaRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
        this.categoriaRepository = categoriaRepository;
    }

	@Override
	public void run(String... strings) throws Exception {
        Cliente cliente =  new Cliente(null, "Jailson Ribeiro","98984-764627", "0894747588475", "jailson@gmail.com");

        Endereco endereco = new Endereco(null, "Santo André","63",
                "Ritinha","Santa Inês","Maranhão",
                "65300000","Proximo á Ultragaz");

        cliente.addEndereco(endereco);
        clienteRepository.save(cliente);


        Categoria categoria1 = new Categoria(null, "Bebidas", "Refrigerantes, Alcools e sucos");
        Categoria categoria2 = new Categoria(null, "Massas", "Pizzas, salgados, espaquete , pasteis");

        categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));


        Produto produto1 = new Produto(null, "Pizzaria", new BigDecimal(68.32), Instant.now());
        Produto produto2 = new Produto(null, "Refrigerante", new BigDecimal(8.32), Instant.now());

        categoria2.addProduto(produto1);
        categoria1.addProduto(produto2);

        produtoRepository.saveAll(Arrays.asList(produto1, produto2));

        Pedido pedido1 = new Pedido(null, cliente, 1, Instant.now());
        ItemPedido item1 = new ItemPedido(produto1, pedido1, 10, new BigDecimal(68.32),"Pedido ok" );
        ItemPedido item2 = new ItemPedido(produto2, pedido1, 5, new BigDecimal(10),"Pedido ok" );


        pedido1.getItens().addAll(Arrays.asList(item1,item2));
        pedidoRepository.save(pedido1);






	}



}
