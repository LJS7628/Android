package com.example.threadsample;

public class Horse extends Thread{
    String name;
    public Horse(String name){
        this.name = name;
    }

    // 서브스레드의 main문
    @Override
    public void run() {
        for(int i =0; i<25; i++){
            System.out.println(name+" : "+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
