package pl.damianmarkowski.sosk;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class StudentCoursesFinishedListCustomAdapter extends BaseAdapter{
    List<CourseHelper> result = new ArrayList<CourseHelper>();
    Context context;
    Intent intentStudentCourse;
    private static LayoutInflater inflater=null;
    public StudentCoursesFinishedListCustomAdapter(StudentMainScreen mainActivity, List<CourseHelper> coursesList) {
        // TODO Auto-generated constructor stub
        for(int i=0;i<coursesList.size();i++){
            result.add(coursesList.get(i));
        }
        context=mainActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        intentStudentCourse = new Intent(context, StudentCourseScreen.class);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView name;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.student_courses_list_item, null);
        holder.name=(TextView) rowView.findViewById(R.id.courseName);
        holder.name.setText(result.get(position).getName());
        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVars.courseName = result.get(position).getName();
                GlobalVars.courseId = result.get(position).getId();
                context.startActivity(intentStudentCourse);
            }
        });
        return rowView;
    }

}