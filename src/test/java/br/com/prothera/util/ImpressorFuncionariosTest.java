package br.com.prothera.util;

import br.com.prothera.model.Funcionario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ImpressorFuncionariosTest {

    @Test
    @DisplayName("Deve confirmar que a tabela é montada com nome, data, salário e função formatados")
    void deveMontarTabelaComDadosFormatados() {
        Funcionario funcionario = new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador");

        String tabela = ImpressorFuncionarios.montarTabela(List.of(funcionario));

        assertThat(tabela)
                .contains("Nome")
                .contains("Maria")
                .contains("18/10/2000")
                .contains("2.009,44")
                .contains("Operador");
    }

    @Test
    @DisplayName("Deve confirmar que a largura das colunas se ajusta ao maior valor de cada uma")
    void deveAjustarLarguraDasColunasAoMaiorValor() {
        Funcionario funcionario = new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista");

        String tabela = ImpressorFuncionarios.montarTabela(List.of(funcionario));
        List<String> linhas = tabela.lines().toList();

        assertThat(linhas).allSatisfy(linha -> assertThat(linha).hasSize(linhas.get(0).length()));
    }
}
