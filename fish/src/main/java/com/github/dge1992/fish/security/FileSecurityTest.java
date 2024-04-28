package com.github.dge1992.fish.security;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileSecurityTest {

    public static void main(String[] args) throws IOException {
        File file = FileUtils.getFile("/Users/ganendong/Documents/workspace/blog");
        FileInputStream fileInputStream = FileUtils.openInputStream(file);
    }
}
