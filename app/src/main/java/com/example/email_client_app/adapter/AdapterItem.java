 package com.example.email_client_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.email_client_app.R;
import com.example.email_client_app.helper.ItemListener;
import com.example.email_client_app.item.ItemEmail;
import com.squareup.picasso.Picasso;
import org.w3c.dom.Text;
import java.util.ArrayList;
import java.util.List;

 public class AdapterItem extends RecyclerView.Adapter<AdapterItem.MyHolder>{
    private Context context;
    private ArrayList<ItemEmail>emails;
    private ItemListener listener;
    public AdapterItem(Context context, ArrayList<ItemEmail> emails) {
        this.context = context;
        this.emails = emails;
    }

    public void setListener(ItemListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_email,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.tvNameSent.setText(emails.get(position).getName());
        holder.tvSubject.setText(emails.get(position).getSubject());
        holder.tvDescription.setText(emails.get(position).getDescription());
        holder.tvTags.setText("Tags");
        holder.tvDateSent.setText(emails.get(position).getDate());
        Picasso.with(context).load(emails.get(position).getImgProfile()).into(holder.imgProfile);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onClick(position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (listener!=null){
                    listener.onLongClick(position);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return emails.size();
    }
    public void filterList(ArrayList<ItemEmail>emailFilter){
        emails = emailFilter;
        notifyDataSetChanged();
    }
    public class MyHolder extends RecyclerView.ViewHolder {
        private ImageView imgProfile;
        private TextView tvNameSent;
        private TextView tvDateSent;
        private TextView tvSubject;
        private TextView tvDescription;
        private ImageView imgStar;
        private TextView tvTags;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imgProfile = itemView.findViewById(R.id.img_person_sent);
            tvNameSent = itemView.findViewById(R.id.tv_person_sent);
            tvDateSent = itemView.findViewById(R.id.tv_time_received);
            tvSubject = itemView.findViewById(R.id.tv_subject_sent);
            tvDescription = itemView.findViewById(R.id.tv_description);
            imgStar = itemView.findViewById(R.id.img_star);
            tvTags = itemView.findViewById(R.id.tv_tags);
        }
    }
}
