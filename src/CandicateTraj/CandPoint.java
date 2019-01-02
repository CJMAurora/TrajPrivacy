package CandicateTraj;

import GirdRoadNetwork.Point;

import java.util.ArrayList;

public class CandPoint {
    Lv lv;
    ArrayList<Point> pointList;
    public CandPoint(){
        lv=null;
        pointList=new ArrayList<>();
    }
    public CandPoint(Lv lv,ArrayList<Point> pointList){
        this.lv=lv;
        this.pointList=pointList;
    }
}
