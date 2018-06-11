package cervejariassc.ine5612.ufsc.br.cervejariassc.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "cerveja")
public class Cerveja {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "idcervejaria")
    private int idCervejaria;

    @ColumnInfo(name = "nome")
    private String nome;

    @ColumnInfo(name = "link_img")
    private String linkImg;

    public Cerveja() {

    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public int getIdCervejaria() {
        return idCervejaria;
    }

    public void setIdCervejaria(int idCervejaria) {
        this.idCervejaria = idCervejaria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLinkImg() {
        return linkImg;
    }

    public void setLinkImg(String linkImg) {
        this.linkImg = linkImg;
    }
}
