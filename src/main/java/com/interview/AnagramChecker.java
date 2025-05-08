package com.interview;

import java.util.HashMap;
import java.util.Map;

public class AnagramChecker {

    public static boolean isAnagram(String str1, String str2) {
        // Clean and normalize strings
        String s1 = str1.replaceAll("\\s", "").toLowerCase();
        String s2 = str2.replaceAll("\\s", "").toLowerCase();

        // Early exit if lengths don't match
        if (s1.length() != s2.length()) {
            return false;
        }

        // Count characters in first string
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : s1.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        // Subtract character counts using second string
        for (char c : s2.toCharArray()) {
            if (!charCount.containsKey(c)) {
                return false;
            }
            charCount.put(c, charCount.get(c) - 1);
            if (charCount.get(c) == 0) {
                charCount.remove(c);
            }
        }

        // If map is empty, they are anagrams
        return charCount.isEmpty();
    }

    public static void main(String[] args) {
        String word1 = "Debit Card";
        String word2 = "Bad Credit";

        if (isAnagram(word1, word2)) {
            System.out.println(word1 + " and " + word2 + " are anagrams.");
        } else {
            System.out.println(word1 + " and " + word2 + " are NOT anagrams.");
        }
    }
}
