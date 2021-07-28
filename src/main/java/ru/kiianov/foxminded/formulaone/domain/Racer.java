package ru.kiianov.foxminded.formulaone.domain;

import java.util.Objects;

public class Racer {
    private final String abbreviation;
    private final String name;
    private final String team;

    public Racer(String abbreviation, String name, String team) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
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
                Objects.equals(name, racer.name) &&
                Objects.equals(team, racer.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(abbreviation, name, team);
    }

    @Override
    public String toString() {
        return "Racer{" +
                "abbreviation='" + abbreviation + '\'' +
                ", name='" + name + '\'' +
                ", team='" + team + '\'' +
                '}';
    }
}
