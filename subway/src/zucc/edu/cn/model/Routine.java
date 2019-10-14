package zucc.edu.cn.model;

import java.util.ArrayList;
import java.util.List;

public class Routine {
    private Station star;//起点
    private Station end;//终点
    private int distance;
    private List<Station> path=new ArrayList<>();
    public Station getStar() {
        return star;
    }

    public void setStar(Station star) {
        this.star = star;
    }

    public Station getEnd() {
        return end;
    }

    public void setEnd(Station end) {
        this.end = end;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public List<Station> getPath() {
        return path;
    }

    public void setPath(List<Station> path) {
        this.path = path;
    }
    public Routine(Station star,Station end,int distance){
        this.star=star;
        this.end=end;
        this.distance=distance;
    }
    public Routine() {

    }

}
