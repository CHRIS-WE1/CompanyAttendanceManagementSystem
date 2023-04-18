package system;

import user.DataPackage;
import user.Student;
import utils.DataUtils;
import utils.GradeUtils;

import java.io.IOException;
import java.util.*;

public class StuGradesManage {
    public static void FucSelect(DataPackage dataPackage) throws IOException, ClassNotFoundException {
        System.out.println("-----请输入你要进行的操作编号-----");
        System.out.println("        1.登记/修改学生成绩");
        System.out.println("        2.获取学生成绩及班级排名");
        System.out.println("        3.获取各班各科平均分及及格率");
        System.out.println("        4.跳转到学生信息管理系统");
        System.out.println("        5.跳转到专业课程信息管理");
        System.out.println("        6.退出系统");
        System.out.println("-----------------------------");
        Scanner scanner = new Scanner(System.in);
        int selectNo = scanner.nextInt();
        StuGradesManage stuGradesManage = new StuGradesManage();
        switch (selectNo) {
            case 1 -> stuGradesManage.SetGrade(dataPackage);
            case 2 -> stuGradesManage.GradeAndRank(dataPackage);
            case 3 -> stuGradesManage.getRateAndAverage(dataPackage);
            case 4 -> StuMessageManage.FucSelect(dataPackage);
            case 5-> DepartmentManage.FunSelect(dataPackage);
            case 6 -> Operation.exitSystem(dataPackage);
            default -> {
                System.out.println("编号输入错误，该编号下无功能");
                StuGradesManage.FucSelect(dataPackage);
            }
        }
    }

    public void GradeAndRank(DataPackage dataPackage) throws IOException, ClassNotFoundException {
        HashMap<String,Student> stuData= dataPackage.getStuData();
        GradeUtils gradeUtils = new GradeUtils();
        System.out.println("------选择查询方式--------");
        System.out.println("------1.按学号--------");
        System.out.println("------2.按班级--------");
        System.out.println("------3.回到功能菜单---");
        System.out.println("------------------------");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.next();
        if (option.equals("1")){
            String id = scanner.next();
            if (stuData.containsKey(id)){
                Student student = stuData.get(id);
                student.PrintGrade();
                int rank = gradeUtils.getRank(student,dataPackage);
                System.out.println("班级排名为："+rank);
                System.out.println("输入任意值回到功能菜单");
                String m =scanner.next();
                GradeAndRank(dataPackage);
            }else {
                System.out.println("该学生不存在，请重新输入");
                GradeAndRank(dataPackage);
            }
        }else if (option.equals("2")){
            System.out.println("输入专业:");
            String department = scanner.next();
            System.out.println("输入班级");
            String classes = scanner.next();
            if (dataPackage.getDepartmentList().containsKey(department)&&dataPackage.getDepartmentList().get(department).getClassList().containsKey(classes)){
                HashMap<String,Student> studentHashMap = dataPackage.getDepartmentList().get(department).getClassList().get(classes).getStuListByClasses();
//                System.out.println("该班级学生：");
//                for (Map.Entry<String,Student> studentEntry :studentHashMap.entrySet()){
//                    System.out.println(studentEntry.getKey());
//                }
                gradeUtils.getRank(studentHashMap,dataPackage);
                ArrayList<Student> list = dataPackage.getListHasRanked();
                DataUtils dataUtils = new DataUtils();
                dataUtils.StuGradeMesPrint(list);
                System.out.println("输入任意值回到功能菜单");
                String m =scanner.next();
                GradeAndRank(dataPackage);
            }else {
                System.out.println("该班级不存在，请重新输入");
                GradeAndRank(dataPackage);
            }
        }else {
            StuGradesManage.FucSelect(dataPackage);
        }
    }

    public void getRateAndAverage(DataPackage dataPackage) throws IOException, ClassNotFoundException {
        HashMap<String,Student> stuData = dataPackage.getStuData();
        GradeUtils gradeUtils = new GradeUtils();
        DataUtils dataUtils = new DataUtils();
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------------------------------------------");
        System.out.println("输入要查询的班级所在专业：");
        String department = scanner.next();
        System.out.println("输入班级");
        String classes = scanner.next();
        if (dataPackage.getDepartmentList().containsKey(department)&&dataPackage.getDepartmentList().get(department).getClassList().containsKey(classes)){
            gradeUtils.getRateAndAverage(department,classes,dataPackage);
            dataUtils.classAverageAndRate(department,classes,dataPackage);
        }else {
            System.out.println("该班级不存在，请重新输入");
        }
        System.out.println("------------------------------------------------");
        System.out.println("输入任意值回到功能菜单");
        String m =scanner.next();
        StuGradesManage.FucSelect(dataPackage);
    }

    public void SetGrade(DataPackage dataPackage) throws IOException, ClassNotFoundException {
        HashMap<String,Student> stuData = dataPackage.getStuData();
        System.out.println("请输入学生的学号（输入z回到功能界面）：");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.next();
        if (id.equals("z")){
            FucSelect(dataPackage);
        }else if (stuData.containsKey(id)){
            Student student = stuData.get(id);
            GradeUtils gradeUtils = new GradeUtils();
            System.out.println("当前学生的成绩信息为：");
            student.PrintGrade();
            gradeUtils.GradeInput(student,dataPackage);
            System.out.println("成绩已经登记！");
            DataUtils dataUtils  =new DataUtils();
            dataUtils.dataUpload(dataPackage);
            FucSelect(dataPackage);
        }else {
            System.out.println("该学生不存在！请重新输入！");
            SetGrade(dataPackage);
        }
    }
}
