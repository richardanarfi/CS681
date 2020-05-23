package edu.umb.cs.cs681.hw20;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextAnalysis {
	private String documentName;
	private String startletter;
	private int threshold;
	
	public TextAnalysis(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentName() {
		return documentName;
	}
	
	public String getStartletter() {
		return startletter;
	}
	
	public int getThreshold() {
		return threshold;
	}
	
	public String getPath() {
		return "src/edu/umb/cs/cs681/hw20/" + documentName;
	}

	public void setStartletter(String s) {
		startletter = s;
	}

	public void setThreshold(int N) {
		threshold = N;
	}
	
    // This method filters out all words of length greater than N		
    public void filterGreaterThanN(String content, int N) {
        ArrayList<String> words = Arrays.stream(content.split("\\s+")).collect(Collectors.toCollection(ArrayList::new));

        System.out.println("Here is a list of all words of length greater than: " + N);
        List<String> filteredWords = words.stream()
		                                  .parallel()
										  .filter(s -> s.length() > N)
										  .collect(Collectors.toList());
        System.out.println(filteredWords);
		System.out.println();
    }

    // This method filters out all words that start with a specified letter
    public void filterStartsWith(String content, String startletter) {
        ArrayList<String> words = Arrays.stream(content.split("\\s+")).collect(Collectors.toCollection(ArrayList::new));

        System.out.println("Here is a list of all words that start with: " + startletter);
        List<String> filteredWords = words.stream()
		                                  .parallel()
										  .filter(s -> s.startsWith(startletter))
										  .collect(Collectors.toList());
        System.out.println(filteredWords);
		System.out.println();
    }
	
	// This method finds all palindromes in the given document
    public void findPalindromes(String content) {
        ArrayList<String> words = Arrays.stream(content.split("\\s+")).collect(Collectors.toCollection(ArrayList::new));

        System.out.println("Here is a list of all palindromes in the document");
        List<String> palindromes = words.stream()
		                                .parallel()
                                        .filter(s -> s.equals(new StringBuilder(s).reverse().toString()))
                                        .collect(Collectors.toList());
        System.out.println(palindromes);
		System.out.println();
    }
    
	// This method groups all words in the document by length
    public void groupByWordLength(String content) {
        ArrayList<String> words = Arrays.stream(content.split("\\s+"))
                                   .collect(Collectors.toCollection(ArrayList::new));

        System.out.println("Here is a grouping of all words in the document by length");
        Map<Integer, List<String>> wordsByLength = words.stream().parallel().collect(Collectors.groupingBy(String::length));
        for (Map.Entry<Integer, List<String>> entry : wordsByLength.entrySet()) {
            System.out.println("Words of length: " + entry.getKey());
			System.out.println(entry.getValue());
			System.out.println();
        }
		
    }
    
	
	// Test Client
    public static void main(String[] args) {
		TextAnalysis document = new TextAnalysis("document.txt");
        String content;
		try{
		// Read file and save contents as a string
		Path path = Paths.get(document.getPath());
        byte[] bytes = Files.readAllBytes(path);
        content = new String(bytes);
		} catch(Exception e){
		System.out.println("Could not read file");
        content = "";		
		}
		
		if(content != "") {
    			
            // Use parallel stream to determine the number of words in the document
            ArrayList<String> words = Arrays.stream(content.split("\\s+")).collect(Collectors.toCollection(ArrayList::new));
    
            System.out.println("Parallel stream in session...");
    		Integer wordcount = words.stream()
    				.parallel()
                    .reduce(0,
                    (result, word)-> {return ++result;},
                    (finalResult,intermediateResult)->{
                    return finalResult + intermediateResult; }
    		    	);
            System.out.println("There are " + wordcount + " words in this document");
    		System.out.println("\nYou may be interested in the document statistics below:");
    		System.out.println();

            // set parameters
			document.setStartletter("s");
			document.setThreshold(7);
			
            // Run default statistics
            document.filterGreaterThanN(content, document.getThreshold());
    		document.filterStartsWith(content, document.getStartletter());
            document.findPalindromes(content);
            document.groupByWordLength(content);
		}	
    }
}