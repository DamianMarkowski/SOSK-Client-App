package pl.damianmarkowski.sosk;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    Button buttonLogIn;
    Intent intentStudentMainScreen, intentTeacherMainScreen, intentAboutScreen;
    String username, password;
    EditText usernameEditText, passwordEditText;
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogIn = (Button) findViewById(R.id.buttonLogIn);

        intentStudentMainScreen = new Intent(this, StudentMainScreen.class);

        intentStudentMainScreen.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intentTeacherMainScreen = new Intent(this, TeacherMainScreen.class);

        intentTeacherMainScreen.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intentAboutScreen = new Intent(this, AboutScreen.class);

        usernameEditText = (EditText) findViewById(R.id.usernameEditText);

        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        bar = (ProgressBar) this.findViewById(R.id.progressBar);

        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("username",usernameEditText.getText().toString());
                Log.d("password",passwordEditText.getText().toString());
                if(!isEmpty(usernameEditText) && !isEmpty(passwordEditText)){
                    username = usernameEditText.getText().toString();
                    password = passwordEditText.getText().toString();
                    new LoginRequest().execute();
                }else{
                    Context context = getApplicationContext();
                    CharSequence text = "Podano nieprawidłowe dane";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });

    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_about) {
            startActivity(intentAboutScreen);
        }

        return super.onOptionsItemSelected(item);
    }

    public class LoginRequest extends AsyncTask<Void, Void, LoginResult> {

        @Override
        protected void onPreExecute(){
            bar.setVisibility(View.VISIBLE);
        }

        @Override
        protected LoginResult doInBackground(Void... params) {
            try {
                final String url = GlobalVars.hostUrl + "login?username=" + username + "&password=" + Hash.encodeMd5(password);
                Log.d("url",url);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                LoginResult result = restTemplate.getForObject(url, LoginResult.class);
                return result;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(LoginResult result) {

            bar.setVisibility(View.GONE);
            Log.d("user type", result.getUserType() + "");
            Log.d("user id",result.getUserId()+"");

            if(result.getStatus().equals("SUCCESS")){
                GlobalVars.userId = result.getUserId();
                if(result.getUserType().equals("student")){
                    GlobalVars.context.startActivity(intentStudentMainScreen);
                }else{
                    GlobalVars.userType = result.getUserType();
                    GlobalVars.context.startActivity(intentTeacherMainScreen);
                }

            }else{
                CharSequence text = "Podano nieprawidłowe dane";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(GlobalVars.context, text, duration);
                toast.show();
            }
        }
    }
}
