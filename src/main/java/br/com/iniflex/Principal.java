package br.com.iniflex;

import br.com.iniflex.model.Funcionario;
import br.com.iniflex.service.FuncionarioService;
import br.com.iniflex.util.FormatadorBrasileiro;
import br.com.iniflex.util.ImpressorFuncionarios;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Principal {

    private static final BigDecimal PERCENTUAL_AUMENTO = new BigDecimal("10");

    public static void main(String[] args) {
        List<Funcionario> funcionarios = criarFuncionarios();

        FuncionarioService funcionarioService = new FuncionarioService();
        funcionarioService.remover(funcionarios, "João");

        ImpressorFuncionarios.imprimir(funcionarios);

        funcionarioService.aplicarAumento(funcionarios, PERCENTUAL_AUMENTO);
        ImpressorFuncionarios.imprimir(funcionarios);

        imprimirTotalSalarios(funcionarios, funcionarioService);
        imprimirSalariosMinimos(funcionarios, funcionarioService);
    }

    private static void imprimirTotalSalarios(List<Funcionario> funcionarios, FuncionarioService funcionarioService) {
        BigDecimal total = funcionarioService.calcularTotalSalarios(funcionarios);
        System.out.println("Total dos salários: " + FormatadorBrasileiro.formatarValor(total));
    }

    private static void imprimirSalariosMinimos(List<Funcionario> funcionarios, FuncionarioService funcionarioService) {
        funcionarios.forEach(funcionario -> {
            BigDecimal quantidade = funcionarioService.calcularQuantidadeSalariosMinimos(funcionario);
            System.out.println(funcionario.getNome() + ": " + FormatadorBrasileiro.formatarValor(quantidade) + " salários mínimos");
        });
    }

    static List<Funcionario> criarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
        return funcionarios;
    }
}
