package com.ernieyu.feedparser.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ernieyu.feedparser.Element;
import com.ernieyu.feedparser.Feed;
import com.ernieyu.feedparser.FeedType;
import com.ernieyu.feedparser.Item;

/**
 * Test case for DefaultFeedParser.
 */
public class DefaultFeedParserTest {
    private DefaultFeedParser feedParser;

    @Before
    public void setUp() throws Exception {
        feedParser = new DefaultFeedParser();
    }

    @After
    public void tearDown() throws Exception {
        feedParser = null;
    }

    /** Tests parse method on Atom 1.0 feed. */
    @Test
    public void testParseAtom1() {
        Feed feed = null;
        
        try {
            // Open input stream for test feed.
            URL url = getClass().getResource("sample-atom-1.xml");
            InputStream inStream = url.openConnection().getInputStream();
            
            // Parse feed.
            feed = feedParser.parse(inStream);

        } catch (Exception ex) {
            fail(ex.getMessage());
        }

        // Verify feed.
        assertNotNull("feed", feed);
        
        assertEquals("feed type", FeedType.ATOM_1_0, feed.getType());
        assertEquals("feed name", "feed", feed.getName());
        assertEquals("feed title", "Example Feed", feed.getTitle());
        assertEquals("feed link", "http://example.org/", feed.getLink());
        
        Date expectedDate = createDate(2003, 11, 13, 18, 30, 2, "GMT");
        assertEquals("feed pub date", expectedDate, feed.getPubDate());
        
        Element id = feed.getElement("id");
        assertEquals("feed id", "urn:uuid:60a76c80-d399-11d9-b93C-0003939e0af6", id.getContent());

        // Verify entry.
        List<Item> itemList = feed.getItemList();
        assertEquals("feed entries", 1, itemList.size());

        Item item = itemList.get(0);
        assertEquals("item title", "Atom-Powered Robots Run Amok", item.getTitle());
        assertEquals("item link", "http://example.org/2003/12/13/atom03", item.getLink());
        assertEquals("item description", "Some text.", item.getDescription());
        assertEquals("item id", "urn:uuid:1225c695-cfb8-4ebb-aaaa-80da344efa6a", item.getGuid());
        
        expectedDate = createDate(2003, 11, 13, 18, 30, 2, "GMT");
        assertEquals("item pub date", expectedDate, item.getPubDate());
    }

    /** Tests parse method on RSS 1.0 feed. */
    @Test
    public void testParseRss1() {
        Feed feed = null;
        
        try {
            // Open input stream for test feed.
            URL url = getClass().getResource("sample-rss-1.xml");
            InputStream inStream = url.openConnection().getInputStream();
            
            // Parse feed.
            feed = feedParser.parse(inStream);
            
        } catch (Exception ex) {
            fail(ex.getMessage());
        }

        // Verify feed.
        assertNotNull("feed", feed);
        
        assertEquals("feed type", FeedType.RSS_1_0, feed.getType());
        assertEquals("feed name", "rdf", feed.getName());
        assertEquals("feed title", "XML.com", feed.getTitle());
        assertEquals("feed link", "http://xml.com/pub", feed.getLink());

        // Verify item.
        List<Item> itemList = feed.getItemList();
        assertEquals("feed entries", 2, itemList.size());

        Item item = itemList.get(0);
        assertEquals("item title", "Processing Inclusions with XSLT", item.getTitle());
        assertEquals("item link", "http://xml.com/pub/2000/08/09/xslt/xslt.html", item.getLink());
    }

    /** Tests parse method on RSS 2.0 feed. */
    @Test
    public void testParseRss2() {
        Feed feed = null;
        
        try {
            // Open input stream for test feed.
            URL url = getClass().getResource("sample-rss-2.xml");
            InputStream inStream = url.openConnection().getInputStream();
            
            // Parse feed.
            feed = feedParser.parse(inStream);
            
        } catch (Exception ex) {
            fail(ex.getMessage());
        }

        // Verify feed.
        assertNotNull("feed", feed);

        assertEquals("feed type", FeedType.RSS_2_0, feed.getType());
        assertEquals("feed name", "rss", feed.getName());
        assertEquals("feed title", "Liftoff News", feed.getTitle());
        assertEquals("feed link", "http://liftoff.msfc.nasa.gov/", feed.getLink());
        assertEquals("feed descr", "Liftoff to Space Exploration.", feed.getDescription());
        assertEquals("feed language", "en-us", feed.getLanguage());
        
        Date expectedDate = createDate(2003, 5, 10, 4, 0, 0, "GMT");
        assertEquals("feed pub date", expectedDate, feed.getPubDate());

        // Verify item.
        List<Item> itemList = feed.getItemList();
        assertEquals("feed entries", 4, itemList.size());

        Item item = itemList.get(0);
        assertEquals("item title", "Star City", item.getTitle());
        assertEquals("item link", "http://liftoff.msfc.nasa.gov/news/2003/news-starcity.asp", item.getLink());
        assertEquals("item guid", "http://liftoff.msfc.nasa.gov/2003/06/03.html#item573", item.getGuid());
        
        expectedDate = createDate(2003, 5, 3, 9, 39, 21, "GMT");
        assertEquals("item pub date", expectedDate, item.getPubDate());
    }
    
    /**
     * Creates a test Date with the specified values.
     */
    private Date createDate(int year, int month, int day, 
            int hour, int min, int sec, String timezone) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timezone));
        calendar.clear();
        calendar.set(year, month, day, hour, min, sec);
        Date date = calendar.getTime();
        return date;
    }
}
