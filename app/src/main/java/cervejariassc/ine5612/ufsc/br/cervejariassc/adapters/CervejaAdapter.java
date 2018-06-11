package cervejariassc.ine5612.ufsc.br.cervejariassc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cervejariassc.ine5612.ufsc.br.cervejariassc.model.Cerveja;

public class CervejaAdapter extends BaseAdapter {

    private List<Cerveja> cervejas;
    private LayoutInflater layoutInflater;

    public CervejaAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    public CervejaAdapter(Context context, List<Cerveja> cervejas) {
        this(context);
        setCervejas(cervejas);
    }

    public void setCervejas(List<Cerveja> cervejas) {
        this.cervejas = cervejas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (this.cervejas != null)
            return this.cervejas.size();
        else return 0;
    }

    @Override
    public Cerveja getItem(int i) {
        return this.cervejas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.cervejas.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
        }

        Cerveja cerveja = getItem(i);
        String linha1 = cerveja.getNome();
        ((TextView) view.findViewById(android.R.id.text1)).setText(linha1);

        return view;
    }

}