package com.gateway.api.controller;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class exampleTest {

    @Test
    public void test() {

        try {
            Map<String, Integer> result = new HashMap<>();
            Stream<String> lines = Files.lines(Paths.get("C:/source/testing/gateway/src/test/java/com/gateway/api/controller/logs.log"));
            lines.forEach(line -> {
                if (result.computeIfPresent(line, (key, value) -> value + 1) == null) {
                    result.put(line, 1);
                }
            });
            result.forEach((key, value) -> System.out.println(key + ":" + value));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        boolean begin = false;
        boolean end = false;
        boolean concated = false;
        List<String> result = new ArrayList<>();
        String log = "";
        String resultTemplate = " successful logins from that ";
        Map<String, Integer> resultHelper = new HashMap<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:/source/testing/gateway/src/test/java/com/gateway/api/controller/dynatrace2.log"));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("INFO clientIp=")) {
                    begin = true;
                    log = log.concat(line);
                    concated = true;
                }
                if (line.contains("statusCode=200")) {
                    end = true;
                    if (!concated) {
                        log = log.concat(line);
                    }
                }
                if (begin && end) {
                    begin = false;
                    end = false;
                    String Ip = StringUtils.substringBetween(log, "clientIp=", " userAgent");
                    if (resultHelper.computeIfPresent(Ip, (key, value) -> value + 1) == null) {
                        resultHelper.put(Ip, 1);
                    }
                }
                concated = false;
            }
            resultHelper.forEach((key, value) -> result.add(String.valueOf(value) + resultTemplate + key));
            result.forEach(text -> System.out.println(text));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test3() {
        final String regex = "\\w+:\\w+:\\w+";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        AtomicReference<Long> counter = new AtomicReference<>(Long.valueOf(0));

        try {
            Stream<String> lines = Files.lines(Paths.get("C:/source/testing/gateway/src/test/java/com/gateway/api/controller/dynatrace.log"));
            lines.forEach(line -> {
                final Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    counter.getAndSet(counter.get() + 1);
                }
            });
            System.out.println("number of tokens:" + counter.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
