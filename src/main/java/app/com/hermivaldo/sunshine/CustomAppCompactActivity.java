package app.com.hermivaldo.sunshine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import app.com.hermivaldo.sunshine.preferrences.PreferrencesAct;

/**
 * Created by loja926 on 30/09/2015.
 */
public abstract class CustomAppCompactActivity extends AppCompatActivity {

    private Integer resource;

    protected void createMenu(int resource){
        this.resource = resource;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings){
            startActivity(new Intent(this, PreferrencesAct.class));
            return true;
        }
        if(item.getItemId() == R.id.action_map){
            openPreferedLocationMap();
        }

        return super.onOptionsItemSelected(item);
    }

    private void openPreferedLocationMap(){
        SharedPreferences prefs = PreferenceManager.
                getDefaultSharedPreferences(this);
        String location = prefs.getString(getString(R.string.default_location_key),
                getString(R.string.default_location));

        Uri geoLocation = Uri.parse("geo:0,0?").buildUpon()
                .appendQueryParameter("q",location).build();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else{
            Log.e(getClass().getSimpleName(), " Couldn't call " + location);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (resource != null){
            getMenuInflater().inflate(resource,menu);
        }
        return super.onCreateOptionsMenu(menu);
    }
}
