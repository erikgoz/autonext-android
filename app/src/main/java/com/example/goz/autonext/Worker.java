package com.example.goz.autonext;

public class Worker extends Thread {


    private String command;
    private String output;

    Worker(String c){
        this.command = c;

    }


    @Override
    public void run() {

        // Loop for ten iterations.
        // Sleep for a while
        try {
            Process p = Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            // Interrupted exception will occur if
            // the Worker object's interrupt() method
            // is called. interrupt() is inherited
            // from the Thread class.

        }
    }

}