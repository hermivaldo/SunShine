package app.com.hermivaldo.sunshine;

import android.os.AsyncTask;
import android.text.format.Time;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Hermivaldo Braga
 * @version 0.1
 * Chamada da API do WeatherAPI para buscar os resultados necessários
 * com os 7 dias da semana.
 */
public class MakeRequestHttp extends AsyncTask<String, Void, String[]>{

    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;

    /**
     *
     * Busca do clima conforme local, quantidade de dias e
     * tipo de unidade de busca.
     *
     * @param data [0] -> Locale
     * @param data [1] -> How many days
     * @param data [2] -> Kind of units (metric or imperial)
     */
    @Override
    protected String[] doInBackground(String... data) {
        String forecasJsonStr = null;

        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/" +
                    "forecast/daily?q=" + data[0] + "&cnt=" + data[1] + "&mode=json&units=" + data[2]);

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

        try {
            return getWeatherDataFromJson(forecasJsonStr,Integer.parseInt(data[1]));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    private String[] getWeatherDataFromJson(String forecastJsonStr, int numDays)
    throws JSONException{

        final String OWN_LIST = "list";
        final String OWN_WEATHER = "weather";
        final String OWN_TEMPERATURE = "temp";
        final String OWN_MAX = "max";
        final String OWN_MIN = "min";
        final String OWN_DESCRIPTION = "main";

        JSONObject forecastJson = new JSONObject(forecastJsonStr);
        JSONArray weatherArray = forecastJson.getJSONArray(OWN_LIST);

        Time dayTime = new Time();
        dayTime.setToNow();

        int julianStartDay = Time.getJulianDay(System.currentTimeMillis(),
                dayTime.gmtoff);

        dayTime = new Time();

        String[] results = new String[numDays];

        for(int i = 0; i < weatherArray.length(); i++){
            String day;
            String description;
            String highAndLow;

            JSONObject dayForecast = weatherArray.getJSONObject(i);


            long dateTime;
            dateTime = dayTime.setJulianDay(julianStartDay + i);
            day = getReadableDateString(dateTime);


            JSONObject weaterObjet = dayForecast.getJSONArray(OWN_WEATHER).getJSONObject(0);
            description = weaterObjet.getString(OWN_DESCRIPTION);

            JSONObject temJsonObject = dayForecast.getJSONObject(OWN_TEMPERATURE);
            double high = temJsonObject.getDouble(OWN_MAX);
            double low = temJsonObject.getDouble(OWN_MIN);

            highAndLow = formatHighLows(high,low);

            results[i] = day + " - " + description + " - " + highAndLow;

        }

        for(String s : results){
            Log.v(this.getClass().getSimpleName(), s);
        }

        return results;
    }

    /**
     * Formatar a temperatura máxima e mínima que serão exibidos na aplicação.
     */
    private String formatHighLows(double high, double low){
        long roundedHiht = Math.round(high);
        long roundedLow = Math.round(low);

        String highLowStr = roundedHiht + "/" + roundedLow;

        return highLowStr;
    }

    /**
    * Formatar a data do calendário que será exibido.
    * */

    private String getReadableDateString(long time){
        SimpleDateFormat shotDate = new SimpleDateFormat("EEE MMM dd");
        return shotDate.format(time);
    }

    @Override
    protected void onPostExecute(String[] s) {
        /*
         * Atualizar o conteúdo somente se não nulo */
        if (s != null){
            MainActivityFragment.adapter.clear();
            // A utilização do Add requer que os itens sejam adicionados um a um
            for (String valor : s){
                MainActivityFragment.adapter.add(valor);
            }
        }

    }


}
