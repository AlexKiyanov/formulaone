package ru.kiianov.foxminded.formulaone.provider;

import ru.kiianov.foxminded.formulaone.domain.Racer;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;

public class RaceResultChartViewProvider implements RaceViewProvider {
    private static final String STRING_FORMAT = "%2d. %-20s| %-30s| %-10s";
    private static final String LINE_SEPARATOR = "-".repeat(66);
    private static final String LAP_TIME_FORMAT = "%d:%02d:%02d";
    private static final int SECONDS_PER_HOUR = 3600;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int QUALIFY_MAX_RACERS = 15;

    @Override
    public String provideView(List<Racer> racers) {
        final AtomicInteger racerPosition = new AtomicInteger(1);

        return racers.stream()
                .sorted()
                .collect(Collector.of(
                        StringBuilder::new,
                        (builder, racer) -> builder
                                .append(String.format(STRING_FORMAT,
                                        racerPosition.getAndIncrement(),
                                        racer.getRacerName(),
                                        racer.getTeam(),
                                        formatLapTime(racer.getLapTime())))
                                .append(System.lineSeparator())
                                .append(appendChartLineSeparator(racerPosition.intValue())),
                        StringBuilder::append,
                        StringBuilder::toString
                ));
    }

    private String formatLapTime(Duration lapTime) {
        return String.format(LAP_TIME_FORMAT,
                (lapTime.getSeconds() % SECONDS_PER_HOUR) / MINUTES_PER_HOUR,
                (lapTime.getSeconds() % MINUTES_PER_HOUR),
                (lapTime.getNano() / 1_000_000));
    }

    private String appendChartLineSeparator(int racerPosition) {
        return (racerPosition == QUALIFY_MAX_RACERS + 1) ? (LINE_SEPARATOR + System.lineSeparator()) : "";
    }
}
