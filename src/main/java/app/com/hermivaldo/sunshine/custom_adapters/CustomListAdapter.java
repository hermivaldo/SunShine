package app.com.hermivaldo.sunshine.custom_adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import app.com.hermivaldo.sunshine.DetailActivity;
import app.com.hermivaldo.sunshine.MainActivityFragment;
import app.com.hermivaldo.sunshine.R;

/**
 * @author Hermivaldo on 29/09/2015.
 * @version 0.1
 *
 * Criada para customizar o adapter e facilitar o processo da inserção da
 * lógica necessária dos eventos de clique nos elementos da lista.
 */
public class CustomListAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

    // Lista de objetos
    List<String> myList;
    // Layout que será inflado para criar as views
    LayoutInflater inflater;

    Context myContext;

    /**
     *
     * @param myList Lista de elementos que serão adicionadas a tela
     * @param myContext
     */
    public CustomListAdapter(List<String> myList, Context myContext){
        // Inicialização dos atributos.
        this.myList = myList;
        this.inflater = LayoutInflater.from(myContext);
        this.myContext = myContext;
        MainActivityFragment.myList.setOnItemClickListener(this);
    }

    // Total de elementos a ser carregado
    @Override
    public int getCount() {
        return myList.size();
    }

    // Definição de cada objeto
    @Override
    public Object getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.inflate(R.layout.list_item_forecast,null);

        TextView myText = (TextView) view.findViewById(R.id.list_item_forecast_textview);
        myText.setText(myList.get(i));

        return view;
    }


    /**
     *
     *
     @param adapterView The AdapterView where the click happened.
     @param view The view within the AdapterView that was clicked (this will be a view provided by the adapter)
     @param position The position of the view in the adapter.
     @param id The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        this.myContext.startActivity(new Intent(view.getContext(), DetailActivity.class));
    }
}
