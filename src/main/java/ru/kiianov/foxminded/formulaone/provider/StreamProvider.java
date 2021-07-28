package ru.kiianov.foxminded.formulaone.provider;

import java.util.stream.Stream;

public interface StreamProvider {
    Stream<String> provideStream(String fileName);
}
