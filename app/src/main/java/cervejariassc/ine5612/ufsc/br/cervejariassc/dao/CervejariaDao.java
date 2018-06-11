package cervejariassc.ine5612.ufsc.br.cervejariassc.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import cervejariassc.ine5612.ufsc.br.cervejariassc.model.Cervejaria;

@Dao
public interface CervejariaDao {

    @Query("SELECT * FROM cervejaria")
    LiveData<List<Cervejaria>> getAll();

    @Query("SELECT * FROM cervejaria WHERE id IN (:cervejariasIds)")
    List<Cervejaria> loadAllByIds(int[] cervejariasIds);

    @Query("SELECT * FROM cervejaria WHERE nome LIKE :nome LIMIT 1")
    Cervejaria findByName(String nome);

    @Insert
    void insertAll(Cervejaria... cervejarias);

    @Update
    void updateAll(Cervejaria... cervejarias);

    @Delete
    void delete(Cervejaria cervejaria);

    @Query("DELETE FROM cervejaria")
    void deleteAll();

}
