import java.io.*;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.atomic.DoubleAccumulator;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.commons.io.input.ReaderInputStream;

/**
 * Created by liuzh on 2018/1/18.
 */
public class zhuanerjinzhi {
    public static void main(String[] args) throws IOException {
        //转换时间列表
        String filetimePath = "/Users/liuzh/Desktop/reptile/output/data/timelist.txt";
        File file = new File(filetimePath);
        FileInputStream inStream = new FileInputStream(file);
        Reader reader = new InputStreamReader(inStream);
        BufferedReader buff = new BufferedReader(reader);//创建缓冲区
        String len = null;
        File f = new File("/Users/liuzh/Desktop/reptile/output/data/trantimelist.txt");
        FileOutputStream out=new FileOutputStream(f);//如果文件不存在会自动创建
        DataOutputStream timeout = new DataOutputStream(out);
        while ((len = buff.readLine()) != null) {
            try {
                String a = len;
                int aa = Integer.parseInt(a);//String 转成 int
                //timeout.writeInt(aa);
                }
            catch (NumberFormatException e) {}
        }
        out.close();
//==========================================================================================
        //价格转换
        String filepricePath = "/Users/liuzh/Desktop/reptile/output/data/pricelist.txt";

        File file1 = new File(filepricePath);
        FileInputStream inStream1 = new FileInputStream(file1);
        Reader reader1 = new InputStreamReader(inStream1);
        BufferedReader buff1 = new BufferedReader(reader1);//创建缓冲区
        String len1 = null;
        File f1 = new File("/Users/liuzh/Desktop/reptile/output/data/tranpricelist.txt");
        FileOutputStream out1 = new FileOutputStream(f1);//如果文件不存在会自动创建
        DataOutputStream out11 = new DataOutputStream(out1);
        while ((len1 = buff1.readLine()) != null)
        {
                String a1 = len1;
                float aa1 = Float.parseFloat(a1.trim());//直接存数值
                //out11.writeFloat(aa1);
        }
        out11.close();
//=============================================================================================
//       转换numlist
        String filetnumPath = "/Users/liuzh/Desktop/reptile/output/data/numlist.txt";
        File filenum = new File(filetnumPath);  //打开文件
        FileInputStream inStreamnum = new FileInputStream(filenum);
        Reader readernum = new InputStreamReader(inStreamnum);
        BufferedReader buffnum = new BufferedReader(readernum);//创建缓冲区
        String len2 = null;
        File fnum2 = new File("/Users/liuzh/Desktop/reptile/output/data/trannumlist.txt");
        FileOutputStream outnum2 = new FileOutputStream(fnum2);//如果文件不存在会自动创建
        DataOutputStream outnum22 = new DataOutputStream(outnum2);
        while ((len2 = buffnum.readLine()) != null)
        {
            String a2 = len2;
            int aa2 = Integer.parseInt(a2.trim());//String 转成long int
            //outnum22.writeInt(aa2);
        }
        outnum2.close();
        //===============================================================================
        //转换codelist
        String filecodePath = "/Users/liuzh/Desktop/reptile/output/data/codelist.txt";
        File filecode = new File(filecodePath);
        FileInputStream inStreamcode = new FileInputStream(filecode);
        Reader readercode = new InputStreamReader(inStreamcode);
        BufferedReader buffcode = new BufferedReader(readercode);
        String lencode = null;
        File fcode = new File("/Users/liuzh/Desktop/reptile/output/data/trancodelist.txt");
        FileOutputStream outcode = new FileOutputStream(fcode);
        DataOutputStream outcode1 = new DataOutputStream(outcode);
        while((lencode = buffcode.readLine()) != null)
        {
            String acode = lencode;
            byte[] contentInBytes = acode.getBytes();
            //outcode.write(contentInBytes);
        }
        outcode.close();
        //===============================================================================
        //转换sumpricelist
        String filesumpricePath = "/Users/liuzh/Desktop/reptile/output/data/sumprice.txt";
        File filesumprice = new File(filesumpricePath);
        FileInputStream inStreamsumprice = new FileInputStream(filesumprice);
        Reader readersumprice = new InputStreamReader(inStreamsumprice);
        BufferedReader buffsumprice = new BufferedReader(readersumprice);
        String lensumprice = null;
        File fsumprice = new File("/Users/liuzh/Desktop/reptile/output/data/transumprice.txt");
        FileOutputStream outsumprice = new FileOutputStream(fsumprice);
        DataOutputStream outsumprice1 = new DataOutputStream(outsumprice);
        while((lensumprice = buffsumprice.readLine()) != null)
        {
            String asumprice = lensumprice;
            //byte[] contentInBytes = asumprice.getBytes();
            int asumprice2 = Integer.parseInt(asumprice.trim());
            outsumprice1.writeInt(asumprice2);
        }
        outsumprice1.close();

        //===============================================================================
        //转换sumpricelist
        String filefeaturePath = "/Users/liuzh/Desktop/reptile/output/data/features.txt";
        File filefeature = new File(filefeaturePath);
        FileInputStream inStreamfeature = new FileInputStream(filefeature);
        Reader readerfeature = new InputStreamReader(inStreamfeature);
        BufferedReader bufffeature = new BufferedReader(readerfeature);
        String lenfeature = null;
        File ffeature = new File("/Users/liuzh/Desktop/reptile/output/data/tranfeatures.txt");
        FileOutputStream outfeature = new FileOutputStream(ffeature);
        //DataOutputStream outfeature1 = new DataOutputStream(outfeature);
        while((lenfeature = bufffeature.readLine()) != null)
        {
            String afeature = lenfeature;
            byte[] contentInBytes = afeature.getBytes();
            outfeature.write(contentInBytes);
        }
        outfeature.close();
    }

}