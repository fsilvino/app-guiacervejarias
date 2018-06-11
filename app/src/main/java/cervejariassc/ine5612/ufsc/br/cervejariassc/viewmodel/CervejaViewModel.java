package cervejariassc.ine5612.ufsc.br.cervejariassc.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import cervejariassc.ine5612.ufsc.br.cervejariassc.model.Cerveja;
import cervejariassc.ine5612.ufsc.br.cervejariassc.repository.CervejaRepository;

public class CervejaViewModel extends AndroidViewModel {

    private CervejaRepository cervejaRepository;
    private LiveData<List<Cerveja>> cervejas;

    public CervejaViewModel(@NonNull Application application) {
        super(application);
        cervejaRepository = new CervejaRepository(application);
        cervejas = cervejaRepository.getCervejarias();
    }

    public LiveData<List<Cerveja>> getCervejas() {
        return cervejas;
    }

    public void insert(Cerveja cerveja) {
        cervejaRepository.insertAll(cerveja);
    }

}
