package pl.damianmarkowski.sosk;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class StudentLectures extends AppCompatActivity {

    Intent intentMainScreen, intentAboutScreen;
    ListView listView;
    TextView courseName;
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_lectures);

        bar = (ProgressBar) this.findViewById(R.id.progressBar);

        intentMainScreen = new Intent(this, MainActivity.class);

        intentAboutScreen = new Intent(this, AboutScreen.class);

        courseName = (TextView) findViewById(R.id.textViewCourseName);

        courseName.setText("Kurs: " + GlobalVars.courseName);

        new MaterialsRequest().execute();

        listView = (ListView) findViewById(R.id.listView);
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

    public class MaterialsRequest extends AsyncTask<Void, Void, EducationalMaterialsList> {

        @Override
        protected void onPreExecute(){
            bar.setVisibility(View.VISIBLE);
        }

        @Override
        protected EducationalMaterialsList doInBackground(Void... params) {
            try {
                final String url = GlobalVars.hostUrl + "student/educationalMaterials?courseId=" + GlobalVars.courseId;
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                EducationalMaterialsList result = restTemplate.getForObject(url, EducationalMaterialsList.class);
                return result;
            } catch (Exception e) {
                Log.e("StudentLectures", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(EducationalMaterialsList list) {

            int length = list.getList().size();

            String[] fileNames = new String[length];
            ImageView[] icons = new ImageView[] {};

            for(int i=0; i < length; i++){
                fileNames[i] = (list.getList().get(i).getName());
            }

            listView.setAdapter(new StudentLecturesListCustomAdapter(StudentLectures.this, fileNames,icons));

            bar.setVisibility(View.GONE);
        }
    }
}
