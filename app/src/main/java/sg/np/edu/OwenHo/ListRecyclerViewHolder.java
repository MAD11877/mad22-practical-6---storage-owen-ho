package sg.np.edu.OwenHo;

import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class ListRecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView nametxt;
    TextView desctxt;
    ImageView profilePic;
    ConstraintLayout userItem;
    public ListRecyclerViewHolder(View itemView) {
        super(itemView);
        nametxt = itemView.findViewById(R.id.name);
        desctxt = itemView.findViewById(R.id.description);
        profilePic = itemView.findViewById(R.id.profilepic);
        userItem = itemView.findViewById(R.id.useritemview);
    }
}
