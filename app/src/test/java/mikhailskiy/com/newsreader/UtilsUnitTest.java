package mikhailskiy.com.newsreader;

import org.junit.Test;

import mikhailskiy.com.newsreader.utils.DateHelper;

import static org.junit.Assert.*;

public class UtilsUnitTest {
    private final static String DUMMY_DATE = "Sun, 31 Jul 2016 16:19:40 +0300";
    private final static String EXPECTED_DATE = "31 июл 16:19:40";
    @Test
    public void checkDateHelper() throws Exception {
        assertEquals(EXPECTED_DATE,DateHelper.getReadableDate(DUMMY_DATE));
    }
}