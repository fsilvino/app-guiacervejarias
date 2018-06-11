package cervejariassc.ine5612.ufsc.br.cervejariassc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import cervejariassc.ine5612.ufsc.br.cervejariassc.intents.DetalhesCervejariaIntent;
import cervejariassc.ine5612.ufsc.br.cervejariassc.intents.LocalizacaoCervejariaIntent;
import cervejariassc.ine5612.ufsc.br.cervejariassc.model.Cerveja;
import cervejariassc.ine5612.ufsc.br.cervejariassc.model.Cervejaria;
import cervejariassc.ine5612.ufsc.br.cervejariassc.util.DownloadImageTask;

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
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
