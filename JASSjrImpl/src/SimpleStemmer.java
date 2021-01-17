/**
 * 209338R - KATS JAYATHILAKA
 */

import java.util.ArrayList;

public class SimpleStemmer
{
    String result = "";

    public void stem()
    {
        step1a();
        step1b();
        /**
         * More steps can go here.
         */
    }

    /**
     * Step 1a :
     *
     * SSES -> SS (Example : caresses -> caress)
     * IES -> I (Example : ponies -> poni ; ties -> ti)
     * SS -> SS (Example : caress -> caress)
     * S -> (Example : cats -> cat)
     *
     * https://iq.opengenus.org/porter-stemmer/
     */
    private void step1a()
    {
        StringBuilder sb = new StringBuilder();
        if( result.endsWith( "sses" ))
        {
            sb.append( result.substring( 0, result.length() - 4 ) );
            sb.append( "ss" );
        }
        else if( result.endsWith( "ies" ))
        {
            sb.append( result.substring( 0, result.length() - 3 ) );
            sb.append( "i" );
        }
        else if( result.endsWith( "ss" ))
        {
            sb.append( result.substring( 0, result.length() - 2 ) );
            sb.append( "ss" );
        }
        else if( result.endsWith( "s" ))
        {
            sb.append( result.substring( 0, result.length() - 1 ) );
        }
        result = sb.toString();
    }


    /**
     * Step 1b :
     *
     * (m>0) EED -> EE (Example : feed -> feed ; agreed -> agree)
     * (v) ED -> (Example : plastered -> plaster ; bled -> bled)
     * (v) ING -> (Example : motoring -> motor ; sing -> sing)
     * S -> (Example : cats -> cat)
     *
     * https://iq.opengenus.org/porter-stemmer/
     */
    private void step1b()
    {
        StringBuilder sb = new StringBuilder();
        if( result.endsWith( "eed" ) && result.length() > 3 )
        {
            sb.append( result.substring( 0, result.length() - 3 ) );
            sb.append( "ee" );
        }
        else if( isVerb( result.substring( 0, result.length() - 2 ) ) &&  result.endsWith( "ed" ) )
        {
            sb.append( result.substring( 0, result.length() - 2 ) );
        }
        else if( isVerb( result.substring( 0, result.length() - 3 ) ) && result.endsWith( "ing" ))
        {
            sb.append( result.substring( 0, result.length() - 3 ) );
        }
        else if( result.endsWith( "s" ))
        {
            sb.append( result.substring( 0, result.length() - 1 ) );
        }
        result = sb.toString();
    }

    private ArrayList<String> loadEnglishVerbs()
    {
        /**
         * This can be used to load all verbs in english from a file.
         */
        return new ArrayList();
    }

    private boolean isVerb( String word )
    {
        ArrayList<String> engVerbs = loadEnglishVerbs();
        return engVerbs.contains( word );
    }

    public void setToken( String token )
    {
        if( token != null )
        {
            this.result = token.toLowerCase();
        }
    }

    public String getResult()
    {
        return result;
    }
}
