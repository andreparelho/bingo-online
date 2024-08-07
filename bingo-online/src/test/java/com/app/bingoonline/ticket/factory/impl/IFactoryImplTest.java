package com.app.bingoonline.ticket.factory.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class IFactoryImplTest {

    private IFactoryImpl letterIFactoryImpl;

    @BeforeEach
    void config(){
        this.letterIFactoryImpl = new IFactoryImpl();
    }

    @Test
    @DisplayName("This letter need to return 5 int the getQuantity method")
    public void testDeveRetornarValorInteiroCincoQuandoChamarOMetodoQuantity(){
        int actual = this.letterIFactoryImpl.getQuantity();
        int expected = 5;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @DisplayName("This letter need to return 16 in the getStart method")
    public void testDeveRetornarValorDezesseisQuandoChamarOMetodoStart(){
        int actual = this.letterIFactoryImpl.getStart();
        int expected = 16;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @DisplayName("This letter need to return 30 in the getEnd method")
    public void testDeveRetornarOValorTrintaQuandoChamarOMetodoEnd(){
        int actual = this.letterIFactoryImpl.getEnd();
        int expected = 30;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @DisplayName("This letter need to return index 5 when call generateTicket method")
    public void testDeveRetornarIndexDeCincoNumerosQuandoChamarGenerateTicket(){
        List<Integer> ticket = this.letterIFactoryImpl.generateTicketNumbers();
        int ticketIndexSize = ticket.size();
        int expected = 5;
        assertEquals(ticketIndexSize, expected);
    }

    @Test
    @DisplayName("This letter need to return numbers between 16 and 30 when call generateTicket method")
    public void testDeveRetornarValoresEntreDezesseisETrintaQuandoChamarGenerateTicket(){
        List<Integer> ticket = this.letterIFactoryImpl.generateTicketNumbers();
        boolean isValidNumbers = ticket.stream().allMatch(n -> n >= 16 && n <= 30);
        assertTrue(isValidNumbers);
    }
}