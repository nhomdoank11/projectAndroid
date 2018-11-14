package com.example.jcj.learningskid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterAdmin_english_content extends BaseAdapter {
    private AdminEnglishContentGrammarList activity;
    private List<EnglishGrammar> listProduct;
    public AdapterAdmin_english_content(AdminEnglishContentGrammarList activity, List<EnglishGrammar> listProduct) {
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
        MyHolder myHolder = null;
        if(convertView == null){
            convertView = activity.getLayoutInflater().inflate(R.layout.layout_english_grammar,null);
            myHolder =  new MyHolder();
            myHolder.textViewName = convertView.findViewById(R.id.textViewEnglishGrammar);

            convertView.setTag(myHolder);
        }else{
            myHolder = (MyHolder) convertView.getTag();
        }

        myHolder.textViewName.setText(listProduct.get(position).getName());
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EnglishGrammar exam = (EnglishGrammar) getItem(position);
//                Intent intent1 = new Intent(activity.getApplicationContext(), UserEnglishContentGrammarItemDescription.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("exam", exam);
//                intent1.putExtra("bundle", bundle);
//                activity.startActivity(intent1);
//                activity.finish();
//            }
//        });

        return convertView;
    }


    class MyHolder{
        public TextView textViewName;


    }
}
