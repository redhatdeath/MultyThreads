package ru.shanin.multythreads.data;

import timber.log.Timber;

public class AddUser implements Runnable {
    private boolean running = true;
    private int count;
    private final String name;

    public AddUser(String threadName) {
        this.name = threadName;
        Thread.currentThread().setName(this.name);
        Timber.d("threadName = %s", this.name);
    }

    @Override
    public void run() {
        while (running) {
            User user = new User(this.name);
            Timber.d("user = %s", user.toString());
            Worker.addUserToQueue(user);
            try {
                Thread.sleep((int) (Math.random() * 51 + 50));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
            if (count > 50) running = false;
        }
    }
}
