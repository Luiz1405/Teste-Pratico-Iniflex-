package br.com.iniflex.service;

import br.com.iniflex.model.Funcionario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class FuncionarioServiceTest {

    private final FuncionarioService funcionarioService = new FuncionarioService();

    @Test
    @DisplayName("Deve confirmar que o aumento é aplicado a todos os funcionários da lista")
    void deveAplicarAumentoATodosOsFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>(List.of(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("1000.00"), "Operador"),
                new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2000.00"), "Recepcionista")
        ));

        funcionarioService.aplicarAumento(funcionarios, new BigDecimal("10"));

        assertThat(funcionarios)
                .extracting(Funcionario::getSalario)
                .containsExactly(new BigDecimal("1100.00"), new BigDecimal("2200.00"));
    }

    @Test
    @DisplayName("Deve confirmar que o total dos salários soma todos os funcionários da lista")
    void deveCalcularTotalDosSalarios() {
        List<Funcionario> funcionarios = List.of(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("1000.00"), "Operador"),
                new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2000.00"), "Recepcionista")
        );

        BigDecimal total = funcionarioService.calcularTotalSalarios(funcionarios);

        assertThat(total).isEqualByComparingTo("3000.00");
    }

    @Test
    @DisplayName("Deve confirmar que o total dos salários é zero quando a lista está vazia")
    void deveRetornarTotalZeroQuandoListaVazia() {
        BigDecimal total = funcionarioService.calcularTotalSalarios(List.of());

        assertThat(total).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    @DisplayName("Deve confirmar que a quantidade de salários mínimos é calculada com base no salário do funcionário")
    void deveCalcularQuantidadeDeSalariosMinimos() {
        Funcionario funcionario = new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("2424.00"), "Diretor");

        BigDecimal quantidade = funcionarioService.calcularQuantidadeSalariosMinimos(funcionario);

        assertThat(quantidade).isEqualByComparingTo("2.00");
    }

    @Test
    @DisplayName("Deve confirmar que a quantidade de salários mínimos arredonda quando a divisão não é exata")
    void deveArredondarQuantidadeDeSalariosMinimosQuandoDivisaoNaoEExata() {
        Funcionario funcionario = new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1000.00"), "Operador");

        BigDecimal quantidade = funcionarioService.calcularQuantidadeSalariosMinimos(funcionario);

        assertThat(quantidade).isEqualByComparingTo("0.83");
    }

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

    @Test
    @DisplayName("Deve confirmar que os funcionários são agrupados por função")
    void deveAgruparFuncionariosPorFuncao() {
        Funcionario maria = new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador");
        Funcionario heitor = new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador");
        Funcionario laura = new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente");

        Map<String, List<Funcionario>> agrupado = funcionarioService.agruparPorFuncao(List.of(maria, heitor, laura));

        assertThat(agrupado).containsOnlyKeys("Operador", "Gerente");
        assertThat(agrupado.get("Operador")).containsExactly(maria, heitor);
    }

    @Test
    @DisplayName("Deve confirmar que filtra apenas os funcionários que nascem nos meses informados")
    void deveFiltrarFuncionariosPorMesDeNascimento() {
        Funcionario maria = new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador");
        Funcionario helena = new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente");
        Funcionario heloisa = new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista");

        List<Funcionario> aniversariantes = funcionarioService.filtrarPorMesNascimento(List.of(maria, helena, heloisa), 10, 12);

        assertThat(aniversariantes)
                .extracting(Funcionario::getNome)
                .containsExactly("Maria");
    }

    @Test
    @DisplayName("Deve confirmar que retorna lista vazia quando ninguém nasce nos meses informados")
    void deveRetornarListaVaziaQuandoNinguemNasceNosMesesInformados() {
        Funcionario helena = new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente");

        List<Funcionario> aniversariantes = funcionarioService.filtrarPorMesNascimento(List.of(helena), 10, 12);

        assertThat(aniversariantes).isEmpty();
    }

    @Test
    @DisplayName("Deve confirmar que encontra o funcionário com a data de nascimento mais antiga")
    void deveEncontrarFuncionarioMaisVelho() {
        Funcionario maria = new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador");
        Funcionario caio = new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador");

        Optional<Funcionario> maisVelho = funcionarioService.encontrarMaisVelho(List.of(maria, caio));

        assertThat(maisVelho).contains(caio);
    }

    @Test
    @DisplayName("Deve confirmar que retorna vazio ao buscar o mais velho em uma lista vazia")
    void deveRetornarVazioAoBuscarMaisVelhoEmListaVazia() {
        Optional<Funcionario> maisVelho = funcionarioService.encontrarMaisVelho(List.of());

        assertThat(maisVelho).isEmpty();
    }

    @Test
    @DisplayName("Deve confirmar que ordena os funcionários em ordem alfabética pelo nome")
    void deveOrdenarFuncionariosPorNome() {
        Funcionario maria = new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador");
        Funcionario alice = new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista");
        Funcionario caio = new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador");

        List<Funcionario> ordenado = funcionarioService.ordenarPorNome(List.of(maria, alice, caio));

        assertThat(ordenado)
                .extracting(Funcionario::getNome)
                .containsExactly("Alice", "Caio", "Maria");
    }
}
