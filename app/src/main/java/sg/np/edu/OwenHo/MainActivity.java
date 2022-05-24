package sg.np.edu.OwenHo;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<User> data = ListRecyclerAdapter.data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserDBHandler dbHandler = new UserDBHandler(this);

        Intent i = getIntent();
        String uName = i.getStringExtra("name");
        String uDesc = i.getStringExtra("description");
        Integer pos = i.getIntExtra("position",0);

        TextView nameTV = findViewById(R.id.textView);
        TextView descTV = findViewById(R.id.textView2);
        Button followButton = findViewById(R.id.followBtn);
        Button messageButton = findViewById(R.id.msgBtn);

        nameTV.setText(uName);
        descTV.setText(uDesc);
        if(data.get(pos).followed){
            followButton.setText("Unfollow");
        }

        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.get(pos).followed){
                    data.get(pos).followed = false;
                    dbHandler.updateUser(data.get(pos));
                    followButton.setText("Follow");
                    Toast.makeText(MainActivity.this,"Unfollowed",Toast.LENGTH_SHORT).show();
                }
                else{
                    data.get(pos).followed = true;
                    dbHandler.updateUser(data.get(pos));
                    followButton.setText("Unfollow");
                    Toast.makeText(MainActivity.this,"Followed",Toast.LENGTH_SHORT).show();
                }
            }
        });

        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,MessageGroup.class);
                startActivity(i);
            }
        });
    }
}