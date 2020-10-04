package edu.cudenver.amanuelchukala.to_do;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String TASK_TABLE = "TASK_TABLE";
    public static final String COLUMN_TASK_NAME = "TASK_NAME";
    public static final String COLUMN_TASK_DETAIL = "TASK_DETAIL";
    public static final String COLUMN_ACTIVE_TASK = "ACTIVE_TASK";
    public static final String COLUMN_ID = "ID";
    // private static final String COLUMN_ID = "COLUMN_ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "task.db", null, 1);
    }

    // this called the first time a database is accessed. There should be code in here to create a new database.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TASK_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TASK_NAME + " TEXT, " + COLUMN_TASK_DETAIL + " INT, " + COLUMN_ACTIVE_TASK + " BOOL)";
        db.execSQL(createTableStatement);
    }
    // this is called if the database version number changes. It prevents previous users apps from breaking when you change database design.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(TaskModel taskModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TASK_NAME, taskModel.getTaskTitle());
        cv.put(COLUMN_TASK_DETAIL, taskModel.getTaskDescription());
        cv.put(COLUMN_ACTIVE_TASK, taskModel.isActive());
        long insert = db.insert(TASK_TABLE, null, cv);
        if(insert == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean deleteOne(TaskModel taskModel){
        // find taskModel in the database. if it is found, delete it and retun true.
        // if it is not found, return false.

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + TASK_TABLE + " WHERE " + COLUMN_ID + "=" + TaskModel.getId();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            return true;
        }
        else{ return false;}
    }

    public List<TaskModel> getEveryone(){
        List<TaskModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + TASK_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            //loop through the cursor (result set) and create new task obj
            do{
                int taskId = cursor.getInt(0);
                String taskTitle = cursor.getString(1);
                String taskDescription = cursor.getString(2);
                boolean taskActive = cursor.getInt(3) == 1? true: false;
                TaskModel newTaskModel = new TaskModel(taskId, taskTitle, taskDescription, taskActive);
                returnList.add(newTaskModel);
            }while(cursor.moveToNext());

        }
        else {
            //failure. do not  add anything to the list.

        }
        //close both the cursor and the db when done.
        cursor.close();
        db.close();
        return returnList;

    }
}
