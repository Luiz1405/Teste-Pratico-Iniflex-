package br.com.iniflex.service;

import br.com.iniflex.model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class FuncionarioService {

    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    public void remover(List<Funcionario> funcionarios, String nome) {
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals(nome));
    }

    public void aplicarAumento(List<Funcionario> funcionarios, BigDecimal percentual) {
        funcionarios.forEach(funcionario -> funcionario.aumentarSalario(percentual));
    }

    public BigDecimal calcularTotalSalarios(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calcularQuantidadeSalariosMinimos(Funcionario funcionario) {
        return funcionario.getSalario().divide(SALARIO_MINIMO, 2, RoundingMode.HALF_UP);
    }
}
