package utils;

import user.*;

import java.io.*;
import java.util.*;

public class DataUtils {
    //public static  HashMap<String, Student> stuData = new HashMap<String,Student>();
    public DataPackage dataDownload() throws IOException, ClassNotFoundException {
        DataPackage dataPackage = new DataPackage();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        fis = new FileInputStream("./Data/stuData.obj");
        ois = new ObjectInputStream(fis);
        File file = new File("./Data/stuData.obj");
        if(0 == file.length() || !file.exists()){
            System.out.println("文件内容为空");
        }else {
            dataPackage = (DataPackage) ois.readObject();
        }
        ois.close();
       // fis.close();
        return dataPackage;
    }

    public void dataUpload(DataPackage dataPackage) throws IOException {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        fos = new FileOutputStream("./Data/stuData.obj");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(dataPackage);
        oos.flush();
        oos.close();
        System.out.println("数据已经保存！");
    }

    public void classAverageAndRate(String department,String classes,DataPackage dataPackage){
        Classes classes1 = dataPackage.getDepartmentList().get(department).getClassList().get(classes);
        HashMap<String,Double> RateList = classes1.getRateList();
        HashMap<String,Double> averageList = classes1.getAverageList();
        System.out.println("=======================");
        System.out.println(classes1.getName()+"班各科及格率：");
        for (Map.Entry<String,Double> entry : RateList.entrySet()){
            if (entry.getKey().equals("all")){
                continue;
            }
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
        System.out.println("------------------------");
        System.out.println(classes1.getName()+"班各科平均分：");
        for (Map.Entry<String,Double> entry : averageList.entrySet()){
            if (entry.getKey().equals("all")){
                continue;
            }
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
        System.out.println("========================");
    }
    public boolean isMesCorrect(String id,String sex, HashMap<String,Student> data,String method){
        int correct=0;
        if(data.containsKey(id)&&method.equals("add")){
            correct++;
            System.out.println("错误"+correct+"：该学号的学生已存在");
        }
        if (!(sex.equals("男")||sex.equals("女"))){
            correct++;
            System.out.println("错误"+correct+"：性别信息输入出错，请输入‘男’或‘女’！");
        }
        if(correct!=0)
        {
            return false;
        }
        return true;
    }

    public void StuMessagePrint(ArrayList<Student> list){
        System.out.println("-------------------------------------------------------");
        if(list.isEmpty()){
            System.out.println("查询不到相关结果，请修改查询条件！");
        }else{
            System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s\n","序号","学号","姓名","性别","专业","班级");
            int i=1;
            for(Student stu:list){
                System.out.printf("%-15d %-15s %-15s %-15s %-15s %-15s\n",i,stu.getId(),stu.getName(),stu.getSex(),stu.getDepartment(),stu.getClasses());
                i++;
            }
        }
        System.out.println("-------------------------------------------------------");
    }

    public void StuGradeMesPrint(List<Student> list){
        System.out.println("-------------------------------------------------------");
        if(list.isEmpty()){
            System.out.println("查询不到相关结果，请修改查询条件！");
        }else{
            HashMap<String,Double> gradeList = list.get(0).getGradeList();
            System.out.printf("%-15s %-15s %-15s %-15s %-15s ","排名","学号","姓名","专业","班级");
            for (Map.Entry<String,Double> entry: gradeList.entrySet()){
                if (!entry.getKey().equals("all"))
                    System.out.printf("%-5s ",entry.getKey());
            }
            System.out.printf("%-15s\n","总分");
            int i=1;
            for(Student stu:list){
                System.out.printf("%-15d %-15s %-15s %-15s %-15s ",i,stu.getId(),stu.getName(),stu.getDepartment(),stu.getClasses());
                HashMap<String,Double> gradeList2 = stu.getGradeList();
                for (Map.Entry<String,Double> entry: gradeList2.entrySet()){
                    if (!entry.getKey().equals("all"))
                        System.out.printf("%-15.2f ",entry.getValue());
                }
                System.out.printf("%-15.2f\n",gradeList2.get("all"));
                i++;
            }
        }
        System.out.println("-------------------------------------------------------");

    }

    public void ProjectList(HashMap<String, Projects> projectHashMap){
        System.out.println("-------------------------------------------------------");
        if(projectHashMap.isEmpty()){
            System.out.println("无课程信息");
        }else{
            int i=1;
            for(Map.Entry<String,Projects> entry: projectHashMap.entrySet()){
                System.out.println(i+"."+entry.getKey());
                i++;
            }
        }
        System.out.println("-------------------------------------------------------");
    }

    public void ClassList(HashMap<String,Classes> classesHashMap){
        System.out.println("-------------------------------------------------------");
        if(classesHashMap.isEmpty()){
            System.out.println("无班级信息");
        }else{
            int i=1;
            for(Map.Entry<String,Classes> entry: classesHashMap.entrySet()){
                System.out.println(i+"."+entry.getKey());
                i++;
            }
        }
        System.out.println("-------------------------------------------------------");
    }

    public void ProjectList(DataPackage dataPackage){
        HashMap<String, Projects> projectHashMap = dataPackage.getProjectsHashMap();
        System.out.println("-------------------------------------------------------");
        if(projectHashMap.isEmpty()){
            System.out.println("无课程信息");
        }else{
            int i=1;
            for(Map.Entry<String,Projects> entry: projectHashMap.entrySet()){
                System.out.println(i+"."+entry.getKey());
                i++;
            }
        }
        System.out.println("-------------------------------------------------------");
    }

    public void DepartmentList(HashMap<String,Department> departmentHashMap){
        System.out.println("-----------------------------------------------------------");
        int i=1;
        for (Map.Entry<String,Department> entry: departmentHashMap.entrySet()){
            System.out.println(i+"."+entry.getKey());
            i++;
        }
        System.out.println("-----------------------------------------------------------");
    }
    public void DepartmentMes(Department department){
        System.out.println("---------------------------------------------");
        System.out.println("专业名称："+department.getName());
        System.out.println("该专业的班级：");
        HashMap<String,Classes> classesHashMap = department.getClassList();
        HashMap<String,Projects> projectsHashMap = department.getProjectsList();
        int i=1;
        for (Map.Entry<String,Classes> entry: classesHashMap.entrySet()){
            System.out.print(entry.getKey());
            if (i%3==0){
                System.out.print("\n");
            }else {
                System.out.println(" ");
            }
            i++;
        }
        System.out.print("\n");
        System.out.println("该专业修读的课程：");
        i=1;
        for (Map.Entry<String,Projects> entry: projectsHashMap.entrySet()){
            System.out.print(entry.getKey());
            if (i%3==0){
                System.out.print("\n");
            }else {
                System.out.println(" ");
            }
            i++;
        }
        System.out.print("\n");
        System.out.println("---------------------------------------------");
    }
}
