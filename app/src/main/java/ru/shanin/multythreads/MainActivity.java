package ru.shanin.multythreads;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ru.shanin.multythreads.data.AddUser;
import ru.shanin.multythreads.data.Worker;

public class MainActivity extends AppCompatActivity {
    private static final int size;

    static {
        Worker worker = new Worker();
        size = 8;
    }

    private Thread[] mThreads;
    private AddUser[] mAddUser;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void setupMyThreads() {
        mThreads = new Thread[size];
        mAddUser = new AddUser[size];
        for (int i = 0; i < size; i++) {
            mThreads[i] = new Thread(mAddUser[i] = new AddUser("myThread_" + (i + 1)));
            mThreads[i].start();
        }

    }

}