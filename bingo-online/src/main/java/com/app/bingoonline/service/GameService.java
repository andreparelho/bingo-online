package com.app.bingoonline.service;

import java.util.List;
import java.util.Map;

public interface GameService {
    boolean checkVertical(Map<String, List<Integer>> card);
    boolean checkHorizontal(Map<String, List<Integer>> card);
    boolean checkDiagonal(Map<String, List<Integer>> card);
    boolean checkTicket(Map<String, List<Integer>> card);
}
