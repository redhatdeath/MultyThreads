package ru.shanin.multythreads.data;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import timber.log.Timber;

public class Worker {
    private static final ArrayDeque<User> queue;
    static private boolean running;
    static private final Timer myTimer;
    private static final MyTimerClass myTimerClass;

    public static void addUserToQueue(User user) {
        synchronized (queue) {
            queue.add(user);
        }
    }

    public static boolean isRunning() {
        return running;
    }

    static {
        queue = new ArrayDeque<>();
        myTimer = new Timer();
        running = false;
        myTimerClass = new MyTimerClass();
    }

    static class MyTimerClass extends TimerTask {
        @Override
        public void run() {
            if (running) {
                String result = Objects.requireNonNull(queue.poll()).toString();
                Timber.d(result);
            }
            if (queue.isEmpty()) Worker.toStop();
        }
    }

    static public void toStart() {
        running = true;
        myTimer.scheduleAtFixedRate(myTimerClass, 0, 1000);
    }

    static public void toStop() {
        running = false;
        myTimer.cancel();
    }

    public static void setRunning(boolean running) {
        Worker.running = running;
    }
}
