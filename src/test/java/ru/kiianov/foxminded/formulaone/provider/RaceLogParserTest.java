package ru.kiianov.foxminded.formulaone.provider;

import org.junit.jupiter.api.Test;
import ru.kiianov.foxminded.formulaone.domain.Racer;
import ru.kiianov.foxminded.formulaone.parser.RaceLogParser;
import ru.kiianov.foxminded.formulaone.parser.RaceParser;
import ru.kiianov.foxminded.formulaone.reader.FileReader;
import ru.kiianov.foxminded.formulaone.reader.StreamFileReader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RaceLogParserTest {
    private final RaceParser parser = new RaceLogParser();
    private final FileReader reader = new StreamFileReader();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");

    @Test
    void parseShouldCorrectParsingLogString() {

        final List<String> abbreviations = reader.read("src/test/resources/abbreviations.txt");
        final List<String> starts = reader.read("src/test/resources/start.log");
        final List<String> ends = reader.read("src/test/resources/end.log");

        final List<Racer> racers = new ArrayList<>();
        racers.add(new Racer("CSR",
                "Carlos Sainz",
                "RENAULT",
                LocalDateTime.parse("2018-05-24_12:03:15.145", dateTimeFormatter),
                LocalDateTime.parse("2018-05-24_12:04:28.095", dateTimeFormatter)));

        assertEquals(racers.get(0), parser.parse(ends, starts, abbreviations).stream()
                .filter(racer -> "CSR".equals(racer.getAbbreviation())).findAny().get());
    }
}
