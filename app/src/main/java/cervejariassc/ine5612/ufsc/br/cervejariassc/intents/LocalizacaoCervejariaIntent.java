package cervejariassc.ine5612.ufsc.br.cervejariassc.intents;

import android.content.Context;
import android.content.Intent;

import cervejariassc.ine5612.ufsc.br.cervejariassc.DetalhesCervejariaActivity;
import cervejariassc.ine5612.ufsc.br.cervejariassc.LocalizacaoCervejariaActivity;
import cervejariassc.ine5612.ufsc.br.cervejariassc.model.Cervejaria;

public class LocalizacaoCervejariaIntent {

    private Intent intent;
    private static final String CHAVE_BASE = "cervejariassc.ine5612.ufsc.br.cervejariassc.localizacaocervejaria.";
    private static final String CHAVE_CERVEJARIA = CHAVE_BASE + "CERVEJARIA";

    public LocalizacaoCervejariaIntent(Context context) {
        this.intent = new Intent(context, LocalizacaoCervejariaActivity.class);
    }

    public LocalizacaoCervejariaIntent(Context context, Cervejaria cervejaria) {
        this(context);
        setCervejaria(cervejaria);
    }

    public LocalizacaoCervejariaIntent(Intent intent) {
        this.intent = intent;
    }

    private void setCervejaria(Cervejaria cervejaria) {
        this.intent.putExtra(CHAVE_CERVEJARIA, cervejaria);
    }

    public Cervejaria getCervejaria() {
        return (Cervejaria)this.intent.getSerializableExtra(CHAVE_CERVEJARIA);
    }

    public Intent getIntent() {
        return this.intent;
    }

}