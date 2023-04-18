
import system.Operation;
import user.*;
import utils.DataUtils;
import utils.SystemUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws EOFException, IOException, ClassNotFoundException {
        SystemUtils systemUtils = new SystemUtils();
        systemUtils.FileInit();
        File file = new File("./Data/stuData.obj");
        DataPackage dataPackage = new DataPackage();
        if(file.length()!=0&& file.exists()){
            dataPackage = systemUtils.StuDataInit();
        }
        Operation.FucSelect(dataPackage);
    }
}