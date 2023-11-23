package com.app.bingoonline.service.impl;

import com.app.bingoonline.dto.CardDTO;
import com.app.bingoonline.model.TicketModel;
import com.app.bingoonline.model.response.TicketResponse;
import com.app.bingoonline.service.TicketService;

import java.util.*;

public class TicketServiceImpl implements TicketService {

    private final TicketResponse ticketResponse;
    private final CardDTO cardDTO;
    private final TicketModel ticketModel;

    public TicketServiceImpl(TicketResponse ticketResponse, CardDTO cardDTO, TicketModel ticketModel) {
        this.ticketResponse = ticketResponse;
        this.cardDTO = cardDTO;
        this.ticketModel = ticketModel;
    }

    @Override
    public Map<String, int[]> generateCard() {
        Map<String, int[]> ticket = new HashMap<>();
        ticket.put("b", generateRandomNumbers("b"));
        ticket.put("i", generateRandomNumbers("i"));
        ticket.put("n", generateRandomNumbers("n"));
        ticket.put("g", generateRandomNumbers("g"));
        ticket.put("o", generateRandomNumbers("o"));
        return ticket;
    }

    private int[] generateRandomNumbers(String letter){
        int[] numbersForLetter = new int[5];
        String[] lettersTicket = {"b", "i", "n", "g", "o"};
        Map<String, int[]> ticketGenerated = new HashMap<>();

        for (int i = 0; i < lettersTicket.length; i++) {
            if ("n".equalsIgnoreCase(lettersTicket[i])){
                numbersForLetter = new int [4];
            }

            for (int j = 0; j < numbersForLetter.length; j++) {
                Random randomNumber = new Random();
                if ("b".equalsIgnoreCase(letter)){
                    int number = randomNumber.nextInt(1, 15);
                    numbersForLetter[i] = number;
                    ticketGenerated.put(lettersTicket[j], numbersForLetter);
                } else if ("i".equalsIgnoreCase(letter)) {
                    int number = randomNumber.nextInt(16, 30);
                    numbersForLetter[i] = number;
                    ticketGenerated.put(lettersTicket[j], numbersForLetter);
                } else if ("n".equalsIgnoreCase(letter)){
                    ticketGenerated.put(lettersTicket[j], numbersForLetter);
                    int number = randomNumber.nextInt(31, 45);
                    numbersForLetter[i] = number;
                    ticketGenerated.put(lettersTicket[j], numbersForLetter);
                } else if ("g".equalsIgnoreCase(letter)) {
                    numbersForLetter[i] = randomNumber.nextInt(46, 60);
                    ticketGenerated.put(lettersTicket[j], numbersForLetter);
                } else {
                    numbersForLetter[i] = randomNumber.nextInt(61, 75);
                    ticketGenerated.put(lettersTicket[j], numbersForLetter);
                }
            }
        }

        return numbersForLetter;
    }

}
