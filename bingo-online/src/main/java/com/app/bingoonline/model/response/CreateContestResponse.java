package com.app.bingoonline.model.response;

import java.util.Map;
import java.util.Set;

public record CreateContestResponse (Map<String, Set<Integer>> contest) {
}
