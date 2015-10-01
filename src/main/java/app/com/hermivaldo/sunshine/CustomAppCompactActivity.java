package app.com.hermivaldo.sunshine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (resource != null){
            getMenuInflater().inflate(resource,menu);
        }
        return super.onCreateOptionsMenu(menu);
    }
}
