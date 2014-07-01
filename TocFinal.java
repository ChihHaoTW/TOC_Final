import org.json.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class TocFinal
{
    public static String read(String urlStr) throws IOException 
    {  
        URL url = new URL(urlStr);
        URLConnection connection = url.openConnection();
        connection.setDoInput(true);
        InputStream inStream = connection.getInputStream();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
        String s;  
        StringBuilder sb = new StringBuilder();  

        while ((s = in.readLine()) != null)  
        {
            //URLEncoder.encode(s, "UTF-8");
            sb.append(s + "\n");
        }

        in.close();  
        return sb.toString();  
    }  

    public static void main(String args[]) throws IOException
    {
        JSONArray ary = new JSONArray(read(args[0]));

    }
}
