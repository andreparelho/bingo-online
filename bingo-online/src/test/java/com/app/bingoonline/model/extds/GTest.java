package com.app.bingoonline.model.extds;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GTest {
    private G letterG;

    @BeforeEach
    void config(){
        this.letterG = new G();
    }

    @Test
    @DisplayName("This letter need to return 5 int the getQuantity method")
    public void testDeveRetornarValorInteiroCincoQuandoChamarOMetodoQuantity(){
        int actual = this.letterG.getQuantity();
        int expected = 5;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @DisplayName("This letter need to return 46 in the getStart method")
    public void testDeveRetornarValorQuarentaESeisQuandoChamarOMetodoStart(){
        int actual = this.letterG.getStart();
        int expected = 46;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @DisplayName("This letter need to return 60 in the getEnd method")
    public void testDeveRetornarOValorSessentaQuandoChamarOMetodoEnd(){
        int actual = this.letterG.getEnd();
        int expected = 60;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @DisplayName("This letter need to return index 5 when call generateTicket method")
    public void testDeveRetornarIndexDeCincoNumerosQuandoChamarGenerateTicket(){
        Set<Integer> ticket = this.letterG.generateTicketNumbers();
        int ticketIndexSize = ticket.size();
        int expected = 5;
        assertEquals(ticketIndexSize, expected);
    }

    @Test
    @DisplayName("This letter need to return numbers between 46 and 60 when call generateTicket method")
    public void testDeveRetornarValoresEntreQuarentaESeisESessentaQuandoChamarGenerateTicket(){
        Set<Integer> ticket = this.letterG.generateTicketNumbers();
        boolean isValidNumbers = ticket.stream().allMatch(n -> n >= 46 && n <= 60);
        assertTrue(isValidNumbers);
    }
}
