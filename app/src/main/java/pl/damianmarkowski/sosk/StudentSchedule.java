package pl.damianmarkowski.sosk;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class StudentSchedule extends AppCompatActivity {

    Intent intentMainScreen, intentAboutScreen;
    ListView listView;
    TextView courseName;
    String[] dateAndTimes, classTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_schedule);

        intentMainScreen = new Intent(this, MainActivity.class);

        intentAboutScreen = new Intent(this, AboutScreen.class);

        courseName = (TextView) findViewById(R.id.textViewCourseName);

        courseName.setText("Kurs: " + GlobalVars.courseName);

        new StudentScheduleRequest().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_schedule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            new LogoutRequest().execute();
            startActivity(intentMainScreen);
        }else if (id == R.id.action_about){
            startActivity(intentAboutScreen);
        }

        return super.onOptionsItemSelected(item);
    }

    public class StudentScheduleRequest extends AsyncTask<Void, Void, ClassesList> {
        @Override
        protected ClassesList doInBackground(Void... params) {
            try {
                final String url = GlobalVars.hostUrl + "student/schedule?courseId=" + GlobalVars.courseId;
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                ClassesList result = restTemplate.getForObject(url, ClassesList.class);
                return result;
            } catch (Exception e) {
                Log.e("StudentSchedule", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(ClassesList list) {

            int length = list.getList().size();

            dateAndTimes = new String[length];

            classTypes = new String[length];

            for(int i=0; i < length; i++){
                dateAndTimes[i] = list.getList().get(i).getDateAndTime();
                classTypes[i] = list.getList().get(i).getLessonType();
            }

            listView = (ListView) findViewById(R.id.listView);

            listView.setAdapter(new StudentScheduleListCustomAdapter(StudentSchedule.this, dateAndTimes, classTypes));
        }
    }
}
