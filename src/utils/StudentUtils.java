package utils;

import user.DataPackage;
import user.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class StudentUtils {
    public ArrayList<Student> SearchStudentById(String id, HashMap<String, Student> stuData){
        ArrayList<Student> list = new ArrayList<Student>();
        for (Map.Entry<String, Student> entry : stuData.entrySet()) {
            if (entry.getKey().equals(id)) {
                Student stu = (Student) entry.getValue();
                list.add(stu);
            }
        }
        return list;
    }
    public ArrayList<Student> SearchStudentByName(String name, HashMap<String, Student> stuData) {
        ArrayList<Student> list = new ArrayList<Student>();
        for (Map.Entry<String, Student> entry : stuData.entrySet()) {
            Student stu = (Student) entry.getValue();
            if (stu.getName().equals(name))
                list.add(stu);
        }
        return list;
    }

    public ArrayList<Student> SearchStudentByDepartment(String department, DataPackage dataPackage) {
        ArrayList<Student> list = new ArrayList<Student>();
        HashMap<String,Student> stuData=dataPackage.getDepartmentList().get(department).getStuListByDep();
        for (Map.Entry<String, Student> entry : stuData.entrySet()) {
            Student stu = (Student) entry.getValue();
            list.add(stu);
        }
        return list;
    }

    public ArrayList<Student> SearchStudentByClasses(String department,String classes, DataPackage dataPackage) {
        ArrayList<Student> list = new ArrayList<Student>();
        HashMap<String,Student> stuData = dataPackage.getDepartmentList().get(department).getClassList().get(classes).getStuListByClasses();
        for (Map.Entry<String, Student> entry : stuData.entrySet()) {
            Student stu = (Student) entry.getValue();
            list.add(stu);
        }
        return list;
    }
}
