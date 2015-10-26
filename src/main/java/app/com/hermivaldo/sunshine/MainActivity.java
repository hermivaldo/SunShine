package app.com.hermivaldo.sunshine;

import android.os.Bundle;

public class MainActivity extends CustomAppCompactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createMenu(R.menu.main);
    }

}
