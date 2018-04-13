package pl.damianmarkowski.sosk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class StudentCourseScreen extends AppCompatActivity {

    ListView listViewCourseOptions;
    Intent intentMainScreen, intentScheduleScreen, intentLecturesScreen, intentPracticalLessonsScreen, intentAboutScreen;
    TextView courseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_course_screen);

        listViewCourseOptions = (ListView) findViewById(R.id.listViewCourseOptions);

        intentMainScreen = new Intent(this, MainActivity.class);
        intentAboutScreen = new Intent(this, AboutScreen.class);
        intentScheduleScreen = new Intent(this, StudentSchedule.class);
        intentLecturesScreen = new Intent(this, StudentLectures.class);
        intentPracticalLessonsScreen = new Intent(this, StudentPracticalLessons.class);
        courseName = (TextView) findViewById(R.id.textViewCourseName);
        courseName.setText("Kurs: " + GlobalVars.courseName);

        String[] values = new String[] { "Harmonogram",
                "Zajęcia teoretyczne (materiały)",
                "Zajęcia praktyczne"
        };

        listViewCourseOptions.setAdapter(new StudentCourseScreenCustomAdapter(StudentCourseScreen.this, values));

        listViewCourseOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
               switch(position){
                   case 0:
                       startActivity(intentScheduleScreen);
                       break;
                   case 1:
                       startActivity(intentLecturesScreen);
                       break;
                   case 2:
                       startActivity(intentPracticalLessonsScreen);
                       break;
                   default:
                       break;
               }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_course_screen, menu);
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
}
