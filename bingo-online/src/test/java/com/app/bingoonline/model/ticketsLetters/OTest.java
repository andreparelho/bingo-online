package com.app.bingoonline.model.ticketsLetters;

import com.app.bingoonline.model.ticket.card.O;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OTest {

    private O letterO;

    @BeforeEach
    void config(){
        this.letterO = new O();
    }

    @Test
    @DisplayName("This letter need to return 5 int the getQuantity method")
    public void testDeveRetornarValorInteiroCincoQuandoChamarOMetodoQuantity(){
        int actual = this.letterO.getQuantity();
        int expected = 5;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @DisplayName("This letter need to return 61 in the getStart method")
    public void testDeveRetornarValorSessentaEUmQuandoChamarOMetodoStart(){
        int actual = this.letterO.getStart();
        int expected = 61;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @DisplayName("This letter need to return 75 in the getEnd method")
    public void testDeveRetornarOValorSetentaECincoQuandoChamarOMetodoEnd(){
        int actual = this.letterO.getEnd();
        int expected = 75;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @DisplayName("This letter need to return index 5 when call generateTicket method")
    public void testDeveRetornarIndexDeCincoNumerosQuandoChamarGenerateTicket(){
        Set<Integer> ticket = this.letterO.generateTicketNumbers();
        int ticketIndexSize = ticket.size();
        int expected = 5;
        assertEquals(ticketIndexSize, expected);
    }

    @Test
    @DisplayName("This letter need to return numbers between 61 and 75 when call generateTicket method")
    public void testDeveRetornarValoresEntreQuarentaESeisESessentaQuandoChamarGenerateTicket(){
        Set<Integer> ticket = this.letterO.generateTicketNumbers();
        boolean isValidNumbers = ticket.stream().allMatch(n -> n >= 61 && n <= 75);
        assertTrue(isValidNumbers);
    }
}
