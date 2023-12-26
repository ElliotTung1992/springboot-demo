package com.github.dge1992.fish.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ThreadTest {

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        threadTest.caseOne(threadTest);
    }

    List<String> list = new ArrayList<>();
    AtomicInteger i = new AtomicInteger(1);

    private void addList(String str){
        synchronized (list){
            log.info("addList...");
            list.add(str);
            list.notifyAll();
        }
    }

    private String getFromList() throws InterruptedException {
        synchronized (list) {
            log.info("start getList...");
            while (list.isEmpty()){
                log.info("waiting...");
                list.wait();
            }
            String remove = this.list.remove(0);
            log.info("getList = {}", remove);
            return remove;
        }
    }

    private void caseOne(ThreadTest threadTest) {
        // test Object.wait();Object.notify();Object.notifyAll()
        Thread threadOne = new Thread(() -> {
            try {
                threadTest.getFromList();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread threadTwo = new Thread(() -> {
            threadTest.addList(String.valueOf(i.incrementAndGet()));
        });

        threadOne.start();

        try {
            Thread.sleep(1 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        threadTwo.start();

    }
}
