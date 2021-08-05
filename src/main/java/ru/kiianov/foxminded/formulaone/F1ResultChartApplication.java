package ru.kiianov.foxminded.formulaone;

import ru.kiianov.foxminded.formulaone.parser.RaceLogParser;
import ru.kiianov.foxminded.formulaone.provider.RaceResultChartViewProvider;
import ru.kiianov.foxminded.formulaone.reader.StreamFileReader;
import ru.kiianov.foxminded.formulaone.validator.Validator;

import java.util.Scanner;

public class F1ResultChartApplication {
    public static void main(String[] args) {
        final ResultChartGenerator resultChartGenerator = new ResultChartGenerator(
                new Validator(),
                new StreamFileReader(),
                new RaceLogParser(),
                new RaceResultChartViewProvider());

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Input abbreviations absolute file path:");
            String abbreviationsFilePath = scanner.nextLine();
            System.out.println("Input start.log absolute file path:");
            String startFilePath = scanner.nextLine();
            System.out.println("Input end.log absolute file path:");
            String endFilePath = scanner.nextLine();

            System.out.println(resultChartGenerator.generateResultChart(
                    abbreviationsFilePath,
                    startFilePath,
                    endFilePath));
        }
    }
}
