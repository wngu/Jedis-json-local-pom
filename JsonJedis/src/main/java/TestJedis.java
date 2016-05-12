import redis.clients.jedis.Jedis;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.io.FileWriter;
import java.io.BufferedReader;

public class TestJedis {
    public static String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

  public static void main(String[] args) throws IOException {
    //Connecting to Redis on localhost
    Jedis jedis = new Jedis("xps4", 22121);
  //  Jedis jedis = new Jedis("localhost");
    //adding a new key
      //System.out.println(jedis.hcdsetproxy("key777", "myvalue55555"));
    //getting the key value
    //System.out.println(jedis.hcdgetproxy("key456"));

    //generating a json object
    JSONObject obj = new JSONObject();
    obj.put("name", "mystuff.com");
    obj.put("age", new Integer(100));
    JSONArray list = new JSONArray();
    list.add("msg 1");
    list.add("msg 2");
    list.add("msg 6");
    list.add("msg 7");
    obj.put("messages", list);
    System.out.println(obj.toString());
    System.out.println(obj.toJSONString());

    //saving json object to a file "test.json"
    try {
      FileWriter file = new FileWriter("test.json");
      file.write(obj.toJSONString());
      file.flush();
      file.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
      //reading json object from a file
     // String buf = readFile("confJSON");
      String buf = readFile("confHE");
      System.out.println(buf);

      JSONParser parser = new JSONParser();
      try {
          System.out.println(jedis.hcdsetproxy("test", parser.parse(buf).toString()));
      }
      catch (  Exception e) {
          throw new AssertionError("not a valid JSON object");
      }



 //   jedis.hcdsetproxy("test", buf);
    //testing redis cmd: ping, info, set, get,
    //                hcdsetproxy, hcdgetproxy
//    System.out.println(jedis.ping());
//    System.out.println(jedis.info());
//    jedis.set("keys1", obj.toString());
//    System.out.println(jedis.hcdsetproxy("keyhcd", obj.toString()));
//    System.out.println("from redis server: "+jedis.get("keys1"));

      try {
          jedis.quit();
      } catch (Exception e) {
          // ignore the exception node, so that all other normal nodes can release all connections.
      }

  }
}
