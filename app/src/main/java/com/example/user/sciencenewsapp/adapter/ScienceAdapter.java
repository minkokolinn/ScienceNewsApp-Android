package com.example.user.sciencenewsapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.sciencenewsapp.R;
import com.example.user.sciencenewsapp.data.vo.Items;
import com.example.user.sciencenewsapp.data.vo.Science;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ScienceAdapter extends RecyclerView.Adapter<ScienceAdapter.ScienceHolder>{
    Context ctxt;
    List<Items> items;

    public ScienceAdapter(Context ctxt, List<Items> items) {
        this.ctxt = ctxt;
        this.items = items;
    }

    @NonNull
    @Override
    public ScienceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li=LayoutInflater.from(ctxt);
        View v=li.inflate(R.layout.sample_view,parent,false);
        ScienceHolder sh=new ScienceHolder(v);
        return sh;
    }

    @Override
    public void onBindViewHolder(@NonNull ScienceHolder holder, int position) {
        Items i=items.get(position);
        Picasso.get().load(i.getEnclosure().getLink())
                .into(holder.iv);
        holder.tvTitle.setText(i.getTitle());
        holder.tvContent.setText(i.getContent());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ScienceHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tvTitle,tvContent;
        public ScienceHolder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv_sv);
            tvTitle=itemView.findViewById(R.id.tv_title_sv);
            tvContent=itemView.findViewById(R.id.tv_content_sv);
        }
    }
}
