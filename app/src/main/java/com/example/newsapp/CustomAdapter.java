package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<News> {
    Context context;
    List<News> list;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<News> objects) {
        super(context, resource, objects);
        this.context = context;
        this.list = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE );
        view = inflater.inflate(R.layout.row,null);
        TextView heading = (TextView) view.findViewById(R.id.newsHeading);
        TextView readMore = (TextView) view.findViewById(R.id.readMore);
        ImageView imageView = (ImageView) view.findViewById(R.id.newsImage);
        LinearLayout clickable = (LinearLayout) view.findViewById(R.id.clickable);
        clickable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = Integer.toString( list.get(position).id);
                String url = list.get(position).url;
                String heading = list.get(position).heading;
                String desc = list.get(position).description;
                String reference = list.get(position).reference;

                Intent intent = new Intent(getContext(), NewsDetails.class);
                intent.putExtra("id", id);
                intent.putExtra("url", url);
                intent.putExtra("heading", heading);
                intent.putExtra("description", desc);
                intent.putExtra("reference", reference);
                context.startActivity(intent);
            }
        });

        heading.setText(list.get(position).heading);
        Glide.with(context).load(list.get(position).url).into(imageView);

        return view;
    }

}
