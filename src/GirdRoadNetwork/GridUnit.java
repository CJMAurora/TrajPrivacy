package GirdRoadNetwork;

import java.util.ArrayList;

public class GridUnit {
    private int xGrid;
    private int yGrid;
    private ArrayList<Point> PofG;
    public GridUnit(){
        xGrid=0;
        yGrid=0;
        PofG=new ArrayList<Point>();
    }
    public GridUnit(int xGrid,int yGrid){
        this.xGrid=xGrid;
        this.yGrid=yGrid;
        PofG=new ArrayList<Point>();
    }

    public void setPofG(Point p) {
        PofG.add(p);
    }
    public ArrayList<Point> getPofG(){
        return PofG;
    }
}
