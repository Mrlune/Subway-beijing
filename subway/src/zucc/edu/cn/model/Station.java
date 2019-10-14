package zucc.edu.cn.model;

import java.util.ArrayList;
import java.util.List;

public class Station {

    private String name;//站点名字

    private String line;//所属路线

    private List<Station> linkStations = new ArrayList<>();//相邻连接站点

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getLine() {

        return line;
    }

    public void setLine(String line) {

        this.line = line;
    }
    public List<Station> getLinkStations() {
        return linkStations;
    }
    public void setLinkStations(List<Station> linkStations) {

        this.linkStations = linkStations;
    }

    public Station(String name, String line) {
        this.name = name;
        this.line = line;
    }


    public Station(String name) {

        this.name = name;
    }
    public boolean equals(Object obj) {
        if(this==obj)
            return true;
        else if(obj instanceof Station) {
            Station s = (Station) obj;
            if(s.getName().equals(this.getName())){
                return true;
            } else {
                return false;
            }
        }else {
            return false;
        }
    }

    public Station (){

    }


}
