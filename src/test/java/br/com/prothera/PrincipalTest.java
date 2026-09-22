package br.com.prothera;

import br.com.prothera.model.Funcionario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PrincipalTest {

    @Test
    @DisplayName("Deve confirmar que os funcionários são criados na mesma ordem do enunciado")
    void deveCriarFuncionariosNaOrdemEsperada() {
        List<Funcionario> funcionarios = Principal.criarFuncionarios();

        assertThat(funcionarios)
                .extracting(Funcionario::getNome)
                .containsExactly(
                        "Maria", "João", "Caio", "Miguel", "Alice",
                        "Heitor", "Arthur", "Laura", "Heloísa", "Helena"
                );
    }
}
