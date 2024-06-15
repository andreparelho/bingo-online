package com.app.bingoonline.infrastructure.util.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.nimbusds.jose.shaded.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class Mapper {
    public String mapToJson(Map<String, List<Integer>> map) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(map);
    }

    public List<Integer> convertStringToList(String data) {
        List<Integer> numbers = new ArrayList<>();

        String[] stringArray = data.split(",\\s*");
        Arrays.stream(stringArray).forEach(s -> numbers.add(Integer.valueOf(s)));

        return numbers;
    }

    public Map<String, Integer> convertStringToMap(String data) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, List<Integer>>>(){}.getType();

        Map<String, List<Integer>> map = gson.fromJson(data, type);

        Map<String, Integer> newMap = new HashMap<>();
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<Integer> values = entry.getValue();
            for (int i = 0; i < values.size(); i++) {
                String newKey = key.toUpperCase() + (i + 1);
                newMap.put(newKey, values.get(i));
            }
        }

        return newMap;
    }

    public Map<String, List<Integer>> jsonToMap(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, List<Integer>> tempMap = objectMapper.readValue(jsonString, new TypeReference<Map<String, List<Integer>>>() {});
        Map<String, List<Integer>> numberMap = new HashMap<>();
        for (Map.Entry<String, List<Integer>> entry : tempMap.entrySet()) {
            numberMap.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        return numberMap;
    }

    public String convertListToString(List<String> integerList){
        return integerList.stream().map(String::valueOf).collect(Collectors.joining(","));
    }
}
