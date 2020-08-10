package com.technet.pandy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static android.content.Context.WIFI_SERVICE;

public class NetSupport{
    private String JSON_STRING = "";
    private static InputStream CLIENT_IN = null;
    private static OutputStream CLIENT_OUT = null;
    private static URL SERVER_URL = null;
    private static HttpURLConnection SERVER_CONN = null;
    String _IP;
    Context CTX;



    public InputStream getStrm(String url) {
        try{
            SERVER_URL = new URL(url);
            SERVER_CONN = (HttpURLConnection)SERVER_URL.openConnection();
            SERVER_CONN.setRequestMethod("GET");
            CLIENT_IN = SERVER_CONN.getInputStream();
            return CLIENT_IN;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public NetSupport(Context CTX){
        this.CTX = CTX;
    }

    public NetSupport(){

    }

    public String getIP(){
        WifiManager wm = (WifiManager) CTX.getApplicationContext().getSystemService(WIFI_SERVICE);
        _IP = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        if(_IP.equals("0.0.0.0")){
            _IP  = "127.0.0.1";
        }
        Log.i("IPXXXXXX",_IP);
        return _IP;
    }

    String FROM_SERVER = "";
    int HAND_OVER = 0;
    public int sendString(String url,String[] keys,String[] vals){
        try{
            String datax = "";
            int k = 0;
            for(String key:keys){
                if(k == 0){
                    datax = datax + key + "=" + URLEncoder.encode(vals[k],"UTF-8");
                }else{
                    datax = datax + "&" + key + "=" + URLEncoder.encode(vals[k],"UTF-8");
                }
                k++;
            }
            FROM_SERVER = "";
            //datax = URLEncoder.encode(datax, "UTF-8");
            Log.i("loooopp", datax);
            //("data=" + data + "&tgt=" + type);
            SERVER_URL = new URL(url);
            SERVER_CONN = (HttpURLConnection)SERVER_URL.openConnection();
            SERVER_CONN.setConnectTimeout(30000);
            //SERVER_CONN = (HttpURLConnection) SERVER_URL.openConnection();
            SERVER_CONN.setDoOutput(true);
            SERVER_CONN.setRequestMethod("POST");
            SERVER_CONN.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            SERVER_CONN.setRequestProperty("Content-Length",Integer.toString(datax.length()));
            SERVER_CONN.setRequestProperty("charset","utf-8");
            SERVER_CONN.connect();
            CLIENT_OUT = SERVER_CONN.getOutputStream();
            DataOutputStream DS = new DataOutputStream(CLIENT_OUT);
            DS.write(datax.getBytes());
            Log.i("CODEXXXXXX",Integer.toString(SERVER_CONN.getResponseCode()));
            if(SERVER_CONN.getResponseCode() == HttpURLConnection.HTTP_OK){
                CLIENT_IN = SERVER_CONN.getInputStream();
                FROM_SERVER = new FileMgr(CLIENT_IN).readLine();
                Log.i("from_server", "sevell" + FROM_SERVER);
//            Log.i("Data",);
                CLIENT_IN.close();
            }else{
                CLIENT_IN = SERVER_CONN.getInputStream();
                FROM_SERVER = new FileMgr(CLIENT_IN).readLine();
                Log.i("from_server", FROM_SERVER);
                CLIENT_IN.close();
            }
            /*if(HAND_OVER == 1){
                return 1;
            }else {
                if (FROM_SERVER.equals("DONE") || new DataParser("[" + FROM_SERVER + "]").getValue(0, "STATE").equals("PASSED") || new DataParser("[" + FROM_SERVER + "]").getValue(0, "STATE").equals("DONE")) {
                    return 1;
                } else {
                    return 0;
                }
            }*/
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public String getSrvIP(String _IP){
        int kk = 0;
        int del_pos = 0;
        for(int t  = 0;t < _IP.length();t++){
            if(Character.toString(_IP.charAt(t)).equals(".")){
                Log.i("pppcc","picric" + kk);
                if(kk == 2){
                    del_pos = t;
                    break;
                }
                kk++;
            }
        }
        Log.i("pollic","f"+ del_pos);
        StringBuffer SB = new StringBuffer(_IP);
        SB.delete(del_pos + 1,_IP.length());
        String prx = SB.toString();
        String url = "";
        for(int i = 100;i < 256;i++){
            if(getStrm("http://" + prx + String.valueOf(i) + ":80/Rapang/Data/Config.json") != null){
                Log.i("search state","http://" + prx + String.valueOf(i) + "---PASS");
                url = "http://" + prx + String.valueOf(i) + ":80/Rapang/";
                break;
            }else{
                Log.i("search state","http://" + prx + String.valueOf(i) + "---FAIL");
            }
        }
        return url;
    }

    public String getString(String addr){
        String def_ret = "";
        try{
            InputStream is = getStrm(addr);
            int c;
            while((c = is.read()) != -1){
                def_ret = def_ret + Character.toString((char)c);
            }
            return def_ret;
        }catch (Exception e){
            e.printStackTrace();
            return def_ret;
        }
    }

    public Bitmap getImgBit(String addr){
        Bitmap bmp;
        try{
            InputStream is = getStrm(addr);
            return BitmapFactory.decodeStream(is);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}