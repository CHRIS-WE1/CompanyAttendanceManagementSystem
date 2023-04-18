package user;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Classes implements Serializable {

    private static final long serialVersionUID = 1579382202659443247L;
    private String name;
    private HashMap<String,Double> averageList = new HashMap<>();
    private HashMap<String,Double> rateList=new HashMap<>();
    private HashMap<String,Student> stuListByClasses = new HashMap<>();

    public HashMap<String, Student> getStuListByClasses() {
        return stuListByClasses;
    }

    public void setStuListByClasses(HashMap<String, Student> stuListByClasses) {
        this.stuListByClasses = stuListByClasses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Double> getAverageList() {
        return averageList;
    }

    public void setAverageList(HashMap<String, Double> averageList) {
        this.averageList = averageList;
    }

    public HashMap<String, Double> getRateList() {
        return rateList;
    }

    public void setRateList(HashMap<String, Double> rateList) {
        this.rateList = rateList;
    }
}
