package com.app.bingoonline.controller.response;

import java.util.Map;
import java.util.Set;

public record CreateContestResponse (Map<String, Set<Integer>> contest) {
}
