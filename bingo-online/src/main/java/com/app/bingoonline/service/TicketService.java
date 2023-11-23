package com.app.bingoonline.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface TicketService {
    Map<String, int[]> generateTicket();
}
