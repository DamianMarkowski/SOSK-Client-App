package pl.damianmarkowski.sosk;

import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class LogoutRequest extends AsyncTask<Void, Void, RestMessage> {
    @Override
    protected RestMessage doInBackground(Void... params) {
        try {
            final String url = GlobalVars.hostUrl + "logout";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            RestMessage message = restTemplate.getForObject(url, RestMessage.class);
            return message;
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }

    @Override
    protected void onPostExecute(RestMessage message) {

        /*CharSequence text = "otrzymano odpowiedź z serwisu: " + message.getContent();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(GlobalVars.context, text, duration);
        toast.show();*/
    }
}
