package com.ernieyu.feedparser;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ernieyu.feedparser.FeedUtils;

/**
 * Test case for FeedUtils.
 */
public class FeedUtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /** Tests method to convert Atom date text. */
    @Test
    public void testConvertAtomDate() {
        // Convert test date.
        String testStr = "2003-12-13T18:30:02Z";
        Date date = FeedUtils.convertAtomDate(testStr);
        
        Date expectedDate = createDate(2003, 11, 13, 18, 30, 2, 0, "GMT");
        assertEquals("Atom date", expectedDate, date);
    }

    /** Tests method to convert Atom date text with time offset. */
    @Test
    public void testConvertAtomDateWithTimeZone() {
        // Convert test date.
        String testStr = "2003-12-13T18:30:02-05:00";
        Date date = FeedUtils.convertAtomDate(testStr);
        
        Date expectedDate = createDate(2003, 11, 13, 18, 30, 2, 0, "GMT-05:00");
        assertEquals("Atom date", expectedDate, date);
    }

    /** Tests method to convert Atom date text with fractional seconds. */
    @Test
    public void testConvertAtomDateWithFraction() {
        // Convert test date.
        String testStr = "2003-12-13T18:30:02.21-05:00";
        Date date = FeedUtils.convertAtomDate(testStr);
        
        Date expectedDate = createDate(2003, 11, 13, 18, 30, 2, 210, "GMT-05:00");
        assertEquals("Atom date", expectedDate, date);
    }

    /** Tests method to convert RSS 1.0 date text. */
    @Test
    public void testConvertRss1Date() {
        // Convert test date.
        String testStr = "2003-12-13T18:30:02Z";
        Date date = FeedUtils.convertAtomDate(testStr);
        
        Date expectedDate = createDate(2003, 11, 13, 18, 30, 2, 0, "GMT");
        assertEquals("RSS 1 date", expectedDate, date);
    }

    /** Tests method to convert RSS 2.0 date text. */
    @Test
    public void testConvertRss2Date() {
        // Convert test date.
        String testStr = "Tue, 10 Jun 2003 04:00:00 GMT";
        Date date = FeedUtils.convertRss2Date(testStr);
        
        Date expectedDate = createDate(2003, 5, 10, 4, 0, 0, 0, "GMT");
        assertEquals("RSS 2 date", expectedDate, date);
    }

    /** Tests method to convert RSS 2.0 date text with time offset. */
    @Test
    public void testConvertRss2DateWithTimeZone() {
        // Convert test date.
        String testStr = "Tue, 10 Jun 2003 04:00:00 +0200";
        Date date = FeedUtils.convertRss2Date(testStr);
        
        Date expectedDate = createDate(2003, 5, 10, 4, 0, 0, 0, "GMT+02:00");
        assertEquals("RSS 2 date", expectedDate, date);
    }
    
    /**
     * Creates a test Date with the specified values.
     */
    private Date createDate(int year, int month, int day, 
            int hour, int min, int sec, int millisec, String timezone) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timezone));
        calendar.clear();
        calendar.set(year, month, day, hour, min, sec);
        calendar.set(Calendar.MILLISECOND, millisec);
        Date date = calendar.getTime();
        return date;
    }
}
