package ru.kiianov.foxminded.formulaone.provider;

import ru.kiianov.foxminded.formulaone.domain.Racer;

import java.util.List;

public interface RaceViewProvider {
    String provideView(List<Racer> racers);
}
