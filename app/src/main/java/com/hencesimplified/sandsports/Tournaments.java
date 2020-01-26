package com.hencesimplified.sandsports;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Tournaments extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ListView t_listview;
    tournament_cls t_list_cls;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    String t_name_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournaments);

        Intent intent=getIntent();
        t_name_id=intent.getStringExtra("games_id");

        firebaseDatabase=FirebaseDatabase.getInstance();
        t_listview=findViewById(R.id.tourn_list);

        t_list_cls=new tournament_cls();


        databaseReference=firebaseDatabase.getReference("tournament/");

        list=new ArrayList<>();
        adapter=new ArrayAdapter<>(getApplicationContext(),R.layout.txtviewlist,R.id.txt_for_list,list);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    t_list_cls=ds.getValue(tournament_cls.class);

                    if(t_name_id.equals(t_list_cls.getGame()))
                    {
                        list.add(t_list_cls.getT_name());
                    }

                }

                try{
                    t_listview.setAdapter(adapter);
                }catch (Exception e)
                {
                    System.out.println(e);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
