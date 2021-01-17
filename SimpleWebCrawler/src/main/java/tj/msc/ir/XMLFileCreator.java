package tj.msc.ir;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XMLFileCreator
{

    public static final String xmlFilePath = "D:\\MSc\\Semester 3\\Information Retrieval\\A2\\CS5615-IR-A2-Minimalistic-IR-System\\SimpleWebCrawler\\infile";

    public static void main( String argv[] )
    {
        ArrayList<String> testDocTitles = new ArrayList();
        testDocTitles.add( "Hello" );
        testDocTitles.add( null );
        testDocTitles.add( "is doc" );
        testDocTitles.add( "is doc" );
        testDocTitles.add( "list" );

        writeCrawledInfoToXml( testDocTitles );
    }

    public static void writeCrawledInfoToXml( ArrayList<String> list )
    {
        if( list != null || !list.isEmpty() )
        {
            try
            {
                Document xml = XMLFileCreator.createXMLDocument();

                // root element
                Element root = xml.createElement( "COVER" );
                xml.appendChild( root );

                int docNo = 0;
                for( String docTitle : list )
                {
                    if( ( docTitle != null ) && !docTitle.equals( "" ) )
                    {
                        // elDoc element
                        Element elDoc = xml.createElement( "DOC" );

                        // elDocNo element
                        Element elDocNo = xml.createElement( "DOCNO" );
                        elDocNo.appendChild( xml.createTextNode( ( ++docNo ) + "" ) );
                        elDoc.appendChild( elDocNo );

                        elDoc.appendChild( xml.createTextNode( docTitle ) );
                        root.appendChild( elDoc );
                    }
                }

                XMLFileCreator.saveXMLDocument( xml );
            }
            catch( ParserConfigurationException pce )
            {
                pce.printStackTrace();
            }
            catch( TransformerException tfe )
            {
                tfe.printStackTrace();
            }
        }
    }

    private static Document createXMLDocument() throws ParserConfigurationException
    {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        //        return documentBuilder.parse( "infile.xml" );
        return documentBuilder.newDocument();
    }

    private static void saveXMLDocument( Document xml ) throws TransformerException
    {
        // create the xml file
        // transform the DOM Object to an XML File
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty( OutputKeys.INDENT, "yes" );
        DOMSource domSource = new DOMSource( xml );
        StreamResult streamResult = new StreamResult( new File( xmlFilePath + System.currentTimeMillis() + ".xml" ) );

        // If you use
        // StreamResult result = new StreamResult(System.out);
        // the output will be pushed to the standard output ...
        // You can use that for debugging
        transformer.transform( domSource, streamResult );
    }
}