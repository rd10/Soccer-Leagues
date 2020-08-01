/*package com.example;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ValidateTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test //(expected = IllegalArgumentException.class)
    public void isValueAnInteger() throws Exception{
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("sadfds is not an integer");
        Validate.isInteger("sadfds");
    }

    @Test
    public void isValueInValidRange() {
        boolean valid = Validate.isRangeValid(1,20,20);
        assertEquals(true, valid);
    }
}*/
