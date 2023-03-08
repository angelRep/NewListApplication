package com.example.newlistapplication.ui.main;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.CallLog;
import android.text.format.DateFormat;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.newlistapplication.MainActivity;
import com.example.newlistapplication.MyObject;
import com.example.newlistapplication.R;

import static androidx.core.app.ActivityCompat.requestPermissions;

public class CustomListAdapter extends ArrayAdapter<MyObject>
{
    private ArrayList<MyObject> data;
    private Context context;
    private int resource;
    private View view;
    private Holder holder;
    //private HashMap<String,String> hashMap;
    private float x1,x2;
    static final int MIN_DISTANCE = 50;

    private static final int PHONE_CALL_REQUEST=0;

    public CustomListAdapter(Context context, int resource,ArrayList<MyObject> objects)
    {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.data=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(resource, parent,false);

        ConstraintLayout hiddenlayout = (ConstraintLayout)view.findViewById(R.id.swipeLayer);

        holder=new Holder();
        holder.text_name = (TextView)view.findViewById(R.id.textViewName);
        holder.text_description = (TextView)view.findViewById(R.id.textViewDescription);
        holder.text_email = (TextView)view.findViewById(R.id.textViewEmail);
        holder.text_phone = (TextView)view.findViewById(R.id.textViewPhone);

        holder.emailBtn = (ImageButton)view.findViewById(R.id.buttonEmail);
        holder.phoneBtn = (ImageButton)view.findViewById(R.id.buttonPhone);

        MyObject obj =data.get(position);

        holder.text_name.setText(obj.getFullname());
        holder.text_description.setText(obj.getDescription());
        holder.text_email.setText(obj.getEmail());
        holder.text_phone.setText(obj.getPhone());

        holder.emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Email Button Pressed", Toast.LENGTH_SHORT).show();
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"+holder.text_email.getText()));
                //emailIntent.setType("text/plain");
                //emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email Subject");
                //emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message");

                context.startActivity(emailIntent);
            }
        });

        holder.phoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Phone Button Pressed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_CALL);

                intent.setData(Uri.parse("tel:" + holder.text_phone.getText()));

                if (context.checkSelfPermission(Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(v.getContext(), "Call permission not granted", Toast.LENGTH_SHORT).show();
                }
                else {
                    context.startActivity(intent);
                }
            }
        });

        //animation for swipe
        Transition transition = new Slide(Gravity.RIGHT);
        transition.setDuration(600);
        transition.addTarget(hiddenlayout);

        //swipe
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        float deltaX = x2 - x1;
                        if (Math.abs(deltaX) > MIN_DISTANCE)
                        {
                            //Toast.makeText(v.getContext(), "left2right swipe", Toast.LENGTH_SHORT).show();

                            if (x1<x2) {
                                TransitionManager.beginDelayedTransition(parent, transition);
                                hiddenlayout.setVisibility(View.INVISIBLE);
                            }
                            else {
                                TransitionManager.beginDelayedTransition(parent, transition);
                                hiddenlayout.setVisibility(View.VISIBLE);
                            }
                        }
                        else
                        {
                            // consider as something else - a screen tap for example
                        }
                        break;
                }
                return true;//View.OnTouchListener.super.onTouchEvent(event);
            }
        });

        return view;
    }

    public class Holder
    {
        TextView text_name;
        TextView text_description;
        TextView text_email;
        TextView text_phone;

        ImageButton phoneBtn;
        ImageButton emailBtn;
    }

}
