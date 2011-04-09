package com.ernieyu.feedparser.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.helpers.AttributesImpl;

/**
 * Test case for BaseItem.
 */
public class BaseItemTest {
    private static final String URI = "uri";
    private static final String NAME = "test";
    private static final String TITLE_1 = "Test Item";
    private static final String LINK_1 = "http://www.helloworld.com/";
    private static final String DESCR_1 = "Hello world";
    private static final String GUID_1 = "http://www.helloworld.com/";
    
    private BaseItem item;

    @Before
    public void setUp() throws Exception {
        item = new Rss2Item(URI, NAME, new AttributesImpl());
        
        BaseElement title = new BaseElement(URI, "title");
        title.setContent(TITLE_1);
        item.addElement("title", title);
        
        BaseElement link = new BaseElement(URI, "link");
        link.setContent(LINK_1);
        item.addElement("link", link);
        
        BaseElement descr = new BaseElement(URI, "description");
        descr.setContent(DESCR_1);
        item.addElement("description", descr);
        
        BaseElement guid = new BaseElement(URI, "guid");
        guid.setContent(GUID_1);
        item.addElement("guid", guid);
    }

    @After
    public void tearDown() throws Exception {
        item = null;
    }

    /**
     * Tests method to return descriptive string.
     */
    @Test
    public void testToString() {
        assertEquals("toString", TITLE_1, item.toString());
    }

    /**
     * Tests method to compare items.
     */
    @Test
    public void testEqualsObject() {
        // Check AtomItem with same id.
        BaseItem item2 = createAtomItem(TITLE_1, LINK_1, DESCR_1, GUID_1);
        assertEquals("equals", item, item2);
        
        // Check AtomItem with missing id but same link.
        item2 = createAtomItem(TITLE_1, GUID_1, DESCR_1, null);
        assertEquals("equals", item, item2);
        
        // Check AtomItem with different id.
        item2 = createAtomItem(TITLE_1, LINK_1, DESCR_1, "http://www.blahblah.com/");
        assertFalse("not equals", item.equals(item2));
    }

    /**
     * Tests method to return hash code.
     */
    @Test
    public void testHashCode() {
        // Check AtomItem with same id.
        BaseItem item2 = createAtomItem(TITLE_1, LINK_1, DESCR_1, GUID_1);
        assertEquals("hash code", item.hashCode(), item2.hashCode());
        
        // Check AtomItem with missing id but same link.
        item2 = createAtomItem(TITLE_1, GUID_1, DESCR_1, null);
        assertEquals("hash code", item.hashCode(), item2.hashCode());
        
        // Check AtomItem with different id.
        item2 = createAtomItem(TITLE_1, LINK_1, DESCR_1, "http://www.blahblah.com/");
        assertFalse("hash code", (item.hashCode() == item2.hashCode()));
    }
    
    /**
     * Creates a new AtomItem with the specified parameters.
     */
    private AtomItem createAtomItem(String title, String link, 
            String description, String guid) {
        
        AtomItem newItem = new AtomItem(URI, NAME, new AttributesImpl());
        
        if (title != null) {
            BaseElement element = new BaseElement(URI, "title");
            element.setContent(title);
            newItem.addElement("title", element);
        }
        
        if (link != null) {
            AttributesImpl attrs = new AttributesImpl();
            attrs.addAttribute("", "href", "href", "CDATA", link);
            BaseElement element = new BaseElement(URI, "link", attrs);
            newItem.addElement("link", element);
        }

        if (description != null) {
            BaseElement element = new BaseElement(URI, "content");
            element.setContent(description);
            newItem.addElement("content", element);
        }

        if (guid != null) {
            BaseElement element = new BaseElement(URI, "id");
            element.setContent(guid);
            newItem.addElement("id", element);
        }
        
        return newItem;
    }
}
