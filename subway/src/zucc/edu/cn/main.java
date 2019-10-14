package zucc.edu.cn;


import zucc.edu.cn.manager.Dijkstra;
import zucc.edu.cn.manager.FileOperate;
import zucc.edu.cn.model.Routine;
import zucc.edu.cn.model.Station;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.io.File;
import java.io.UnsupportedEncodingException;

public class main {
    //    public static void main(String[] args) throws IOException {
//        if(args.length==2&&args[0].equals("-map"))
//            Subway.Data(args[1]);
//        else if(args.length==6&&args[0].equals("-a")&&args[2].equals("-map")&&args[4].equals("-o")) {
//            Subway.Data(args[3]);
//            writeRoute(args[1],args[5]);//写入station.txt
//        }
//        else if(args.length==7&&args[0].equals("-b")&&args[3].equals("-map")&&args[5].equals("-o")){
//            Subway.Data(args[4]);
//            Result(args[1],args[2],args[6]);
//            //写入routine.txt
//        }
//        else {
//            System.out.println("命令不正确");
//        }
//    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        // write your code here
//        String str=args[1];
//        String str1=URLDecoder.decode(str,"UTF-8");
//        System.out.println(str1);
//        String str2=convertEncodingFormat(str1,"iso-8859-1","UTF-8");
//        System.out.println(str2);
//        for(String s:args)
//
//            System.out.println(s);
        switch (args[0]) {
            //读入subway.txt文件数据
            case "-map":
                //-map subway.txt
                if (args.length == 2) {
                    FileOperate.Readfile = System.getProperty("user.dir") + File.separator + args[1];
                    //根据路径，读取地铁信息，并打印。
                    FileOperate.getFile();
                    System.out.println("成功读取subway.txt文件");
                } else {
                    System.out.println("验证参数格式！");
                }
                break;
            //查询指定线路的文件数据，并写入到station.txt文件中
            case "-a":
                //-a 1号线 -map subway.txt -o station.txt

                if (args.length == 6) {
                    FileOperate.Readfile = System.getProperty("user.dir") + File.separator + args[3];
                    FileOperate.Writefile = System.getProperty("user.dir") + File.separator + args[5];

                    FileOperate.getFile();
                    System.out.println(args[1]);
                    if (FileOperate.Map.keySet().contains(args[1])) {
                        FileOperate.loadLine(args[1]);
                        System.out.println("已将地铁" + args[1] + "的各站点信息写入station.txt文件");
                    } else {
                        System.out.println("线路不存在");
                    }
                } else {

                    System.out.println("验证参数格式！");
                }
                break;
            //查询指定站点之间的最短路径信息
            case "-b":
                //-b 天安门西 北京大学东门 -map subway.txt -o routine.txt
                System.out.println(1);
                if (args.length == 7) {
                    FileOperate.Readfile = System.getProperty("user.dir") + File.separator + args[4];
                    FileOperate.Writefile = System.getProperty("user.dir") + File.separator + args[6];
                    FileOperate.getFile();
                    FileOperate.shorestPath(args[1],args[2]);
                    System.out.println("已将" + args[1] + "到" + args[2] + "最短路径的结果写入routine. txt文件");

                } else {
                    System.out.println("验证参数格式！");
                }
                break;
            default:
                System.out.println("验证参数格式！");
        }
    }
}

