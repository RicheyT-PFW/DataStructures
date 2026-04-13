package mod7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/*
A Trie (pronounced "try") is a tree-like data structure often used to efficiently store and retrieve 
strings, especially when dealing with a set of words or prefixes. Each node in the trie has
up to 26 child nodes, each corresponding to a letter in the alphabet. When a word is stored 
in the trie, each character is a separate node pointing to the next character node in the word as a child node. 

The last character in the word has a count for the frequency of that word in the trie:
          ( )
        /  |  \
     t /  A|   \ i
      /    |    \
    (t)   (A)   (i)
    / \    15    11
 o /   \ e        \ n
  /     \          \
(to)    (te)       (in)
 7      / | \        5
     a / d|  \ n     | n
      /   |   \      |
   (tea)(ted)(ten) (inn)
     3    4    12    9


*/





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
            if(!Character.isAlphabetic(c)) {
                continue;
            }
            int index = c - 'a'; // Calculates to 'a' = 0, 'b' = 1, ..., 'z' = 25
            //TODO Check if the child node exists. If not, create the node for that character
            if(current.children[index] == null) {
                current.children[index] = new TrieNode<Character>(c);
            }

            //TODO Move to the child node
            current = current.children[index];            
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
            if(!Character.isAlphabetic(c)) {
                continue;
            }
            //TODO Look for the child node. If not found, return null. If found, move to that node
            if(current.children[index] == null) {
                return null;
            } else {
                current = current.children[index];
            }                                            
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
               findSuggestions(node.children[i], (prefix + c), suggestions); 
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
    Trie trie = new Trie();
    // Read CSV file
    File file = new File("/workspaces/DataStructures/assets/lab7/word_frequencies.csv"); // make sure it's in your project root
    Scanner scanner = new Scanner(file);

      // Skip header
    if (scanner.hasNextLine()) {
        scanner.nextLine();
    }

    while (scanner.hasNextLine()) {
        String line = scanner.nextLine().trim();
        if (line.isEmpty()) continue;

        String[] parts = line.split(",");
        if (parts.length < 2) continue;

        String word = parts[0].toLowerCase();
        int frequency = Integer.parseInt(parts[1]);

        trie.add(word, frequency);
    }

    scanner.close();

    // Search words
    System.out.println("Trie 'college' occurrences: " + trie.search("college"));
    System.out.println("Trie 'computerized' occurrences: " + trie.search("computerized"));
    System.out.println("Trie 'science' occurrences: " + trie.search("science"));

    // Add "data" twice
    System.out.println("Adding 'data' to trie twice");
    trie.add("data");
    trie.add("data");

    System.out.println("Trie 'data' occurrences: " + trie.search("data"));

    // Print totals
    System.out.println("Total unique words in trie: " + uniqueWords);
    System.out.println("Total count of all words in trie: " + totalCount);

    // Suggestions
    System.out.println("\nSuggestions for 'compu':");
    System.out.println(trie.getSuggestions("compu"));

    System.out.println("\nSuggestions for 'scr':");
    System.out.println(trie.getSuggestions("scr"));
}


    /*

    Sample Output of main method:
    
    Trie 'college' occurrences: 224634
    Trie 'computerized' occurrences: 3239
    Trie 'science' occurrences: 170488
    Adding 'data' to trie twice
    Trie 'data' occurrences: 2
    Total unique words in trie: 6045
    Total count of all words in trie: 91050857
    
    Suggestions for 'compu':
    Heap:
    computational 3336
    \___computerenhanced 54
    \___computerized 3239
        \___computersavvy 69
        \___compulsiveness 44
    
    
    Suggestions for 'scr':
    Heap:
    screen 11581
    \___screw 5762
        \___screwworm 48
        \___scrappy 898
            \___scrutinized 43
    \___scrap 3388
        \___scrape 2224
            \___scrunch 923
            \___scrubber 566
        \___screaming 1513
            \___scrubbed 92
            \___scripting 130

    */

}

