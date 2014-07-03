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
        URL url = new URL(urlStr);
        URLConnection connection = url.openConnection();
        connection.setDoInput(true);
        InputStream inStream = connection.getInputStream();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
        //BufferedReader in = new BufferedReader(new FileReader("5386c065e7259bb37d9270e5"));

        String s;  
        StringBuilder sb = new StringBuilder();  

        while ((s = in.readLine()) != null)  
        {
            //URLEncoder.encode(s, "UTF-8");
            sb.append(s);
            //System.out.println(s);
        }

        in.close();  
        return sb.toString();  
    }  

    public static void main(String args[]) throws IOException
    {
        //long time1, time2, time3;
        //int com = 2;
        Map<String, Integer> pair = new HashMap<String, Integer>();

        String text = read("http://www.datagarage.io/api/5386c065e7259bb37d9270e5");

        JSONArray ary = new JSONArray(text);

        int jlength = ary.getJSONObject(0).length();

        String[] key_order = new String[jlength];

        int first = text.indexOf('{');
        int last = text.indexOf('}');

        int it = 0;
        //System.out.println(text.substring(first + 2, text.indexOf(':') - 1));
        key_order[it++] = text.substring(first + 2, text.indexOf(':') - 1);
        for (int cur = text.indexOf(',', first); cur <= last; ) 
        {
            //System.out.println(text.substring(cur + 2, text.indexOf(':', cur) - 1));
            key_order[it++] = text.substring(cur + 2, text.indexOf(':', cur) - 1);
            cur = text.indexOf(',', cur + 1);
        }

        /*Iterator keys = ary.getJSONObject(0).keys();

        while(keys.hasNext())
        {
            String key = (String)keys.next();
            System.out.println(key + " " + ary.getJSONObject(0).get(key).toString());
        }*/

        JSONObject jobj;
        Iterator<String> key1, key2, key3, key4;
        String skey1, skey2, skey3, skey4, allkey = "";

        int L = 4, K = 2;

        for(int i = 0; i < ary.length(); i++)
        {
            jobj = ary.getJSONObject(i);

            switch(L)
            {
                case 2:
                    key1 = jobj.keys();
                    
                    int j = 0;

                    while(key1.hasNext())
                    {
                        key2 = jobj.keys();
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
                            //System.out.println(allkey);

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
                    break;

                case 3:
                    key1 = jobj.keys();

                    int jj = 0, l = 0, n = 0;

                    while(key1.hasNext())
                    {
                        key2 = jobj.keys();
                        
                        //key2 = key1;
                        skey1 = (String)key1.next();
                        skey1 += ":" + jobj.get(skey1);
                        for(int k = 0; k <= jj; k++)
                            key2.next();

                        jj++;
                        n = 0;

                        while(key2.hasNext()) 
                        {
                            key3 = jobj.keys();

                            skey2 = key2.next();
                            skey2 += ":" + jobj.get(skey2);
                            //allkey = skey1 + "@" + skey2;
                            //System.out.println(skey1 + " " + skey2);
                            for(int k = 0; k <= n + jj; k++)
                                key3.next();
                            n++;

                            while(key3.hasNext()) 
                            {
                                skey3 = key3.next();
                                skey3 += ":" + jobj.get(skey3);
                                allkey = skey1 + "@" + skey2 + "@" + skey3;

                                //System.out.println(allkey);

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
                    break;

                case 4:
                    key1 = jobj.keys();

                    int jjj = 0, ll = 0, nn = 0, mm = 0;

                    while(key1.hasNext())
                    {
                        key2 = jobj.keys();
                        
                        //key2 = key1;
                        skey1 = (String)key1.next();
                        skey1 += ":" + jobj.get(skey1);
                        for(int k = 0; k <= jjj; k++)
                            key2.next();

                        jjj++;
                        nn = 0;

                        while(key2.hasNext()) 
                        {
                            key3 = jobj.keys();

                            skey2 = key2.next();
                            skey2 += ":" + jobj.get(skey2);
                            //allkey = skey1 + "@" + skey2;
                            //System.out.println(skey1 + " " + skey2);
                            for(int k = 0; k <= nn + jjj; k++)
                                key3.next();
                            nn++;
                            mm = 0;

                            while(key3.hasNext()) 
                            {
                                key4 = jobj.keys();

                                skey3 = key3.next();
                                skey3 += ":" + jobj.get(skey3);
                                //allkey = skey1 + "@" + skey2 + "@" + skey3;
                                //System.out.println(allkey);
                                for(int k = 0; k <= nn + jjj + mm; k++)
                                    key4.next();
                                mm++;

                                while(key4.hasNext()) 
                                {
                                    skey4 = key4.next();
                                    skey4 += ":" + jobj.get(skey4);
                                    allkey = skey1 + "@" + skey2 + "@" + skey3 + "@" + skey4;

                                    //System.out.println(allkey);

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
                    }
                    break;   

                default:
                   break;                  

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

        int i = 0, index;
        String sort_allkey;
        FileWriter fw = new FileWriter("output.txt");
        for (Map.Entry<String, Integer> entry : list_Data) 
        {
            if (i++ == 3) break;
            sort_allkey = "";
            allkey = entry.getKey();

            for (int ii = 0; ii < jlength; ii++)
            {
                if (allkey.contains(key_order[ii]))
                {
                    index = allkey.indexOf(key_order[ii]);
                    if(allkey.indexOf("@", index) == -1)
                        //System.out.println(allkey.substring(index));
                        sort_allkey += allkey.substring(index) + ",";
                    else
                        //System.out.println(allkey.substring(index, allkey.indexOf("@", index)));
                        sort_allkey += allkey.substring(index, allkey.indexOf("@", index)) + ",";
                }
                else
                    continue;
            }

            sort_allkey = sort_allkey.substring(0, sort_allkey.length()-1) + ";" + entry.getValue();
            //System.out.println(sort_allkey);
            fw.write(sort_allkey + '\n');
        }

        fw.flush();
        fw.close();

            //System.out.println(list_Data);

            /*for (String key : pair.keySet()) 
                System.out.println(key + ":::" + pair.get(key));*/


    }
}
