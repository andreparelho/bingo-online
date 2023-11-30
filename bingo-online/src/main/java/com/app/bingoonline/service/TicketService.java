package com.app.bingoonline.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

public interface TicketService {
    Map<String, Set<Integer>> generateTicket();
}
