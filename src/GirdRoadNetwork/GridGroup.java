package GirdRoadNetwork;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class GridGroup {
    public static ArrayList<GridUnit> gIH;//网格索引数组
    public static double xStartAll;//总范围起始点的横坐标
    public static double yStartAll;//总范围起始点的纵坐标
    public static double xEndAll;//总范围结束点的横坐标
    public static double yEndAll;//总范围结束点的纵坐标
    public static double dx;//每个网格的横向增量
    public static double dy;//每个网格的纵向增量
    public static int xNum;//网格索引的横向网格总数
    public static int yNum;//网格索引的纵向网格总数

    public GridGroup(){
        gIH=new ArrayList<>();
    }

    public GridGroup(double xStartAll,double yStartAll,double xEndAll,double yEndAll,double dx,double dy){
        this.xStartAll=xStartAll;
        this.yStartAll=yStartAll;
        this.xEndAll=xEndAll;
        this.yEndAll=yEndAll;
        this.dx=dx;
        this.dy=dy;
        xNum=(int)((xEndAll-xStartAll)/dx)+1;
        yNum=(int)((yEndAll-yStartAll)/dy)+1;
        gIH=new ArrayList<>();
    }

    /**
     * 将文件中的数据读到内存中，将其存储到网格
     * @param file
     * @return
     */
    private ArrayList<GridUnit> creatGridGroup(File file) {
        /*===================生成网格索引的标识，并保存到全局索引对象中=========================*/
        for(int i=0;i<xNum;i++){
            for(int j=0;j<yNum;j++){
                gIH.add(new GridUnit(i,j));
            }
        }
        try {
            String tab=" ";
            BufferedReader br=new BufferedReader(new FileReader(file));
            String line=null;
            while((line=br.readLine())!=null){
                if(line.equals(""))
                    continue;
                else {
                    String data[]=line.split(tab);
                    double x=Double.parseDouble(data[1]);
                    double y=Double.parseDouble(data[2]);
                    Point p=new Point(x,y);
                    int xP = (int)((x-xStartAll)/dx);
                    int yP = (int)((y-yStartAll)/dy);
                    GridUnit gIHUnit = gIH.get(xP*(yNum)+yP);//获取点对应的网格索引对象
                    gIHUnit.setPofG(p);
                }
            }
            br.close();
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("读文件出错");
        }
        return gIH;

    }

    /**
     * 对网格进行初始化
     */
    public void initGrid(){
        double dx=0.1;//每个网格的横向增量
        double dy=0.1;
        double xStartAll=-124.389343-dx/2;
        double yStartAll=32.541302-dy/2;//总范围起始点的纵坐标
        double xEndAll=-114.294258+dx/2;//总范围结束点的横坐标
        double yEndAll=42.017231+dy/2;//总范围结束点的纵坐标

        GridGroup gg=new GridGroup(xStartAll,yStartAll,xEndAll,yEndAll,dx,dy);
        String path="file/node.txt";
        File file=new File(path);//路径名
        ArrayList<GridUnit> gu= gg.creatGridGroup(file);

        int sum=0;
        for(int i=0;i<gu.size();i++){
            System.out.println(gu.get(i).getPofG());
            sum= sum+gu.get(i).getPofG().size();

        }
        System.out.println(dx/2);
        System.out.println(sum);
    }

    /**
     * 在网格上进行查询操作
     * @param qPoint
     * @param qRadius
     * @return
     */
    public ArrayList<Point> queryGrid(Point qPoint, double qRadius){
        ArrayList<Point> pQueryResult = new ArrayList<Point>();
        double qPointStartX=qPoint.xCoor-qRadius;
        double qPointStartY=qPoint.yCoor-qRadius;
        double qPointEndX=qPoint.xCoor+qRadius;
        double qPointEndY=qPoint.yCoor+qRadius;
        /*=======================获取查询起始点和结束点的网格标识=============================*/
        int xQueryIndexStart = (int)((qPointStartX-xStartAll)/dx);
        int yQueryIndexStart = (int)((qPointStartY-yStartAll)/dy);
        int xQueryIndexEnd = (int)((qPointEndX-xStartAll)/dx);
        int yQueryIndexEnd = (int)((qPointEndY-yStartAll)/dy);
        System.out.println(xQueryIndexStart+" "+yQueryIndexStart+" "+xQueryIndexEnd+" "+yQueryIndexEnd);
        /*=======================================================================*/

        /**查询时，因为网格的划分的增量等于查询半径的2倍（这种方法只适用在本论文里，因为每个点的查询半径都一样）
         * 所以只存在两种情况，要不查询范围中没有完整网格，要不只含有一个完整网格
         */

        /*==========当查询范围中没有完整的网格时，将与查询范围相交的每个网格都取出，并对每个网格中的点进行比较，返回符合查询条件的点=============*/
        if(((xQueryIndexEnd-xQueryIndexStart)==1)||((yQueryIndexEnd-yQueryIndexStart)==1)){
            //pQueryResult.clear();//将结果数组清空
            for(int x=xQueryIndexStart;x<=xQueryIndexEnd;x++){
                for(int y=yQueryIndexStart;y<=yQueryIndexEnd;y++){
                    GridUnit GU=gIH.get((x*yNum)+y);//得到对应的网格单元
                    ArrayList<Point> pOne=GU.getPofG();
                    System.out.println("矩形查询中的点个数为"+pOne.size());
                    for(int i=0;i<pOne.size();i++){
                        double r=Math.sqrt(Math.pow((pOne.get(i).xCoor-qPoint.xCoor),2)+Math.pow((pOne.get(i).yCoor-qPoint.yCoor),2));
                        if(r<=qRadius)
                            pQueryResult.add(pOne.get(i));
                    }

                }
            }
        }
        /*==========================================================================================*/

        /*====================当查询范围在一个网格内时，将这个网格中的点取出，并过滤出符合查询条件的点===========================*/
        else if(((xQueryIndexEnd-xQueryIndexStart)==0)&&((yQueryIndexEnd-yQueryIndexStart)==0)){
            GridUnit GU=gIH.get((xQueryIndexStart*yNum)+yQueryIndexStart);//得到对应的网格单元
            ArrayList<Point> pOne= GU.getPofG();
            for(int i=0;i<pOne.size();i++){
                double r=Math.sqrt(Math.pow((pOne.get(i).xCoor-qPoint.xCoor),2)+Math.pow((pOne.get(i).yCoor-qPoint.yCoor),2));
                if(r<=qRadius)
                    pQueryResult.add(pOne.get(i));
            }
        }
        /*==========================================================================================*/
        return pQueryResult;//返回查询结果
    }


}
