package GirdRoadNetwork;

import java.io.File;
import java.util.ArrayList;

public class testGridGroup {
    public static void main(String[] args) throws Exception {
//        double dx=0.1;//每个网格的横向增量
//        double dy=0.1;
//        double xStartAll=-124.389343-dx/2;
//        double yStartAll=32.541302-dy/2;//总范围起始点的纵坐标
//        double xEndAll=-114.294258+dx/2;//总范围结束点的横坐标
//        double yEndAll=42.017231+dy/2;//总范围结束点的纵坐标
//
//        GridGroup gg=new GridGroup(xStartAll,yStartAll,xEndAll,yEndAll,dx,dy);
//        String path="file/node.txt";
//        File file=new File(path);//路径名
//        ArrayList<GridUnit> gu= gg.creatGridGroup(file);
//        int sum=0;
//        for(int i=0;i<gu.size();i++){
//            System.out.println(gu.get(i).getPofG());
//           sum= sum+gu.get(i).getPofG().size();
//
//        }
//        System.out.println(dx/2);
//        System.out.println(sum);
//        QueryGrid qg=new QueryGrid();
//        Point p=new Point(-118.2696247101,34.0426141541);
//        ArrayList<Point> pQueryResultHash = new ArrayList<Point>();
//        Long startTime = System.nanoTime();
//        pQueryResultHash = qg.gridIndexQuery(gg,p,0.05);//进行有索引的点查询
//        Long endTime = System.nanoTime();
//        System.out.print("哈希索引下，查询结果中点的总数是：");
//        System.out.println(pQueryResultHash.size());
//        System.out.print("在以哈希的方式建立建立索引的情况下，范围查询的时间：");
//        System.out.println(endTime-startTime);

       // System.out.println(gu.get(1).getPofG());
        GridGroup gg=new GridGroup();
        gg.initGrid();

    }
}
