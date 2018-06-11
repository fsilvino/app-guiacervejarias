package cervejariassc.ine5612.ufsc.br.cervejariassc.database;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import cervejariassc.ine5612.ufsc.br.cervejariassc.dao.CervejaDao;
import cervejariassc.ine5612.ufsc.br.cervejariassc.dao.CervejariaDao;
import cervejariassc.ine5612.ufsc.br.cervejariassc.model.Cerveja;
import cervejariassc.ine5612.ufsc.br.cervejariassc.model.Cervejaria;

@Database(entities = {Cervejaria.class, Cerveja.class}, version = 3, exportSchema = false)
public abstract class CervejariaDB extends RoomDatabase {

    private static CervejariaDB instance;

    public abstract CervejariaDao cervejariaDao();
    public abstract CervejaDao cervejaDao();

    public static CervejariaDB getInstance(Application application) {
        if (instance == null) {
            synchronized (CervejariaDB.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(application, CervejariaDB.class, "cervejaria_db")
                            .fallbackToDestructiveMigration()
                            .addCallback(sCervejariaDatabaseCallback)
                            .build();
                }
            }
        }
        return instance;
    }

    private static RoomDatabase.Callback sCervejariaDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(instance).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final CervejariaDao cervejariaDao;

        PopulateDbAsync(CervejariaDB db) {
            cervejariaDao = db.cervejariaDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateCervejarias();
            return null;
        }

        private void populateCervejarias() {
            List<Cervejaria> all = cervejariaDao.getAll().getValue();
            if (all == null || all.size() == 0) {

                Cervejaria cervejaria1 = new Cervejaria();
                cervejaria1.setId(1);
                cervejaria1.setNome("OPA Bier");
                cervejaria1.setCidade("Joinville");
                cervejaria1.setEstado("SC");
                cervejaria1.setEndereco("Rua Dona Francisca, 13700 – Pirabeiraba");
                cervejaria1.setHorarioFuncionamento("Terça a Sexta: 17h às 00h\n" +
                                                    "Sábado e Domingo: 15h às 00h");
                cervejaria1.setLatitude(-26.229843);
                cervejaria1.setLongitude(-48.899074);
                cervejaria1.setLinkVideoYoutube("https://www.youtube.com/watch?v=d747Ys_MnB8");
                cervejaria1.setLinkLogo("https://opabier.com.br/wp-content/uploads/2015/07/logo-opa-bier.png");

                Cervejaria cervejaria2 = new Cervejaria();
                cervejaria2.setId(2);
                cervejaria2.setNome("Debron Bier");
                cervejaria2.setCidade("Jaboatão dos Guararapes");
                cervejaria2.setEstado("PE");
                cervejaria2.setEndereco("Estr. da Batalha, 1832 - Prazeres");
                cervejaria2.setHorarioFuncionamento("Segunda a Sexa: 18h às 00h");
                cervejaria2.setLatitude(-8.145573);
                cervejaria2.setLongitude(-34.918937);
                cervejaria2.setLinkVideoYoutube("https://www.youtube.com/watch?v=JMcodSuHCxo");
                cervejaria2.setLinkLogo("http://www.debronbier.com.br/assets/default/img/logo.png");

                Cervejaria cervejaria3 = new Cervejaria();
                cervejaria3.setId(3);
                cervejaria3.setNome("Caras de Malte");
                cervejaria3.setCidade("Campos do Jordão");
                cervejaria3.setEstado("SP");
                cervejaria3.setEndereco("AV. Pedro paulo, 1500 - Descansópolis");
                cervejaria3.setHorarioFuncionamento("Domingo a Quinta: 12h às 18h30\n" +
                                                    "Sexta e Sábado: 12h às 22h");
                cervejaria3.setLatitude(-22.71);
                cervejaria3.setLongitude(-45.548186);
                cervejaria3.setLinkVideoYoutube("https://www.youtube.com/watch?v=Er5pPErjFD8&index=9&list=PLhw1JcswDXbNHpA0nd4wqeLHl61ReoY2W");
                cervejaria3.setLinkLogo("http://www.carasdemalte.com.br/images/logo.png");

                try {
                    cervejariaDao.insertAll(cervejaria1, cervejaria2, cervejaria3);
                } catch (Exception e) {
                    Log.e("erro", e.getMessage());
                }
            }
        }



    }

}
