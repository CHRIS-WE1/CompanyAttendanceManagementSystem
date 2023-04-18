package system;

import user.DataPackage;
import utils.DataUtils;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Operation {
    public static void FucSelect(DataPackage dataPackage) throws IOException, ClassNotFoundException {
        System.out.println("-----请输入你要进行的操作编号-----");
        System.out.println("        1.学生信息管理系统");
        System.out.println("        2.成绩管理系统");
        System.out.println("        3.专业课程信息管理");
        System.out.println("        4.退出系统");
        System.out.println("-----------------------------");
        Scanner scanner = new Scanner(System.in);
        int selectNo = scanner.nextInt();
        switch (selectNo){
            case 1:StuMessageManage.FucSelect(dataPackage);
                    break;
            case 2:StuGradesManage.FucSelect(dataPackage);
                    break;
            case 3:DepartmentManage.FunSelect(dataPackage);
                    break;
            case 4:exitSystem(dataPackage);
            default:System.out.println("编号输入错误，该编号下无功能");
                    Operation.FucSelect(dataPackage);
                    break;
        }
    }

    public static void exitSystem(DataPackage dataPackage) throws IOException {
        System.out.println("您确定保存对刚刚信息的修改吗？(是/否)");
        Scanner scanner = new Scanner(System.in);
        char select = scanner.next().charAt(0);
        DataUtils dataUtils = new DataUtils();
        switch (select){
            case '是':
                dataUtils.dataUpload(dataPackage);
                System.out.println("系统已关闭");
                exit(0);
                break;
            case '否':
                System.out.println("系统已关闭");
                exit(0);
                break;
            default:System.out.println("输入错误请重新输入：");
                    Operation.exitSystem(dataPackage);
        }
    }
}
