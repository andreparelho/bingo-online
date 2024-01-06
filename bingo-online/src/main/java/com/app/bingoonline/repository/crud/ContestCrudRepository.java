package com.app.bingoonline.repository.crud;

import com.app.bingoonline.entity.ContestEntity;
import org.springframework.data.repository.CrudRepository;


public interface ContestCrudRepository extends CrudRepository<ContestEntity, Long> {
    ContestEntity findByNumber(int number);
}
