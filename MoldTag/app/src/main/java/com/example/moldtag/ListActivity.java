package com.example.moldtag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> kalıpListesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        listView = findViewById(R.id.listView);

        Intent intent = getIntent();
        kalıpListesi = intent.getStringArrayListExtra("kalıpListesi");

        if (kalıpListesi != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, kalıpListesi) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = convertView;

                    if (view == null) {
                        LayoutInflater inflater = LayoutInflater.from(getContext());
                        view = inflater.inflate(R.layout.list_item, null);
                    }

                    TextView textView = view.findViewById(R.id.textView);
                    ImageView imageView = view.findViewById(R.id.imageView);

                    textView.setText(kalıpListesi.get(position));

                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Çarpıya tıklandığında, numarayı listeden kaldır
                            kalıpListesi.remove(position);
                            notifyDataSetChanged(); // Değişikliği ListView'a bildir
                            Toast.makeText(ListActivity.this, "Kalıp listeden silindi.", Toast.LENGTH_SHORT).show();
                        }
                    });

                    return view;
                }
            };
            listView.setAdapter(adapter);
        } else {
            Toast.makeText(this, "Kalıp listesi boş.", Toast.LENGTH_SHORT).show();
        }

    }
}
