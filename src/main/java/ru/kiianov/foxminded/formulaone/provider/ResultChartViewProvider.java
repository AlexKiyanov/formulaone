package ru.kiianov.foxminded.formulaone.provider;

import ru.kiianov.foxminded.formulaone.domain.LapData;
import ru.kiianov.foxminded.formulaone.domain.Racer;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;

public class ResultChartViewProvider implements ViewProvider {
    private static final String STRING_FORMAT = "%2d. %-20s| %-30s| %-10s";
    private static final String LINE_SEPARATOR = "-".repeat(66);

    @Override
    public String provideView(Map<String, LapData> raceLog, Map<String, Racer> racers) {
        final AtomicInteger racerPosition = new AtomicInteger(1);

        return raceLog.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collector.of(
                        StringBuilder::new,
                        (builder, entry) -> builder
                                .append(String.format(STRING_FORMAT,
                                        racerPosition.getAndIncrement(),
                                        getRacerName(racers, entry.getKey()),
                                        getRacerTeam(racers, entry.getKey()),
                                        entry.getValue().getLapTime()))
                                .append(System.lineSeparator())
                                .append(appendChartLineSeparator(racerPosition.intValue())),
                        StringBuilder::append,
                        StringBuilder::toString
                ));
    }

    private String getRacerName(Map<String, Racer> racers, String key) {
        return racers.get(key).getName();
    }

    private String getRacerTeam(Map<String, Racer> racers, String key) {
        return racers.get(key).getTeam();
    }

    private String appendChartLineSeparator(int racerPosition) {
        final int QUALIFY_MAX_RACERS = 15;
        if (racerPosition == QUALIFY_MAX_RACERS + 1) {
            return LINE_SEPARATOR + System.lineSeparator();
        } else {
            return "";
        }
    }
}
