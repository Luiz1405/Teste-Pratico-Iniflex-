package br.com.iniflex.util;

import br.com.iniflex.model.Funcionario;

import java.util.ArrayList;
import java.util.List;

public final class ImpressorFuncionarios {

    private static final String[] CABECALHO = {"Nome", "Data Nascimento", "Salário", "Função"};

    private ImpressorFuncionarios() {
    }

    public static void imprimir(List<Funcionario> funcionarios) {
        System.out.println(montarTabela(funcionarios));
    }

    public static String montarTabela(List<Funcionario> funcionarios) {
        int[] larguras = calcularLarguras(funcionarios);
        String linhaSeparadora = montarLinhaSeparadora(larguras);

        List<String> linhas = new ArrayList<>();
        linhas.add(linhaSeparadora);
        linhas.add(montarLinha(CABECALHO, larguras));
        linhas.add(linhaSeparadora);
        funcionarios.forEach(funcionario -> linhas.add(montarLinha(linhaDados(funcionario), larguras)));
        linhas.add(linhaSeparadora);

        return String.join(System.lineSeparator(), linhas);
    }

    private static String[] linhaDados(Funcionario funcionario) {
        return new String[] {
                funcionario.getNome(),
                FormatadorBrasileiro.formatarData(funcionario.getDataNascimento()),
                FormatadorBrasileiro.formatarValor(funcionario.getSalario()),
                funcionario.getFuncao()
        };
    }

    private static int[] calcularLarguras(List<Funcionario> funcionarios) {
        int[] larguras = new int[CABECALHO.length];
        for (int coluna = 0; coluna < CABECALHO.length; coluna++) {
            larguras[coluna] = CABECALHO[coluna].length();
        }
        for (Funcionario funcionario : funcionarios) {
            String[] linha = linhaDados(funcionario);
            for (int coluna = 0; coluna < linha.length; coluna++) {
                larguras[coluna] = Math.max(larguras[coluna], linha[coluna].length());
            }
        }
        return larguras;
    }

    private static String montarLinha(String[] colunas, int[] larguras) {
        StringBuilder linha = new StringBuilder("|");
        for (int coluna = 0; coluna < colunas.length; coluna++) {
            linha.append(' ')
                    .append(alinharEsquerda(colunas[coluna], larguras[coluna]))
                    .append(' ')
                    .append('|');
        }
        return linha.toString();
    }

    private static String montarLinhaSeparadora(int[] larguras) {
        StringBuilder linha = new StringBuilder("+");
        for (int largura : larguras) {
            linha.append("-".repeat(largura + 2)).append('+');
        }
        return linha.toString();
    }

    private static String alinharEsquerda(String texto, int largura) {
        return String.format("%-" + largura + "s", texto);
    }
}
