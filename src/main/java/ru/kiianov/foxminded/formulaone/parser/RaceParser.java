package ru.kiianov.foxminded.formulaone.parser;

import ru.kiianov.foxminded.formulaone.domain.Racer;

import java.util.List;

public interface RaceParser {
    List<Racer> parse(List<String> ends, List<String> starts, List<String> abbreviations);
}
