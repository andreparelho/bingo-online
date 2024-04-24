package com.app.bingoonline.model.extds;

import com.app.bingoonline.model.Letter;
import org.springframework.stereotype.Component;

@Component
public class O extends Letter {
    @Override
    public int getQuantity(){
        return 5;
    }

    @Override
    public int getStart() {
        return 61;
    }

    @Override
    public int getEnd() {
        return 75;
    }
}
