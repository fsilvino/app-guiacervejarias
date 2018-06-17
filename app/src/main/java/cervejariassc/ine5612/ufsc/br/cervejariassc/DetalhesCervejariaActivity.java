package cervejariassc.ine5612.ufsc.br.cervejariassc;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import cervejariassc.ine5612.ufsc.br.cervejariassc.adapters.CervejaAdapter;
import cervejariassc.ine5612.ufsc.br.cervejariassc.intents.DetalhesCervejariaIntent;
import cervejariassc.ine5612.ufsc.br.cervejariassc.intents.LocalizacaoCervejariaIntent;
import cervejariassc.ine5612.ufsc.br.cervejariassc.model.Cerveja;
import cervejariassc.ine5612.ufsc.br.cervejariassc.model.Cervejaria;
import cervejariassc.ine5612.ufsc.br.cervejariassc.util.DownloadImageTask;
import cervejariassc.ine5612.ufsc.br.cervejariassc.viewmodel.CervejaViewModel;

public class DetalhesCervejariaActivity extends AppCompatActivity {

    private Cervejaria cervejaria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_cervejaria);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Detalhes");

        DetalhesCervejariaIntent intent = new DetalhesCervejariaIntent(getIntent());
        cervejaria = intent.getCervejaria();

        TextView txtNome = (TextView) findViewById(R.id.txtNomeCervejaria);
        txtNome.setText(cervejaria.getNome());

        TextView txtEndereco = (TextView) findViewById(R.id.txtEnderecoCervejaria);
        txtEndereco.setText(cervejaria.getEndereco());

        TextView txtHorario = (TextView) findViewById(R.id.txtHorarioFuncionamentoCervejaria);
        txtHorario.setText(cervejaria.getHorarioFuncionamento());

        ImageView imageView = (ImageView) findViewById(R.id.imgLogo);
        new DownloadImageTask(imageView).execute(cervejaria.getLinkLogo());

        Button btLocalizacao = (Button) findViewById(R.id.btLocalizacao);
        btLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new LocalizacaoCervejariaIntent(view.getContext(), cervejaria).getIntent());
            }
        });

        Button btVideo = (Button) findViewById(R.id.btVideo);
        btVideo.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + cervejaria.getIdVideoYoutube()));
                Intent navegadorIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.youtube.com/watch?v=" + cervejaria.getIdVideoYoutube()));
                try {
                    startActivity(appIntent);
                } catch (ActivityNotFoundException ex) {
                    startActivity(navegadorIntent);
                }
            }
        });

        ListView listView = (ListView) findViewById(R.id.lstCervejas);
        final CervejaAdapter adapter = new CervejaAdapter(this);
        listView.setAdapter(adapter);

        CervejaViewModel cervejaViewModel = ViewModelProviders.of(this).get(CervejaViewModel.class);

        cervejaViewModel.getCervejas().observe(this, new android.arch.lifecycle.Observer<List<Cerveja>>() {
            @Override
            public void onChanged(@Nullable List<Cerveja> cervejas) {
                List<Cerveja> list = new ArrayList<>();
                if (cervejas != null) {
                    for (Cerveja cerveja : cervejas) {
                        if (cerveja.getIdCervejaria() == cervejaria.getId()) {
                            list.add(cerveja);
                        }
                    }
                }
                adapter.setCervejas(list);
            }
            });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
