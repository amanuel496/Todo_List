package edu.cudenver.amanuelchukala.to_do;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class TaskView extends DialogFragment {
    private TextView edtxtTaskName;
    private TextView edtxtDetail;

    private ImageButton btnMainMenu;
    private ImageButton btnDelete;
    private ImageButton btnSave;
    private boolean isActive;

    private TaskModel taskModel;

    public TaskView(TaskModel taskModel) {
        this.taskModel = taskModel;
   }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_task_show, null);

        edtxtTaskName = dialogView.findViewById(R.id.edtxtTitle1);
        edtxtDetail = dialogView.findViewById(R.id.edttxtDescription1);

        edtxtTaskName.setText(taskModel.getTaskTitle());
        edtxtDetail.setText(taskModel.getTaskDescription());

        btnMainMenu = dialogView.findViewById(R.id.btnMainMenu);
        btnSave = dialogView.findViewById(R.id.btnSave);
        btnDelete = dialogView.findViewById(R.id.btnDelete);

        builder.setView(dialogView).setMessage("");

        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
                dismiss();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Home callingActivity = (Home) getActivity();
                callingActivity.delContact(taskModel);
                dismiss();
            }});
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Home callingActivity = (Home) getActivity();
                callingActivity.delContact(taskModel);

                String taskTitle = edtxtTaskName.getText().toString();
                String taskDescription = edtxtDetail.getText().toString();

                TaskModel taskModel = new TaskModel(0, taskTitle, taskDescription, isActive);

                //Home callingActivity = (Home) getActivity();
                callingActivity.addNewContact(taskModel);

                DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
                boolean succuess = dataBaseHelper.addOne(taskModel);
                Toast.makeText(getContext(), "success", Toast.LENGTH_LONG).show();
                dismiss();
            }
        });


        return builder.create();
    }}