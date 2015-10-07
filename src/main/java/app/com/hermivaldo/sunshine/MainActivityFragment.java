package app.com.hermivaldo.sunshine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * A placeholder fragment containing a simple view.
 * @author Hermivaldo Braga
 * @version 0.1
 *
 */
public class MainActivityFragment extends Fragment {

    public static ListView myList;

    public MainActivityFragment() {
        // Set that Fragment can use MenuOptions.
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the MenuOptions with the layout menu
        inflater.inflate(R.menu.forecastfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Get element ID
        int itemId = item.getItemId();
        if (itemId == R.id.refresh){
            updateWeather();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Metodo utilizado para buscar as informações sobre o tempo.
     */
    private void updateWeather(){
        // Make request in Webservice.
        MakeRequestHttp weatherTask = new MakeRequestHttp(getActivity());

        // Carregar as informações que foram salvas no aplicativo.
        SharedPreferences prefs = PreferenceManager.
                getDefaultSharedPreferences(getActivity());

        String location = prefs.getString(getString(R.string.default_location_key),
                getString(R.string.default_location));
        String unit = prefs.getString(getString(R.string.pref_units_key),
                getString(R.string.pref_units_metric));

        weatherTask.execute(location, "7", unit);
    }

    @Override
    public void onStart() {
        super.onStart();
        updateWeather();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the view that will show in the screen
        View myView = inflater.inflate(R.layout.fragment_main, container, false);
        myList = (ListView) myView.findViewById(R.id.listview_forecast);
        return myView;
    }
}
