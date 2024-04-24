package com.app.bingoonline.mapper;

import com.app.bingoonline.entity.ContestEntity;
import com.app.bingoonline.model.dto.ContestDTO;
import org.springframework.stereotype.Component;

@Component
public class ContestMapper {
    public ContestDTO entityToDTO(ContestEntity contestEntity){
        return ContestDTO.builder()
                .id(contestEntity.getId())
                .raffleNumbers(contestEntity.getRaffleNumbers())
                .number(contestEntity.getNumber())
                .contestNumber(contestEntity.getContestNumber())
                .build();
    }

    public ContestEntity DTOToEntity(ContestDTO contestDTO){
        return ContestEntity.builder()
                .raffleNumbers(contestDTO.getRaffleNumbers())
                .number(contestDTO.getNumber())
                .contestNumber(contestDTO.getContestNumber())
                .build();
    }
}
