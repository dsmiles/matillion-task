package com.github.dsmiles.bestimage.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestUtils {

    public static String readResourceFile(String fileName) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get("src/test/resources/" + fileName))) {
            return stream.collect(Collectors.joining(System.lineSeparator()));
        }
    }
}
