//package GirdRoadNetwork;
//
//import CandicateTraj.*;
//
//import java.io.File;
//import java.util.ArrayList;
//
//public class QueryGrid {
//    /**
//     * 函数功能：在建好的网格索引文件之上进行范围查询，返回符合查询条件的点，
//     * @param gIHAll：全局网格索引对象，保存的是全局索引有关的信息
//
//     * @return：该函数返回的是符合范围查询条件的结果数组
//     * @throws Exception
//     */
//    GridGroup gg=new GridGroup();

   // private  ArrayList<Point> gridIndexQuery(GridGroup gIHAll,Point qPoint, double qRadius)throws Exception{
//        ArrayList<Point> pQueryResult = new ArrayList<Point>();
//        double qPointStartX=qPoint.xCoor-qRadius;
//        double qPointStartY=qPoint.yCoor-qRadius;
//        double qPointEndX=qPoint.xCoor+qRadius;
//        double qPointEndY=qPoint.yCoor+qRadius;
//        /*=======================获取查询起始点和结束点的网格标识=============================*/
//        int xQueryIndexStart = (int)((qPointStartX-gIHAll.getxStartAll())/(gIHAll.getDx()));
//        int yQueryIndexStart = (int)((qPointStartY-gIHAll.getyStartAll())/(gIHAll.getDy()));
//        int xQueryIndexEnd = (int)((qPointEndX-gIHAll.getxStartAll())/(gIHAll.getDx()));
//        int yQueryIndexEnd = (int)((qPointEndY-gIHAll.getyStartAll())/(gIHAll.getDy()));
//        System.out.println(xQueryIndexStart+" "+yQueryIndexStart+" "+xQueryIndexEnd+" "+yQueryIndexEnd);
//        /*=======================================================================*/
//
//        /**查询时，因为网格的划分的增量等于查询半径的2倍（这种方法只适用在本论文里，因为每个点的查询半径都一样）
//         * 所以只存在两种情况，要不查询范围中没有完整网格，要不只含有一个完整网格
//         */
//
//        /*==========当查询范围中没有完整的网格时，将与查询范围相交的每个网格都取出，并对每个网格中的点进行比较，返回符合查询条件的点=============*/
//        if(((xQueryIndexEnd-xQueryIndexStart)==1)||((yQueryIndexEnd-yQueryIndexStart)==1)){
//            //pQueryResult.clear();//将结果数组清空
//            for(int x=xQueryIndexStart;x<=xQueryIndexEnd;x++){
//                for(int y=yQueryIndexStart;y<=yQueryIndexEnd;y++){
//                    GridUnit GU=gIHAll.getgIH().get((x*gIHAll.getyNum())+y);//得到对应的网格单元
//                    ArrayList<Point> pOne=GU.getPofG();
//                    System.out.println("矩形查询中的点个数为"+pOne.size());
//                    for(int i=0;i<pOne.size();i++){
//                        double r=Math.sqrt(Math.pow((pOne.get(i).xCoor-qPoint.xCoor),2)+Math.pow((pOne.get(i).yCoor-qPoint.yCoor),2));
//                        if(r<=qRadius)
//                            pQueryResult.add(pOne.get(i));
//                    }
//
//                }
//            }
//        }
//        /*==========================================================================================*/
//
//        /*====================当查询范围在一个网格内时，将这个网格中的点取出，并过滤出符合查询条件的点===========================*/
//        else if(((xQueryIndexEnd-xQueryIndexStart)==0)&&((yQueryIndexEnd-yQueryIndexStart)==0)){
//            GridUnit GU=gIHAll.getgIH().get((xQueryIndexStart*gIHAll.getyNum())+yQueryIndexStart);//得到对应的网格单元
//            ArrayList<Point> pOne= GU.getPofG();
//            for(int i=0;i<pOne.size();i++){
//                double r=Math.sqrt(Math.pow((pOne.get(i).xCoor-qPoint.xCoor),2)+Math.pow((pOne.get(i).yCoor-qPoint.yCoor),2));
//                if(r<=qRadius)
//                    pQueryResult.add(pOne.get(i));
//            }
//        }
//        /*==========================================================================================*/
//        System.out.println("查询");
//
//        return pQueryResult;//返回查询结果
   // }

//    /**
//     * 给定一个用户签到点，得到该用户签到点的候选假位置集
//     * @param lv
//     * @throws Exception
//     */
//    public ArrayList<Point> getQueryResult(Lv lv) throws Exception {
////        double dx=0.1;//每个网格的横向增量
////        double dy=0.1;
////        double xStartAll=-124.389343-dx/2;
////        double yStartAll=32.541302-dy/2;//总范围起始点的纵坐标
////        double xEndAll=-114.294258+dx/2;//总范围结束点的横坐标
////        double yEndAll=42.017231+dy/2;//总范围结束点的纵坐标
////
////        GridGroup gg=new GridGroup(xStartAll,yStartAll,xEndAll,yEndAll,dx,dy);
////        String path="file/node.txt";
////        File file=new File(path);//路径名
////        ArrayList<GridUnit> gu= gg.creatGridGroup(file);
////        int sum=0;
////        for(int i=0;i<gu.size();i++){
////            System.out.println(gu.get(i).getPofG());
////            sum= sum+gu.get(i).getPofG().size();
////
////        }
////        System.out.println(dx/2);
////        System.out.println(sum);
//        //Point p=new Point(-118.2696247101,34.0426141541);
//        Point p=lv.getP();
//        System.out.println(p.xCoor+" "+p.yCoor);
//        Long startTime = System.nanoTime();
//        ArrayList<Point> pQueryResultHash = gridIndexQuery(gg,p,0.05);//进行有索引的点查询
//        Long endTime = System.nanoTime();
//        System.out.print("哈希索引下，查询结果中点的总数是：");
//        System.out.println(pQueryResultHash.size());
//        System.out.print("在以哈希的方式建立建立索引的情况下，范围查询的时间：");
//        System.out.println(endTime-startTime);
//        return pQueryResultHash;
//    }
//
//}
