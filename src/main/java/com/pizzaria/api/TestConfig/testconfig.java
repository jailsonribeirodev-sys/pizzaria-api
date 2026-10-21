package com.pizzaria.api.TestConfig;

import com.pizzaria.api.entities.Categoria;
import com.pizzaria.api.entities.Cliente;
import com.pizzaria.api.entities.Produto;
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
        Cliente cliente =  new Cliente("Jailson Ribeiro","98984-764627", "0894747588475", "jailson@gmail.com");
        clienteRepository.save(cliente);


        Categoria categoria1 = new Categoria( "Bebidas", "Refrigerantes, Alcools e sucos");
        Categoria categoria2 = new Categoria( "Massas", "Pizzas, salgados, espaquete , pasteis");

        categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));


        Produto produto1 = new Produto( "Pizzaria", new BigDecimal(68.32), Instant.now());
        Produto produto2 = new Produto( "Refrigerante", new BigDecimal(8.32), Instant.now());

        categoria2.addProduto(produto1);
        categoria1.addProduto(produto2);

        produtoRepository.saveAll(Arrays.asList(produto1, produto2));







	}



}
