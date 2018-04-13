package pl.damianmarkowski.sosk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class TeacherPracticalLessonsListCustomAdapter extends BaseAdapter{

    String [] result;
    Context context;
    Intent intentAddTip;
    private static LayoutInflater inflater=null;
    public TeacherPracticalLessonsListCustomAdapter(TeacherPracticalLessons mainActivity, String[] students) {
        // TODO Auto-generated constructor stub
        result=students;
        context=mainActivity;
        intentAddTip = new Intent(GlobalVars.context, TeacherAddTipScreen.class);
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.teacher_practical_lessons_list_item, null);
        holder.student=(TextView) rowView.findViewById(R.id.studentName);
        holder.student.setText(result[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(intentAddTip);
            }
        });

        ImageButton plusIcon = (ImageButton) rowView.findViewById(R.id.plusIcon);
        plusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(intentAddTip);
            }
        });
        return rowView;
    }
}
