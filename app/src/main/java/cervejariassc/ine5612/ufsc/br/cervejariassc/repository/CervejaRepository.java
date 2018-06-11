package cervejariassc.ine5612.ufsc.br.cervejariassc.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import cervejariassc.ine5612.ufsc.br.cervejariassc.dao.CervejaDao;
import cervejariassc.ine5612.ufsc.br.cervejariassc.database.CervejariaDB;
import cervejariassc.ine5612.ufsc.br.cervejariassc.model.Cerveja;

public class CervejaRepository {

    private CervejaDao cervejaDao;
    private LiveData<List<Cerveja>> cervejas;

    public CervejaRepository(Application application) {
        cervejaDao = CervejariaDB.getInstance(application).cervejaDao();
        cervejas = cervejaDao.getAll();
    }

    public LiveData<List<Cerveja>> getCervejarias() {
        return cervejas;
    }

    public void insertAll(Cerveja... cervejas) {
        new InsertAsyncTask(cervejaDao).execute(cervejas);
    }

    private static class InsertAsyncTask extends AsyncTask<Cerveja, Void, Void> {

        private CervejaDao mAsyncTaskDao;

        InsertAsyncTask(CervejaDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Cerveja... params) {
            mAsyncTaskDao.insertAll(params);
            return null;
        }
    }

}
