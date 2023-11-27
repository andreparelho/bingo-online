package com.app.bingoonline.model.extds;

import com.app.bingoonline.model.extds.B;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BTest {

    public B letterB = new B();

    @Test
    @Description("This letter need to return 5 int the getQuantity method")
    public void testGetQuantity_WhenLetterB_ShouldReturnFiveInt(){
        int actual = this.letterB.getQuantity();
        int expected = 5;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @Description("This letter need to return 1 in the getStart method")
    public void testGetStart_WhenLetterB_ShouldReturnOne(){
        int actual = this.letterB.getStart();
        int expected = 1;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @Description("This letter need to return 15 in the getEnd method")
    public void testGeEnd_WhenLetterB_ShouldReturnFifteen(){
        int actual = this.letterB.getEnd();
        int expected = 15;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }
}
