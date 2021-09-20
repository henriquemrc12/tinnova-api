package com.tinnova.api.veiculo.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoCreateForm {

    private String nome;

    private String marca;

    private Integer ano;

    private String descricao;

    private boolean vendido;
}
