package br.com.iniflex.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class FuncionarioTest {

    @Test
    @DisplayName("Deve confirmar que um salário negativo não é aceito na criação do funcionário")
    void naoDeveAceitarSalarioNegativo() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("-500.00"), "Operador"));
    }

    @Test
    @DisplayName("Deve confirmar que o salário é aumentado no percentual informado")
    void deveAumentarSalarioNoPercentualInformado() {
        Funcionario funcionario = new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("1000.00"), "Operador");

        funcionario.aumentarSalario(new BigDecimal("10"));

        assertThat(funcionario.getSalario()).isEqualByComparingTo("1100.00");
    }

    @Test
    @DisplayName("Deve confirmar que o aumento arredonda o salário em duas casas decimais")
    void deveArredondarSalarioEmDuasCasasDecimais() {
        Funcionario funcionario = new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador");

        funcionario.aumentarSalario(new BigDecimal("10"));

        assertThat(funcionario.getSalario()).isEqualByComparingTo("10819.75");
    }

    @Test
    @DisplayName("Deve confirmar que o percentual de aumento não fica fixo em 10%")
    void deveAumentarSalarioComPercentualDiferenteDeDez() {
        Funcionario funcionario = new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("1000.00"), "Recepcionista");

        funcionario.aumentarSalario(new BigDecimal("25"));

        assertThat(funcionario.getSalario()).isEqualByComparingTo("1250.00");
    }
}
