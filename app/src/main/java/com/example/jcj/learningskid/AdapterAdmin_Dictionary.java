package com.example.jcj.learningskid;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AdapterAdmin_Dictionary extends BaseAdapter  {

    private AdminAcceptDictionary activity;
    private List<Dictionary> dictionaryList2;
    private List<Dictionary> checkDuplicate = null;

    public AdapterAdmin_Dictionary(AdminAcceptDictionary activity, List<Dictionary> dictionaryList2) {
        this.activity = activity;
        this.dictionaryList2 = dictionaryList2;
    }


    @Override
    public int getCount() {
        return dictionaryList2.size();
    }

    @Override
    public Object getItem(int position) {
        return dictionaryList2.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AdapterAdmin_Dictionary.MyHolder myHolder = null;
        if(convertView == null){
            convertView = activity.getLayoutInflater().inflate(R.layout.layout_admin_dictionary,null);
            myHolder = new AdapterAdmin_Dictionary.MyHolder();
            myHolder.textView = convertView.findViewById(R.id.txtViewDic);
            myHolder.btnAccept = convertView.findViewById(R.id.btnAccpet);
            myHolder.btnCancel = convertView.findViewById(R.id.btnCancel);
            final String Id = dictionaryList2.get(position).getId();
            final String Vi = dictionaryList2.get(position).getVietNam();
            final String En = dictionaryList2.get(position).getEnglish();
            final String Ex = dictionaryList2.get(position).getExample();
            myHolder.textView.setText( "Vi:" + Vi + "\n" + "En:" + En + "\n" + "Ex: " + Ex);
            myHolder.btnAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    final Dictionary valuses2 = new Dictionary();
                    valuses2.setId(Id);
                    valuses2.setVietNam(Vi);
                    valuses2.setEnglish(En);
                    valuses2.setExample(Ex);
                    MainActivity.learningskid.collection("DicProduct").whereEqualTo("english", En).get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot documentSnapshots) {
                                    checkDuplicate = new ArrayList<>();
                                    for (DocumentSnapshot doc : documentSnapshots) {
                                        Dictionary dictionary = new Dictionary(doc.getString(Id), doc.getString(Vi), doc.getString(En),
                                                doc.getString(Ex));
                                        checkDuplicate.add(dictionary);
                                    }
                                    if (checkDuplicate.size() > 0) {
                                        Toast.makeText(v.getContext(), "This word was in the dictionary!", Toast.LENGTH_SHORT).show();
                                    } else{
                                        MainActivity.learningskid.collection("DicProduct").document(Id).set(valuses2)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Toast.makeText(v.getContext(), "SUCCESSFULLY!", Toast.LENGTH_SHORT).show();
                                                        MainActivity.learningskid.collection("Dictionary").document(Id).delete()
                                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void aVoid) {
                                                                        Toast.makeText(v.getContext(), "Delete Success!", Toast.LENGTH_SHORT).show();
                                                                        Intent intent = new Intent(v.getContext(),AdminSearchDictionary.class);
                                                                        activity.startActivity(intent);
                                                                        activity.finish();
                                                                    }
                                                                })
                                                                .addOnFailureListener(new OnFailureListener() {
                                                                    @Override
                                                                    public void onFailure(@NonNull Exception e) {
                                                                        Toast.makeText(v.getContext(), "Delete Fail!", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                });
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(v.getContext(), "ERROR!", Toast.LENGTH_LONG).show();
                                                        Log.d(MainActivity.TAG, e.toString());
                                                    }
                                                });
                                    }
                                }
                            });
                }
            });
            myHolder.btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    MainActivity.learningskid.collection("Dictionary").document(Id).delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(v.getContext(),"Delete Success!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(v.getContext(),AdminSearchDictionary.class);
                                    activity.startActivity(intent);
                                    activity.finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(v.getContext(),"Delete Fail", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            });
            convertView.setTag(myHolder);
        }else {
            myHolder = (AdapterAdmin_Dictionary.MyHolder) convertView.getTag();
        }

        return convertView;
    }



    class MyHolder {
        public TextView textView;
        public Button btnAccept,btnCancel;
    }
}
