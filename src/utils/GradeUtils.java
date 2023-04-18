package utils;

import user.*;

import java.io.IOException;
import java.util.*;
import java.util.zip.CheckedOutputStream;

public class GradeUtils {

    public void GradeInput(Student student,DataPackage dataPackage){
        HashMap<String, Projects> projectHashMap= dataPackage.getDepartmentList().get(student.getDepartment()).getProjectsList();
        HashMap<String,Double> gradeList = student.getGradeList();
        Scanner scanner = new Scanner(System.in);
        double total = 0;
        for (Map.Entry<String,Projects> entry: projectHashMap.entrySet()){
            System.out.println("输入"+entry.getKey()+"成绩：");
            double point = scanner.nextDouble();
            Projects projects = projectHashMap.get(entry.getKey());
            if (point> projects.getPoint())
                point=projects.getPoint();
            if (point<0)
                point=0;
            total+=point;
            gradeList.put(entry.getKey(), point);
        }
        gradeList.put("all",total);
        student.setGradeList(gradeList);
    }
    

    public int getRank(Student student,DataPackage dataPackage){
        int rank = 0;
        HashMap<String,Student> studentHashMap = dataPackage.getDepartmentList().get(student.getDepartment()).getClassList().get(student.getClasses()).getStuListByClasses();
        getRank(studentHashMap,dataPackage);
        ArrayList<Student> list = dataPackage.getListHasRanked();
        rank=list.indexOf(student)+1;
        return rank;
    }
    public void getRank(HashMap<String,Student> studentHashMap,DataPackage dataPackage){
        TreeMap<Double,Student> studentList  =new TreeMap<Double,Student>((o1, o2) -> {
            if (o1==null||o2==null)
                return 0;
            return o2.compareTo(o1);
        });
        ArrayList<Student> withoutGrade = new ArrayList<>();
        for (Map.Entry<String,Student> entry : studentHashMap.entrySet()){
//            System.out.println(entry.getValue().getGradeList().get("all"));
            if (entry.getValue().getGradeList().get("all")==null){
              //  System.out.println(entry.getValue().getName());
                withoutGrade.add(entry.getValue());
            }
            else{
                studentList.put(entry.getValue().getGradeList().get("all"), entry.getValue());
            }

        }
        ArrayList<Student> listHasRanked = new ArrayList<>();
        for (Map.Entry<Double,Student> entry : studentList.entrySet()){
            listHasRanked.add(entry.getValue());
        }
        listHasRanked.addAll(withoutGrade);
//        for (int i=0;i< listHasRanked.size();i++){
//            Student student = listHasRanked.get(i);
//            System.out.println(student.getName());
//        }
        dataPackage.setListHasRanked(listHasRanked);
    }
    
    public void getRateAndAverage (String department,String classes,DataPackage dataPackage){
        Classes classes1 = dataPackage.getDepartmentList().get(department).getClassList().get(classes);
        HashMap<String,Double> averageList = classes1.getAverageList();
        HashMap<String,Double> rateList=classes1.getRateList();
        HashMap<String,Student> studentHashMap=classes1.getStuListByClasses();
        int count = studentHashMap.size();
        HashMap<String,Double> totalList = new HashMap<>();
        HashMap<String,Double> countList= new HashMap<>();
        for (Map.Entry<String,Student> entry: studentHashMap.entrySet()){
            HashMap<String,Double> gradeList = entry.getValue().getGradeList();
//            for (Map.Entry<String,Double> entry1: gradeList.entrySet()){
//                System.out.println(entry1.getKey()+":"+entry1.getValue());
//            }
            for (Map.Entry<String,Double> entry1: gradeList.entrySet()){
                if (!totalList.containsKey(entry1.getKey())){
                    totalList.put(entry1.getKey(),0.0);
                }
                if (!countList.containsKey(entry1.getKey())){
                    countList.put(entry1.getKey(),0.0);
                }
                if (entry1.getValue()>=60){
                   // countList.put(entry1.getKey(),countList.get(entry1.getKey())+1);
                    countList.replace(entry1.getKey(),countList.get(entry1.getKey())+1.0);
                }
                totalList.put(entry1.getKey(), totalList.get(entry1.getKey())+entry1.getValue());
            }
        }
        for (Map.Entry<String,Double> entry: totalList.entrySet()){
            if (!averageList.containsKey(entry.getKey())){
                averageList.put(entry.getKey(),0.0);
            }
            averageList.put(entry.getKey(), entry.getValue()/count);
        }

        for (Map.Entry<String,Double> entry: countList.entrySet()){
            if (!rateList.containsKey(entry.getKey())){
                rateList.put(entry.getKey(),0.0);
            }
            rateList.put(entry.getKey(), entry.getValue()/count);
        }
    }
}
