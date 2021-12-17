package ru.shanin.multythreads;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ru.shanin.multythreads.data.AddUser;
import ru.shanin.multythreads.data.Worker;

public class MainActivity extends AppCompatActivity {
    private static final int size;
    private static final Worker worker;

    static {
        worker = new Worker();
        size = 8;
    }

    private Thread[] mThreads;
    private AddUser[] mAddUser;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupMyThreads();

    }

    private void setupMyThreads() {
        mThreads = new Thread[size];
        mAddUser = new AddUser[size];
        for (int i = 0; i < size; i++) {
            mThreads[i] = new Thread(mAddUser[i] = new AddUser("myThread_" + (i + 1)));
            mThreads[i].start();
        }
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.toStart();


    }

}