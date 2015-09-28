package app.com.hermivaldo.sunshine;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 * @author Hermivaldo Braga
 * @version 0.1
 *
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the view that will show in the screen
        View myView = inflater.inflate(R.layout.fragment_main, container, false);

        // Values to show inside screen
        String[] data = new String[]{
                "Today - Sunny - 88 / 63",
                "Tomorrow - Foggy - 70 / 46",
                "Weds - Cloudy - 72 / 63",
                "Thurs - Rainy - 64 / 51",
                "Fri - Foggy - 70 / 46",
                "Sat - Sunny - 86 / 68"
        };


        List<String> lista = new ArrayList<>(
                Arrays.asList(data));

        // Get element List inside the my layout.
        ListView myList = (ListView) myView.findViewById(R.id.listview_forecast);

        // Create the adapter adapter
        ArrayAdapter adapter = new ArrayAdapter(
                // The current context
                getActivity(),
                // Id of list item layout
                R.layout.list_item_forecast,
                // Id of textview to populate
                R.id.list_item_forecast_textview
                // data
                ,lista);

        // Set data inside the list
        myList.setAdapter(adapter);

        return myView;
    }
}
