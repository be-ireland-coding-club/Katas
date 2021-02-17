package com.bearingpoint.codingclub.kata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author John McDonnell
 */
public class RomanNumerals {

    private static final SortedMap<Integer, Character> TRANSLATIONS = new TreeMap<>(Collections.reverseOrder());

    static {
        TRANSLATIONS.putAll(Map.of(
                1, 'I',
                5, 'V',
                10, 'X',
                50, 'L',
                100, 'C',
                500, 'D',
                1000, 'M'));
    }

    public String convert(Integer num) {
        StringBuilder romanNumeralValue = new StringBuilder();

        List<Integer> keys = new ArrayList<>(TRANSLATIONS.keySet());

        for (int idx = 0; idx < keys.size(); idx++) {
            while (num >= keys.get(idx)) {
                if (num >= keys.get(idx)) {
                    romanNumeralValue.append(TRANSLATIONS.get(keys.get(idx)));
                    num = num - keys.get(idx);
                }
            }

            if (hasFullyConverted(num)) {
                num = handleConvertNinesIfPossible(idx, keys, num, romanNumeralValue);

                num = handleConvertingPreviousValueIfPossible(idx, keys, num, romanNumeralValue);
            }
        }

        return romanNumeralValue.toString();
    }

    private Integer handleConvertingPreviousValueIfPossible(int idx, List<Integer> keys, Integer num, StringBuilder romanNumeralValue) {
        if (idx != keys.size() - 1 && (idx % 2 == 1)) {
            if (num == (keys.get(idx) - keys.get(idx + 1))) {
                romanNumeralValue.append(TRANSLATIONS.get(keys.get(idx + 1)));
                romanNumeralValue.append(TRANSLATIONS.get(keys.get(idx)));
                num = num - (keys.get(idx) - keys.get(idx + 1));
            }
        }
        return num;
    }

    private Integer handleConvertNinesIfPossible(int idx, List<Integer> keys, Integer num, StringBuilder romanNumeralValue) {
        if (idx != keys.size() - 2 && ((keys.get(idx) - keys.get(idx + 2)) % 9 == 0)) {
            if (num >= (keys.get(idx) - keys.get(idx + 2))) {
                romanNumeralValue.append(TRANSLATIONS.get(keys.get(idx + 2)));
                romanNumeralValue.append(TRANSLATIONS.get(keys.get(idx)));
                num = num - (keys.get(idx) - keys.get(idx + 2));
            }
        }
        return num;
    }

    private boolean hasFullyConverted(Integer num) {
        return num != 0;
    }
}
