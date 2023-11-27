package com.app.bingoonline.model.extds;

import com.app.bingoonline.model.extds.B;
import com.app.bingoonline.model.extds.I;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ITest {

    public I letterI = new I();

    @Test
    @Description("This letter need to return 5 int the getQuantity method")
    public void testGetQuantity_WhenLetterI_ShouldReturnFiveInt(){
        int actual = this.letterI.getQuantity();
        int expected = 5;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @Description("This letter need to return 16 in the getStart method")
    public void testGetStart_WhenLetterI_ShouldReturnSixteen(){
        int actual = this.letterI.getStart();
        int expected = 16;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @Description("This letter need to return 30 in the getEnd method")
    public void testGeEnd_WhenLetterI_ShouldReturnThirty(){
        int actual = this.letterI.getEnd();
        int expected = 30;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }
}
