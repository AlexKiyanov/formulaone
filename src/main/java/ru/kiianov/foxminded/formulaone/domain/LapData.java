package ru.kiianov.foxminded.formulaone.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class LapData implements Comparable<LapData> {
    private final Duration lapTime;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public LapData(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.lapTime = Duration.between(startTime, endTime);
    }

    public String getLapTime() {
        return String.format("%d:%02d:%02d",
                (lapTime.getSeconds() % 3600) / 60,
                (lapTime.getSeconds() % 60),
                (lapTime.getNano() / 1_000_000));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LapData lapData = (LapData) o;
        return Objects.equals(lapTime, lapData.lapTime) &&
                Objects.equals(startTime, lapData.startTime) &&
                Objects.equals(endTime, lapData.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lapTime, startTime, endTime);
    }

    @Override
    public String toString() {
        return "LapData{" +
                "lapTime=" + lapTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    @Override
    public int compareTo(LapData o) {
        return this.lapTime.compareTo(o.lapTime);
    }
}
