package com.example.moldtag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editTextKalıpNumarası;
    Button buttonEkle, buttonKalıpAra, buttonListeleriGör, buttonDestek;
    ListView listViewKalıplar;
    List<String> kalıpListesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextKalıpNumarası = findViewById(R.id.editTextKalıpNumarası);
        buttonEkle = findViewById(R.id.buttonEkle);
        buttonKalıpAra = findViewById(R.id.buttonKalıpAra);
        buttonListeleriGör = findViewById(R.id.buttonListeleriGör);
        buttonDestek = findViewById(R.id.buttonDestek);
        listViewKalıplar = findViewById(R.id.listViewKalıplar);

        kalıpListesi = new ArrayList<>();

        buttonEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String girilenKalıp = editTextKalıpNumarası.getText().toString().trim();
                if (!girilenKalıp.isEmpty()) {
                    kalıpListesi.add(girilenKalıp);
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, kalıpListesi);
                    listViewKalıplar.setAdapter(adapter);
                    editTextKalıpNumarası.setText("");
                    Toast.makeText(MainActivity.this, "Kalıp başarıyla eklendi.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonKalıpAra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String arananKalıp = editTextKalıpNumarası.getText().toString().trim();
                if (!arananKalıp.isEmpty()) {
                    if (kalıpListesi.contains(arananKalıp)) {
                        Toast.makeText(MainActivity.this, "Kalıp mevcut!", Toast.LENGTH_SHORT).show();
                        getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                        getWindow().getDecorView().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                            }
                        }, 1000);
                    } else {
                        Toast.makeText(MainActivity.this, "Kalıp mevcut değil!", Toast.LENGTH_SHORT).show();
                        getWindow().getDecorView().setBackgroundColor(Color.RED);
                        getWindow().getDecorView().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                            }
                        }, 1000);
                    }
                }
            }
        });

        buttonListeleriGör.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!kalıpListesi.isEmpty()) { // Liste boş değilse Intent'i gönder
                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
                    intent.putStringArrayListExtra("kalıpListesi", (ArrayList<String>) kalıpListesi);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Liste boş olduğu için gösterilemiyor.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        buttonDestek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Bana ulaşın: ertugrull_yldrm@outlook.com", Toast.LENGTH_LONG).show();
            }
        });

        listViewKalıplar.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                kalıpListesi.remove(position);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, kalıpListesi);
                listViewKalıplar.setAdapter(adapter);
                return true;
            }
        });
    }
}