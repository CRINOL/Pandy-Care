package com.technet.pandy;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class DialogMgr {

    Context CONTEXT;
    WindowManager WM;
    JSONArray RES_ARR = new JSONArray();

    public DialogMgr(Context CTX){
        CONTEXT = CTX;
        WM = (WindowManager)CTX.getSystemService(Context.WINDOW_SERVICE);
    }

    Dialog WAITER_DIALOG;
    public void showWaiting(String msg){
        final Dialog dialog = new Dialog(CONTEXT);
        WAITER_DIALOG = dialog;
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.waiting);
        ((TextView)dialog.findViewById(R.id.waiter)).setText(msg);
        dialog.show();
    }

    Dialog ALERT_DIALOG;
    public void alertSingle(String title,String msg){
        AlertDialog.Builder alertDialogBuilder = new
                AlertDialog.Builder(CONTEXT);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(msg);
        alertDialogBuilder.setPositiveButton("OK, GOT YOU!",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                }
        );
        ALERT_DIALOG = alertDialogBuilder.create();
        ALERT_DIALOG.show();
    }

    int RET = -1;
    public void alert(String title,String msg,String ok_val){
        AlertDialog.Builder alertDialogBuilder = new
                AlertDialog.Builder(CONTEXT);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(msg);
        alertDialogBuilder.setPositiveButton(ok_val,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        RET = 1;
                    }
                }
        );
        alertDialogBuilder.setNegativeButton("CANCEL",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        RET = 0;
                    }
                }
        );
        ALERT_DIALOG = alertDialogBuilder.create();
        ALERT_DIALOG.show();
    }

    public void alertCustom(String title,String msg,String ok_val,String no_val){
        AlertDialog.Builder alertDialogBuilder = new
                AlertDialog.Builder(CONTEXT);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(msg);
        alertDialogBuilder.setPositiveButton(ok_val,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        RET = 1;
                    }
                }
        );
        alertDialogBuilder.setNegativeButton(no_val,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        RET = 0;
                    }
                }
        );
        ALERT_DIALOG = alertDialogBuilder.create();
        ALERT_DIALOG.show();
    }

    public void recomm1(String dss_name,String adv,int err,int type){
        final Dialog dialog = new Dialog(CONTEXT);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.recomm1);
        if(err == 0) {
            if(type == 1) {
                ((TextView) dialog.findViewById(R.id.r_head)).setText("RECOMMENDED TREATMENT FOR " + dss_name);
            }else if(type == 2){
                ((TextView) dialog.findViewById(R.id.r_head)).setText("HOW TO DO " + dss_name + " PROPERLY?");
            }
        }else{
            if(type == 1) {
                ((TextView) dialog.findViewById(R.id.r_head)).setText("TREATMENT INFORMATION FOR " + dss_name + " IS NOT AVAILABLE");
            }else{
                ((TextView) dialog.findViewById(R.id.r_head)).setText("INFORMATION ABOUT " + dss_name + " IS NOT AVAILABLE");
            }
        }
        if(type == 1) {
            ((TextView) dialog.findViewById(R.id.r_body)).setText(adv);
        }else if(type == 2){
            if(err != 1){
                ((TextView) dialog.findViewById(R.id.r_body)).setText(Html.fromHtml(adv));
            }else{
                ((TextView) dialog.findViewById(R.id.r_body)).setText(adv);
            }
        }
        dialog.findViewById(R.id.go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        ALERT_DIALOG = dialog;
        dialog.show();
    }

    String DATE_STR = "";
    Dialog DATE_DIALOG;
    public void doDate(String title,String crr_date){
        final Dialog dialog = new Dialog(CONTEXT);
        DATE_DIALOG = dialog;
        DATE_STR = crr_date == null ? "" : crr_date;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.date_picker);
        ((TextView)dialog.findViewById(R.id.head)).setText(title);
        final DatePicker dp = dialog.findViewById(R.id.date);
        dialog.findViewById(R.id.go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int d = dp.getDayOfMonth();
                int m = dp.getMonth() + 1;
                int y = dp.getYear();
                String dt = "";String mn = "";String yy = "";
                if(d < 10){
                    dt = "0" + d;
                }else{
                    dt = String.valueOf(d);
                }
                if(m < 10){
                    mn = "0" + m;
                }else{
                    mn = String.valueOf(m);
                }
                //String dt = new DecimalFormat("##").format(d);
                DATE_STR =  dt + "/" + mn + "/" + y;
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    String OPT = "";
    int SEL = 0;
    Dialog OPT_DIALOG;
    public void showOpts(final String[] opts){
        OPT_DIALOG = new Dialog(CONTEXT);
        OPT_DIALOG.setContentView(R.layout.list_opts);
        ListView lv = OPT_DIALOG.findViewById(R.id.lst);
        lv.setAdapter(new ArrayAdapter<String>(CONTEXT, android.R.layout.simple_list_item_1, opts));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SEL = i;
                OPT = opts[i];
                OPT_DIALOG.dismiss();
            }
        });
        OPT_DIALOG.show();
    }

    String SYM_LEVEL = "";
    String SYM_LEVEL_STR = "";
    public void showSymLevels(JSONObject data, final int index){
        ALERT_DIALOG = new Dialog(CONTEXT);
        OPT_DIALOG.setContentView(R.layout.levels);
        ((Button)ALERT_DIALOG.findViewById(R.id.clk15)).setText(data.get("_3543").toString());
        ((Button)ALERT_DIALOG.findViewById(R.id.clk16)).setText(data.get("_3545").toString());
        ((Button)ALERT_DIALOG.findViewById(R.id.clk17)).setText(data.get("_3546").toString());
        ((Button)ALERT_DIALOG.findViewById(R.id.clk18)).setText("I AM NOT SURE");
        ALERT_DIALOG.findViewById(R.id.clk15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jbj = (JSONObject)RES_ARR.get(index);
                SYM_LEVEL = "XX141";
                SYM_LEVEL_STR = jbj.get(SYM_LEVEL).toString();
                ALERT_DIALOG.dismiss();
            }
        });
        ALERT_DIALOG.findViewById(R.id.clk16).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jbj = (JSONObject)RES_ARR.get(index);
                SYM_LEVEL = "XX142";
                SYM_LEVEL_STR = jbj.get(SYM_LEVEL).toString();
                ALERT_DIALOG.dismiss();
            }
        });
        ALERT_DIALOG.findViewById(R.id.clk17).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jbj = (JSONObject)RES_ARR.get(index);
                SYM_LEVEL = "XX140";
                SYM_LEVEL_STR = jbj.get(SYM_LEVEL).toString();
                ALERT_DIALOG.dismiss();
            }
        });
        ALERT_DIALOG.findViewById(R.id.clk15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jbj = (JSONObject)RES_ARR.get(index);
                SYM_LEVEL = "XX14X";
                SYM_LEVEL_STR = "Not sure about this symptom";
                ALERT_DIALOG.dismiss();
            }
        });
        ALERT_DIALOG.show();
    }

}
