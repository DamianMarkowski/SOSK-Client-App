package pl.damianmarkowski.sosk;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class TeacherCourseScreen extends AppCompatActivity {

    ListView listViewCourseOptions;
    Intent intentMainScreen, intentTeacherSchedule, intentTeacherPracticalLessons, intentTeacherLectures, intentAboutScreen;
    TextView courseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_course_screen);

        intentMainScreen = new Intent(this, MainActivity.class);

        intentAboutScreen = new Intent(this, AboutScreen.class);

        intentTeacherSchedule = new Intent(this, TeacherSchedule.class);

        intentTeacherLectures = new Intent(this, TeacherLectures.class);

        intentTeacherPracticalLessons = new Intent(this, TeacherPracticalLessons.class);

        listViewCourseOptions = (ListView) findViewById(R.id.listViewCourseOptions);

        courseName = (TextView) findViewById(R.id.textViewCourseName);

        courseName.setText(GlobalVars.courseName);

        String[] values = new String[] { "Harmonogram","Kursanci"
        };

        listViewCourseOptions.setAdapter(new TeacherCourseScreenCustomAdapter(TeacherCourseScreen.this, values));

        listViewCourseOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                switch (position) {
                    case 0:
                        startActivity(intentTeacherSchedule);
                        break;
                    case 1:
                        if(GlobalVars.userType == "lecturer"){
                            startActivity(intentTeacherLectures);
                        }else{
                            startActivity(intentTeacherPracticalLessons);
                        }
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
        getMenuInflater().inflate(R.menu.menu_teacher_course_screen, menu);
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
