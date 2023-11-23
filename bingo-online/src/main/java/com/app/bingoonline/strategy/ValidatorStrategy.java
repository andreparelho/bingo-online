package com.app.bingoonline.strategy;

import com.app.bingoonline.validate.LetterValidator;

import java.util.List;

public interface ValidatorStrategy {
    List<LetterValidator> getValidators();
}
