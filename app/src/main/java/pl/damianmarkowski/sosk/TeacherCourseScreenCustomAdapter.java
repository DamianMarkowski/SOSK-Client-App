package pl.damianmarkowski.sosk;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TeacherCourseScreenCustomAdapter extends BaseAdapter {
    String [] result;
    Context context;
    private static LayoutInflater inflater=null;
    Intent intentTeacherSchedule, intentTeacherPracticalLessons, intentTeacherLectures;
    public TeacherCourseScreenCustomAdapter(TeacherCourseScreen mainActivity, String[] data) {
        // TODO Auto-generated constructor stub
        result=data;
        context=mainActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        intentTeacherSchedule = new Intent(context, TeacherSchedule.class);
        intentTeacherPracticalLessons = new Intent(context, TeacherPracticalLessons.class);
        intentTeacherLectures = new Intent(context, TeacherLectures.class);
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
        TextView textView;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.course_details_list_item, null);
        holder.textView=(TextView) rowView.findViewById(R.id.textView);
        holder.textView.setText(result[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        context.startActivity(intentTeacherSchedule);
                        break;
                    case 1:
                        context.startActivity(intentTeacherLectures);
                        break;
                    case 2:
                        context.startActivity(intentTeacherPracticalLessons);
                        break;
                }
            }
        });
        return rowView;
    }

}
