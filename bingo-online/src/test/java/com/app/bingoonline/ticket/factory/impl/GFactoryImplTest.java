package com.app.bingoonline.ticket.factory.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class GFactoryImplTest {
    private GFactoryImpl letterGFactoryImpl;

    @BeforeEach
    void config(){
        this.letterGFactoryImpl = new GFactoryImpl();
    }

    @Test
    @DisplayName("This letter need to return 5 int the getQuantity method")
    public void testDeveRetornarValorInteiroCincoQuandoChamarOMetodoQuantity(){
        int actual = this.letterGFactoryImpl.getQuantity();
        int expected = 5;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @DisplayName("This letter need to return 46 in the getStart method")
    public void testDeveRetornarValorQuarentaESeisQuandoChamarOMetodoStart(){
        int actual = this.letterGFactoryImpl.getStart();
        int expected = 46;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @DisplayName("This letter need to return 60 in the getEnd method")
    public void testDeveRetornarOValorSessentaQuandoChamarOMetodoEnd(){
        int actual = this.letterGFactoryImpl.getEnd();
        int expected = 60;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @DisplayName("This letter need to return index 5 when call generateTicket method")
    public void testDeveRetornarIndexDeCincoNumerosQuandoChamarGenerateTicket(){
        List<Integer> ticket = this.letterGFactoryImpl.generateTicketNumbers();
        int ticketIndexSize = ticket.size();
        int expected = 5;
        assertEquals(ticketIndexSize, expected);
    }

    @Test
    @DisplayName("This letter need to return numbers between 46 and 60 when call generateTicket method")
    public void testDeveRetornarValoresEntreQuarentaESeisESessentaQuandoChamarGenerateTicket(){
        List<Integer> ticket = this.letterGFactoryImpl.generateTicketNumbers();
        boolean isValidNumbers = ticket.stream().allMatch(n -> n >= 46 && n <= 60);
        assertTrue(isValidNumbers);
    }
}