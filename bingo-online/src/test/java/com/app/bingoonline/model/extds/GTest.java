package com.app.bingoonline.model.extds;

import com.app.bingoonline.model.extds.G;
import com.app.bingoonline.model.extds.I;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GTest {
    public G letterG = new G();

    @Test
    @Description("This letter need to return 5 int the getQuantity method")
    public void testGetQuantity_WhenLetterG_ShouldReturnFiveInt(){
        int actual = this.letterG.getQuantity();
        int expected = 5;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @Description("This letter need to return 46 in the getStart method")
    public void testGetStart_WhenLetterG_ShouldReturnFourthSix(){
        int actual = this.letterG.getStart();
        int expected = 46;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @Description("This letter need to return 60 in the getEnd method")
    public void testGeEnd_WhenLetterG_ShouldReturnSixth(){
        int actual = this.letterG.getEnd();
        int expected = 60;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }
}
