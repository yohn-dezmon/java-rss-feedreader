package com.jdes.rssfeed;

import com.jdes.rssfeed.controller.Feed;
import com.jdes.rssfeed.controller.FeedMessage;
import com.jdes.rssfeed.controller.RSSFeedParser;

public class ReadTest {
	public static void main(String[] args) {
        RSSFeedParser parser = new RSSFeedParser(
                "https://www.vogella.com/article.rss");
        Feed feed = parser.readFeed();
        System.out.println(feed);
        for (FeedMessage message : feed.getMessages()) {
            System.out.println(message);

        }

    }

}
