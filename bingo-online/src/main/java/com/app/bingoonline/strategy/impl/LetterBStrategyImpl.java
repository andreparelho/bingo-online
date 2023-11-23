package com.app.bingoonline.strategy.impl;


import com.app.bingoonline.strategy.ValidatorStrategy;
import com.app.bingoonline.validate.LetterValidator;
import com.app.bingoonline.validate.impl.LetterBValidateImpl;

import java.util.List;

public class LetterBStrategyImpl implements ValidatorStrategy {
    private final LetterBValidateImpl letterBValidate;

    public LetterBStrategyImpl(LetterBValidateImpl letterBValidate) {
        this.letterBValidate = letterBValidate;
    }

    @Override
    public List<LetterValidator> getValidators() {
        return List.of(this.letterBValidate);
    }
}
