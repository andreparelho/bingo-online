package com.app.bingoonline.mapper;

import com.app.bingoonline.entity.ContestEntity;
import com.app.bingoonline.model.dto.ContestDTO;
import org.springframework.stereotype.Component;

@Component
public class ContestMapper {
    public ContestDTO entityToDTO(ContestEntity contestEntity){
        return new ContestDTO(
                contestEntity.getId(),
                contestEntity.getNumber(),
                contestEntity.getRaffleNumbers(),
                contestEntity.getContestNumber()
        );
    }

    public ContestEntity DTOToEntity(ContestDTO contestDTO){
        return ContestEntity.builder()
                .raffleNumbers(contestDTO.raffleNumbers())
                .number(contestDTO.number())
                .contestNumber(contestDTO.contestNumber())
                .build();
    }
}
