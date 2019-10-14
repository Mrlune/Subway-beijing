package zucc.edu.cn.manager;
import zucc.edu.cn.model.Routine;
import zucc.edu.cn.model.Station;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
public class FileOperate {
    public static String Readfile;
    public static String Writefile;
    public static HashMap<String,List<Station>> Map=new HashMap<>();//存放地铁线路信息
    public  static HashSet<List<Station>> lineSet=new LinkedHashSet<>();//所有线路的集合
    public static void getFile() {

        File file=new File(Readfile);


        BufferedReader rd=null;
        /* 读取数据 */
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file),"UTF-8");
            rd=new BufferedReader(inputStreamReader);
            String lineTxt = null;
            while ((lineTxt = rd.readLine()) != null) {
                String[] s = lineTxt.split(" ");//创建一个临时存放数据的数组
                List<Station>  Stations = new ArrayList<>();
                for(int i = 1;i<s.length;i++){     //将读入的站点并加入到对应线路的List中
                    Station station = new Station(s[i],s[0]);
                    Stations.add(station);
                }
                lineSet.add(Stations);
                Map.put(s[0],Stations);
            }
            rd.close();
        } catch (UnsupportedEncodingException e) {  //异常处理
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        }
    public static void loadLine(String Line){
        File file=new File(Writefile);
        List<Station> line;
        BufferedWriter bw = null;
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
            bw = new BufferedWriter(outputStreamWriter);
            line=Map.get(Line);
            bw.write(Line+"\n");
            System.out.println("--------");
            System.out.println("--------");

            for(int i=0;i<line.size();i++)
                    bw.write(line.get(i).getName()+"\r\n");//写入指定路线的站点信息
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static  String getLineName(Station station){//根据站点名称得到线路名称
        String sName=station.getName();
        for(Map.Entry<String,List<Station>>entry:Map.entrySet()){
            List<Station> stations=entry.getValue();
            for(Station s:stations){
                if(s.getName().equals(sName))
                    return entry.getKey();
            }
        }
        return  null;
    }

    public static void shorestPath(String star,String end){
        File file = new File(Writefile);
        BufferedWriter bw = null;

        try{
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
            bw = new BufferedWriter(outputStreamWriter);

            Routine routine=Dijkstra.dijkstra(new Station(star),new Station(end));

            bw.write((routine.getPath().size()+1)+"\r\n");
            String lineName=getLineName(routine.getStar());
            bw.write(lineName+"\n");
            bw.write(routine.getStar().getName()+"\n");
            for(int i=0;i<routine.getPath().size()-1;i++) {
                bw.write(routine.getPath().get(i).getName()+"\n");
                if(!routine.getPath().get(i+1).getLine().equals(routine.getPath().get(i).getLine())){
                    bw.write("---需换乘"+routine.getPath().get(i+1).getLine()+"---\n");
                }
            }
            bw.write(routine.getEnd().getName()+"\n");
            bw.close();

        }catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}