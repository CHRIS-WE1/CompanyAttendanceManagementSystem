package user;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Department implements Serializable {
    private static final long serialVersionUID = -8772106133434899120L;
    private HashMap<String,Projects> projectsList = new HashMap<>();
    private String name;
    private HashMap<String,Classes> classList = new HashMap<>();
    private HashMap<String,Student> stuListByDep = new HashMap<>();

    public HashMap<String, Student> getStuListByDep() {
        return stuListByDep;
    }

    public void setStuListByDep(HashMap<String, Student> stuListByDep) {
        this.stuListByDep = stuListByDep;
    }

    public HashMap<String, Projects> getProjectsList() {
        return projectsList;
    }

    public void setProjectsList(HashMap<String,Projects> projectsList) {
        this.projectsList = projectsList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public HashMap<String,Classes> getClassList() {
        return classList;
    }

    public void setClassList(HashMap<String,Classes> classList) {
        this.classList = classList;
    }
}
