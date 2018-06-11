package cervejariassc.ine5612.ufsc.br.cervejariassc.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import cervejariassc.ine5612.ufsc.br.cervejariassc.dao.CervejariaDao;
import cervejariassc.ine5612.ufsc.br.cervejariassc.database.CervejariaDB;
import cervejariassc.ine5612.ufsc.br.cervejariassc.model.Cervejaria;

public class CervejariaRepository {

    private CervejariaDao cervejariaDao;
    private LiveData<List<Cervejaria>> cervejarias;

    public CervejariaRepository(Application application) {
        cervejariaDao = CervejariaDB.getInstance(application).cervejariaDao();
        cervejarias = cervejariaDao.getAll();
    }

    public LiveData<List<Cervejaria>> getCervejarias() {
        return cervejarias;
    }

    public void insertAll(Cervejaria... cervejarias) {
        new InsertAsyncTask(cervejariaDao).execute(cervejarias);
    }

    private static class InsertAsyncTask extends AsyncTask<Cervejaria, Void, Void> {

        private CervejariaDao mAsyncTaskDao;

        InsertAsyncTask(CervejariaDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Cervejaria... params) {
            mAsyncTaskDao.insertAll(params);
            return null;
        }
    }

}
