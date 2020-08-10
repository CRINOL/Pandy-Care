package com.technet.pandy;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.simple.JSONObject;

import java.util.ArrayList;

import static android.view.View.VISIBLE;

public class PCS{

    Context CTX;
    DialogMgr DMGR;
    LayoutInflater LI;
    CircleMgr CMGR;
    ArrayList<Circle> MY_CIRCLES;
    ArrayAdapter<Circle> CIRCLE_ADAPTER;
    JSONObject CONFIG;

    public PCS(Context CTX, DialogMgr DMGR,LayoutInflater LI,JSONObject CONFIG){
        this.CTX = CTX;
        this.DMGR = DMGR;
        this.CONFIG = CONFIG;
        this.LI = LI;
        CMGR = new CircleMgr(CTX);
    }

    public void doCircles(){
        new CircleLoader().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void loadCircles(){
        CIRCLE_ADAPTER =  new ArrayAdapter<Circle>(CTX,R.layout.circle_join,MY_CIRCLES){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if(convertView == null){
                    convertView = LI.inflate(R.layout.circle_join,null);
                }
                TextView title = convertView.findViewById(R.id.title);
                TextView mem = convertView.findViewById(R.id.pop);
                TextView crt = convertView.findViewById(R.id.crt);
                convertView.findViewById(R.id.nxt).setVisibility(View.INVISIBLE);
                title.setText(MY_CIRCLES.get(position).C1751);
                mem.setText(MY_CIRCLES.get(position).C1752);
                crt.setText("Created by " +MY_CIRCLES.get(position).C1753);
                return convertView;
            }
        };
        ListView lv = ((Activity)CTX).findViewById(R.id.spec_list);
        lv.setAdapter(CIRCLE_ADAPTER);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                
            }
        });
    }

    public void warnEmpty(int which){
        switch(which){
            case 0:((TextView)((Activity)CTX).findViewById(R.id.warning)).setText(CTX.getResources().getString(R.string.no_tabs));
                ((Button)((Activity)CTX).findViewById(R.id.nxt)).setText("ADD NOW");
                break;
            case 1:((TextView)((Activity)CTX).findViewById(R.id.warning)).setText(CTX.getResources().getString(R.string.no_dragula));
                ((Button)((Activity)CTX).findViewById(R.id.nxt)).setText("ORDER NOW");
                break;
            case 2:((TextView)((Activity)CTX).findViewById(R.id.warning)).setText(CTX.getResources().getString(R.string.no_circles));
                ((Button)((Activity)CTX).findViewById(R.id.nxt)).setText("JOIN NOW");
                break;
            case 3:((TextView)((Activity)CTX).findViewById(R.id.warning)).setText(CTX.getResources().getString(R.string.no_doctors));
                ((Button)((Activity)CTX).findViewById(R.id.nxt)).setText("START NOW");
                break;
            case 4:((TextView)((Activity)CTX).findViewById(R.id.warning)).setText(CTX.getResources().getString(R.string.no_clinics));
                ((Button)((Activity)CTX).findViewById(R.id.nxt)).setText("OK FINE");
                break;
            default:
        }
        ((Activity)CTX).findViewById(R.id.spec_head).setVisibility(View.INVISIBLE);
        ((Activity)CTX).findViewById(R.id.spec_warn).setVisibility(VISIBLE);
    }

    private class CircleLoader extends AsyncTask<Void,Void,Void>{

        int err = 0;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            DMGR.showWaiting("Loading circles please wait...");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            MY_CIRCLES = CMGR.getMyCircles(CONFIG.get("PKXS_407").toString());
            if(MY_CIRCLES == null){
                err = 1;
            }else{
                if(MY_CIRCLES.size() == 0){
                    err = 2;
                }else{
                    err = 0;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            DMGR.WAITER_DIALOG.dismiss();
            ((Activity)CTX).findViewById(R.id.main).setVisibility(View.INVISIBLE);
            new AnimationHelper(((Activity)CTX).findViewById(R.id.spec),CTX).zoomIn();
            if(err == 0){
                loadCircles();
            }else if(err == 2){
                warnEmpty(2);
            }else{
                Toast.makeText(CTX,"Unknown Error Occurred",Toast.LENGTH_LONG).show();
            }
        }
    }


}