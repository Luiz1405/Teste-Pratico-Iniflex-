package br.com.iniflex.service;

import br.com.iniflex.model.Funcionario;

import java.util.List;

public class FuncionarioService {

    public void remover(List<Funcionario> funcionarios, String nome) {
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals(nome));
    }
}
