package com.github.dge1992.fish.exception;

import com.github.dge1992.fish.domain.Person;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionTest {

    public static void main(String[] args) {
        try{
            // 业务代码处理
            Person person = null;
            String name = person.getName();
            try{
                // 业务代码处理
                Person person2 = null;
                String name2 = person2.getName();
            }catch(Exception e){
                log.error("你的程序有异常啦",e);
            }
        }catch(Exception e){
            log.error("你的程序有异常啦",e);
        }
        /*try {
            try {
                Person person = null;
                String name = person.getName();
            } catch (Exception e) {
                throw e;
            } finally {
                System.out.println("aa");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
