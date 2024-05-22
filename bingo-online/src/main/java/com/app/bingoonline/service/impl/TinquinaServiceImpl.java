package com.app.bingoonline.service.impl;

import com.app.bingoonline.service.TinquinaService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TinquinaServiceImpl implements TinquinaService {
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
}
