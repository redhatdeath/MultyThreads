package ru.shanin.multythreads;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ru.shanin.multythreads.data.AddUser;
import ru.shanin.multythreads.data.Worker;
import timber.log.Timber;

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
        Timber.plant(new Timber.DebugTree());
        setupMyThreads();

    }

    private void setupMyThreads() {
        mThreads = new Thread[size];
        mAddUser = new AddUser[size];
        for (int i = 0; i < size; i++) {
            mAddUser[i] = new AddUser("myThread_" + (i + 1));
            mThreads[i] = new Thread(mAddUser[i]);
            Timber.d("Create thread №"+String.valueOf(i+1));
        }
        for (int i = 0; i < size; i++) {
            mThreads[i].start();
        }
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Worker.toStart();


    }

}