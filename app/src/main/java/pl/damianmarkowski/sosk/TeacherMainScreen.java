package pl.damianmarkowski.sosk;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class TeacherMainScreen extends AppCompatActivity {

    ListView listViewCourses;
    Intent intentMainScreen, intentAboutScreen;
    String[] coursesList, courseStatusesList;
    Integer[] courseIdsList;
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main_screen);

        listViewCourses = (ListView) findViewById(R.id.listViewCourses);

        intentMainScreen = new Intent(this, MainActivity.class);

        intentAboutScreen = new Intent(this, AboutScreen.class);

        bar = (ProgressBar) this.findViewById(R.id.progressBar);

        new CoursesRequest().execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_teacher_main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            new LogoutRequest().execute();
            startActivity(intentMainScreen);
        }else if (id == R.id.action_about){
            startActivity(intentAboutScreen);
        }

        return super.onOptionsItemSelected(item);
    }

    public class CoursesRequest extends AsyncTask<Void, Void, CoursesList> {

        @Override
        protected void onPreExecute(){
            bar.setVisibility(View.VISIBLE);
        }

        @Override
        protected CoursesList doInBackground(Void... params) {
            try {
                final String url = GlobalVars.hostUrl + "teacher/courses?userId=" + GlobalVars.userId;
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                CoursesList result = restTemplate.getForObject(url, CoursesList.class);
                return result;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(CoursesList list) {

            int length = list.getList().size();

            coursesList = new String[length];
            courseStatusesList = new String[length];
            courseIdsList = new Integer[length];

            for(int i=0; i < length; i++){
                coursesList[i] = list.getList().get(i).getName();
                courseStatusesList[i] = list.getList().get(i).getStatus();
                courseIdsList[i] = list.getList().get(i).getId();
            }

            listViewCourses.setAdapter(new TeacherCoursesListCustomAdapter(TeacherMainScreen.this, coursesList,courseStatusesList, courseIdsList));

            bar.setVisibility(View.GONE);
        }
    }
}
