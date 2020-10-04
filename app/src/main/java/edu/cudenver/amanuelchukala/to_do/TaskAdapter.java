package edu.cudenver.amanuelchukala.to_do;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ListItemHolder> {
    private Home home;
    private ArrayList<TaskModel> taskModelList;

    public TaskAdapter(Home home, ArrayList<TaskModel> taskModelList) {
        this.home = home;
        this.taskModelList = taskModelList;
    }

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ListItemHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {
        TaskModel taskModel = taskModelList.get(position);
        holder.cbTask.setText(taskModel.getTaskTitle());
        holder.position = position;
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return taskModelList.size();
    }

    public class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView cbTask;

        private int position;

        public ListItemHolder (View view) {
            super(view);

            cbTask = view.findViewById(R.id.txtTaskTitle);
            view.setClickable(true);
            view.setOnClickListener(this);
        }

        public void onClick (View view) {
            home.showTasks(getAdapterPosition());
        }

    }
}
