package utils;

import user.DataPackage;

import java.io.File;
import java.io.IOException;

public class SystemUtils {

    public void FileInit() throws IOException {
        File f = new File("./Data");
        if (!f.exists()){
            System.out.println("文件夹为创建，已创建");
            f.mkdirs();
        }
        File stuData = new File("./Data/stuData.obj");
        if(!stuData.exists()){
            System.out.println("文件不存在");
            if(stuData.createNewFile()){
                System.out.println("文件已经生成");
            }
        }
        File gradeData = new File("./Data/gradeData.obj");
        if(!gradeData.exists()){
            System.out.println("文件不存在");
            if(gradeData.createNewFile()){
                System.out.println("文件已经生成");
            }
        }
        File classData = new File("./Data/classData.obj");
        if (!classData.exists()){
            System.out.println("文件不存在");
            if(classData.createNewFile()){
                System.out.println("文件已经生成");
            }
        }
    }

    public DataPackage StuDataInit() throws IOException, ClassNotFoundException {
        DataUtils dataUtils = new DataUtils();
        DataPackage dataPackage = dataUtils.dataDownload();
        return dataPackage;
    }

}
