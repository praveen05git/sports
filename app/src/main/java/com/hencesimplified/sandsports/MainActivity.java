package com.hencesimplified.sandsports;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ListView games_listview;
    games_list g_list_cls;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase=FirebaseDatabase.getInstance();
        games_listview=findViewById(R.id.games_list);

        g_list_cls=new games_list();

        databaseReference=firebaseDatabase.getReference("games/");

        list=new ArrayList<>();
        adapter=new ArrayAdapter<>(getApplicationContext(),R.layout.txtviewlist,R.id.txt_for_list,list);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    g_list_cls=ds.getValue(games_list.class);
                    list.add(g_list_cls.getG_name());
                }

                try{
                    games_listview.setAdapter(adapter);
                }catch (Exception e)
                {
                    System.out.println(e);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        games_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Toast.makeText(getApplicationContext(),list.get(i),Toast.LENGTH_SHORT).show();

                Intent tournament_view_intent=new Intent(getApplicationContext(),Tournaments.class);
                tournament_view_intent.putExtra("games_id",list.get(i));
                startActivity(tournament_view_intent);



            }
        });


    }
}
