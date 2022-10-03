package br.com.marciocostarjr.repository;

import br.com.marciocostarjr.dto.ProdutoDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static br.com.marciocostarjr.dto.enums.Categoria.*;

@Repository
public class ProdutoRepository {

    private static final List<ProdutoDTO> PRODUCTS = List.of(

            new ProdutoDTO("Fogão", 500.00, APARELHOS),
            new ProdutoDTO("Liquidificador", 125.00, APARELHOS),
            new ProdutoDTO("Secador", 65.00, APARELHOS),
            new ProdutoDTO("Torradeira", 48.00, APARELHOS),
            new ProdutoDTO("Geladeira", 1200.00, APARELHOS),

            new ProdutoDTO("Oratória", 13.00, LIVROS),
            new ProdutoDTO("Lógica de Programação", 70.00, LIVROS),
            new ProdutoDTO("Java para Iniciantes", 41.00, LIVROS),
            new ProdutoDTO("Clean Architecture", 32.00, LIVROS),
            new ProdutoDTO("Microserviços", 16.00, LIVROS),

            new ProdutoDTO("Escova de Cabelo", 19.99, COSMETICOS),
            new ProdutoDTO("Shampoo", 25.00, COSMETICOS),
            new ProdutoDTO("Sabonete", 2.50, COSMETICOS),

            new ProdutoDTO("TV", 1999.25, ELETRONICOS),
            new ProdutoDTO("Headphone", 133.25, ELETRONICOS),
            new ProdutoDTO("Notebook", 2517.25, ELETRONICOS),
            new ProdutoDTO("Monitor", 650.25, ELETRONICOS),

            new ProdutoDTO("Vassoura", 9.90, UTENSILIOS),
            new ProdutoDTO("Lixeira", 5.90, UTENSILIOS),
            new ProdutoDTO("Escorredor de pratos", 99.99, UTENSILIOS)
    );

    public ProdutoDTO obterProdutoAleatorio() {
        final int intervalo = ThreadLocalRandom.current().nextInt(0, 20);
        return PRODUCTS.get(intervalo);
    }
}
