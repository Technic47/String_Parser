package ru.kuznetsov.demo;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parseTest() {
        String data = "aaaaabcccc";

        List<String> demo = Arrays.asList("a: 5", "c: 4", "b: 1");
        List<String> results = Parser.parse(data, true);

        assertEquals(demo, results);
    }

    @Test
    void reverseParseTest() {
        String data = "aaaaabcccc";

        List<String> demo = Arrays.asList("b: 1", "c: 4", "a: 5");
        List<String> results = Parser.parse(data, false);

        assertEquals(demo, results);
    }
}