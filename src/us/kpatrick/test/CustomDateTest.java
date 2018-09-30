package us.kpatrick.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import us.kpatrick.CustomDate;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class CustomDateTest {


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void minuteDifference1() {
        assertEquals(1, CustomDate.minuteDifference1(LocalDateTime.now().toString(), LocalDateTime.now().plusMinutes(1).toString()));
    }
}