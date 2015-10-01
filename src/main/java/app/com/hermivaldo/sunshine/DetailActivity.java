package app.com.hermivaldo.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author Hermivaldo Braga on 29/09/2015.
 * @version 0.1
 */
public class DetailActivity extends CustomAppCompactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        // Pegar item ao qual valor será alterado.
        TextView text = (TextView) findViewById(R.id.txt_details_activity);
        // Recuperar valor passado para a Act.
        String value = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        // Atualizar campo de texto.
        text.setText(value);

        // Utilizar menu e eventos.
        createMenu(R.menu.main);
    }

}
