import org.json.*;
import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.lang.System.*;

public class TocFinal
{
    public static String read(String urlStr) throws IOException 
    {  
        //URL url = new URL(urlStr);
        //URLConnection connection = url.openConnection();
        //connection.setDoInput(true);
        //InputStream inStream = connection.getInputStream();
        
        //BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
        BufferedReader in = new BufferedReader(new FileReader("5386c065e7259bb37d9270e5"));

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
        long time1, time2, time3;
        int com = 2;
        Map<String, Integer> pair = new HashMap<String, Integer>();

        String text = new String(Files.readAllBytes(Paths.get("5386c065e7259bb37d9270e5")), StandardCharsets.UTF_8);
        JSONArray ary = new JSONArray(text);

        int jlength = ary.getJSONObject(0).length();

        /*Iterator keys = ary.getJSONObject(0).keys();

        while(keys.hasNext())
        {
            String key = (String)keys.next();
            System.out.println(key + " " + ary.getJSONObject(0).get(key).toString());
        }*/

        JSONObject jobj;

        for(int i = 0; i < ary.length(); i++)
        {
            jobj = ary.getJSONObject(i);

            Iterator<String> key1 = ary.getJSONObject(0).keys();
            Iterator<String> key2 = ary.getJSONObject(0).keys();
            String skey1, skey2, allkey;
            int j = 0;

            while(key1.hasNext())
            {
                key2 = ary.getJSONObject(0).keys();
                //key2 = key1;
                skey1 = (String)key1.next();
                skey1 += ":" + jobj.get(skey1);
                for(int k = 0; k <= j; k++)
                    key2.next();
                j++;

                while(key2.hasNext()) 
                {
                    skey2 = key2.next();
                    skey2 += ":" + jobj.get(skey2);
                    allkey = skey1 + "@" + skey2;
                    //System.out.println(skey1 + " " + skey2);

                    if (!pair.containsKey(allkey)) 
                    {
                        pair.put(allkey, 1);
                    }
                    else
                    {
                        pair.put(allkey, pair.get(allkey) + 1);
                    }
                }
            }
        }


        List<Map.Entry<String, Integer>> list_Data = new ArrayList<Map.Entry<String, Integer>>(pair.entrySet());

        Collections.sort(list_Data, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2)
            {
                return (entry2.getValue() - entry1.getValue());
            }
        });

        int i = 0;
        for (Map.Entry<String, Integer> entry : list_Data) 
        {
            if (i++ == 3) break;
            System.out.println(entry.getKey() + "::::" + entry.getValue());
        }

            //System.out.println(list_Data);

            /*for (String key : pair.keySet()) 
                System.out.println(key + ":::" + pair.get(key));*/


    }
}
