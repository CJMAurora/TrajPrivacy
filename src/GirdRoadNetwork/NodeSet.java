//package GirdRoadNetwork;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.util.ArrayList;
//
///**
// * 1、将文件中路网上的点放到内存中
// * 2、得到路网的边界点
// */
//
//public class NodeSet {
//    public static ArrayList<Node> nodeList=new ArrayList<>();
//    public void readData() {
//        try {
//            String tab=" ";
//            String path="file/node.txt";
//            File file=new File(path);//路径名
//            BufferedReader br=new BufferedReader(new FileReader(file));
//            String line=null;
//            while((line=br.readLine())!=null){
//                if(line.equals(""))
//                    continue;
//                else {
//                    String data[]=line.split(tab);
//                    Node node=new Node();
//                    node.setNodeId(Integer.parseInt(data[0]));
//                    node.setxCoor(Double.parseDouble(data[1]));
//                    node.setyCoor(Double.parseDouble(data[2]));
//                    nodeList.add(node);
//                }
//            }
//            br.close();
//        }catch(Exception e) {
//            e.printStackTrace();
//            System.out.println("读文件出错");
//        }
//    }
//    public Border getBorder(){
//        Border border=new Border();
//        border.xMin=nodeList.get(0).getxCoor();
//        border.yMin=nodeList.get(0).getyCoor();
//        border.xMax=nodeList.get(0).getxCoor();
//        border.yMax=nodeList.get(0).getyCoor();
//        //得到地图的边界值
//        for(int i=0;i<nodeList.size();i++){
//            if(border.xMin>nodeList.get(i).getxCoor())
//                border.xMin=nodeList.get(i).getxCoor();
//            if(border.yMin>nodeList.get(i).getyCoor())
//                border.yMin=nodeList.get(i).getyCoor();
//            if(border.xMax<nodeList.get(i).getxCoor())
//                border.xMax=nodeList.get(i).getxCoor();
//            if( border.yMax<nodeList.get(i).getyCoor())
//                border.yMax=nodeList.get(i).getyCoor();
//        }
//        System.out.println("最小坐标为："+"("+border.xMin+","+ border.yMin+")");
//        System.out.println("最大坐标为："+"("+border.xMax+","+border.yMax+")");
//        return border;
//    }
//    class Border{
//        double xMin;
//        double yMin;
//        double xMax;
//        double yMax;
//    }
//
//}
