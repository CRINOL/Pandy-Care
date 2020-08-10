package com.technet.pandy;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PandyCare extends AppCompatActivity {

    DialogMgr DMGR;
    String[] OPTS = new String[5];
    String[] SUBS = new String[5];
    String[] params = {"{\"K904\":\"KATALO ABUBAKER\",\"PKXS_407\":\"PND-8250-8010-001\"}"};
    JSONObject CONFIG;
    String USR_DATA = "{\"S14A\":\"0\",\"S14B\":\"0\",\"S14C\":\"0\",\"S14D\":\"0\",\"S14E\":\"0\"}";
    JSONObject USER_DATA;
    PCS PCS;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //params = getIntent().getStringArrayExtra("params");
        setContentView(R.layout.pandy_care);
        DMGR = new DialogMgr(this);
        new QueryLoader().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void genLoad() {
        ((TextView)findViewById(R.id.logo)).setText(CONFIG.get("K904").toString());
        ListView lv = findViewById(R.id.opts_list);
        final String[] args = {"S14A","S14B","S14C","S14D","S14E"};
        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.two_row_item, OPTS) {
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.two_row_item, null);
                }
                ((TextView) convertView.findViewById(R.id.title)).setText(OPTS[position] + "(" + USER_DATA.get(args[position]).toString() + ")");
                ((TextView) convertView.findViewById(R.id.info)).setText(SUBS[position]);
                return convertView;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 2){
                    PCS.doCircles();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(findViewById(R.id.main).getVisibility() == View.INVISIBLE){
            new AnimationHelper(findViewById(R.id.spec),this).zoomOut();
            new AnimationHelper(findViewById(R.id.main),this).zoomIn();
        }else{
            finish();
        }
    }

    String SRV_ADDR = "http://127.0.0.1:80";

    private class QueryLoader extends AsyncTask<Void, Void, Void> {
        int err = 0;
        NetSupport NS = new NetSupport();

        @Override
        protected void onPreExecute() {
            DMGR.showWaiting("Loading Data...");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                OPTS[0] = getResources().getString(R.string.pdc_menu_1);
                OPTS[1] = getResources().getString(R.string.pdc_menu_2);
                OPTS[2] = getResources().getString(R.string.pdc_menu_3);
                OPTS[3] = getResources().getString(R.string.pdc_menu_4);
                OPTS[4] = getResources().getString(R.string.pdc_menu_5);
                SUBS[0] = getResources().getString(R.string.pdc_menu_sub_1);
                SUBS[1] = getResources().getString(R.string.pdc_menu_sub_2);
                SUBS[2] = getResources().getString(R.string.pdc_menu_sub_3);
                SUBS[3] = getResources().getString(R.string.pdc_menu_sub_4);
                SUBS[4] = getResources().getString(R.string.pdc_menu_sub_5);
                CONFIG = (JSONObject) new JSONParser().parse(params[0]);
                if(NS.sendString(SRV_ADDR,new String[]{"Z510","Z511"},new String[]{CONFIG.get("PKXS_407").toString(),"PCD"}) == 1){
                    USR_DATA = NS.FROM_SERVER;
                }
                USER_DATA = (JSONObject) new JSONParser().parse(USR_DATA);
                err = 0;
            } catch (Exception ex) {
                ex.printStackTrace();
                err = 1;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            DMGR.WAITER_DIALOG.dismiss();
            if(err == 0){
                PCS = new PCS(PandyCare.this,DMGR,CONFIG);
                genLoad();
            }
        }
    }
}