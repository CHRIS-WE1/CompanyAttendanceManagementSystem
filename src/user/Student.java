package user;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Student implements Serializable {
    private static final long serialVersionUID = -1392899228807879420L;
    private String name;
    private String id;
    private String sex;
    private String classes;
    private String department;

    private HashMap<String,Double> gradeList = new HashMap<>();

    public HashMap<String, Double> getGradeList() {
        return gradeList;
    }

    public void setGradeList(HashMap<String, Double> gradeList) {
        this.gradeList = gradeList;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Student(String name, String id, String sex, String classes, String department) {
        this.name = name;
        this.id = id;
        this.sex = sex;
        this.classes = classes;
        this.department = department;
    }

    public void PrintGrade() {
        System.out.println("====================================");
        for (Map.Entry<String,Double> entry : gradeList.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
        System.out.println("====================================");
    }

    public void PrintMessage() {
        System.out.println("学号："+id+" 姓名："+name+" 性别："+sex+" 专业："+department+" 班级："+classes);
    }
}
