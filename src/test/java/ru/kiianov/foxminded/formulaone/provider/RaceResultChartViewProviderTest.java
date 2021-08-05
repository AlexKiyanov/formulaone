package ru.kiianov.foxminded.formulaone.provider;

import org.junit.jupiter.api.Test;
import ru.kiianov.foxminded.formulaone.domain.Racer;
import ru.kiianov.foxminded.formulaone.parser.RaceLogParser;
import ru.kiianov.foxminded.formulaone.parser.RaceParser;
import ru.kiianov.foxminded.formulaone.reader.FileReader;
import ru.kiianov.foxminded.formulaone.reader.StreamFileReader;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RaceResultChartViewProviderTest {
    private final RaceResultChartViewProvider viewProvider = new RaceResultChartViewProvider();
    private final FileReader reader = new StreamFileReader();
    private final RaceParser parser = new RaceLogParser();

    @Test
    void provideViewShouldReturnCorrectView() {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
        final List<String> abbreviations = reader.read("src/test/resources/abbreviations.txt");
        final List<String> starts = reader.read("src/test/resources/start.log");
        final List<String> ends = reader.read("src/test/resources/end.log");

        final List<Racer> racers = parser.parse(ends, starts, abbreviations);

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

        assertEquals(expected, viewProvider.provideView(racers));
    }
}
