package cervejariassc.ine5612.ufsc.br.cervejariassc.intents;

import android.content.Context;
import android.content.Intent;

import cervejariassc.ine5612.ufsc.br.cervejariassc.DetalhesCervejariaActivity;
import cervejariassc.ine5612.ufsc.br.cervejariassc.model.Cervejaria;

public class DetalhesCervejariaIntent {

    private Intent intent;
    private static final String CHAVE_BASE = "cervejariassc.ine5612.ufsc.br.cervejariassc.detalhescervejaria.";
    private static final String CHAVE_ID_CERVEJARIA = CHAVE_BASE + "ID";
    private static final String CHAVE_CERVEJARIA = CHAVE_BASE + "CERVEJARIA";

    public DetalhesCervejariaIntent(Context context) {
        this.intent = new Intent(context, DetalhesCervejariaActivity.class);
    }

    public DetalhesCervejariaIntent(Context context, Cervejaria cervejaria) {
        this(context);
        setCervejaria(cervejaria);
    }

    public DetalhesCervejariaIntent(Context context, int id) {
        this(context);
        setId(id);
    }

    public DetalhesCervejariaIntent(Intent intent) {
        this.intent = intent;
    }

    private void setCervejaria(Cervejaria cervejaria) {
        this.intent.putExtra(CHAVE_CERVEJARIA, cervejaria);
    }

    public Cervejaria getCervejaria() {
        return (Cervejaria)this.intent.getSerializableExtra(CHAVE_CERVEJARIA);
    }

    public void setId(int id) {
        this.intent.putExtra(CHAVE_ID_CERVEJARIA, id);
    }

    public int getId() {
        return this.intent.getIntExtra(CHAVE_ID_CERVEJARIA, 0);
    }

    public Intent getIntent() {
        return this.intent;
    }

}