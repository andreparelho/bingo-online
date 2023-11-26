package com.app.bingoonline.model.extds;

import com.app.bingoonline.model.extds.I;
import com.app.bingoonline.model.extds.N;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NTest {

    public N letterN = new N();

    @Test
    @Description("This letter need to return 4 int the getQuantity method")
    public void getQuantityByLetterB(){
        int actual = this.letterN.getQuantity();
        int expected = 4;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @Description("This letter need to return 31 in the getStart method")
    public void getStartOneInLetterB(){
        int actual = this.letterN.getStart();
        int expected = 31;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @Description("This letter need to return 45 in the getEnd method")
    public void getEndFifteenInLetterB(){
        int actual = this.letterN.getEnd();
        int expected = 45;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }
}
