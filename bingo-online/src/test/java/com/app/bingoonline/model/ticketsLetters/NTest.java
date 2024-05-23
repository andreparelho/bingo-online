package com.app.bingoonline.model.ticketsLetters;

import com.app.bingoonline.model.ticket.card.N;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class NTest {

    private N letterN;

    @BeforeEach
    void config(){
        this.letterN = new N();
    }

    @Test
    @DisplayName("This letter need to return 4 int the getQuantity method")
    public void testDeveRetornarValorInteiroQuatroQuandoChamarOMetodoQuantity(){
        int actual = this.letterN.getQuantity();
        int expected = 4;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @DisplayName("This letter need to return 31 in the getStart method")
    public void testDeveRetornarValorTrintaEUmQuandoChamarOMetodoStart(){
        int actual = this.letterN.getStart();
        int expected = 31;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @DisplayName("This letter need to return 45 in the getEnd method")
    public void testDeveRetornarOValorQuarentaECincoQuandoChamarOMetodoEnd(){
        int actual = this.letterN.getEnd();
        int expected = 45;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @DisplayName("This letter need to return index 4 when call generateTicket method")
    public void testDeveRetornarIndexDeCincoNumerosQuandoChamarGenerateTicket(){
        Set<Integer> ticket = this.letterN.generateTicketNumbers();
        int ticketIndexSize = ticket.size();
        int expected = 4;
        assertEquals(ticketIndexSize, expected);
    }

    @Test
    @DisplayName("This letter need to return numbers between 31 and 45 when call generateTicket method")
    public void testDeveRetornarValoresEntreTrintaEUmEQuarentaECincoQuandoChamarGenerateTicket(){
        Set<Integer> ticket = this.letterN.generateTicketNumbers();
        boolean isValidNumbers = ticket.stream().allMatch(n -> n >= 31 && n <= 45);
        assertTrue(isValidNumbers);
    }
}
