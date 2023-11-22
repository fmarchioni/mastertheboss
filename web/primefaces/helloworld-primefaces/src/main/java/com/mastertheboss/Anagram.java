package com.mastertheboss;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
 

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named("bean")
@ViewScoped
public class Anagram implements Serializable  {
    private String word;
 
    private String anagram;
 

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

 
    
    public void generateAnagram() {
         
 
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
        addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Anagram generated!");
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
