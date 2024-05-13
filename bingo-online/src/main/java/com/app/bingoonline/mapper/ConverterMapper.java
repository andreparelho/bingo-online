package com.app.bingoonline.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ConverterMapper {
    public String mapToJson(Map<String, Set<Integer>> map) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(map);
    }

    public List<Integer> convertStringToList(String data) {
        List<Integer> numbers = new ArrayList<>();

        String[] stringArray = data.split(",\\s*");

        for (String str : stringArray) {
            numbers.add(Integer.parseInt(str.trim()));
        }

        return numbers;
    }

    public String convertListToString(List<String> integerList){
        return integerList.stream().map(String::valueOf).collect(Collectors.joining(","));
    }
}
