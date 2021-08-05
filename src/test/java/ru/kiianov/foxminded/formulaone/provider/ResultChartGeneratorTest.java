package ru.kiianov.foxminded.formulaone.provider;

import org.junit.jupiter.api.Test;
import ru.kiianov.foxminded.formulaone.ResultChartGenerator;
import ru.kiianov.foxminded.formulaone.parser.RaceLogParser;
import ru.kiianov.foxminded.formulaone.reader.StreamFileReader;
import ru.kiianov.foxminded.formulaone.validator.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResultChartGeneratorTest {

    private final ResultChartGenerator resultChartGenerator = new ResultChartGenerator(
            new Validator(),
            new StreamFileReader(),
            new RaceLogParser(),
            new RaceResultChartViewProvider());

    @Test
    void provideViewShouldReturnCorrectView() {
        final String expected =
                " 1. Sebastian Vettel    | FERRARI                       | 1:04:415  \n" +
                " 2. Daniel Ricciardo    | RED BULL RACING TAG HEUER     | 1:12:13   \n" +
                " 3. Valtteri Bottas     | MERCEDES                      | 1:12:434  \n" +
                " 4. Lewis Hamilton      | MERCEDES                      | 1:12:460  \n" +
                " 5. Stoffel Vandoorne   | MCLAREN RENAULT               | 1:12:463  \n" +
                " 6. Kimi Raikkonen      | FERRARI                       | 1:12:639  \n" +
                " 7. Fernando Alonso     | MCLAREN RENAULT               | 1:12:657  \n" +
                " 8. Sergey Sirotkin     | WILLIAMS MERCEDES             | 1:12:706  \n" +
                " 9. Charles Leclerc     | SAUBER FERRARI                | 1:12:829  \n" +
                "10. Sergio Perez        | FORCE INDIA MERCEDES          | 1:12:848  \n" +
                "11. Romain Grosjean     | HAAS FERRARI                  | 1:12:930  \n" +
                "12. Pierre Gasly        | SCUDERIA TORO ROSSO HONDA     | 1:12:941  \n" +
                "13. Carlos Sainz        | RENAULT                       | 1:12:950  \n" +
                "14. Esteban Ocon        | FORCE INDIA MERCEDES          | 1:13:28   \n" +
                "15. Nico Hulkenberg     | RENAULT                       | 1:13:65   \n" +
                "------------------------------------------------------------------\n" +
                "16. Brendon Hartley     | SCUDERIA TORO ROSSO HONDA     | 1:13:179  \n" +
                "17. Marcus Ericsson     | SAUBER FERRARI                | 1:13:265  \n" +
                "18. Lance Stroll        | WILLIAMS MERCEDES             | 1:13:323  \n" +
                "19. Kevin Magnussen     | HAAS FERRARI                  | 1:13:393  \n";

        assertEquals(expected, resultChartGenerator.generateResultChart(
                "src/test/resources/abbreviations.txt",
                "src/test/resources/start.log",
                "src/test/resources/end.log"));
    }
}
