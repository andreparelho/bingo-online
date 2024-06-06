package com.app.bingoonline.contest.dto.response;

import java.util.Map;
import java.util.Set;

public record CreateContestResponse (Map<String, Set<Integer>> contest) {
}
