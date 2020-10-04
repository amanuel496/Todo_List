package edu.cudenver.amanuelchukala.to_do;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddNewTask extends DialogFragment {
   private EditText edtxtTitle;
   private EditText edtDescription;
   private String taskTitle;
   private String taskDescription;
   boolean isActive;
   private ImageButton btnSave;
   private ImageButton btnMainMenu;
   private Button btnClear;

   public AddNewTask() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflator = getActivity().getLayoutInflater();

        View dialogView = inflator.inflate(R.layout.dialog_task_add, null);

        edtxtTitle = dialogView.findViewById(R.id.edtxtTitle);
        edtDescription = dialogView.findViewById(R.id.edttxtDescription);


        btnSave = dialogView.findViewById(R.id.btnSave);
      //  btnClear = dialogView.findViewById(R.id.btnClear);
        btnMainMenu = dialogView.findViewById(R.id.btnMainMenu);

        btnMainMenu.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    dismiss();
                }
        }
        );

        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String taskTitle = edtxtTitle.getText().toString();
                String taskDescription = edtDescription.getText().toString();

                TaskModel taskModel = new TaskModel(0, taskTitle, taskDescription, isActive);

                Home callingActivity = (Home) getActivity();
                callingActivity.addNewContact(taskModel);

                DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
                boolean succuess = dataBaseHelper.addOne(taskModel);
                Toast.makeText(getContext(), "success", Toast.LENGTH_LONG).show();
                dismiss();
            }
        });
        builder.setView(dialogView);
        return builder.create();
    }
}
