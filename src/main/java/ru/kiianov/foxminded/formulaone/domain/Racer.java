package ru.kiianov.foxminded.formulaone.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Racer implements Comparable<Racer> {
    private final String abbreviation;
    private final String racerName;
    private final String team;
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final Duration lapTime;

    public Racer(String abbreviation, String racerName, String team, LocalDateTime start, LocalDateTime end) {
        this.abbreviation = abbreviation;
        this.racerName = racerName;
        this.team = team;
        this.start = start;
        this.end = end;
        this.lapTime = Duration.between(start, end);
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getRacerName() {
        return racerName;
    }

    public String getTeam() {
        return team;
    }

    public Duration getLapTime() {
        return lapTime;
    }

    @Override
    public int compareTo(Racer o) {
        return this.lapTime.compareTo(o.lapTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Racer racer = (Racer) o;
        return Objects.equals(abbreviation, racer.abbreviation) &&
                Objects.equals(racerName, racer.racerName) &&
                Objects.equals(team, racer.team) &&
                Objects.equals(start, racer.start) &&
                Objects.equals(end, racer.end) &&
                Objects.equals(lapTime, racer.lapTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(abbreviation, racerName, team, start, end, lapTime);
    }

    @Override
    public String toString() {
        return "Racer{" +
                "abbreviation='" + abbreviation + '\'' +
                ", racerName='" + racerName + '\'' +
                ", team='" + team + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", lapTime=" + lapTime +
                '}';
    }
}
