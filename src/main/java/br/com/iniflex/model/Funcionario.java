package br.com.iniflex.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Funcionario extends Pessoa {

    private BigDecimal salario;
    private final String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        if (salario.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Salário não pode ser negativo: " + salario);
        }
        this.salario = salario;
        this.funcao = funcao;
    }

    public void aumentarSalario(BigDecimal percentual) {
        BigDecimal fatorAumento = BigDecimal.ONE.add(percentual.divide(BigDecimal.valueOf(100)));
        this.salario = salario.multiply(fatorAumento).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getFuncao() {
        return funcao;
    }
}
