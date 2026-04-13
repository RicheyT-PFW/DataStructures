package M7_Trees;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Trie {
    private static class TrieNode<E> {
        E value;
        TrieNode<E>[] children;
        int frequency; // frequency > 0 indicates a complete word

        @SuppressWarnings("unchecked")
        public TrieNode(E value) {
            this.value = value;
            this.children = new TrieNode[26];
            this.frequency = 0;
        }
    }

    private final TrieNode<Character> root;
    private static long totalCount = 0;
    private static long uniqueWords = 0;

    public Trie() {
        root = new TrieNode<>('\0'); // root has no character and never changes
    }

    // Add a single word occurrence into the trie
    public void add(String word) {
        add(word, 1);
        totalCount += 1;
    }

    // Add a specified frequency to a word occurrence
    public void add(String word, int frequency) {
        TrieNode<Character> current = root;

        for (char c : word.toLowerCase().toCharArray()) {
            int index = c - 'a'; // Calculates to 'a' = 0, 'b' = 1, ..., 'z' = 25
            //TODO Check if the child node exists. If not, create the node for that character
            

            //TODO Move to the child node
            
        }
        //If the word is new, increment the unique word count
        if (current.frequency == 0) {
            uniqueWords++;
        }
        //Increase the frequency of the word and the total count
        current.frequency += frequency; //Naturally marks the end of a word
        totalCount += frequency;
    }

    // Search for a word in the trie
    // Return frequency (0: not found, > 0 found)
    public int search(String word) {
        TrieNode<Character> node = findNode(word);
        return node != null ? node.frequency : 0;
    }

    // Helper: Traverse to the end node of a word/prefix
    private TrieNode<Character> findNode(String s) {
        TrieNode<Character> current = root;

        for (char c : s.toLowerCase().toCharArray()) {
            int index = c - 'a'; // Calculates to 'a' = 0, 'b' = 1, ..., 'z' = 25
            //TODO Look for the child node. If not found, return null. If found, move to that node
            

        }
        return current; // Found the last node of the word/prefix
    }

    // Get 5 word completion suggestions using a heap
    public Heap<Word> getSuggestions(String prefix) {
        Heap<Word> suggestions = new Heap<>();
        TrieNode<Character> node = findNode(prefix);

        if (node != null) {
            findSuggestions(node, prefix, suggestions);
        }
        return suggestions;
    }
    // Helper: Recursively find suggestions
    private void findSuggestions(TrieNode<Character> node, String prefix, Heap<Word> suggestions) {
        if (node.frequency > 0) {
            // Made it to a complete word node, add it to the suggestions
            suggestions.add(new Word(prefix, node.frequency));
        }
        for (int i = 0; i < node.children.length; i++) {
            if (node.children[i] != null) {
                char c = (char) ('a' + i); // Convert index back to character
                //TODO Recursively call findSuggestions for the child node
                
            }
        }
    }

    // Helper class to store entire word and frequency for heap sorting
    private static class Word implements Comparable<Word>{
        String word;
        int frequency;

        public Word(String word, int frequency) {
            this.word = word;
            this.frequency = frequency;
        }

        @Override
        public String toString() {
            return word + " " + frequency;
        }

        @Override
        public int compareTo(Word other) {
            // Sort by frequency in descending order
            return Integer.compare(this.frequency, other.frequency);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        //TODO
    }
}

