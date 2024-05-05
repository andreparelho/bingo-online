package com.app.bingoonline.model.dto;


import java.util.UUID;

public record ContestDTO (UUID id, int number, String raffleNumbers, int contestNumber) {
}