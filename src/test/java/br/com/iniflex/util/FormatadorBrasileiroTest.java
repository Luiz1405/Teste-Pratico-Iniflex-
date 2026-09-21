package br.com.iniflex.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class FormatadorBrasileiroTest {

    @Test
    @DisplayName("Deve confirmar que a data é formatada no padrão dd/MM/yyyy")
    void deveFormatarData() {
        String dataFormatada = FormatadorBrasileiro.formatarData(LocalDate.of(2000, 10, 18));

        assertThat(dataFormatada).isEqualTo("18/10/2000");
    }

    @Test
    @DisplayName("Deve confirmar que o valor monetário usa ponto de milhar e vírgula decimal")
    void deveFormatarValorComSeparadoresBrasileiros() {
        String valorFormatado = FormatadorBrasileiro.formatarValor(new BigDecimal("19119.88"));

        assertThat(valorFormatado).isEqualTo("19.119,88");
    }

    @Test
    @DisplayName("Deve confirmar que valores sem casas decimais recebem duas casas na formatação")
    void deveFormatarValorComDuasCasasDecimais() {
        String valorFormatado = FormatadorBrasileiro.formatarValor(new BigDecimal("1500"));

        assertThat(valorFormatado).isEqualTo("1.500,00");
    }
}
