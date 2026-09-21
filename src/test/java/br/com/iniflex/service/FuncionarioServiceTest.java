package br.com.iniflex.service;

import br.com.iniflex.model.Funcionario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FuncionarioServiceTest {

    private final FuncionarioService funcionarioService = new FuncionarioService();

    @Test
    @DisplayName("Deve confirmar que o funcionário joão é removido da lista")
    void deveRemoverFuncionarioPorNome() {
        List<Funcionario> funcionarios = new ArrayList<>(List.of(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
                new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador")));

        funcionarioService.remover(funcionarios, "João");

        assertThat(funcionarios)
                .extracting(Funcionario::getNome)
                .containsExactly("Maria");
    }

    @Test
    @DisplayName("Deve confirmar que a lista permanece inalterada quando o nome informado não existe")
    void naoDeveAlterarListaQuandoNomeNaoExiste() {
        List<Funcionario> funcionarios = new ArrayList<>(List.of(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador")));

        funcionarioService.remover(funcionarios, "Inexistente");

        assertThat(funcionarios).hasSize(1);
    }
}
