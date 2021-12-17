package ru.shanin.multythreads.data;

import java.util.ArrayDeque;
import java.util.Timer;
import java.util.TimerTask;

public class Worker {
    private static final ArrayDeque<Data> queue;
    static private boolean running;
    static private final Timer myTimer;
    private static final MyTimerClass myTimerClass;

    public static void addDataToQueue(Data data) {
        synchronized (queue) {
            queue.add(data);
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
            if (running)
                System.out.println(queue.poll());
            if (queue.isEmpty()) Worker.toStop();
        }
    }

    static public void toStart() {
        running = true;
        myTimer.scheduleAtFixedRate(myTimerClass, 0, 50);
    }

    static public void toStop() {
        running = false;
        myTimer.cancel();
    }
}
