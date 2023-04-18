package system;

import user.*;
import utils.DataUtils;
import utils.SystemUtils;

import java.io.IOException;
import java.util.*;

public class DepartmentManage {
    public static void FunSelect(DataPackage dataPackage) throws IOException, ClassNotFoundException {
        System.out.println("-----请输入你要进行的操作编号-----");
        System.out.println("      1.新建专业");
        System.out.println("      2.修改专业信息");
        System.out.println("      3.删除专业");
        System.out.println("      4.查询专业信息");
        System.out.println("      5.添加课程");
        System.out.println("      6.修改课程");
        System.out.println("      7.查询课程");
        System.out.println("      8.删除课程");
        System.out.println("      9.跳转到学生信息管理系统");
        System.out.println("      10.跳转到学生成绩管理系统");
        System.out.println("      11.退出系统");
        System.out.println("------------------------------");
        Scanner scanner = new Scanner(System.in);
        int selectNo= scanner.nextInt();
        DepartmentManage departmentManage = new DepartmentManage();
        switch (selectNo) {
            case 1 -> departmentManage.AddDepartment(dataPackage);
            case 2 -> departmentManage.AlterDepartment(dataPackage);
            case 3 -> departmentManage.DeleteDepartment(dataPackage);
            case 4 -> departmentManage.QueryDepartment(dataPackage);
            case 5 -> departmentManage.AddProject(dataPackage);
            case 6 -> departmentManage.AlterProject(dataPackage);
            case 7 -> departmentManage.QueryProject(dataPackage);
            case 8 -> departmentManage.DeleteProject(dataPackage);
            case 9 -> StuMessageManage.FucSelect(dataPackage);
            case 10 -> StuGradesManage.FucSelect(dataPackage);
            default -> Operation.exitSystem(dataPackage);
        }
    }

    public void QueryDepartment(DataPackage dataPackage) throws IOException, ClassNotFoundException {
        HashMap<String,Department> departmentHashMap = dataPackage.getDepartmentList();
        System.out.println("-----输入要查询的专业名称(输入Z退出查询/输入A获取专业列表)-----");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        if(name.equals("Z")){
            FunSelect(dataPackage);
        }
        DataUtils dataUtils = new DataUtils();
        if (name.equals("A")){
            dataUtils.DepartmentList(departmentHashMap);
            QueryDepartment(dataPackage);
        }
        if (departmentHashMap.containsKey(name)){
            Department department = departmentHashMap.get(name);
            dataUtils.DepartmentMes(department);
        }else {
            System.out.println("该专业不存在");
        }
        FunSelect(dataPackage);
    }

    public void DeleteDepartment(DataPackage dataPackage) throws IOException, ClassNotFoundException {
        HashMap<String,Department> departmentHashMap =dataPackage.getDepartmentList();
        System.out.println("-----输入要删除的专业名称（输入Z退出删除/输入A获取专业列表）-----");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        if(name.equals("Z")){
            FunSelect(dataPackage);
        }
        DataUtils dataUtils = new DataUtils();
        if (name.equals("A")){
            dataUtils.DepartmentList(departmentHashMap);
            DeleteDepartment(dataPackage);
        }
        if (departmentHashMap.containsKey(name)){
            Department department = departmentHashMap.get(name);
            if(department.getStuListByDep().isEmpty()){
                departmentHashMap.remove(name);
                dataPackage.setDepartmentList(departmentHashMap);
                System.out.println("该专业已被删除");
            }else {
                System.out.println("该专业下还有学生，请修改学生信息后再删除");
            }
        }else {
            System.out.println("该专业不存在");
        }
        FunSelect(dataPackage);
    }
    public void AlterDepartment(DataPackage dataPackage) throws IOException, ClassNotFoundException {
        HashMap<String,Department> departmentHashMap = dataPackage.getDepartmentList();
        HashMap<String,Projects> allProjectsHashMap = dataPackage.getProjectsHashMap();
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----输入要修改的专业名称（输入Z退出修改/输入A获取专业列表）-----");
        String name = scanner.next();
        if(name.equals("Z")){
            FunSelect(dataPackage);
        }
        DataUtils dataUtils = new DataUtils();
        if (name.equals("A")){
            dataUtils.DepartmentList(departmentHashMap);
            AlterDepartment(dataPackage);
        }
        if (departmentHashMap.containsKey(name)){
            Department department = departmentHashMap.get(name);
            System.out.println("-----请选择要修改的内容------");
            System.out.println("输入格式为“序号,序号,...,序号”");
            System.out.println("         1.修改课程列表");
            System.out.println("         2.修改班级列表");
            String selectList = scanner.next();
            String[] selects = selectList.split(",");
            for (String select : selects) {
                switch (select) {
                    case "1" -> {
                        HashMap<String,Projects> projectsHashMap = department.getProjectsList();
                        System.out.println("当前专业课程如下：");
                        dataUtils.ProjectList(projectsHashMap);
                        System.out.println("====输入操作序号====");
                        System.out.println("-----1.删除课程-----");
                        System.out.println("-----2.添加课程-----");
                        System.out.println("输入格式”课程，课程，....，课程“");
                        System.out.println("===================");
                        String op = scanner.next();
                        if (op.equals("1")){
                            System.out.println("输入要删除的课程名称：");
                            String pros=scanner.next();
                            String[] projects = pros.split("，");
                            for(String pro : projects){
                                if (projectsHashMap.containsKey(pro)){
                                    projectsHashMap.remove(pro);
                                }
                            }
                        }else if (op.equals("2")){
                            System.out.println("输入要添加的课程名称：");
                            String pros=scanner.next();
                            String[] projects = pros.split("，");
                            for(String pro : projects){
                                if(allProjectsHashMap.containsKey(pro)){
                                    projectsHashMap.put(pro, allProjectsHashMap.get(pro));
                                }


                            }
                        }
                        department.setProjectsList(projectsHashMap);
                    }
                    case "2" -> {
                        HashMap<String,Classes> classesHashMap =department.getClassList();
                        System.out.println("当前专业有如下班级：");
                        for (Map.Entry<String,Classes> entry:classesHashMap.entrySet()){
                            System.out.println(entry.getKey());
                        }
                        System.out.println("====输入操作序号====");
                        System.out.println("-----1.删除班级-----");
                        System.out.println("-----2.添加班级-----");
                        System.out.println("===================");
                        String op = scanner.next();
                        if (op.equals("1")){
                            System.out.println("输入要删除的班级名称");
                            String className = scanner.next();
                            if (classesHashMap.get(className).getStuListByClasses().isEmpty()){
                                classesHashMap.remove(className);
                            } else {
                              System.out.println(className+"下还有学生，请相关信息后在删除");
                            }
                        }else if (op.equals("2")){
                            System.out.println("输入新建班级名称");
                            String className = scanner.next();
                            if (!classesHashMap.containsKey(className)){
                                Classes classes = new Classes();
                                classes.setName(className);
                                classesHashMap.put(className,classes);
                            }
                        }else{
                            System.out.println("输入错误");
                            FunSelect(dataPackage);
                        }
                        department.setClassList(classesHashMap);
                    }
                }
                departmentHashMap.replace(name,department);
                dataPackage.setDepartmentList(departmentHashMap);
            }
        }else {
            System.out.println("该专业不存在");
        }
        FunSelect(dataPackage);
    }
    public void AddDepartment(DataPackage dataPackage) throws IOException, ClassNotFoundException {
        HashMap<String,Department> departmentList = dataPackage.getDepartmentList();
        System.out.println("-----请输入你要进行的操作编号-----");
        System.out.println("        1.新建专业");
        System.out.println("        2.回到功能菜单");
        System.out.println("------------------------------");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.next();
        if(option.equals("2")){
            DepartmentManage.FunSelect(dataPackage);
        }
        Department department = new Department();
        System.out.println("输入新建专业名称");
        String name = scanner.next();
        if (departmentList.containsKey(name)){
            System.out.println("专业已存在，请重新输入");
            AddDepartment(dataPackage);
        }
        department.setName(name);
        System.out.println("创建班级：");
        HashMap<String,Classes> classesList = new HashMap<>();
        while(true){
            System.out.println("---输入1创建班级---");
            System.out.println("---输入2结束创建---");
            String op = scanner.next();
            if (op.equals("2")){
                break;
            }
            Classes classes = new Classes();
            System.out.println("输入班级名称：");
            String cname = scanner.next();
            classes.setName(cname);
            classesList.put(cname,classes);
        }
        department.setClassList(classesList);
        HashMap<String,Projects> projectsList = new HashMap<>();
        System.out.println("为专业设置修读课程，可选课程有：");
        DataUtils dataUtils = new DataUtils();
        dataUtils.ProjectList(dataPackage);
        HashMap<String, Projects> projectHashMap = dataPackage.getProjectsHashMap();
        while (true){
            System.out.println("输入选择的课程名称：（输入Z退出）");
            String project = scanner.next();
            if (project.equals("Z")){
                break;
            }
            else if (!projectHashMap.containsKey(project)){
                System.out.println("课程不存在");
            }else {
                projectsList.put(project, projectHashMap.get(project));
                System.out.println("课程 "+project+" 已选择");
            }
        }
        department.setProjectsList(projectsList);
        departmentList.put(name,department);
        dataPackage.setDepartmentList(departmentList);
        FunSelect(dataPackage);
    }

    public void AddProject(DataPackage dataPackage) throws IOException, ClassNotFoundException {
        HashMap<String, Projects> projectsHashMap = dataPackage.getProjectsHashMap();
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----请输入你要进行的操作编号-----");
        System.out.println("        1.新建课程");
        System.out.println("        2.回到功能菜单");
        System.out.println("------------------------------");
        String option = scanner.next();
        if(option.equals("2")){
            DepartmentManage.FunSelect(dataPackage);
        }
        Projects projects = new Projects();
        System.out.println("设置课程名称");
        String name = scanner.next();
        if (projectsHashMap.containsKey(name)){
            System.out.println("该课程已存在，请重新输入");
            AddProject(dataPackage);
        }
        projects.setName(name);
        System.out.println("设置课程类型（考查/考试）");
        String type = scanner.next();
        projects.setType(type);
        System.out.println("设置课程分值");
        Double point = scanner.nextDouble();
        projects.setPoint(point);
        System.out.println("当前课程信息如下，是否创建（Y/N）：");
        projects.PrintMes();
        String op = scanner.next();
        if (op.equals("Y")){
            projectsHashMap.put(name,projects);
            dataPackage.setProjectsHashMap(projectsHashMap);
            System.out.println("创建成功");
            DataUtils dataUtils  =new DataUtils();
            dataUtils.dataUpload(dataPackage);
        }
        AddProject(dataPackage);
    }

    public void QueryProject(DataPackage dataPackage) throws IOException, ClassNotFoundException {
        HashMap<String, Projects> projectsHashMap = dataPackage.getProjectsHashMap();
        System.out.println("输入课程名称：");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        if (projectsHashMap.containsKey(name)){
            Projects projects = projectsHashMap.get(name);
            System.out.println("该课程信息为：");
            projects.PrintMes();
        }else{
            System.out.println("无该课程信息");
        }
        FunSelect(dataPackage);
    }

    public void DeleteProject(DataPackage dataPackage) throws IOException, ClassNotFoundException {
        HashMap<String, Projects> projectsHashMap = dataPackage.getProjectsHashMap();
        System.out.println("输入要删除的课程名称：");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        if (projectsHashMap.containsKey(name)){
            Projects projects = projectsHashMap.get(name);
            System.out.println("该课程信息为：");
            projects.PrintMes();
            System.out.println("你确定要删除吗？（Y/N）");
            String op = scanner.next();
            if (op.equals("Y")){
                projectsHashMap.remove(name);
                dataPackage.setProjectsHashMap(projectsHashMap);
                System.out.println("删除成功");
                DataUtils dataUtils  =new DataUtils();
                dataUtils.dataUpload(dataPackage);
            }
        }else{
            System.out.println("无该课程信息");
        }
        FunSelect(dataPackage);
    }

    public void AlterProject(DataPackage dataPackage) throws IOException, ClassNotFoundException {
        HashMap<String, Projects> projectsHashMap = dataPackage.getProjectsHashMap();
        System.out.println("请输入要修改的课程名称：");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        if (projectsHashMap.containsKey(name)){
            Projects projects = projectsHashMap.get(name);
            System.out.println("该课程信息为：");
            projects.PrintMes();
            System.out.println("-----请选择要修改的内容------");
            System.out.println("输入格式为“序号,序号,...,序号”");
            System.out.println("         1.分值");
            System.out.println("         2.类型");
            String selectList = scanner.next();
            String[] selects = selectList.split(",");
            for (String select : selects) {
                switch (select) {
                    case "1" -> {
                        System.out.println("输入新分值：");
                        double newPoint = scanner.nextDouble();
                        projects.setPoint(newPoint);
                    }
                    case "2" -> {
                        System.out.println("输入新课程类型：");
                        String type = scanner.next();
                        projects.setType(type);
                    }
                }
            }
            projectsHashMap.replace(name,projects);
            dataPackage.setProjectsHashMap(projectsHashMap);
            System.out.println("修改成功");
            DataUtils dataUtils  =new DataUtils();
            dataUtils.dataUpload(dataPackage);
        }else{
            System.out.println("该课程不存在");
        }
        FunSelect(dataPackage);
    }
}
