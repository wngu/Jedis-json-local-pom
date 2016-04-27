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

public class TestJedis {
  public static void main(String[] args) {
    //Connecting to Redis on localhost
    Jedis jedis = new Jedis("localhost", 22121);
  //  Jedis jedis = new Jedis("localhost");
    //adding a new key
      System.out.println(jedis.hcdsetproxy("key777", "myvalue55555"));
    //getting the key value
    System.out.println(jedis.hcdgetproxy("key456"));

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

    //testing redis cmd: ping, info, set, get,
    //                hcdsetproxy, hcdgetproxy
    System.out.println(jedis.ping());
//    System.out.println(jedis.info());
    jedis.set("keys1", obj.toString());
    System.out.println(jedis.hcdsetproxy("keyhcd", obj.toString()));
    System.out.println("from redis server: "+jedis.get("keys1"));
  }
}
