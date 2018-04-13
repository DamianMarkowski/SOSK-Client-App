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

public class StudentPracticalLessonsListCustomAdapter extends BaseAdapter{
    String [] result;
    Context context;
    String [] datesArray;
    private static LayoutInflater inflater=null;
    public StudentPracticalLessonsListCustomAdapter(StudentPracticalLessons mainActivity, String[] tips, String[] dates) {
        // TODO Auto-generated constructor stub
        result=tips;
        context=mainActivity;
        datesArray=dates;
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
        TextView tip;
        TextView date;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.student_practical_lessons_list_item, null);
        holder.tip=(TextView) rowView.findViewById(R.id.tip);
        holder.date=(TextView) rowView.findViewById(R.id.date);
        holder.tip.setText(result[position]);
        holder.date.setText(datesArray[position]);
        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return rowView;
    }

}