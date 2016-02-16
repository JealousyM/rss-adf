package by.perevertkin.servlets;


import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;

import com.sun.syndication.feed.synd.SyndFeedImpl;

import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class RssServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "application/rss+xml";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType("rss_2.0");
        feed.setLink("http://wwww.oracle-adf.info");
        feed.setLanguage("en");
        feed.setTitle("Mikhail Perevertkin");
        feed.setDescription("Articles about Oracle Fusion Middleware");
        feed.setEncoding("utf-8");
        feed.setPublishedDate(new Date());
        
        List<SyndEntry> entries = new ArrayList<SyndEntry>();
        for (int i=1; i<10; i++) {
           SyndEntry entry = new SyndEntryImpl();
                       entry.setTitle("Title "+i);
                       SyndContent description = new SyndContentImpl();
                       description.setType("text/html");
                       description.setValue("Description "+i);
                       entry.setDescription(description);
                       entry.setPublishedDate(new Date());
                       entries.add(entry);

       }
        feed.setEntries(entries);  
        response.setContentType(CONTENT_TYPE);
        SyndFeedOutput output = new SyndFeedOutput();

        try {
            output.output(feed, response.getWriter());
        } catch (FeedException e) {
            e.printStackTrace();
        }
    }
}
