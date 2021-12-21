package ru.shanin.multythreads;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ru.shanin.multythreads.data.AddUser;
import ru.shanin.multythreads.data.Worker;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    private static final int size;
    private static final boolean WORKING = true;
    private static final boolean WAITING = false;

    private Button bt;
    private TextView tv;

    static {
        new Worker();
        size = 8;
    }

    private void setupLayout() {
        bt = findViewById(R.id.bt);
        tv = findViewById(R.id.tv);
        bt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Worker.isRunning()) Worker.setRunning(WAITING);
                        else Worker.setRunning(WORKING);
                    }
                }
        );

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timber.plant(new Timber.DebugTree());
        setupMyThreads();
        setupLayout();
        //runOnUiThread

    }

    private void setupMyThreads() {
        Thread[] mThreads = new Thread[size];
        AddUser[] mAddUser = new AddUser[size];
        for (int i = 0; i < size; i++) {
            mAddUser[i] = new AddUser("myThread_" + (i + 1));
            mThreads[i] = new Thread(mAddUser[i]);
            Timber.d("Create thread №%s", String.valueOf(i + 1));
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