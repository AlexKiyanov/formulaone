package ru.kiianov.foxminded.formulaone.parcer;

import ru.kiianov.foxminded.formulaone.domain.LapData;
import ru.kiianov.foxminded.formulaone.provider.StreamFromFileProvider;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LogParser {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
    private static final int ABBREVIATION_LENGTH = 3;

    public Map<String, LapData> parse(String startLog, String endLog) {
        final Map<String, LocalDateTime> startLogData = parseLog(startLog);

        final Map<String, LocalDateTime> endLogData = parseLog(endLog);

        final Map<String, LapData> result = new HashMap<>();

        startLogData.forEach((String abb, LocalDateTime startDataTime) ->
                result.put(abb, new LapData(startDataTime, endLogData.get(abb))));

        return result;
    }

    private Map<String, LocalDateTime> parseLog(String log) {
        return new StreamFromFileProvider().provideStream(log)
                .collect(Collectors.toMap(
                        line -> line.substring(0, ABBREVIATION_LENGTH),
                        line -> LocalDateTime.parse(line.substring(ABBREVIATION_LENGTH), DATE_TIME_FORMATTER))
                );
    }
}
