/**
 * 209338R - KATS JAYATHILAKA
 */

package tj.msc.ir;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Pattern;

public class HtmlCrawler extends WebCrawler
{

    private final static Pattern FILTERS = Pattern.compile( ".*(\\.(css|js|gif|jpg|png|mp3|mp4|zip|gz))$" );

    public static ArrayList<String> docTitles = new ArrayList();

    /**
     * Specify whether the given url should be crawled or not based on the crawling logic. Here URLs with extensions
     * css, js etc will not be visited
     */
    @Override
    public boolean shouldVisit( Page referringPage, WebURL url )
    {
        System.out.println( "shouldVisit: " + url.getURL().toLowerCase() );

        String href = url.getURL().toLowerCase();
        boolean result = !FILTERS.matcher( href ).matches();

        if( result )
        {
            System.out.println( "URL Should Visit" );
        }
        else
        {
            System.out.println( "URL Should not Visit" );
        }

        return result;
    }

    /**
     * This function is called when a page is fetched and ready to be processed by the program.
     */
    @Override
    public void visit( Page page )
    {
        String url = page.getWebURL().getURL();
        System.out.println( "URL: " + url );

        if( page.getParseData() instanceof HtmlParseData )
        {
            HtmlParseData htmlParseData = ( HtmlParseData ) page.getParseData();
            String title = htmlParseData.getTitle(); //extract text from page
            String text = htmlParseData.getText(); //extract text from page
            String html = htmlParseData.getHtml(); //extract html from page
            Set<WebURL> links = htmlParseData.getOutgoingUrls();

            System.out.println( "---------------------------------------------------------" );
            System.out.println( "Page URL: " + url );
            System.out.println( "Page Title: " + title );
            System.out.println( "Text length: " + text.length() );
            System.out.println( "Html length: " + html.length() );
            System.out.println( "Number of outgoing links: " + links.size() );
            System.out.println( "---------------------------------------------------------" );

            System.out.println( "---------------------------------------------------------" );
            System.out.println( "Page URL: " + url );
            System.out.println( "Page Title: " + title );
            System.out.println( "Text length: " + text.length() );
            System.out.println( "Html length: " + html.length() );
            System.out.println( "Number of outgoing links: " + links.size() );
            System.out.println( "---------------------------------------------------------" );

            docTitles.add( title );
        }
    }
}