package com.ernieyu.feedparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ernieyu.feedparser.Feed;
import com.ernieyu.feedparser.FeedParser;
import com.ernieyu.feedparser.FeedParserFactory;
import com.ernieyu.feedparser.FeedType;
import com.ernieyu.feedparser.Item;

/**
 * Test case using live feeds.
 */
public class LiveFeedTestSuite {
    private final static String BBC_WORLD_NEWS = "http://feeds.bbci.co.uk/news/world/rss.xml";
    private final static String NYT_WORLD_NEWS = "http://feeds.nytimes.com/nyt/rss/World";
    private final static String USGS_QUAKE_ATOM = "http://earthquake.usgs.gov/earthquakes/catalogs/1hour-M1.xml";
    private final static String USGS_QUAKE_RSS = "http://earthquake.usgs.gov/earthquakes/catalogs/eqs1hour-M1.xml";
    
    private final static Logger LOG = Logger.getLogger(LiveFeedTestSuite.class.getName());
    
    private FeedParser feedParser;

    @Before
    public void setUp() throws Exception {
        feedParser = FeedParserFactory.newParser();
    }

    @After
    public void tearDown() throws Exception {
        feedParser = null;
    }

    /** Test using BBC world news RSS feed. */
    @Test
    public void testBBCNewsRss() {
        try {
            // Open input stream for test feed.
            URL url = new URL(BBC_WORLD_NEWS);
            InputStream inStream = url.openConnection().getInputStream();
            
            // Parse feed.
            Feed feed = feedParser.parse(inStream);

            assertEquals("feed name", "rss", feed.getName());
            assertEquals("feed type", FeedType.RSS_2_0, feed.getType());
            assertEquals("feed title", "BBC News - World", feed.getTitle());

            List<Item> itemList = feed.getItemList();
            LOG.log(Level.INFO, "item count = " + itemList.size());
            assertTrue("item count", itemList.size() > 0);
            
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    /** Test using NY Times world news RSS feed. */
    @Test
    public void testNYTimesRss() {
        try {
            // Open input stream for test feed.
            URL url = new URL(NYT_WORLD_NEWS);
            InputStream inStream = url.openConnection().getInputStream();
            
            // Parse feed.
            Feed feed = feedParser.parse(inStream);

            assertEquals("feed name", "rss", feed.getName());
            assertEquals("feed type", FeedType.RSS_2_0, feed.getType());
            assertEquals("feed title", "NYT > World", feed.getTitle());

            List<Item> itemList = feed.getItemList();
            LOG.log(Level.INFO, "item count = " + itemList.size());
            assertTrue("item count", itemList.size() > 0);
            
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    /** Test using USGS earthquakes Atom feed. */
    @Test
    public void testUSGSQuakesAtom() {
        try {
            // Open input stream for test feed.
            URL url = new URL(USGS_QUAKE_ATOM);
            InputStream inStream = url.openConnection().getInputStream();
            
            // Parse feed.
            Feed feed = feedParser.parse(inStream);

            assertEquals("feed name", "feed", feed.getName());
            assertEquals("feed type", FeedType.ATOM_1_0, feed.getType());
            assertEquals("feed title", "USGS M 1+ Earthquakes", feed.getTitle());

            List<Item> itemList = feed.getItemList();
            LOG.log(Level.INFO, "item count = " + itemList.size());
            assertTrue("item count", itemList.size() > 0);
            
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    /** Test using USGS earthquakes RSS feed. */
    @Test
    public void testUSGSQuakesRss() {
        try {
            // Open input stream for test feed.
            URL url = new URL(USGS_QUAKE_RSS);
            InputStream inStream = url.openConnection().getInputStream();
            
            // Parse feed.
            Feed feed = feedParser.parse(inStream);

            assertEquals("feed name", "rss", feed.getName());
            assertEquals("feed type", FeedType.RSS_2_0, feed.getType());
            assertEquals("feed title", "USGS M 1+ Earthquakes", feed.getTitle());

            List<Item> itemList = feed.getItemList();
            LOG.log(Level.INFO, "item count = " + itemList.size());
            assertTrue("item count", itemList.size() > 0);
            
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }
}
