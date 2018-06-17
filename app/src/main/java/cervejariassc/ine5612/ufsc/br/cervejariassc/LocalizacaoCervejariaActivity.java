package cervejariassc.ine5612.ufsc.br.cervejariassc;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import cervejariassc.ine5612.ufsc.br.cervejariassc.intents.LocalizacaoCervejariaIntent;
import cervejariassc.ine5612.ufsc.br.cervejariassc.model.Cervejaria;

public class LocalizacaoCervejariaActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Cervejaria cervejaria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacao_cervejaria);

        cervejaria = new LocalizacaoCervejariaIntent(getIntent()).getCervejaria();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker and move the camera
        LatLng coord = new LatLng(cervejaria.getLatitude(), cervejaria.getLongitude());
        mMap.addMarker(new MarkerOptions().position(coord).title(cervejaria.getNome()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coord));
    }
}
