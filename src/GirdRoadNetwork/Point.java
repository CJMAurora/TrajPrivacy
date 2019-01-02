package GirdRoadNetwork;

public class Point {
    double xCoor;
    double yCoor;
    public Point(){}
    public Point(double xCoor,double yCoor){
        this.xCoor=xCoor;
        this.yCoor=yCoor;
    }
    public Point getPoint(){
        Point p=new Point(xCoor,yCoor);
        return p;
    }

    public double getxCoor() {
        return xCoor;
    }

    public double getyCoor() {
        return yCoor;
    }
    public Boolean isEqual(Point p1){
        if((p1.xCoor==xCoor)&&(p1.yCoor==yCoor))
            return true;
        return false;
    }
    public double distance(Point p1){
        double dis=Math.sqrt(Math.pow((p1.xCoor-xCoor),2)+Math.pow((p1.yCoor-yCoor),2));
        return dis;
    }
}
