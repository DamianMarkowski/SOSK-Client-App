package pl.damianmarkowski.sosk;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class TeacherLectures extends AppCompatActivity {

    Intent intentMainScreen, intentAboutScreen;
    ListView listView;
    TextView courseName;
    String[] students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_lectures);

        intentMainScreen = new Intent(this, MainActivity.class);

        intentAboutScreen = new Intent(this, AboutScreen.class);

        listView = (ListView) findViewById(R.id.listView);

        courseName = (TextView) findViewById(R.id.textViewCourseName);

        courseName.setText("Kurs: " + GlobalVars.courseName);

        new LecturerStudentsListRequest().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_lectures, menu);
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

    public class LecturerStudentsListRequest extends AsyncTask<Void, Void, StudentsList> {
        @Override
        protected StudentsList doInBackground(Void... params) {
            try {
                final String url = GlobalVars.hostUrl + "teacher/lecturer/listOfStudents?courseId=" + GlobalVars.courseId;
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                StudentsList result = restTemplate.getForObject(url, StudentsList.class);
                return result;
            } catch (Exception e) {
                Log.e("StudentsList", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(StudentsList list) {

            int length = list.getList().size();

            students = new String[length];

            for(int i=0; i < length; i++){
                students[i] = list.getList().get(i).getFirstName() + " " + list.getList().get(i).getLastName();
            }

            listView = (ListView) findViewById(R.id.listView);

            listView.setAdapter(new TeacherLecturesListCustomAdapter(TeacherLectures.this, students));
        }
    }
}
