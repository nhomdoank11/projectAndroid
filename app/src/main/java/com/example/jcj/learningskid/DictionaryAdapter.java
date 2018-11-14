package com.example.jcj.learningskid;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DictionaryAdapter extends BaseAdapter {

    private AdminSearchDictionary activity;
    private List<Dictionary> dictionaryList;

    public DictionaryAdapter(AdminSearchDictionary activity, List<Dictionary> dictionaryList) {
        this.activity = activity;
        this.dictionaryList = dictionaryList;
    }

    @Override
    public int getCount() {
        return dictionaryList.size();
    }

    @Override
    public Object getItem(int position) {
        return dictionaryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder myHolder = null;
        if(convertView == null){
            convertView = activity.getLayoutInflater().inflate(R.layout.layout_dictionary,null);
            myHolder = new MyHolder();
            myHolder.textView = convertView.findViewById(R.id.txtView);
            String vi = dictionaryList.get(position).getVietNam();
            String en = dictionaryList.get(position).getEnglish();
            String ex = dictionaryList.get(position).getExample();
            myHolder.textView.setText( "EN: " + vi + "\n" + "EX: " + ex);

            convertView.setTag(myHolder);
        }else {
            myHolder = (MyHolder) convertView.getTag();
        }

        return convertView;
    }



    class MyHolder {
        public TextView textView;
    }
}