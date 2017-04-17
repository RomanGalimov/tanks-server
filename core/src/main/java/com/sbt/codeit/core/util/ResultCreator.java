package com.sbt.codeit.core.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultCreator {

    public static void createResult(String logFileName) {
        try {
            Map<String, Integer> result = Files.lines(Paths.get(logFileName)).collect(
                    Collectors.groupingBy(line -> line.substring(0, line.indexOf("=")),
                    Collectors.summingInt(line -> Integer.valueOf(line.substring(line.indexOf("=") + 1)))));
            Files.write(Paths.get("result.txt"), result.entrySet().stream()
                    .<CharSequence>map(entry -> entry.getKey() + "=" + entry.getValue())
                    .collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
