package cervejariassc.ine5612.ufsc.br.cervejariassc.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import cervejariassc.ine5612.ufsc.br.cervejariassc.model.Cerveja;

@Dao
public interface CervejaDao {

    @Query("SELECT * FROM cerveja")
    LiveData<List<Cerveja>> getAll();

    @Query("SELECT * FROM cerveja WHERE id IN (:cervejasIds)")
    LiveData<List<Cerveja>> loadAllByIds(int[] cervejasIds);

    @Query("SELECT * FROM cerveja where idcervejaria = :idCervejaria")
    LiveData<List<Cerveja>> getAllByIdCervejaria(int idCervejaria);

    @Query("SELECT * FROM cerveja WHERE nome LIKE :nome LIMIT 1")
    Cerveja findByName(String nome);

    @Insert
    void insertAll(Cerveja... cervejas);

    @Update
    void updateAll(Cerveja... cervejas);

    @Delete
    void delete(Cerveja cerveja);

    @Query("DELETE FROM cerveja")
    void deleteAll();

}
