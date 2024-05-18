package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowDataActivity extends AppCompatActivity {

RecyclerView recyclerView;
DatabaseHelper helper;
HashMap<String,String>hashMap;
ArrayList<HashMap<String,String>>arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
recyclerView=findViewById(R.id.recyclerView);
helper=new DatabaseHelper(this);

        Intent intent=getIntent();
       String id=intent.getStringExtra("ID");


        Cursor cursor=helper.GetAllData(id);
        if(cursor!=null&& cursor.getCount()>0){
            while(cursor.moveToNext()){
                int Id=cursor.getInt(0);
                String name=cursor.getString(1);
                String clas=cursor.getString(2);
                String age=cursor.getString(3);
                String groupe=cursor.getString(4);
                String number =cursor.getString(5);

                hashMap=new HashMap<>();
                hashMap.put("Id",""+Id);
                hashMap.put("name",name);
                hashMap.put("clas",clas);
                hashMap.put("age",age);
                hashMap.put("groupe",groupe);
                hashMap.put("number",number);
                arrayList.add(hashMap);
            }
            recyclerView.setAdapter(new XAdapter());
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

    }




public  class XAdapter extends  RecyclerView.Adapter<XAdapter.Hoder>{


    public  class  Hoder extends  RecyclerView.ViewHolder{

        TextView tvName,tvClass,tvAge,tvNumber,tvCall,tvUpdate,tvDelete;


        public Hoder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName);
            tvClass=itemView.findViewById(R.id.tvClass);
            tvAge=itemView.findViewById(R.id.tvAge);
            tvNumber=itemView.findViewById(R.id.tvNumber);
            tvCall=itemView.findViewById(R.id.tvCall);
            tvUpdate=itemView.findViewById(R.id.tvUpdate);
            tvDelete=itemView.findViewById(R.id.tvDelete);

        }
    }




    @NonNull
    @Override
    public Hoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.showitem,parent,false);
        return new Hoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Hoder holder, int position) {

        hashMap=arrayList.get(position);
        String Id=hashMap.get("Id");
        String name=hashMap.get("name");
        String clas=hashMap.get("clas");
        String age=hashMap.get("age");
        String groupe=hashMap.get("groupe");
        String number=hashMap.get("number");
        holder.tvName.setText("Name: "+name);
        holder.tvClass.setText("Class: "+clas);
        holder.tvAge.setText("Age: "+age);
        holder.tvNumber.setText("Number: "+number);

        holder.tvCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });

        holder.tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowDataActivity.this, UpdateActivity.class);
                intent.putExtra("ID", Id);
                intent.putExtra("name", name);
                intent.putExtra("clas", clas);
                intent.putExtra("age", age);
                intent.putExtra("number", number);
                startActivity(intent);
            }
        });

        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.Deletee(Id);
                Toast.makeText(ShowDataActivity.this,"Delete Item",Toast.LENGTH_LONG).show();
                startActivity(new Intent(ShowDataActivity.this,HomeActivity.class));
            }
        });




    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}








}