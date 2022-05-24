package sg.np.edu.OwenHo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

public class ListRecyclerAdapter extends RecyclerView.Adapter<ListRecyclerViewHolder>{

    public static ArrayList<User> data;

    public ListRecyclerAdapter(ArrayList<User> input) {
        data = input;
    }

    public int getItemViewType(int position){
        User u = data.get(position);
        if (u.name.endsWith("7")){
            return 1;//1 is special layout
        }
        else{
            return 0;//0 is default layout
        }
    }

    public ListRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = null;
        if(viewType==1){
            item = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.user_list_alternative,
                    parent,
                    false);
        }
        else{
            item = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.user_list,
                    parent,
                    false);
        }
        return new ListRecyclerViewHolder(item);
    }
    public void onBindViewHolder(ListRecyclerViewHolder holder, int position) {
        User u = data.get(position);
        holder.nametxt.setText(u.name);
        holder.desctxt.setText(u.description);
        holder.userItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.userItem.getContext());
                builder.setTitle("Profile");
                builder.setMessage(u.name);
                builder.setCancelable(false);
                builder.setPositiveButton("View", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        Random r = new Random();
                        Intent i = new Intent(holder.userItem.getContext(),MainActivity.class);
                        i.putExtra("name",holder.nametxt.getText().toString());
                        i.putExtra("description",holder.desctxt.getText().toString());
                        i.putExtra("position",holder.getAdapterPosition());

                        holder.userItem.getContext().startActivity(i);
                    }
                });
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){

                    }
                });
                builder.show();
            }
        });
    }
    public int getItemCount() {
        return data.size();
    }
}
