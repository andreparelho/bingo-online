package com.app.bingoonline.model.ticketsLetters;

import com.app.bingoonline.model.Letter;
import org.springframework.stereotype.Component;

@Component
public class B extends Letter {
    @Override
    public int getQuantity() {
        return 5;
    }

    @Override
    public int getStart() {
        return 1;
    }

    @Override
    public int getEnd() {
        return 15;
    }
}
