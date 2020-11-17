package code.main.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import code.main.R;

public class AdapterProfile extends RecyclerView.Adapter<AdapterProfile.MyViewHolder>{

    String days[], selectedWorkout, ex1, ex2, ex3, ex4, w1, w2, w3, w4;
    Context cot;

    public AdapterProfile(Context ct, String days[], String sWork, String e1, String e2,String e3,String e4, String w1, String w2,String w3,String w4){
        cot = ct;
        this.days = days;
        this.selectedWorkout = sWork;
        this.w1 = w1;
        this.w2 = w2;
        this.w3 = w3;
        this.w4 = w4;
        this.ex1 = e1;
        this.ex2 = e2;
        this.ex3 = e3;
        this.ex4 = e4;



    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(cot);
        View view =    inflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
    holder.dayText.setText(this.days[position]);
    holder.workTypeText.setText(this.selectedWorkout);
    holder.w1.setText(this.w1);
    holder.w2.setText(this.w2);
    holder.w3.setText(this.w3);
    holder.w4.setText(this.w4);

    holder.ex1.setText(this.ex1);
    holder.ex2.setText(this.ex2);
    holder.ex3.setText(this.ex3);
    holder.ex4.setText(this.ex4);

        holder.mainrow.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(cot, ProfileActivityDetails.class);
            intent.putExtra("day", days[position]);
            cot.startActivity(intent);
        }
    });

    }

    @Override
    public int getItemCount() {
        return this.days.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView dayText, workTypeText, ex1, ex2, ex3, ex4, w1, w2, w3, w4;

        ConstraintLayout mainrow;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dayText = itemView.findViewById(R.id.day);
            workTypeText = itemView.findViewById(R.id.selectedWorkout);
            ex1 = itemView.findViewById(R.id.exr1);
            ex2 = itemView.findViewById(R.id.exr2);
            ex3 = itemView.findViewById(R.id.exr3);
            ex4 = itemView.findViewById(R.id.exr4);

            w1 = itemView.findViewById(R.id.weight1);
            w2 = itemView.findViewById(R.id.weight2);
            w3 = itemView.findViewById(R.id.weight3);
            w4 = itemView.findViewById(R.id.weight4);

            mainrow = itemView.findViewById(R.id.mainrow);
        }
    }

}


