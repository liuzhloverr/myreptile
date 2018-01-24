/**
 * Created by liuzh on 2018/1/16.
 */

import java.io.*;
import java.net.URL;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.text.SimpleDateFormat;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FalseFileFilter;
public class reptile {
    public static void main(String[] args) throws IOException, InterruptedException {

        long startTime=System.currentTimeMillis();   //获取开始时间


        //=======================================================================

        String codefilePath = "/Users/liuzh/Desktop/reptile/output/codelist.txt";
        File codefile = new File(codefilePath);
        FileInputStream incodeStream = new FileInputStream(codefile);
        Reader codereader = new InputStreamReader(incodeStream);
        BufferedReader codebuff = new BufferedReader(codereader);//创建缓冲区
        //codebuff输出的是股票编号

//        /*** 错误链接的输出路径*/
//        String errorPath = "/Users/liuzh/Desktop/reptile/output/errdata/" + System.currentTimeMillis() + "error.txt";
//        FileOutputStream outStream = new FileOutputStream(errorPath);
//        Writer writer = new OutputStreamWriter(outStream);
//        BufferedWriter buffwriter = new BufferedWriter(writer);
        String codelen = null;
        String len = null;

        while ((codelen = codebuff.readLine()) != null) {

            String filePath = "/Users/liuzh/Desktop/reptile/output/dataset.txt";
            File file = new File(filePath);//新建一个文件
            FileInputStream inStream = new FileInputStream(file);
            String outdir = "/Users/liuzh/Desktop/reptile/output/tmp/";
            /*** 输入流*/
            Reader reader = new InputStreamReader(inStream);
            BufferedReader buff = new BufferedReader(reader);//创建缓冲区

            System.out.print(codelen + "\n");

            //算法每下载（随机数30-50）次,会随机暂停500-3000毫秒
            java.util.Random random = new java.util.Random();// 定义随机类
            int result = random.nextInt(3);
            int ii = result + 1;

            while ((len = buff.readLine()) != null ) {     //读取下载链接文件 一次读一行
                //len = "http://market.finance.sina.com.cn/downxls.php?date=2010-01-01&symbol="
                String lenfin = len + codelen;
                //System.out.print(lenfin+"\n");

                if(ii>0)
                {
                    downloadFromUrl(lenfin, outdir);      //调用下载链接的函数
                    ii--;
                }
                //================================================================
                else
                {
                    downloadFromUrl(lenfin, outdir);      //调用下载链接的函数
                    try {
                        java.util.Random random1 = new java.util.Random();// 定义随机类
                        int result1 = random.nextInt(1000);
                        int sleepTime = result1 + 2000;
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {e.printStackTrace();
                    }
                }
                //================================================================
            }

//===============================================================================================
        }
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)/1000+"秒");
    }

    public static String downloadFromUrl(String url, String dir) {
        try {
            URL httpurl = new URL(url);
            String sname = url;
            String Ssname = sname.substring(sname.indexOf("=") + 1, sname.lastIndexOf("&"));
            String Sscode = sname.substring(sname.length() - 8, sname.length());//截取股票代码
            String fileName = Sscode + Ssname + ".txt";
            System.out.print(Ssname);
            System.out.println(fileName);
            File f = new File(dir + fileName);
            FileUtils.copyURLToFile(httpurl, f);  //把下载的文件写到文件夹

            //在这里解析文件
            FileInputStream inStream = null;
            inStream = new FileInputStream(dir + fileName); //把文件写入流
            //InputStreamReader read = new InputStreamReader(new FileInputStream(file), "gb2312");
            Reader reader = new InputStreamReader(inStream, "GB18030");
            BufferedReader buff1 = new BufferedReader(reader);//创建缓冲区
            String b;

            try {
                while ((b = buff1.readLine()) != null) {
                    if (isNumeric(b.substring(0, 1))) {                                 //判断首行是不是文字
                        //String a = b.substring(0, 2) + b.substring(3, 5) + b.substring(6, 8);//提取成交时间
                        //String price = b.substring(9, 15);                                   //价格
                        String date1 = Ssname.substring(0, 4) + Ssname.substring(5, 7) + Ssname.substring(8, 10);
                        //Ssname截取的是完整格式的日期例如:2010-01-01
                        String[] numprice = b.split("\t");
                        String numprice1 = numprice[1];
                        String[] num = b.split("\t");
                        String num3 = num[3];
                        String[] tnum = b.split("\t");
                        String num4 = tnum[4];
                        String[] feature = b.split("\t");
                        String ffeature = feature[5];
                        int ffeaturetran = 0;
                        switch (ffeature)
                        {
                            case "中性盘":
                            ffeaturetran = 0;
                                break;
                            case"买盘":
                            ffeaturetran = 1;
                                break;
                            case"卖盘":
                            ffeaturetran = 2;
                                break;
                        }
                        String scode = sname.substring(sname.length() - 8, sname.length());//截取股票代码
                        String scodetran = scode.substring(2,8);
                        File fout = new File("/Users/liuzh/Desktop/reptile/output/data/timelist.txt");
                        BufferedWriter output = new BufferedWriter(new FileWriter(fout, true));//true,则追加写入text文本
                        String aa = b.substring(0, 8);
                        String date2 = Ssname + " " + aa;//把时间组合成2010-01-01 14:00:00的格式
                        String date3 = dateToStamp(date2);//转换时间戳
                        String date4 = date3.substring(0, 10);//时间戳后三位全部是0,所以省略掉了
                        //System.out.print(date4 + "\n");
                        output.write(date4 + "\n"); //10个字节 10位数可用一个int表示
                        output.flush();
                        output.close();
                        //=======================================
                        File fprice = new File("/Users/liuzh/Desktop/reptile/output/data/pricelist.txt");
                        BufferedWriter priceout = new BufferedWriter(new FileWriter(fprice, true));//true,则追加写入text文本
                        priceout.write(numprice1 + "\n");
                        priceout.flush();
                        priceout.close();
                        //=======================================
                        File fnum = new File("/Users/liuzh/Desktop/reptile/output/data/numlist.txt");
                        BufferedWriter numout = new BufferedWriter(new FileWriter(fnum, true));//true,则追加写入text文本
                        numout.write(num3 + "\n");
                        numout.flush();
                        numout.close();
                        //=======================================
                        File fcode = new File("/Users/liuzh/Desktop/reptile/output/data/codelist.txt");
                        BufferedWriter codeout = new BufferedWriter(new FileWriter(fcode, true));//true,则追加写入text文本
                        codeout.write(scodetran + "\n");
                        codeout.flush();
                        codeout.close();
                        //=======================================
                        File fsumprice = new File("/Users/liuzh/Desktop/reptile/output/data/sumprice.txt");
                        BufferedWriter sumpriceout = new BufferedWriter(new FileWriter(fsumprice, true));//true,则追加写入text文本
                        sumpriceout.write(num4 + "\n");
                        sumpriceout.flush();
                        sumpriceout.close();
                        //=======================================
                        File ffeatures = new File("/Users/liuzh/Desktop/reptile/output/data/features.txt");
                        BufferedWriter featureseout = new BufferedWriter(new FileWriter(ffeatures, true));//true,则追加写入text文本
                        //GB18030编码方式
                        featureseout.write(ffeaturetran + "\n");
                        featureseout.flush();
                        featureseout.close();

                        //删除文件
                        delFile("/Users/liuzh/Desktop/reptile/output/tmp", fileName);
                    }
                }

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (IOException e) {e.printStackTrace();
        } catch (Exception e) {e.printStackTrace();
        }
        return "Success";
    }

    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            //System.out.println(str.charAt(i));
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    //把时间转换成时间戳
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    public static void delFile(String path, String filename) {
        File file = new File(path + "/" + filename);
        if (file.exists() && file.isFile())
            file.delete();
    }
}
