package ru.kiianov.foxminded.formulaone.parser;

import ru.kiianov.foxminded.formulaone.domain.Racer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class RaceLogParser implements RaceParser {
    private static final int ABBREVIATION_LENGTH = 3;
    private static final String LOG_LINE_PART_DELIMITER = "_";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd" +
            LOG_LINE_PART_DELIMITER + "HH:mm:ss.SSS");

    private static final int ABBREVIATION_LINE_PART = 0;
    private static final int RACER_NAME_LINE_PART = 1;
    private static final int RACER_TEAM_LINE_PART = 2;

    @Override
    public List<Racer> parse(List<String> ends, List<String> starts, List<String> abbreviations) {
        return abbreviations.stream()
                .collect(Collector.of(
                        ArrayList::new,

                        (list, line) -> list.add(new Racer(
                                getAbbreviation(line),
                                getRacerName(line),
                                getRacerTeam(line),
                                parseDate(starts, getAbbreviation(line)),
                                parseDate(ends, getAbbreviation(line)))),

                        (ArrayList<Racer> list1, ArrayList<Racer> list2) -> {
                            list1.addAll(list2);
                            return list1;
                        }
                ));
    }

    private String getAbbreviation(String abbreviationLine) {
        return abbreviationLine.split(LOG_LINE_PART_DELIMITER)[ABBREVIATION_LINE_PART];
    }

    private String getRacerName(String abbreviationLine) {
        return abbreviationLine.split(LOG_LINE_PART_DELIMITER)[RACER_NAME_LINE_PART];
    }

    private String getRacerTeam(String abbreviationLine) {
        return abbreviationLine.split(LOG_LINE_PART_DELIMITER)[RACER_TEAM_LINE_PART];
    }

    private LocalDateTime parseDate(List<String> log, String abbreviation) {
        return LocalDateTime.parse(
                log.stream()
                        .filter(line -> line.contains(abbreviation))
                        .map(line -> line.substring(ABBREVIATION_LENGTH))
                        .collect(Collectors.joining("")),
                DATE_TIME_FORMATTER);
    }
}
