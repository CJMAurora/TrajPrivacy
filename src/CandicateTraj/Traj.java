package CandicateTraj;

import java.util.ArrayList;
/**
 *
 * @author Aurora
 *记录用户每天的轨迹
 *包括日期和访问点序列
 */

public class Traj {
    private String date;
    private ArrayList<Lv> LvList;
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public ArrayList<Lv> getLvList() {
        return LvList;
    }
    public void setLvList(ArrayList<Lv> LvList) {
        this.LvList = LvList;
    }
}
