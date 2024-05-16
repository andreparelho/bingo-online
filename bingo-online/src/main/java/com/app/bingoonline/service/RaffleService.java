package com.app.bingoonline.service;

import com.app.bingoonline.entity.RaffleEntity;
import com.app.bingoonline.model.response.RaffleResponse;

public interface RaffleService {
    RaffleResponse getRaffleNumber(int contestNumber);
    RaffleEntity getRaffle(int contestNumber);
}
