package ru.kuznetsov.demo;

import java.util.*;

/**
 * String parser.
 */
public class Parser {
    /**
     * Main method for parsing string to chars and their occurrence.
     *
     * @param data     string to parse.
     * @param reversed order of results.
     * @return List with results.
     */
    public static List<String> parse(String data, boolean reversed) {
        List<String> resultList = toList(toMap(data));

        if (reversed) {
            Collections.reverse(resultList);
        }
        return resultList;
    }

    /**
     * Parse string to Map.
     * Keys are chars of string.
     * Values are number of keys in string.
     *
     * @param data string to parse.
     * @return formed Map.
     */
    private static Map<Character, Integer> toMap(String data) {
        char[] chars = data.toCharArray();
        Map<Character, Integer> map = new HashMap<>();

        for (char symbol : chars) {
            if (!map.containsKey(symbol)) {
                map.put(symbol, 1);
            } else {
                int oldValue = map.get(symbol);
                map.replace(symbol, oldValue + 1);
            }
        }
        return map;
    }

    /**
     * Form List of strings.
     * Each item is string representation of map`s Key : Value.
     * Results are sorted.
     *
     * @param map map to parse.
     * @return formed List.
     */
    private static List<String> toList(Map<Character, Integer> map) {
        List<String> resultList = new ArrayList<>();

        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(item -> {
                    String values = item.getKey() + ": " + item.getValue();
                    resultList.add(values);
                });

        return resultList;
    }
}
