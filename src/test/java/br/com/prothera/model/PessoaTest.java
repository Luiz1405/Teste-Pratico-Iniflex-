package br.com.prothera.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class PessoaTest {

    @Test
    @DisplayName("Deve confirmar que a idade é calculada com base na data de referência informada")
    void deveCalcularIdadeComBaseNaDataDeReferencia() {
        Pessoa pessoa = new Pessoa("Maria", LocalDate.of(2000, 10, 18));

        int idade = pessoa.calcularIdade(LocalDate.of(2024, 10, 18));

        assertThat(idade).isEqualTo(24);
    }

    @Test
    @DisplayName("Deve confirmar que a idade não é somada antes do aniversário no ano de referência")
    void naoDeveSomarIdadeAntesDoAniversario() {
        Pessoa pessoa = new Pessoa("Maria", LocalDate.of(2000, 10, 18));

        int idade = pessoa.calcularIdade(LocalDate.of(2024, 10, 17));

        assertThat(idade).isEqualTo(23);
    }
}
