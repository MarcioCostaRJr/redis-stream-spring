package br.com.marciocostarjr.dto;

import br.com.marciocostarjr.dto.enums.Categoria;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private String nome;
    private Double preco;
    private Categoria categoria;

}
