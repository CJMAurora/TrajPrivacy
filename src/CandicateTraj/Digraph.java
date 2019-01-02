package CandicateTraj;
import GirdRoadNetwork.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 这里图用树结构实现
 */
public class Digraph {
    class VertexNode{
        Point vertex;
        double dis;
        ArrayList<Integer> sonPList;
        public VertexNode(){
            sonPList=new ArrayList<>();
        }
        public VertexNode(Point vertex,double dis){
            this.vertex=vertex;
            this.dis=dis;
            sonPList=new ArrayList<>();
        }
    }

    public ArrayList<VertexNode> genDigraph(ArrayList<CandPoint> CandList,double v){
        //先建立顶点表
        ArrayList<VertexNode> verNodeList=new ArrayList<>();
        for(int i=0;i<CandList.size();i++){
            VertexNode vertexNode=new VertexNode(CandList.get(i).lv.p,0);
            verNodeList.add(vertexNode);
            if(CandList.get(i).pointList!=null){
                for(int j=0;j<CandList.get(i).pointList.size();j++){
                    //求出候选点与真实点之间的距离
                    double r=CandList.get(i).lv.p.distance(CandList.get(i).pointList.get(j));
                    vertexNode=new VertexNode(CandList.get(i).pointList.get(j),r);
                    verNodeList.add(vertexNode);
                }
            }

        }
        //创建邻接表

        for(int i=0;i<CandList.size()-1;i++){
            //真实轨迹中相邻两位置的时间乘以最大速度，得到距离阈值
            double disThed=v*(CandList.get(i+1).lv.hour-(double)CandList.get(i).lv.hour+(CandList.get(i+1).lv.min-(double)CandList.get(i).lv.min)/60);
            //得到真实轨迹中该位置索引
            System.out.println(CandList.get(i+1).lv.hour-(double)CandList.get(i).lv.hour+(CandList.get(i+1).lv.min-(double)CandList.get(i).lv.min)/60);
            System.out.println("相邻位置间的距离阈值为"+disThed);
            int num=0;
            for(int n=0;n<i;n++){
                if(CandList.get(n).pointList!=null)
                    num=num+CandList.get(n).pointList.size()+1;
                else
                    num=num+1;
            }
            //先判断真实轨迹中的点到其邻接点的候选点的距离是否可达
            if(CandList.get(i+1).pointList!=null){
                //真实轨迹中下一个点的开始位置
                int num1;
                if(CandList.get(i).pointList!=null)
                    num1=num+CandList.get(i).pointList.size()+1;
                else
                    num1=num+1;
                for(int j=0;j<CandList.get(i+1).pointList.size();j++){
                    double x=CandList.get(i).lv.p.distance(CandList.get(i+1).pointList.get(j));

                    if(x<=disThed){
                        verNodeList.get(num).sonPList.add(num1+j+1);
                    }
                }
                //
                System.out.println(verNodeList.get(num).sonPList);
//                for(int l=0;l< verNodeList.get(num).sonPList.size();l++){
//
//                }
            }
            //再判断该点的候选点与其邻接点及邻接点的候选点的距离是否可达
            if(CandList.get(i).pointList!=null){
                //真实轨迹中下一个点的开始位置
                int num1;
                if(CandList.get(i).pointList!=null)
                    num1=num+CandList.get(i).pointList.size()+1;
                else
                    num1=num+1;
                for(int k=0;k<CandList.get(i).pointList.size();k++){
                    //当点为候选点时，还要考虑其与真实点的可达性
                    double x=CandList.get(i).pointList.get(k).distance(CandList.get(i+1).lv.p);

                   // int num1=num+CandList.get(i).pointList.size()+1;
                    if(x<=disThed)
                        verNodeList.get(num+k+1).sonPList.add(num1);
                    if(CandList.get(i+1).pointList!=null){
                        for(int j=0;j<CandList.get(i+1).pointList.size();j++){
                            x=CandList.get(i).pointList.get(k).distance(CandList.get(i+1).pointList.get(j));
                            System.out.println(x);
                            if(x<=disThed){
                                verNodeList.get(num+k+1).sonPList.add(num1+j+1);
                            }
                        }

                    }
                    System.out.println(verNodeList.get(num+k+1).sonPList);
//                    for(int l=0;l< verNodeList.get(num).sonPList.size();l++){
//                        System.out.println(verNodeList.get(num).sonPList);
//                    }

                }
            }

        }
//        for(int i=0;i<verNodeList.size();i++){
//            System.out.println(verNodeList.get(i).vertex.getxCoor()+","+verNodeList.get(i).vertex.getyCoor()+" "+verNodeList.get(i).sonPList);
//        }
        System.out.println("顶点个数为："+verNodeList.size());
        return verNodeList;

    }
    //得到所有可能的假轨迹
    public void getAllDummy(ArrayList<CandPoint> CandList,double v){
        ArrayList<VertexNode> verNodeList=genDigraph(CandList,v);
        ArrayList< ArrayList<Point>> dummyList=new ArrayList<>();
        for(int i=0;i<verNodeList.size();i++){
            ArrayList<Point> dummy=new ArrayList<>();
            //将顶点表中的顶点放到轨迹中
            dummy.add(verNodeList.get(i).vertex);
            ArrayList<Integer> next=verNodeList.get(i).sonPList;
            while(next!=null){

            }


        }

    }
    public static void main(String[] args) throws Exception {
        InitializeCandidate ic=new InitializeCandidate();
        ArrayList<CandPoint> CandList=ic.initCandidate(2,0.1389974);
        Digraph dg=new Digraph();
        dg.genDigraph(CandList,0.1389974);
    }
}
