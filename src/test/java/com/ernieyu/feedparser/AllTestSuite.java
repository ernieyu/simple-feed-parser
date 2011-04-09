package com.ernieyu.feedparser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ernieyu.feedparser.impl.BaseElementTest;
import com.ernieyu.feedparser.impl.BaseItemTest;
import com.ernieyu.feedparser.impl.DefaultFeedParserTest;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * JUnit 4 test suite to run all tests.
 */
@RunWith(Suite.class)
@SuiteClasses({
    BaseElementTest.class,
    BaseItemTest.class,
    DefaultFeedParserTest.class,
    FeedUtilsTest.class
})
public class AllTestSuite {

    public static Test suite() {
        TestSuite suite = new TestSuite(AllTestSuite.class.getName());
        //$JUnit-BEGIN$

        //$JUnit-END$
        return suite;
    }

}
