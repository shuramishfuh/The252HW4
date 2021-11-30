package ImplementationFormatterEngine;

import InterfacesformatterEngine.IinputParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class handles user's input from telegram
 */

public class InputParser implements IinputParser {

    
    /**
     * Splits user's input on whitespace or " or - for when Instructors first/last
     * name is made up of two words
     * 
     * @param str String received from user
     * @return A List of Strings
     */
    @Override
    public List<String> convertStringToInstruction(String str) {
        List<String> list = new ArrayList<String>();
        Matcher m = Pattern.compile("([^\"]\\S*|\".+?\"|[^-])\\s*").matcher(str);
        while (m.find())
            list.add(m.group(1).replaceAll("\"|-", " "));
        return list;
    }
}
