package cervejariassc.ine5612.ufsc.br.cervejariassc;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cervejariassc.ine5612.ufsc.br.cervejariassc.adapters.CervejariaAdapter;
import cervejariassc.ine5612.ufsc.br.cervejariassc.intents.DetalhesCervejariaIntent;
import cervejariassc.ine5612.ufsc.br.cervejariassc.model.Cervejaria;
import cervejariassc.ine5612.ufsc.br.cervejariassc.viewmodel.CervejariaViewModel;

public class MainActivity extends AppCompatActivity {

    private CervejariaViewModel cervejariaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Guia de Cervejarias");

        ListView listView = (ListView) findViewById(R.id.lstCervejarias);
        final CervejariaAdapter adapter = new CervejariaAdapter(this);
        listView.setAdapter(adapter);

        cervejariaViewModel = ViewModelProviders.of(this).get(CervejariaViewModel.class);

        cervejariaViewModel.getCervejarias().observe(this, new Observer<List<Cervejaria>>() {
            @Override
            public void onChanged(@Nullable List<Cervejaria> cervejarias) {
                adapter.setCervejarias(cervejarias);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                abreDetalhesCervejaria(adapter.getItem(i));
            }
        });
    }

    public void abreDetalhesCervejaria(Cervejaria cervejaria) {
        startActivity(new DetalhesCervejariaIntent(this, cervejaria).getIntent());
    }
}
