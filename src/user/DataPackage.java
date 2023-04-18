package user;

import utils.DataUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class DataPackage implements Serializable {

    private static final long serialVersionUID = 6026318094758650793L;
    HashMap<String,Student> stuData = new HashMap<String,Student>();
    ArrayList<Student> listHasRanked = new ArrayList<>();
    HashMap<String,Projects> projectsHashMap = new HashMap<String,Projects>();
    HashMap<String,Department> departmentList = new HashMap<String,Department>();

    public ArrayList<Student> getListHasRanked() {
        return listHasRanked;
    }
    public void setListHasRanked(ArrayList<Student> listHasRanked) {
        this.listHasRanked = listHasRanked;
    }

    public HashMap<String, Projects> getProjectsHashMap() {
        return projectsHashMap;
    }

    public void setProjectsHashMap(HashMap<String, Projects> projectsHashMap) {
        this.projectsHashMap = projectsHashMap;
    }

    public HashMap<String, Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(HashMap<String, Department> departmentList) {
        this.departmentList = departmentList;
    }

    public HashMap<String, Student> getStuData() {
        return stuData;
    }

    public void setStuData(HashMap<String, Student> stuData) {
        this.stuData = stuData;
    }
}
