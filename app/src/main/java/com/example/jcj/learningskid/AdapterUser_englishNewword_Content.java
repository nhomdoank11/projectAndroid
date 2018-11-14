package com.example.jcj.learningskid;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterUser_englishNewword_Content extends BaseAdapter {
    private UserEnglishNewwordContent activity;
    private List<EnglishNewwordContent> lessonList;
    public AdapterUser_englishNewword_Content(UserEnglishNewwordContent activity, List<EnglishNewwordContent> lessonList){
        this.activity = activity;
        this.lessonList = lessonList;
    }

    @Override
    public int getCount() {
        return lessonList.size();
    }

    @Override
    public Object getItem(int position) {
        return lessonList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder myHolder = null;
        if(convertView == null){
            convertView = activity.getLayoutInflater().inflate(R.layout.layout_english_newword_content, null);
            myHolder = new MyHolder();
            myHolder.txtID = convertView.findViewById(R.id.txtwordid);
            myHolder.txtWord = convertView.findViewById(R.id.txtword);
            convertView.setTag(myHolder);
        } else {
            myHolder = (MyHolder) convertView.getTag();
        }
        myHolder.txtID.setText(lessonList.get(position).getId()+"");
        myHolder.txtWord.setText(lessonList.get(position).getWord());
        return convertView;
    }
    class MyHolder {
        public TextView txtID;
        public TextView txtWord;

    }
}
