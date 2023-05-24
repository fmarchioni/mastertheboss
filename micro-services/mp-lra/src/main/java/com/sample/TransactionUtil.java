package com.sample;

import jakarta.enterprise.context.ApplicationScoped;
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
public class TransactionUtil {
    public static boolean checkExpression(String input) {
        String regex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        
        return matcher.matches();
    }
    public String getId(String id) {
        return id.split("/")[id.split("/").length - 1];
    }
}
