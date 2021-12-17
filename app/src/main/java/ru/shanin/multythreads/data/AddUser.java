package ru.shanin.multythreads.data;

public class AddUser implements Runnable {
    private boolean running;
    private int count;

    public AddUser(String threadName) {
        Thread.currentThread().setName(threadName);
    }

    @Override
    public void run() {
        while (running) {
            Worker.addUserToQueue(new User(Thread.currentThread().getName()));
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
