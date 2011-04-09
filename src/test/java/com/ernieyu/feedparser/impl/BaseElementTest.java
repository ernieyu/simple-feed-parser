package com.ernieyu.feedparser.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;

import com.ernieyu.feedparser.Element;
import com.ernieyu.feedparser.impl.BaseElement;

/**
 * Test case for BaseElement.
 */
public class BaseElementTest {
    private static final String URI = "uri";
    private static final String NAME = "test";
    
    private BaseElement element;

    @Before
    public void setUp() throws Exception {
        element = new BaseElement(URI, NAME);
    }

    @After
    public void tearDown() throws Exception {
        element = null;
    }

    /** Tests constructor with name parameter. */
    @Test
    public void testBaseElement() {
        assertEquals("uri", URI, element.getUri());
        assertEquals("name", NAME, element.getName());
    }

    /** Tests constructor with attributes. */
    @Test
    public void testBaseElementAttributes() {
        // Create test attribute.
        final String ATTR_NAME = "href";
        final String ATTR_VALUE = "http://example.org/";
        AttributesImpl testAttrs = new AttributesImpl();
        testAttrs.addAttribute("", ATTR_NAME, ATTR_NAME, "CDATA", ATTR_VALUE);
        
        // Create element with attribute.
        element = new BaseElement(URI, NAME, testAttrs);
        
        // Reset original attributes.
        testAttrs.removeAttribute(0);
        
        // Verify attributes retained in element.
        Attributes attrs = element.getAttributes();
        assertEquals("attr count", 1, attrs.getLength());
        assertEquals("attr value", ATTR_VALUE, attrs.getValue(ATTR_NAME));
    }

    /** Tests method to set content. */
    @Test
    public void testSetContent() {
        String content = "hello";
        element.setContent(content);
        
        assertEquals("content", content, element.getContent());
    }

    /** Tests method to add child element. */
    @Test
    public void testAddElement() {
        // Add child element.
        Element child = new BaseElement("", "test");
        element.addElement("child", child);
        
        // Verify child element.
        Element result = element.getElement("child");
        assertEquals("child element", child.getName(), result.getName());
        
        // Verify child list.
        List<Element> results = element.getElementList("child");
        assertEquals("child count", 1, results.size());
        
        // Verify child keys.
        Set<String> keys = element.getElementKeys();
        assertEquals("child keys", 1, keys.size());
    }
}
