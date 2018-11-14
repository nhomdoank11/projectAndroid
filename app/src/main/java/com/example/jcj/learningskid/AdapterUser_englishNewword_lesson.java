package com.example.jcj.learningskid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterUser_englishNewword_lesson extends BaseAdapter {
    private UserEnglishNewwordLesson activity;
    private List<EnglishNewwordLesson> lessonList;
    public AdapterUser_englishNewword_lesson(UserEnglishNewwordLesson activity, List<EnglishNewwordLesson> lessonList){
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyHolder myHolder = null;
        if(convertView == null){
            convertView = activity.getLayoutInflater().inflate(R.layout.layout_english_newword_lesson, null);
            myHolder = new MyHolder();
            myHolder.txtID = convertView.findViewById(R.id.txtID);
            myHolder.txtName = convertView.findViewById(R.id.txtName);
            convertView.setTag(myHolder);
        } else {
            myHolder = (MyHolder) convertView.getTag();
        }
        myHolder.txtID.setText(lessonList.get(position).getId()+"");
        myHolder.txtName.setText(lessonList.get(position).getName());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnglishNewwordLesson lesson = (EnglishNewwordLesson) getItem(position);
                Intent intent1 = new Intent(activity.getApplicationContext(), UserEnglishNewwordContent.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("exam", exam);
                intent1.putExtra("lessonID", lesson.getId());
                activity.startActivity(intent1);
                activity.finish();
            }
        });

        return convertView;
    }
    class MyHolder {
        public TextView txtID;
        public TextView txtName;

    }
}
