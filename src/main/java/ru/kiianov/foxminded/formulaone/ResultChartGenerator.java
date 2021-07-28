package ru.kiianov.foxminded.formulaone;

import ru.kiianov.foxminded.formulaone.parcer.AbbreviationParser;
import ru.kiianov.foxminded.formulaone.parcer.LogParser;
import ru.kiianov.foxminded.formulaone.provider.ViewProvider;
import ru.kiianov.foxminded.formulaone.validator.Validator;

public class ResultChartGenerator {
    private final AbbreviationParser abbreviationParser;
    private final LogParser logParser;
    private final ViewProvider viewProvider;
    private final Validator validator;

    public ResultChartGenerator(AbbreviationParser abbreviationParser,
                                LogParser logParser,
                                ViewProvider viewProvider,
                                Validator validator) {
        this.abbreviationParser = abbreviationParser;
        this.logParser = logParser;
        this.viewProvider = viewProvider;
        this.validator = validator;
    }

    public String generateResultChart(String abbreviationFileName, String startLogFileName, String endLogFileName) {
        validator.validate(abbreviationFileName, startLogFileName, endLogFileName);

        return viewProvider.provideView(
                logParser.parse(startLogFileName, endLogFileName),
                abbreviationParser.parse(abbreviationFileName));
    }
}
