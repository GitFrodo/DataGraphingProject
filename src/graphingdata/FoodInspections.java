import java.net.*;
import java.util.*;
import com.google.gson.*;

public class FoodInspections {
    public static void main(String[] args) {
        String s = "http://apps.who.int/gho/athena/data/GHO/WHS4_544.json?profile=simple&filter=YEAR:1980";
        URL url = null;
        try {
            url = new URL(s);
        } catch (Exception e) {
            System.out.println("Improper URL " + s);
            System.exit(-1);
        }
     
        // read from the URL
        Scanner scan = null;
        try {
            scan = new Scanner(url.openStream());
        } catch (Exception e) {
            System.out.println("Could not connect to " + s);
            System.exit(-1);
        }
        
        String str = new String();
        while (scan.hasNext()) {
            str += scan.nextLine() + "\n";
        }
        scan.close();

        Gson gson = new Gson();
        PolioDataset pds = gson.fromJson(str, PolioDataset.class);
        PolioDataPoint[] pdp = pds.getDataSet();
        
        for (PolioDataPoint dp : pdp) {
            System.out.println(dp);
        }
    }
}
