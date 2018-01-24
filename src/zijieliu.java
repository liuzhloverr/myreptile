/**
 * Created by liuzh on 2018/1/18.
 */
import java.io.*;
public class zijieliu {
        public static void main(String args[])throws IOException{

            DataInputStream in = new DataInputStream(new FileInputStream("/Users/liuzh/Desktop/reptile/output/data/timelist.txt"));
            DataOutputStream out = new DataOutputStream(new  FileOutputStream("/Users/liuzh/Desktop/reptile/output/data/trantimelist.txt"));
            BufferedReader d  = new BufferedReader(new InputStreamReader(in));

            String count;
            while((count = d.readLine()) != null){
                //String u = count.toString();
                //long u = Long.valueOf(count.toString());
                //System.out.println(u);
                out.writeBytes(count);
            }
            d.close();
            out.close();
        }
    }

