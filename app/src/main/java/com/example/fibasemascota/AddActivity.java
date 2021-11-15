package com.example.fibasemascota;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    EditText nombre,tipo,edad;
    Button btnRegistrar,btnCancel;
    CheckBox rabia,distemper,parvovirus;

    TextView tv_valor,valopm,valopme,valorgrande,valorgigante;
    Spinner spinner;
    DatabaseReference dbref;
    ValueEventListener listener;
    ArrayList<String> list,list2;
    ArrayAdapter<String> adapter,adapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        nombre=findViewById(R.id.txtNombreR);
        tipo=findViewById(R.id.txtTipoR);
        edad=findViewById(R.id.txtEdadR);
        btnRegistrar=findViewById(R.id.btnRegistrar);
        btnCancel=findViewById(R.id.btnBack);

        rabia=findViewById(R.id.vRabia);
        distemper=findViewById(R.id.vDistemper);
        parvovirus=findViewById(R.id.vParvovirus);

        tv_valor=findViewById(R.id.valosipin);
        valopm=findViewById(R.id.valorpesom);
        valopme=findViewById(R.id.valorpesome);
        valorgrande=findViewById(R.id.valorgrande);
        valorgigante=findViewById(R.id.valorgigante);
        spinner=findViewById(R.id.idspiner);


        DatabaseReference ref1=dbref=FirebaseDatabase.getInstance().getReference("edadM");
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list=new ArrayList<String>();
                for (DataSnapshot areaSnapshot: snapshot.getChildren()){


                    String areaName=areaSnapshot.child("cachorro").getValue(String.class);
                    String areaapeso=areaSnapshot.child("mini").getValue(String.class);
                    String areaapeso2=areaSnapshot.child("mediano").getValue(String.class);
                    String areaapeso3=areaSnapshot.child("grande").getValue(String.class);
                    String areaapeso4=areaSnapshot.child("gigante").getValue(String.class);

                    list.add(areaName+" % "+areaapeso+" : "+areaapeso2+"$"+areaapeso3+"#"+areaapeso4);
                }
                adapter=new ArrayAdapter<String>(AddActivity.this,R.layout.support_simple_spinner_dropdown_item,list);
                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String item=parent.getSelectedItem().toString();

                        String pm=item;
                        int cont=item.length();
                        String tot=String.valueOf(cont);




                        String pm2=item;
                        String pm3=item;

                        String parte1=pm3.substring(0,pm3.lastIndexOf('%'));
                        String parte2=pm2.substring(pm2.lastIndexOf('%')+1,pm2.lastIndexOf(':'));
                        String parte3=pm2.substring(pm2.lastIndexOf(':')+1,pm2.lastIndexOf('$'));
                        String parte4=pm2.substring(pm2.lastIndexOf('$')+1,pm2.lastIndexOf('#'));
                        String parte5=pm2.substring(pm2.lastIndexOf('#')+1);

                        tv_valor.setText(parte1);
                        valopm.setText(parte2);
                        valopme.setText(parte3);
                        valorgrande.setText(parte4);
                        valorgigante.setText(parte5);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    insertaDatos();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    finish();
            }
        });



    }
    public void datosSelect(){
        listener=dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot mydata: snapshot.getChildren())
                    list.add(mydata.getValue().toString());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void vacunas(){
        if (rabia.isChecked()==true)
            rabia.setText("Rabia");

        else
            rabia.setText("");

        if (distemper.isChecked()==true)
            distemper.setText("Distemper");
        else
            distemper.setText("");

        if (parvovirus.isChecked()==true)
            parvovirus.setText("Parvovirus");
        else
            parvovirus.setText("");
    }
    private void insertaDatos(){

        vacunas();

        Map<String,Object> map=new HashMap<>();
        map.put("nombre",nombre.getText().toString());
        map.put("tipo",tipo.getText().toString());
        map.put("edad",edad.getText().toString());

        map.put("vDistemper",distemper.getText().toString());
       map.put("vParvovirus",parvovirus.getText().toString());
        map.put("vRabia",rabia.getText().toString());

        map.put("tamanio",tv_valor.getText().toString());
        map.put("tmini",valopm.getText().toString());
        map.put("tmediano",valopme.getText().toString());
        map.put("tgrande",valorgrande.getText().toString());
        map.put("tgigante",valorgigante.getText().toString());


        FirebaseDatabase.getInstance().getReference().child("mascotag").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this,"Se registro correctamenta",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddActivity.this,"Error No se registro ",Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void limpiar(){
        nombre.setText("");
        tipo.setText("");
        edad.setText("");
        distemper.setText("");
        parvovirus.setText("");
        rabia.setText("");
        tv_valor.setText("");
        valopm.setText("");
        valopme.setText("");
        valorgrande.setText("");
        valorgigante.setText("");

    }

}