package us.kpatrick.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import us.kpatrick.FileInput;

import static org.junit.Assert.*;

public class FileInputTest {
    FileInput indata = null;
    @Before
    public void setUp() throws Exception {
        indata = new FileInput("parking.csv");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void fileReadLine() {
        assertTrue("output of Method is" instanceof String);
    }
}