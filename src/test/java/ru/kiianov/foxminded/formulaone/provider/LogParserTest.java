package ru.kiianov.foxminded.formulaone.provider;

import org.junit.jupiter.api.Test;
import ru.kiianov.foxminded.formulaone.domain.LapData;
import ru.kiianov.foxminded.formulaone.parcer.LogParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogParserTest {
    private final LogParser parser = new LogParser();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");

    @Test
    void logParseShouldCorrectParsingLogString() {
        Map<String, LapData> expected = new HashMap<>();
        expected.put("SVF", new LapData(LocalDateTime.parse("2018-05-24_12:02:58.917", dateTimeFormatter),
                LocalDateTime.parse("2018-05-24_12:04:03.332", dateTimeFormatter)));

        assertEquals(expected.get("SVF"), parser.parse("start.log", "end.log").get("SVF"));
    }
}
