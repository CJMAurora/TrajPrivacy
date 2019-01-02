package CandicateTraj;
import GirdRoadNetwork.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class AllUserInfo {
    public static ArrayList<UserTrajs> userTrajList=new ArrayList<>();
    //public static ArrayList<Loc> locList=new ArrayList<>();
    ArrayList<Integer> allId=new ArrayList<>();
    ArrayList<String> allDate=new ArrayList<>();
    ArrayList<Integer> allHour=new ArrayList<>();
    ArrayList<Integer> allMin=new ArrayList<>();
    ArrayList<Point> allPoint=new ArrayList<>();
   // ArrayList<Integer> allLoc=new ArrayList<>();
    public  void putDataFromFile(){//将数据从文件中读取出来，并分别放到各个数组中
        try{
            String tab="\t";
            File file = new File("E:\\Gowalla_totalCheckins.txt");//文件
            //File file = new File("E:\\test.txt");//文件
            //File file =new File(E:\\test.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));//br是字符流
            String line = null;
            while((line=br.readLine())!=null){
                if(line.equals(""))//如果是空行，就跳过
                    continue;
                else{              //否则将其分离，然后放到对应的数组中
                    String[] data=line.split(tab);
                    allId.add(Integer.parseInt(data[0]));
                    String[] data1=data[1].split("T");
                    allDate.add(data1[0]);
                    String[] data2=data1[1].split(":");
                    allHour.add(Integer.parseInt(data2[0]));
                    allMin.add(Integer.parseInt(data2[1]));
                    //直接用坐标表示
                    Point p=new Point(Double.parseDouble(data[3]),Double.parseDouble(data[2]));
                    allPoint.add(p);
                }

            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("读取文件出错");
        }
    }
    //得到同一天下的访问地点Lv
    private ArrayList<Lv> getLvSomedate(int dayStart,int dayend){
        ArrayList<Lv> lvList=new ArrayList<Lv>();
        for(int i=dayend;i>=dayStart;i--){//因为原始签到数据是从大到小的顺序，存储的时候就倒着存储了
            int sign=-1;//标记，用来标记该地点是否访问过
            Lv lv=new Lv();
           // GenLoc genLoc=new GenLoc();

            for(int j=0;j<lvList.size();j++){//进行去重操作
                if(allPoint.get(i).isEqual(lvList.get(j).p)) {
                    sign = j;
                    break;
                }
            }
            if(sign==-1){
                lv.setHour(allHour.get(i));
                lv.setMin(allMin.get(i));
                lv.setP(allPoint.get(i));
                lvList.add(lv);
            }
        }
        return lvList;
    }
    //得到同一个ID下的trajectory
    private ArrayList<Traj> getTrajSameId(int idStart,int idEnd){
        ArrayList<Traj> trajList=new ArrayList<>();
        int dayStart=idStart;
        String headDay=allDate.get(dayStart);//headDay开始时指向最开始的天

        for(int i=idStart;i<=idEnd;i++){
            //用于判断是否走到了最后的id，防止数组越界
            if(i == idEnd){
                Traj traj=new Traj();
                traj.setDate(headDay);
                traj.setLvList(getLvSomedate(dayStart,idEnd-1));
                trajList.add(traj);
                break;
            }
            if(!(allDate.get(i).equals(headDay))){
                Traj traj=new Traj();
                traj.setDate(headDay);
                traj.setLvList(getLvSomedate(dayStart,i-1));
                trajList.add(traj);
                dayStart=i;
                headDay=allDate.get(i);
            }

        }
        return trajList;
    }
    public void getAllUserInfo(){//存储数据

        int idStart=0;
        int headId=allId.get(0);
        for(int i=0;i<allId.size();i++)
        {

            if(headId!=allId.get(i))//找到同一个id的截至位置
            {
                UserTrajs userTrajs=new UserTrajs();
                userTrajs.setId(headId);
                userTrajs.setTrajList(getTrajSameId(idStart,i));
                userTrajList.add(userTrajs);
                idStart=i;
                headId=allId.get(i);
            }
            if(i==(allId.size()-1)){
                UserTrajs userTrajs=new UserTrajs();
                userTrajs.setId(headId);
                userTrajs.setTrajList(getTrajSameId(idStart,i+1));
                userTrajList.add(userTrajs);

            }

        }
    }
    public ArrayList<Traj> getTrajById(int id){//测试功能，看其轨迹点是否正确
        ArrayList<Traj> t=new ArrayList<>();
	    	for(int i=0;i< userTrajList.size();i++){
	    		if( userTrajList.get(i).getId()==id){
	    			System.out.println(id);
	    			t= userTrajList.get(i).getTrajList();
	    			for(int j=0;j<t.size();j++){
	    				System.out.println(t.get(j).getDate());
	    				ArrayList<Lv> l=t.get(j).getLvList();
	    				for(int n=0;n<l.size();n++){
	    					System.out.println(l.get(n).hour+" "+l.get(n).min+" "+l.get(n).p.getxCoor()+" "+l.get(n).p.getyCoor());
	    					//System.out.println(l.get(n).min);
	    					//System.out.println(l.get(n).genLoc.singleId);
	    				}
	    			}
	    		}
	    	}
	    	return t;
	    }

}
