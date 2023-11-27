package com.app.bingoonline.model.extds;

import com.app.bingoonline.model.extds.I;
import com.app.bingoonline.model.extds.O;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OTest {

    public O letterO = new O();

    @Test
    @Description("This letter need to return 5 int the getQuantity method")
    public void testGetQuantity_WhenLetterO_ShouldReturnFiveInt(){
        int actual = this.letterO.getQuantity();
        int expected = 5;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @Description("This letter need to return 61 in the getStart method")
    public void testGetStart_WhenLetterO_ShouldReturnSixthOne(){
        int actual = this.letterO.getStart();
        int expected = 61;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @Description("This letter need to return 75 in the getEnd method")
    public void testGetEnd_WhenLetterO_ShouldReturnSeventhFive(){
        int actual = this.letterO.getEnd();
        int expected = 75;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }
}
