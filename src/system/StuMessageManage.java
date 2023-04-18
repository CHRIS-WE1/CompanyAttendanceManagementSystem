package system;

import user.Classes;
import user.DataPackage;
import user.Department;
import user.Student;
import utils.DataUtils;
import utils.StudentUtils;

import java.io.IOException;
import java.util.*;

import static java.lang.System.exit;

public class StuMessageManage {
    public static void FucSelect(DataPackage dataPackage) throws IOException, ClassNotFoundException {
        System.out.println("-----请输入你要进行的操作编号-----");
        System.out.println("        1.创建学生信息（不含成绩）");
        System.out.println("        2.修改学生信息");
        System.out.println("        3.删除学生信息");
        System.out.println("        4.查询学生信息");
        System.out.println("        5.列出所有学生信息");
        System.out.println("        6.跳转到成绩管理系统");
        System.out.println("        7.跳转到专业课程信息管理系统");
        System.out.println("        8.退出系统");
        System.out.println("-----------------------------");
        Scanner scanner = new Scanner(System.in);
        int selectNo = scanner.nextInt();
        StuMessageManage stuMessageManage = new StuMessageManage();
        switch (selectNo){
            case 1:stuMessageManage.addStu(dataPackage);break;
            case 2:stuMessageManage.alterStuMes(dataPackage);break;
            case 3:stuMessageManage.DeleteStuMessage(dataPackage);break;
            case 4:stuMessageManage.QueryStuMessage(dataPackage);break;
            case 5:stuMessageManage.ListStuMessage(dataPackage);break;
            case 6: StuGradesManage.FucSelect(dataPackage);break;
            case 7:DepartmentManage.FunSelect(dataPackage);break;
            case 8:Operation.exitSystem(dataPackage);break;
            default:System.out.println("编号输入错误，该编号下无功能，请重新选择");
                StuMessageManage.FucSelect(dataPackage);
                break;
        }

    }

    private void ListStuMessage(DataPackage dataPackage) throws IOException, ClassNotFoundException {
        HashMap<String, Student> stuData =dataPackage.getStuData();
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> list = new ArrayList<Student>();
        for (Map.Entry<String, Student> entry : stuData.entrySet()) {
            Student stu = (Student) entry.getValue();
            list.add(stu);
        }
        DataUtils dataUtils = new DataUtils();
        dataUtils.StuMessagePrint(list);
        System.out.println("-----------以上为所有学生列表------------");
        System.out.println("----按任意按键回到功能列表----");
        char a = scanner.next().charAt(0);
        StuMessageManage.FucSelect(dataPackage);
    }

    public void addStu(DataPackage dataPackage) throws IOException, ClassNotFoundException {
            HashMap<String,Student> stuData = dataPackage.getStuData();
            HashMap<String, Department> departmentHashMap = dataPackage.getDepartmentList();
            DataUtils dataUtils = new DataUtils();
            System.out.println("请输入学生的姓名，学号，性别，班级，专业：");
            Student student = null;
            Scanner scanner = new Scanner(System.in);
            System.out.println("姓名：");
            String name = scanner.next();
            System.out.println("学号：");
            String id = scanner.next();
            System.out.println("性别：");
            String sex = scanner.next();
            System.out.println("专业：");
            System.out.println("可选专业如下：");
            dataUtils.DepartmentList(departmentHashMap);
            String department = scanner.next();
            if (!departmentHashMap.containsKey(department)){
                System.out.println("无专业，请重新输入");
                addStu(dataPackage);
            }
            HashMap<String, Classes> classesHashMap = departmentHashMap.get(department).getClassList();
            System.out.println("班级：");
            System.out.println("可选班级如下：");
            dataUtils.ClassList(classesHashMap);
            String classes = scanner.next();
            if (!classesHashMap.containsKey(classes)){
                System.out.println("无班级，请重新输入");
                addStu(dataPackage);
            }
            student = new Student(name,id,sex,classes,department);
            if (dataUtils.isMesCorrect(student.getId(),student.getSex(),stuData,"add")){
                System.out.println("该学生信息如下：");
                System.out.println("姓名："+name+" 学号："+id+" 性别："+sex+" 班级："+classes+" 专业："+department);
                System.out.println("您确定创建该学生吗？(Y/N)");
                char select = scanner.next().charAt(0);
                if (select=='Y'){
                    departmentHashMap.get(department).getStuListByDep().put(student.getId(),student);
                    classesHashMap.get(classes).getStuListByClasses().put(student.getId(),student);
                    stuData.put(student.getId(),student);
                    departmentHashMap.get(department).setClassList(classesHashMap);
                    dataPackage.setDepartmentList(departmentHashMap);
                    dataPackage.setStuData(stuData);
                    dataUtils.dataUpload(dataPackage);
                }else {
                    StuMessageManage.FucSelect(dataPackage);
                }
            }else {
                System.out.println("您是否重新输入学生信息？（Y/N）");
                char select = scanner.next().charAt(0);
                if (select=='Y'){
                    addStu(dataPackage);
                }else {
                    StuMessageManage.FucSelect(dataPackage);
                }
            }
            StuMessageManage.FucSelect(dataPackage);
    }

    public void alterStuMes(DataPackage dataPackage) throws IOException, ClassNotFoundException {
        HashMap<String, Student> stuData = dataPackage.getStuData();
        HashMap<String,Department> departmentHashMap =dataPackage.getDepartmentList();
        System.out.println("请输入修改学生的学号：");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.next();

        if (!stuData.containsKey(id)){
            System.out.println("该学生不存在！");
            System.out.println("-----输入功能编号-----");
            System.out.println("     1.重新输入学号   ");
            System.out.println("     2.回到功能菜单   ");
            System.out.println("-------------------");
            int option = scanner.nextInt();
            if (option==1){
                alterStuMes(dataPackage);
            }else {
                StuMessageManage.FucSelect(dataPackage);
            }
        }else {
            Student student = stuData.get(id);
            String newName = null;
            String newSex = null;
            String newClasses = null;
            String newDepartment = null;
            System.out.println("该学生的信息为：");
            System.out.println("姓名："+student.getName()+" 学号："+student.getId()+" 性别："
                    +student.getSex()+" 班级："+student.getClasses()+" 专业："+student.getDepartment());
            System.out.println("-----选择需要修改的信息-----");
            System.out.println("输入格式为“序号,序号,...,序号”");
            System.out.println("         1.姓名");
            System.out.println("         2.性别");
            System.out.println("         3.专业");
            System.out.println("         4.班级");
            String selectList = scanner.next();
            String[] selects = selectList.split(",");
            for (String select : selects) {
                switch (select) {
                    case "1" -> {
                        System.out.println("输入新姓名：");
                        newName = scanner.next();
                    }
                    case "2" -> {
                        System.out.println("输入新性别：");
                        newSex = scanner.next();
                    }
                    case "3" -> {
                        System.out.println("输入新专业：");
                        newDepartment = scanner.next();
                    }
                    case "4" -> {
                        System.out.println("输入新班级：");
                        HashMap<String,Classes> classesHashMap = new HashMap<>();
                        if (newDepartment==null){
                            classesHashMap = departmentHashMap.get(student.getDepartment()).getClassList();
                        }else {
                            classesHashMap = departmentHashMap.get(newDepartment).getClassList();
                        }
                        System.out.println("可选班级为：");
                        DataUtils dataUtils = new DataUtils();
                        dataUtils.ClassList(classesHashMap);
                        newClasses = scanner.next();
                    }
                }
            }
            DataUtils utils = new DataUtils();
            String sex = null;
            if (newSex==null)
                sex= student.getSex();
            else
                sex=newSex;
            if(utils.isMesCorrect(student.getId(),sex,stuData,"alter")){
                if (newName!=null)
                    student.setName(newName);
                if (newSex!=null)
                    student.setSex(newSex);
                System.out.println("确定将该学生信息修改为："+"姓名："+student.getName()+" 学号："+student.getId()+" 性别："
                        +student.getSex()+" 班级："+student.getClasses()+" 专业："+student.getDepartment()+"吗？（Y/N）");
                char select = scanner.next().charAt(0);
                if (select=='Y'){
                    if (newDepartment!=null){
                        departmentHashMap.get(student.getDepartment()).getClassList().get(student.getClasses()).getStuListByClasses().remove(student.getId());
                        departmentHashMap.get(student.getDepartment()).getStuListByDep().remove(student.getId());
                        student.setDepartment(newDepartment);
                        student.setClasses(newClasses);
                        departmentHashMap.get(student.getDepartment()).getClassList().get(student.getClasses()).getStuListByClasses().put(student.getId(),student);
                        departmentHashMap.get(student.getDepartment()).getStuListByDep().put(student.getId(),student);
                    }
                    if (newClasses!=null&&newDepartment==null){
                        departmentHashMap.get(student.getDepartment()).getClassList().get(student.getClasses()).getStuListByClasses().remove(student.getId());
                        student.setClasses(newClasses);
                        departmentHashMap.get(student.getDepartment()).getClassList().get(student.getClasses()).getStuListByClasses().put(student.getId(),student);
                    }
                    stuData.replace(student.getId(),student);
                    dataPackage.setDepartmentList(departmentHashMap);
                    dataPackage.setStuData(stuData);
                    DataUtils dataUtils  =new DataUtils();
                    dataUtils.dataUpload(dataPackage);
                    StuMessageManage.FucSelect(dataPackage);
                }else {
                    StuMessageManage.FucSelect(dataPackage);
                }
            }
        }
    StuMessageManage.FucSelect(dataPackage);
    }

    public void DeleteStuMessage(DataPackage dataPackage) throws IOException, ClassNotFoundException {
        HashMap<String, Student> stuData = dataPackage.getStuData();
        System.out.println("请输入要删除学生的学号：");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.next();
        if (!stuData.containsKey(id)){
            System.out.println("该学生不存在!是否重新输入学生信息？（Y/N）");
            char select = scanner.next().charAt(0);
            if (select=='Y'){
                DeleteStuMessage(dataPackage);
            }else {
                StuMessageManage.FucSelect(dataPackage);
            }
        }else {
            Student student = (Student) stuData.get(id);
            System.out.println("该学生信息为：");
            student.PrintMessage();
            System.out.println("您确定删除该学生吗？（Y/N）");
            char select = scanner.next().charAt(0);
            if (select=='Y'){
              dataPackage.getStuData().remove(student.getId());
              dataPackage.getDepartmentList().get(student.getDepartment()).getClassList().get(student.getClasses()).getStuListByClasses().remove(student.getId());
              dataPackage.getDepartmentList().get(student.getDepartment()).getStuListByDep().remove(student.getId());
              dataPackage.getStuData().remove(student.getId());
              System.out.println("该学生已删除");
                DataUtils dataUtils  =new DataUtils();
                dataUtils.dataUpload(dataPackage);
            }
            StuMessageManage.FucSelect(dataPackage);
        }
    }

    public void QueryStuMessage(DataPackage dataPackage) throws IOException, ClassNotFoundException {
        HashMap<String, Student> stuData = dataPackage.getStuData();
        System.out.println("-----请选择查询方式，输入方式的序号----");
        System.out.println("         1.按学号查询");
        System.out.println("         2.按姓名查询");
        System.out.println("         3.按专业查询");
        System.out.println("         4.按班级查询");
        System.out.println("         5.回到功能列表");
        System.out.println("-----------请输入序号----------");
        Scanner scanner  =new Scanner(System.in);
        int option = scanner.nextInt();
        ArrayList<Student> students = new ArrayList<Student>();
        StudentUtils studentUtils = new StudentUtils();
        switch (option){
            case 1:
                System.out.println("请输入学号：");
                String id = scanner.next();
                students=studentUtils.SearchStudentById(id,stuData);
                break;
            case 2:
                System.out.println("请输入姓名：");
                String name = scanner.next();
                students=studentUtils.SearchStudentByName(name,stuData);
                break;
            case 3:
                System.out.println("请输入专业：");
                String department = scanner.next();
                students=studentUtils.SearchStudentByDepartment(department,dataPackage);
                break;
            case 4:
                System.out.println("输入专业：");
                String department1 = scanner.next();
                System.out.println("请输入班级：");
                String classes = scanner.next();
                students=studentUtils.SearchStudentByClasses(department1,classes,dataPackage);
                break;
            default:StuMessageManage.FucSelect(dataPackage);
        }
        DataUtils utils = new DataUtils();
        utils.StuMessagePrint(students);
        System.out.println("-------以上为查询结果-------");
        System.out.println("----按任意按键回到功能列表----");
        char a = scanner.next().charAt(0);
        StuMessageManage.FucSelect(dataPackage);
    }
}
