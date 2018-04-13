package pl.damianmarkowski.sosk;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class StudentMainScreen extends AppCompatActivity {

    ListView listViewCoursesInProgress, listViewCoursesNotStarted, listViewCoursesFinished;
    Intent intentSignUpForCourse, intentMainScreen, intentAboutScreen;
    Button signUpForNewCourse;
    List<CourseHelper> coursesList = new ArrayList<CourseHelper>();
    List<CourseHelper> coursesInProgressList = new ArrayList<CourseHelper>();
    List<CourseHelper> coursesNotStartedList = new ArrayList<CourseHelper>();
    List<CourseHelper> coursesFinishedList = new ArrayList<CourseHelper>();
    private ProgressBar bar;
    TextView coursesNotStartedTextView, coursesInProgressTextView, coursesFinishedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main_screen);

        listViewCoursesInProgress = (ListView) findViewById(R.id.listViewCoursesInProgress);
        listViewCoursesNotStarted = (ListView) findViewById(R.id.listViewCoursesNotStarted);
        listViewCoursesFinished = (ListView) findViewById(R.id.listViewCoursesFinished);
        intentSignUpForCourse = new Intent(this, SignUpForCourse.class);
        intentMainScreen = new Intent(this, MainActivity.class);
        intentAboutScreen = new Intent(this, AboutScreen.class);
        signUpForNewCourse = (Button) findViewById(R.id.signUpForNewCourse);
        bar = (ProgressBar) this.findViewById(R.id.progressBar);
        new CoursesRequest().execute();
        signUpForNewCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentSignUpForCourse);
            }
        });
        coursesInProgressTextView = (TextView) findViewById(R.id.inProgressTextView);
        coursesNotStartedTextView = (TextView) findViewById(R.id.notStartedTextView);
        coursesFinishedTextView = (TextView) findViewById(R.id.finishedTextView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_main_screen, menu);
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
                final String url = GlobalVars.hostUrl + "student/courses?userId=" + GlobalVars.userId;
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

            for(int i=0; i < length; i++){
                CourseHelper course = new CourseHelper();
                course.setId(list.getList().get(i).getId());
                course.setName(list.getList().get(i).getName());
                course.setStatus(list.getList().get(i).getStatus());
                coursesList.add(course);
            }

            for(int j=0; j<coursesList.size(); j++){
                if(coursesList.get(j).getStatus() == "w trakcie"){
                    coursesInProgressList.add(coursesList.get(j));
                }else if(coursesList.get(j).getStatus() == "nierozpoczęty"){
                    coursesNotStartedList.add(coursesList.get(j));
                }else{
                    coursesFinishedList.add(coursesList.get(j));
                }
            }

            if(coursesInProgressList.size() > 0){
                coursesInProgressTextView.setVisibility(View.VISIBLE);
                listViewCoursesInProgress.setAdapter(new StudentCoursesInProgressListCustomAdapter(StudentMainScreen.this, coursesInProgressList));
            }

            if(coursesNotStartedList.size() > 0){
                coursesNotStartedTextView.setVisibility(View.VISIBLE);
                listViewCoursesNotStarted.setAdapter(new StudentCoursesNotStartedListCustomAdapter(StudentMainScreen.this, coursesNotStartedList));
            }

            if(coursesFinishedList.size() > 0){
                coursesFinishedTextView.setVisibility(View.VISIBLE);
                listViewCoursesFinished.setAdapter(new StudentCoursesFinishedListCustomAdapter(StudentMainScreen.this, coursesFinishedList));
            }

            bar.setVisibility(View.GONE);
        }
    }
}
