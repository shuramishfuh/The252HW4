package ImplementationFormatterEngine;

import InterfacesformatterEngine.IinputParser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputParser implements IinputParser {

    @Override
    public List<String> convertStringToInstruction(String str) {
        return Arrays.stream(str.split(" ")).collect(Collectors.toList());
    }
}
