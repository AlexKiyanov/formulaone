package ru.kiianov.foxminded.formulaone.provider;

import org.junit.jupiter.api.Test;
import ru.kiianov.foxminded.formulaone.domain.Racer;
import ru.kiianov.foxminded.formulaone.parcer.AbbreviationParser;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AbbreviationParserTest {
    AbbreviationParser parser = new AbbreviationParser();

    @Test
    void parseShouldReturnCorrectRacersMap() {
        final Map<String, Racer> racersMap = new HashMap<>();
        racersMap.put("DRR", new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER"));

        assertEquals(racersMap.get(0), parser.parse("abbreviations.txt").get(0));
    }
}
