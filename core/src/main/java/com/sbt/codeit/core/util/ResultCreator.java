package com.sbt.codeit.core.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class ResultCreator {

    public static void main(String[] args) throws IOException {
        Files.write(Paths.get("result.txt"),
                Files.lines(Paths.get("winners.txt")).collect(
                        Collectors.groupingBy(line -> line.substring(0, line.indexOf("=")),
                                Collectors.summingInt(line -> Integer.valueOf(line.substring(line.indexOf("=") + 1)))))
                        .entrySet().stream()
                        .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                        .map(entry -> entry.getKey() + "=" + entry.getValue())
                        .collect(Collectors.toList()));
    }

}
