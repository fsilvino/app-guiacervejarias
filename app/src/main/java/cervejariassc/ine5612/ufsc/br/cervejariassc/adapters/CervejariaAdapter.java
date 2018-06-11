package cervejariassc.ine5612.ufsc.br.cervejariassc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cervejariassc.ine5612.ufsc.br.cervejariassc.R;
import cervejariassc.ine5612.ufsc.br.cervejariassc.model.Cervejaria;

public class CervejariaAdapter extends BaseAdapter {

    private List<Cervejaria> cervejarias;
    private LayoutInflater layoutInflater;

    public CervejariaAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    public CervejariaAdapter(Context context, List<Cervejaria> cervejarias) {
        this(context);
        setCervejarias(cervejarias);
    }

    public void setCervejarias(List<Cervejaria> cervejarias) {
        this.cervejarias = cervejarias;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (this.cervejarias != null)
            return this.cervejarias.size();
        else return 0;
    }

    @Override
    public Cervejaria getItem(int i) {
        return this.cervejarias.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.cervejarias.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(android.R.layout.two_line_list_item, viewGroup, false);
        }

        Cervejaria cervejaria = getItem(i);
        String linha1 = cervejaria.getNome();
        String linha2 = String.format("%s, %s", cervejaria.getCidade(), cervejaria.getEstado());
        ((TextView) view.findViewById(android.R.id.text1)).setText(linha1);
        ((TextView) view.findViewById(android.R.id.text2)).setText(linha2);

        return view;
    }

}
