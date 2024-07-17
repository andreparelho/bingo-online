package com.app.bingoonline.ticket.factory.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class BFactoryImplTest {

    @InjectMocks
    private BFactoryImpl letterBFactoryImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("This letter need to return 5 int the getQuantity method")
    public void testDeveRetornarValorInteiroCincoQuandoChamarOMetodoQuantity(){
        int actual = this.letterBFactoryImpl.getQuantity();
        int expected = 5;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @DisplayName("This letter need to return 1 in the getStart method")
    public void testDeveRetornarValorUmQuandoChamarOMetodoStart(){
        int actual = this.letterBFactoryImpl.getStart();
        int expected = 1;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @DisplayName("This letter need to return 15 in the getEnd method")
    public void testDeveRetornarOValorQuinzeQuandoChamarOMetodoEnd(){
        int actual = this.letterBFactoryImpl.getEnd();
        int expected = 15;
        assertEquals(expected, actual, () -> actual + " did not equal to " + expected);
    }

    @Test
    @DisplayName("This letter need to return index 5 when call generateTicket method")
    public void testDeveRetornarIndexDeCincoNumerosQuandoChamarGenerateTicket(){
        List<Integer> ticket = this.letterBFactoryImpl.generateTicketNumbers();
        int ticketIndexSize = ticket.size();
        int expected = 5;
        assertEquals(ticketIndexSize, expected);
    }

    @Test
    @DisplayName("This letter need to return numbers between 1 and 15 when call generateTicket method")
    public void testDeveRetornarValoresEntreUmEQuinzeQuandoChamarGenerateTicket(){
        List<Integer> ticket = this.letterBFactoryImpl.generateTicketNumbers();
        boolean isValidNumbers = ticket.stream().allMatch(n -> n >= 1 && n <= 15);
        assertTrue(isValidNumbers);
    }
}