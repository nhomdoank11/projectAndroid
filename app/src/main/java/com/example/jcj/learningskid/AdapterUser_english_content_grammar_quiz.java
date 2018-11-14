package com.example.jcj.learningskid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterUser_english_content_grammar_quiz extends BaseAdapter {
    private UserEnglishContentGrammarQuiz activity;
    private List<EnglishGrammarQuiz> listProduct;
    public AdapterUser_english_content_grammar_quiz(UserEnglishContentGrammarQuiz activity, List<EnglishGrammarQuiz> listProduct) {
        this.activity = activity;
        this.listProduct = listProduct;
    }
    @Override
    public int getCount() {
        return listProduct.size();
    }

    @Override
    public Object getItem(int position) {
        return  listProduct.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        AdapterUser_english_content_grammar_quiz.MyHolder myHolder = null;
        if(convertView == null){
            convertView = activity.getLayoutInflater().inflate(R.layout.layout_english_grammar_quiz,null);
            myHolder = new AdapterUser_english_content_grammar_quiz.MyHolder();
            myHolder.textViewName = convertView.findViewById(R.id.grammarQuizName);
            myHolder.textViewPass = convertView.findViewById(R.id.grammarQuizPass);
            convertView.setTag(myHolder);
        }else{
            myHolder = (AdapterUser_english_content_grammar_quiz.MyHolder) convertView.getTag();
        }

        myHolder.textViewName.setText(listProduct.get(position).getName());
        myHolder.textViewPass.setText(String.valueOf(listProduct.get(position).getPassCondition())+"%" );
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnglishGrammarQuiz exam = (EnglishGrammarQuiz) getItem(position);
                Intent intent1 = new Intent(activity.getApplicationContext(), UserEnglishContentGrammarQuizTest.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("exam", exam);
                intent1.putExtra("bundle", bundle);
                activity.startActivity(intent1);
                activity.finish();
            }
        });

        return convertView;
    }


    class MyHolder{
        public TextView textViewName;
        public TextView textViewPass;

    }
}
