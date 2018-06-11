package cervejariassc.ine5612.ufsc.br.cervejariassc.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import cervejariassc.ine5612.ufsc.br.cervejariassc.model.Cervejaria;
import cervejariassc.ine5612.ufsc.br.cervejariassc.repository.CervejariaRepository;

public class CervejariaViewModel extends AndroidViewModel {

    private CervejariaRepository cervejariaRepository;
    private LiveData<List<Cervejaria>> cervejarias;

    public CervejariaViewModel(@NonNull Application application) {
        super(application);
        cervejariaRepository = new CervejariaRepository(application);
        cervejarias = cervejariaRepository.getCervejarias();
    }

    public LiveData<List<Cervejaria>> getCervejarias() {
        return cervejarias;
    }

    public void insert(Cervejaria cervejaria) {
        cervejariaRepository.insertAll(cervejaria);
    }

}
