package com.mastertheboss;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.enterprise.inject.Model;

@Model
public class Bean {
    private String word;
 
    private String anagram;
 
    public void generateAnagram() {
        Random rand = new Random();
 
        List<Character> characters = new ArrayList<Character>();
        for (char c : word.toCharArray()) {
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(word.length());
        while (characters.size() != 0) {
            int randPicker = (int) (Math.random() * characters.size());
            output.append(characters.remove(randPicker));
        }
        this.anagram = output.toString();
    }
 
    public String getAnagram() {
        return anagram;
    }
 
    public void setAnagram(String anagram) {
        this.anagram = anagram;
    }
 
    public String getWord() {
        return word;
    }
 
    public void setWord(String word) {
        this.word = word;
    }
}
