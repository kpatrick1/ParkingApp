package us.kpatrick.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import us.kpatrick.Tickets;

import static org.junit.Assert.*;

public class TicketsTest {
    Tickets t = null;
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getTickets() {
        //THIS RETUNS AN ARRAY LIST OF TICKETS
    }

    @Test
    public void createNewTicket() {
        //THIS CREATES A NEW TICKET AND RETURNS A DIFFERENT ID EVERYTIME
    }

    @Test
    public void saveLostTicket() {
        //THIS CREATES A NEW TICKET AND RETURNS A DIFFERENT ID EVERYTIME
    }

    @Test
    public void closeTicket() {
        //THIS ADDS THE CHECKOUT DATE TO THE TICKET AND CALCULATES THE TOTAL WHICH VARIES EVERY TIME
    }
}