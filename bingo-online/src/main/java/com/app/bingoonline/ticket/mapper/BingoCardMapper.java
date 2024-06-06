package com.app.bingoonline.ticket.mapper;

import com.app.bingoonline.ticket.model.TicketModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BingoCardMapper {
    private final ObjectMapper objectMapper;

    public BingoCardMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public TicketModel convertStringToBingoCard(String json) throws JsonProcessingException {
        Map<String, List<Integer>> cardMap = objectMapper.readValue(json, new TypeReference<Map<String, List<Integer>>>() {});

        return TicketModel.builder()
                .b(Map.of("b", cardMap.get("b")))
                .i(Map.of("i", cardMap.get("i")))
                .n(Map.of("n", cardMap.get("n")))
                .g(Map.of("g", cardMap.get("g")))
                .o(Map.of("o", cardMap.get("o")))
                .build();
    }

    public String convertBingoCardToString(TicketModel ticketModel) throws JsonProcessingException {
        Map<String, List<Integer>> combinedMap = new HashMap<>();

        combinedMap.putAll(ticketModel.getB());
        combinedMap.putAll(ticketModel.getI());
        combinedMap.putAll(ticketModel.getN());
        combinedMap.putAll(ticketModel.getG());
        combinedMap.putAll(ticketModel.getO());

        return objectMapper.writeValueAsString(combinedMap);
    }
}
