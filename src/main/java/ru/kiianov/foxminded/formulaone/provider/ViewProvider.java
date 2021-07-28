package ru.kiianov.foxminded.formulaone.provider;

import ru.kiianov.foxminded.formulaone.domain.LapData;
import ru.kiianov.foxminded.formulaone.domain.Racer;

import java.util.Map;

public interface ViewProvider {
    String provideView(Map<String, LapData> raceLog, Map<String, Racer> racers);
}
