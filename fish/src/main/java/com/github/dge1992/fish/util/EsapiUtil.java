package com.github.dge1992.fish.util;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.ValidationException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EsapiUtil {

    /*public static void main(String[] args) {
        //校验文件名
        String inputfilename ="xxxx.txt";
        ArrayList<String> allowedExtension = new ArrayList<String>();
        allowedExtension.add("exe");
        allowedExtension.add("jpg");

        if(!ESAPI.validator().isValidFileName("upload",inputfilename, allowedExtension,false)){
            //文件名不合法,throw exception
            System.out.println("出错了");
        }

        //获取随机文件名
        System.out.println(ESAPI.randomizer().getRandomFilename("exe"));
    }*/

    public static final String rootPath = "/test";

    public static String validFilePath(String inputPath, String rootPath) throws ValidationException {
        return ESAPI.validator().getValidDirectoryPath(inputPath, inputPath, new File(rootPath), true);
    }

    public static void main(String[] args) throws ValidationException, IOException {
        String inputPath = "/Users/ganendong/Documents/workspace/blog/Linux/Commands/Networking";
        String rootPath = "/Users/ganendong/Documents/workspace/blog/Linux";

        List<String> allowedExtensions = new ArrayList<>();
        allowedExtensions.add("txt");

        // String aa = "aaa.php";
        // String validFileName = ESAPI.validator().getValidFileName(aa, aa, allowedExtensions, false);

        String validDirectoryPath = ESAPI.validator().getValidDirectoryPath(inputPath, inputPath, new File(rootPath), false);
        System.out.println(validDirectoryPath);

    }
}
