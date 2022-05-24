package sg.np.edu.OwenHo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ArrayList<User> userList = new ArrayList<User>();
        for (int i=0;i<20;i++){
            Random r = new Random();
            userList.add(new User("Name"+r.nextInt(),"Description "+r.nextInt(),i,r.nextBoolean()));
        }
        //Log.d("OWEN","Number of users: "+userList.size());
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ListRecyclerAdapter mAdapter = new ListRecyclerAdapter(userList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }
}