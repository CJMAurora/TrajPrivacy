package CandicateTraj;

public class test {
    public static void main(String[] args) {
        AllUserInfo allUserInfo=new AllUserInfo();
        allUserInfo.putDataFromFile();
        allUserInfo.getAllUserInfo();
        allUserInfo.getTrajById(0);
    }
}
