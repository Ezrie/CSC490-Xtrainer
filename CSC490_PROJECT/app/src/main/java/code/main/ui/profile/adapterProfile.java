package code.main.ui.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import code.main.R;

public class adapterProfile extends RecyclerView.Adapter<adapterProfile.MyViewHolder> {

    String s1[], s2[];
    Context cot;

    public adapterProfile(Context ct, String s1[], String s2[]) {
        cot = ct;
        this.s1 = s1;
        this.s2 = s2;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(cot);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.test1.setText(this.s1[position]);
        holder.test2.setText(this.s2[position]);
    }

    @Override
    public int getItemCount() {
        return this.s2.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView test1, test2;

        ConstraintLayout mainrow;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            test1 = itemView.findViewById(R.id.textView3);
            test2 = itemView.findViewById(R.id.exr1);
            mainrow = itemView.findViewById(R.id.mainrow);
        }
    }

}
