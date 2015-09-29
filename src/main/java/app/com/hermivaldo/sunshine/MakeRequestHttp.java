package app.com.hermivaldo.sunshine;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Hermivaldo Braga
 * @version 0.1
 * Chamada da API do WeatherAPI para buscar os resultados necessários
 * com os 7 dias da semana.
 */
public class MakeRequestHttp extends AsyncTask<Void, Void, String>{

    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;

    @Override
    protected String doInBackground(Void... voids) {
        String forecasJsonStr = null;

        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/" +
                    "forecast/daily?q=Guarulhos&cnt=7&mode=json");

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            if (inputStream == null){
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;

            while ((line = reader.readLine()) != null){
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0){
                return null;
            }

            forecasJsonStr = buffer.toString();

        } catch (Exception e){
            Log.e(MakeRequestHttp.class.getSimpleName(),e.toString());
        }finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }

            if (reader != null){
                try {
                    reader.close();
                } catch (final IOException e){
                    Log.e(this.getClass().getSimpleName(), e.toString());
                }
            }
        }

        return forecasJsonStr;

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.i(this.getClass().getSimpleName(), s);
    }


}
