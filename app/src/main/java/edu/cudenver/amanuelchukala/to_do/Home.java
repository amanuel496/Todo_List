package edu.cudenver.amanuelchukala.to_do;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class Home extends AppCompatActivity {
    private ArrayList<TaskModel> tasksList;
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNewTask addNewTask = new AddNewTask();
                addNewTask.show(getSupportFragmentManager(), " ");
            }
        });

        tasksList = new ArrayList<TaskModel>();
        db = new DataBaseHelper(this);

        recyclerView = findViewById(R.id.recyclerView);
        taskAdapter = new TaskAdapter(this, tasksList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(taskAdapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        showTasksOnRecyclerView(dataBaseHelper);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {

            Toast.makeText(this,"Setting item menu selected", Toast.LENGTH_LONG).show();
            return true;
        }
        else if (id == R.id.app_bar_MainMenu) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            Toast.makeText(this, "home item menu selected", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addNewContact (TaskModel taskModel) {
        tasksList.add(taskModel);
        Log.i ("info", Integer.toString(tasksList.size()));
        taskAdapter.notifyDataSetChanged();
    }
    public void delContact (TaskModel taskModel) {
        db.deleteOne(taskModel);
        tasksList.remove(taskModel);
        Log.i ("info", Integer.toString(tasksList.size()));
        taskAdapter.notifyDataSetChanged();

    }
    public void editContact (TaskModel taskModel, int position){

        tasksList.add(position, taskModel);
        Log.i ("info", "Item edited");
        taskAdapter.notifyItemChanged(position);

    }

    public void showTasks(int contactToShow) {
        TaskView taskView = new TaskView(tasksList.get(contactToShow));
        taskView.show(getSupportFragmentManager(), "");
    }

    private void showTasksOnRecyclerView(DataBaseHelper dataBaseHelper) {
        tasksList = (ArrayList<TaskModel>) dataBaseHelper.getEveryone();
        taskAdapter  = new TaskAdapter(this, tasksList);
        recyclerView.setAdapter(taskAdapter);
    }}