package com.app.bingoonline.mapper;

import com.app.bingoonline.entity.ContestEntity;
import com.app.bingoonline.model.ContestModel;

public class ContestMapper {
    public ContestModel entityToModel(ContestEntity contestEntity){
        return ContestModel.builder()
                .raffleNumbers(contestEntity.getRaffleNumbers())
                .number(contestEntity.getNumber())
                .contestNumber(contestEntity.getContestNumber())
                .build();
    }

    public ContestEntity modelToEntity(ContestModel contestModel){
        return ContestEntity.builder()
                .raffleNumbers(contestModel.getRaffleNumbers())
                .number(contestModel.getNumber())
                .contestNumber(contestModel.getContestNumber())
                .build();
    }
}
