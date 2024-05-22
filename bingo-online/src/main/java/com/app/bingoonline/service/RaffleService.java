package com.app.bingoonline.service;

import com.app.bingoonline.entity.RaffleEntity;
import com.app.bingoonline.controller.response.RaffleResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface RaffleService {
    RaffleResponse getRaffleNumber(int contestNumber) throws JsonProcessingException;
    RaffleEntity getRaffle(int contestNumber);
}
