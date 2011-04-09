Simple Feed Parser

A Java-based parser for RSS and Atom web feeds.

Goals
-----
- simple Java API

- compatible with Java and Android SDKs

- support for RSS 1.0/2.0 and Atom 1.0 feeds

- small resource footprint without external library dependencies

Build
-----
Building the project jar file requires JDK 1.6.0 and Ant 1.7.0 or later.
The following command builds the jar file in the dist folder:

    ant clean jar

The feedparser-[version].jar file may be added to the classpath of any Java
or Android project.

Usage
-----
Parsing an input stream for a feed requires the following statements:

    FeedParser parser = FeedParserFactory.newParser();
    Feed feed = parser.parse(feedStream);

The Feed data structure is a simple hierarchy of Feed, Item, and Element
objects.  Convenience methods are provided for common data values.  Refer
to the API docs at http://ernieyu.github.com/

To obtain an input stream from a URL, an application can use the following
boilerplate code:

    URL url = new URL(urlText);
    HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
    if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
        InputStream feedStream = httpConnection.getInputStream();
        ...
    }

Release history
---------------
0.1 Initial release