package com.app.bingoonline.model;

import com.app.bingoonline.model.extds.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LetterTest {

    public B letterB = new B();
    public N letterN = new N();

    public int expectedLetterN = 4;
    public int expectedOnRestLetters = 5;

    @Test
    @DisplayName("The method generateTicketNumbers need to return five numbers in array, except letter N")
    public void testGenerateTicketNumbers_WhenSendAnyLetterObjectExceptLetterN_ShouldReturnFiveNumbersInArray(){
        Set<Integer> letterBTickets =  this.letterB.generateTicketNumbers();
        int actualOnLetterB = letterBTickets.size();
        assertEquals(this.expectedOnRestLetters, actualOnLetterB, () -> actualOnLetterB + " did not produced 5 numbers on ticket, expected " + this.expectedOnRestLetters);
    }

    @Test
    @DisplayName("The method generateTicketNumbers need to return four numbers in array in letter N")
    public void testGenerateTicketNumbers_WhenSendLetterN_ShouldReturnFourNumbersInArray(){
        Set<Integer> letterNTickets =  this.letterN.generateTicketNumbers();
        int actualLetterN = letterNTickets.size();
        assertEquals(this.expectedLetterN, actualLetterN, () -> actualLetterN + " did not produced 5 numbers on ticket, expected " + this.expectedLetterN);
    }

    @Test
    @DisplayName("this generateTicketNumbers method need to return one array with five index that not repeat")
    public void testGenerateTicketNumbers_WhenSendAnyLetterObjectExceptLetterN_ShouldReturnFiveIndexNumbersThatNotRepeat(){
        Set<Integer> actual = this.letterB.generateTicketNumbers();
        assertNotNull(actual);

        Set<Integer> expected = new HashSet<>();
        while (actual.size() < letterB.getQuantity()){
            Random randomNumber = null;
            actual.add(randomNumber.nextInt(this.letterB.getStart(), this.letterB.getEnd()));
        }

        expected.addAll(actual);
        assertEquals(expected, actual, () -> actual + " did not match to " + expected);
    }

    @Test
    @DisplayName("this generateTicketNumbers method need to return one array with four index")
    public void testGenerateTicketNumbers_WhenSendLetterN_ShouldReturnFourIndexNumbersThatNotRepeat(){
        Set<Integer> actual = this.letterN.generateTicketNumbers();
        assertNotNull(actual);

        Set<Integer> expected = new HashSet<>();
        while (actual.size() < letterN.getQuantity()){
            Random randomNumber = null;
            actual.add(randomNumber.nextInt(this.letterN.getStart(), this.letterN.getEnd()));
        }

        expected.addAll(actual);
        assertEquals(expected, actual, () -> actual + " did not match to " + expected);
    }

}
