package com.technet.pandy;

import android.content.Context;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;

public class CircleMgr{

    Context CTX;
    DialogMgr DMGR;
    NetSupport NS;
    String SRV_ADDR = "http://127.0.0.1:80";
    int NET = 0;

    public CircleMgr(Context CTX){
        this.CTX = CTX;
    }

    public ArrayList<Circle> getCircles(String search_crt){
        try{
            NS = new NetSupport();
            String data = "";
            if(search_crt == null) {
                NET = NS.sendString(SRV_ADDR, new String[]{},new String[]{});
                data = NS.FROM_SERVER;
            }else{
                NET = NS.sendString(SRV_ADDR, new String[]{},new String[]{});
                data = NS.FROM_SERVER;
            }
            if(data.equals("NO_CIRCLES")){
                return new ArrayList<>();
            }else{
                DataParser dp = new DataParser(data);
                return dp.getCirclesSearch();
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public JSONObject getCircle(String _ID){
        try{
            NS = new NetSupport();
            NET = NS.sendString(SRV_ADDR, new String[]{},new String[]{});
            String data = NS.FROM_SERVER;
            return (JSONObject)new JSONParser().parse(data);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<CircleMember> getMembers(String _ID){
        try{
            NS = new NetSupport();
            NET = NS.sendString(SRV_ADDR, new String[]{},new String[]{});
            String data = NS.FROM_SERVER;
            return new DataParser(data).getCircleMems();
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Circle> getMyCircles(String pandy_id){
        try{
            NS = new NetSupport();
            NET = NS.sendString(SRV_ADDR, new String[]{},new String[]{});
            String data = NS.FROM_SERVER;
            if(data.equals("X404") || data.equals("")){
                return new ArrayList<>();
            }else {
                return new DataParser(data).getCirclesSearch();
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public int joinCircle(String _ID,String user_config){
        try{
            NS = new NetSupport();
            NET = NS.sendString(SRV_ADDR, new String[]{},new String[]{});
            if(NS.FROM_SERVER.equals("XX141")){
                return 1;
            }else if(NS.FROM_SERVER.equals("XX140")){
                return 2;
            }else{
                return 0;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return 0;
        }
    }

    public int leaveCircle(String GROUP_ID,String USER_ID){
        try{
            NS = new NetSupport();
            NET = NS.sendString(SRV_ADDR, new String[]{},new String[]{});
            if(NS.FROM_SERVER.equals("XX141")){
                return 1;
            }else if(NS.FROM_SERVER.equals("XX140")){
                return 2;
            }else{
                return 0;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return 0;
        }
    }

    class UISection{

        public void showMyCircle(){

        }

    }

}