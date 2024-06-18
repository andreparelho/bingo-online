package com.app.bingoonline.model.ticketsLetters;

import com.app.bingoonline.ticket.factory.impl.NFactoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
public class NFactoryImplTest {

    private NFactoryImpl letterNFactoryImpl;

    @BeforeEach
    void config(){
        this.letterNFactoryImpl = new NFactoryImpl();
    }

    @Test
    @DisplayName("This letter need to return 4 int the getQuantity method")
    public void testDeveRetornarValorInteiroQuatroQuandoChamarOMetodoQuantity(){
        int actual = this.letterNFactoryImpl.getQuantity();
        int expected = 4;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @DisplayName("This letter need to return 31 in the getStart method")
    public void testDeveRetornarValorTrintaEUmQuandoChamarOMetodoStart(){
        int actual = this.letterNFactoryImpl.getStart();
        int expected = 31;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @DisplayName("This letter need to return 45 in the getEnd method")
    public void testDeveRetornarOValorQuarentaECincoQuandoChamarOMetodoEnd(){
        int actual = this.letterNFactoryImpl.getEnd();
        int expected = 45;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @DisplayName("This letter need to return index 4 when call generateTicket method")
    public void testDeveRetornarIndexDeCincoNumerosQuandoChamarGenerateTicket(){
        List<Integer> ticket = this.letterNFactoryImpl.generateTicketNumbers();
        int ticketIndexSize = ticket.size();
        int expected = 4;
        assertEquals(ticketIndexSize, expected);
    }

    @Test
    @DisplayName("This letter need to return numbers between 31 and 45 when call generateTicket method")
    public void testDeveRetornarValoresEntreTrintaEUmEQuarentaECincoQuandoChamarGenerateTicket(){
        List<Integer> ticket = this.letterNFactoryImpl.generateTicketNumbers();
        boolean isValidNumbers = ticket.stream().allMatch(n -> n >= 31 && n <= 45);
        assertTrue(isValidNumbers);
    }
}
