package pl.damianmarkowski.sosk;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class TeacherLecturesListCustomAdapter extends BaseAdapter{

    String [] result;
    Context context;
    Intent intentStudentDetails;
    private static LayoutInflater inflater=null;
    public TeacherLecturesListCustomAdapter(TeacherLectures mainActivity, String[] students) {
        // TODO Auto-generated constructor stub
        result=students;
        context=mainActivity;
        intentStudentDetails = new Intent(GlobalVars.context, TeacherStudentScreen.class);
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        TextView student;
    }
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.teacher_lectures_list_item, null);
        holder.student=(TextView) rowView.findViewById(R.id.studentName);
        holder.student.setText(result[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(intentStudentDetails);
            }
        });

        ImageButton infoImage = (ImageButton) rowView.findViewById(R.id.infoImage);
        infoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(intentStudentDetails);
            }
        });
        return rowView;
    }
}
