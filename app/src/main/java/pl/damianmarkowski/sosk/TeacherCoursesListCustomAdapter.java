package pl.damianmarkowski.sosk;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class TeacherCoursesListCustomAdapter extends BaseAdapter{
    String [] result;
    Context context;
    String [] statuses;
    Integer[] ids;
    Intent intentTeacherCourse;
    private static LayoutInflater inflater=null;
    public TeacherCoursesListCustomAdapter(TeacherMainScreen mainActivity, String[] coursesNamesList, String[] coursesStatusesList, Integer[] idsList) {
        // TODO Auto-generated constructor stub
        result=coursesNamesList;
        context=mainActivity;
        statuses=coursesStatusesList;
        ids=idsList;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        intentTeacherCourse = new Intent(context, TeacherCourseScreen.class);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
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
        TextView status;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.student_courses_list_item, null);
        holder.name=(TextView) rowView.findViewById(R.id.courseName);
        holder.name.setText(result[position]);
        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVars.courseName = result[position];
                GlobalVars.courseId = ids[position];
                context.startActivity(intentTeacherCourse);
            }
        });
        return rowView;
    }

}