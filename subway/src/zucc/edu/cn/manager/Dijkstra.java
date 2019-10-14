package zucc.edu.cn.manager;

import zucc.edu.cn.model.Routine;
import zucc.edu.cn.model.Station;
import zucc.edu.cn.manager.FileOperate;

import javax.sound.sampled.Line;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Dijkstra {
    public static HashMap<Station, Routine> resultMap = new HashMap<>();//结果集
    public static List<Station> list = new ArrayList<>();//分析过的站点


    public static Routine dijkstra(Station star, Station end) {

        if (!list.contains(star))
            list.add(star);
        if (star.equals(end)) {//如果起始点和终点相同，直接返回
            Routine result = new Routine();

            result.setDistance(0);
            result.setEnd(star);
            result.setStar(star);
            resultMap.put(star, result);
            return resultMap.get(star);
        }
        if (resultMap.isEmpty()) {
            List<Station> linkedStation = getLinkStations(star);
            for (Station station : linkedStation) {
                Routine routine = new Routine();
                routine.setStar(star);
                routine.setEnd(station);
                routine.setDistance(1);
                routine.getPath().add(station);
                resultMap.put(station, routine);
            }
        }
        Station next = getNextStation();
        if (next == null) {
            Routine routine = new Routine();
            routine.setDistance(0);
            routine.setStar(star);
            routine.setEnd(end);
            return resultMap.put(end, routine);
        }
        //如果得到的最佳邻接点与终点相同，则直接返回结果集
        if (next.equals(end))
            return resultMap.get(next);

        List<Station> bestlinkStation = getLinkStations(next);
        for (Station best : bestlinkStation) {
            if (list.contains(best))
                continue;
            int distance =0;

            if (next.getName().equals(best.getName()))
                distance = 0;
            distance = resultMap.get(next).getDistance() + 1;

            List<Station> nextPass = resultMap.get(next).getPath();
            Routine res = resultMap.get(best);
            if (res != null) {
                if (res.getDistance() > distance) {
                    res.setDistance(distance);
                    res.getPath().clear();
                    res.getPath().addAll(nextPass);
                    res.getPath().add(best);
                }
            } else {
                res = new Routine();
                res.setDistance(distance);
                res.setStar(star);
                res.setEnd(best);
                res.getPath().addAll(nextPass);
                res.getPath().add(best);
            }
            resultMap.put(best, res);
        }
        list.add(next);


        return dijkstra(star, end);

    }

    //通过计算最小权值 计算下一个需要分析的点
    private static Station getNextStation() {
        int min = 1000;
        Station rets = null;
        Set<Station> stations = resultMap.keySet();
        for (Station station : stations) {
            if (list.contains(station)) {
                continue;
            }
            Routine result = resultMap.get(station);
            if (result.getDistance() < min) {
                min = result.getDistance();
                rets = result.getEnd();
            }
        }
        return rets;
    }


    private static List<Station> getLinkStations(Station station) {
        List<Station> linkedStaions = new ArrayList<Station>();
        for (List<Station> line : FileOperate.lineSet) {
            for (int i = 0; i < line.size(); i++) {
                if (station.equals(line.get(i))) {
                    if (i == 0) {
                        linkedStaions.add(line.get(i + 1));
                    } else if (i == (line.size() - 1)) {
                        linkedStaions.add(line.get(i - 1));
                    } else {
                        linkedStaions.add(line.get(i + 1));
                        linkedStaions.add(line.get(i - 1));
                    }
                }
            }
        }
        return linkedStaions;


    }
    public static void getResult(String file) throws IOException {
        BufferedWriter b = null;
        b = new BufferedWriter(new FileWriter(file, true));
        Set<List<Station>> lineSet = FileOperate.lineSet;
        for (List<Station> station1 : lineSet) {
            for (Station station : station1) {
                for (List<Station> station2 : lineSet) {
                    for (Station t : station2) {
                        Dijkstra dijkstra = new Dijkstra();
                        Routine result = dijkstra.dijkstra(station, t);
                        resultMap = new HashMap<>();
                        list = new ArrayList<>();
                        for (Station s : result.getPath()) {
                            if (s.getName().equals(t.getName())) {
                                String f1 = station.getName() + "\t" + t.getName() + "\t"
                                        + result.getPath().size() + "\t" + result.getDistance() + "\t";
                                for (Station f : result.getPath()) {
                                    f1 = f1 + f.getName() + ",";
                                }
                                b.write(f1);
                                b.newLine();
                            }
                        }
                    }
                }
            }
        }
        b.flush();
        b.close();
    }
}