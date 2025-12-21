package com.example.memosample2;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MemoAdapter extends BaseAdapter {
    ArrayList<MemoItem> dataset = new ArrayList<>();
    @Override
    public int getCount() {
        return dataset.size();
    }

    @Override
    public Object getItem(int position) {
        return dataset.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(
                            Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_memo,
                    parent,
                    false);
        }
        MemoItem item = dataset.get(position);
        TextView tvTitle = convertView.findViewById(R.id.tvTitle);
        tvTitle.setText(item.getTitle());

        return convertView;
    }

    public void addMemoItemData(String title, String content){
        dataset.add(new MemoItem(title, content));
    }

    public void editMemoItemData(int position,
                                 String title,
                                 String content){
        dataset.set(position, new MemoItem(title, content));

        Log.i("setData", dataset.get(position).getContent());
    }


    public void removeMemoItemData(int position){
        dataset.remove(position);
    }
}
