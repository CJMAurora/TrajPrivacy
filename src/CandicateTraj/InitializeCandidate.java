package CandicateTraj;
import GirdRoadNetwork.*;

import java.util.ArrayList;
import java.util.HashMap;

public class InitializeCandidate {

    public ArrayList<CandPoint> initCandidate(int index,double v) throws Exception {
        AllUserInfo allUserInfo=new AllUserInfo();
        allUserInfo.putDataFromFile();
        allUserInfo.getAllUserInfo();
        ArrayList<Traj> trajList=allUserInfo.getTrajById(0);
        GridGroup gg=new GridGroup();
        //将网格初始化
        gg.initGrid();
        //用户一天的轨迹
        ArrayList<Lv> Trajectory=trajList.get(0).getLvList();
        //将真实轨迹中每个点的候选集存储起来
        ArrayList<CandPoint> CandList=new ArrayList<>();
        for(int i=0;i<Trajectory.size();i++){
            Lv loc=Trajectory.get(i);
            Point p=loc.getP();
            System.out.println(p.getxCoor()+" "+p.getyCoor());
            //根据可达性，进一步确定已知签到点前一个点的候选集
            if(i==index-1){
                Long startTime = System.nanoTime();
                ArrayList<Point> pQueryResultHash = gg.queryGrid(p,0.05);;//进行有索引的点查询
                Long endTime = System.nanoTime();
                ArrayList<Point> pList=new ArrayList<>();
                //距离阈值。其中速度为该用户的最大速度
                double disThed=v*((Trajectory.get(i+1).hour-(double)Trajectory.get(i).hour)+((Trajectory.get(i+1).min-(double)Trajectory.get(i).min)/60));
                System.out.println("阈值为："+disThed);
                System.out.println("真实轨迹的距离为："+p.distance(Trajectory.get(index).p));
                double y=p.distance(Trajectory.get(index).p)/((Trajectory.get(i+1).hour-(double)Trajectory.get(i).hour)+((Trajectory.get(i+1).min-(double)Trajectory.get(i).min)/60));
                System.out.println("速度为："+y);
                for(int j=0;j<pQueryResultHash.size();j++){
                    double x=pQueryResultHash.get(j).distance(Trajectory.get(index).p);
                    System.out.println("距离为"+x);
                    if(pQueryResultHash.get(j).distance(Trajectory.get(index).p)<=disThed){
                        pList.add(pQueryResultHash.get(j));
                    }
                }
                CandPoint cp=new CandPoint(loc,pList);
                CandList.add(cp);
                System.out.print("哈希索引下，查询结果中点的总数是：");
                System.out.println(pQueryResultHash.size());
                System.out.print("在以哈希的方式建立建立索引的情况下，范围查询的时间：");
                System.out.println(endTime-startTime);
                System.out.println("个数为："+pList.size());
            }
            else if(i==index){
                CandPoint cp=new CandPoint(loc,null);
                CandList.add(cp);
            }
            //根据可达性，确定已知签到点后一个点的候选集
            else if(i==index+1){
                Long startTime = System.nanoTime();
                ArrayList<Point> pQueryResultHash = gg.queryGrid(p,0.05);;//进行有索引的点查询
                Long endTime = System.nanoTime();
                ArrayList<Point> pList=new ArrayList<>();
                //距离阈值。其中速度为该用户的最大速度
                double disThed=v*((Trajectory.get(i).hour-(double)Trajectory.get(i-1).hour)+((Trajectory.get(i).min-(double)Trajectory.get(i-1).min)/60));
                System.out.println("阈值为："+disThed);
                System.out.println("真实轨迹的距离为："+p.distance(Trajectory.get(index).p));
                double y=p.distance(Trajectory.get(index).p)/((Trajectory.get(i).hour-(double)Trajectory.get(i-1).hour)+((Trajectory.get(i).min-(double)Trajectory.get(i-1).min)/60));
                System.out.println("速度为："+y);
                for(int j=0;j<pQueryResultHash.size();j++){
                    if(pQueryResultHash.get(j).distance(Trajectory.get(index).p)<=disThed){
                        pList.add(pQueryResultHash.get(j));
                    }
                }
                CandPoint cp=new CandPoint(loc,pList);
                CandList.add(cp);
                System.out.print("哈希索引下，查询结果中点的总数是：");
                System.out.println(pQueryResultHash.size());
                System.out.print("在以哈希的方式建立建立索引的情况下，范围查询的时间：");
                System.out.println(endTime-startTime);
                System.out.println("个数为："+pList.size());
            }
            else{
                Long startTime = System.nanoTime();
                ArrayList<Point> pQueryResultHash = gg.queryGrid(p,0.05);;//进行有索引的点查询
                Long endTime = System.nanoTime();
                CandPoint cp=new CandPoint(loc,pQueryResultHash);
                CandList.add(cp);
                //hm.put(loc,pQueryResultHash);
                //CandList.add(hm);
                System.out.print("哈希索引下，查询结果中点的总数是：");
                System.out.println(pQueryResultHash.size());
                System.out.print("在以哈希的方式建立建立索引的情况下，范围查询的时间：");
                System.out.println(endTime-startTime);
            }


        }
        return CandList;
    }




}
