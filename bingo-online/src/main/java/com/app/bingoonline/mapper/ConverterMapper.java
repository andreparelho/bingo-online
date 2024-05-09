package com.app.bingoonline.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class Converter {
    public String mapToJson(Map<String, Set<Integer>> map) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(map);
    }

    public List<Integer> convertStringToList(String data){
        List<Integer> numbers = new ArrayList<>();

        StringTokenizer tokenizer = new StringTokenizer(data, ",");
        int tokenCount = tokenizer.countTokens();

        String[] stringArray = new String[tokenCount];

        for (int i = 0; i < tokenCount; i++) {
            stringArray[i] = tokenizer.nextToken();
            numbers.add(Integer.valueOf(stringArray[i]));
        }

        return numbers;
    }

    public String convertListToString(List<String> integerList){
        return integerList.stream().map(String::valueOf).collect(Collectors.joining(","));
    }
}
