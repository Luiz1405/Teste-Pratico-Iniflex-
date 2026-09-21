package br.com.iniflex.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class FormatadorBrasileiro {

    private static final Locale LOCALE_BR = Locale.forLanguageTag("pt-BR");
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private FormatadorBrasileiro() {
    }

    public static String formatarData(LocalDate data) {
        return data.format(FORMATO_DATA);
    }

    public static String formatarValor(BigDecimal valor) {
        NumberFormat formatoNumerico = NumberFormat.getNumberInstance(LOCALE_BR);
        formatoNumerico.setMinimumFractionDigits(2);
        formatoNumerico.setMaximumFractionDigits(2);
        return formatoNumerico.format(valor);
    }
}
