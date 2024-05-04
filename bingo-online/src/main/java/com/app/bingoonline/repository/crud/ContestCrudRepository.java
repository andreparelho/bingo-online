package com.app.bingoonline.repository.crud;

import com.app.bingoonline.entity.ContestEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;


public interface ContestCrudRepository extends CrudRepository<ContestEntity, UUID> {
    ContestEntity findByNumber(int number);
}
