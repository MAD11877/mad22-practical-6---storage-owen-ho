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

import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UserDBHandler dbHandler = new UserDBHandler(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ListRecyclerAdapter mAdapter = new ListRecyclerAdapter(dbHandler.getUsers());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        MaterialButton generateBtn = findViewById(R.id.generateButton);
        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initUsers();
                finish();
                startActivity(getIntent());
            }
        });
    }

    public void initUsers(){ //initialize 20 users in the SQLite database
        ArrayList<User> userList = new ArrayList<User>();
        for (int i=0;i<20;i++){
            Random r = new Random();
            User u = new User();
            u.name = "Name"+r.nextInt();
            u.description = "Description "+r.nextInt();
            u.id = i;
            u.followed = r.nextBoolean();
            userList.add(u);
        }

        UserDBHandler dbHandler = new UserDBHandler(this);
        for (User u : userList){
            dbHandler.addUser(u);
        }
    }
}