package com.app.bingoonline.service.impl;

import com.app.bingoonline.service.GameService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class GameServiceImpl implements GameService {
    @Override
    public boolean checkVertical(Map<String, List<Integer>> card) {
        for (List<Integer> values : card.values()) {
            for (Integer value : values) {
                if (value != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean checkHorizontal(Map<String, List<Integer>> card) {
        return false;
    }

    @Override
    public boolean checkDiagonal(Map<String, List<Integer>> card) {
        return false;
    }

    @Override
    public boolean checkTicket(Map<String, List<Integer>> card) {
        return false;
    }
}
