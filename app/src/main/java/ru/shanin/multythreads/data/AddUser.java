package ru.shanin.multythreads.data;

import timber.log.Timber;

public class AddUser implements Runnable {
    private boolean running;
    private int count;

    public AddUser(String threadName) {
        Thread.currentThread().setName(threadName);
        Timber.d("threadName = " + threadName);
    }

    @Override
    public void run() {
        while (running) {
            User user = new User(Thread.currentThread().getName());
            Timber.d("user = " + user.toString());
            Worker.addUserToQueue(user);
//            try {
//                Thread.sleep((int) (Math.random() * 51 + 50));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            count++;
            if (count > 50) running = false;
        }
    }
}
