package com.app.bingoonline.model.extds;

import com.app.bingoonline.model.Letter;
import org.springframework.stereotype.Component;

@Component
public class I extends Letter {
    @Override
    public int getQuantity(){
        return 5;
    }

    @Override
    public int getStart() {
        return 16;
    }

    @Override
    public int getEnd() {
        return 30;
    }
}
