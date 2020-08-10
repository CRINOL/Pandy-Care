package com.technet.pandy;

import android.util.Log;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;

public class DataParser{
    public JSONArray DATA_ARRAY = null;
    private JSONObject DATA_FIELD = null;
    private JSONParser DATA_PARSER = null;
    private Object JSON_OBJECT = null;
    private String DATA_STRING = "";
    private String JSON_STRING = "";
    private ArrayList<DSS> ARR = new ArrayList<>();
    private int DEL_POSITION = 0;

    public DataParser(ArrayList<DSS> ARR){
        this.ARR = ARR;
    }

    public DataParser(String JSON_STRING) {
        try {
            this.JSON_STRING = JSON_STRING;
            DATA_FIELD = new JSONObject();
            DATA_PARSER = new JSONParser();
            JSON_OBJECT = DATA_PARSER.parse(this.JSON_STRING);
            DATA_ARRAY = (JSONArray) JSON_OBJECT;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DataParser() {
        DATA_FIELD = new JSONObject();
    }

    public JSONArray getArray(){
        return DATA_ARRAY;
    }

    public ArrayList<DSS> getDSS(){
        try {
            ArrayList<DSS> dss = new ArrayList<DSS>();
            for (Object obj : DATA_ARRAY) {
                JSONObject jbj = (JSONObject) obj;
                System.out.println("plx" + jbj.toJSONString());
                DSS dss1 = new DSS();
                dss1._2881 = jbj.get("_2881").toString();
                dss1._2882 = parseAsArray(jbj.get("_2882").toString());
                dss1._2883 = jbj.get("_2883").toString();
                dss.add(dss1);
            }
            return dss;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<QSS> getQSS(){
        try {
            ArrayList<QSS> qss = new ArrayList<QSS>();
            for (Object obj : DATA_ARRAY) {
                JSONObject jbj = (JSONObject) obj;
                System.out.println("plx" + jbj.toJSONString());
                QSS qss1 = new QSS();
                qss1._3540 = jbj.get("_3540").toString();
                qss1._3541 = jbj.get("_3541").toString();
                qss1._3542 = jbj.get("_3542").toString();
                qss1._3543 = jbj.get("_3543").toString();
                qss1._3544 = jbj.get("_3544").toString();
                qss1._3545 = jbj.get("_3545").toString();
                qss1._3546 = jbj.get("_3546").toString();
                qss1._3548 = jbj.get("_3548").toString();
                qss.add(qss1);
            }
            return qss;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<QSS> getQSSShort() {
        try {
            ArrayList<QSS> qss = new ArrayList<QSS>();
            for (Object obj : DATA_ARRAY) {
                JSONObject jbj = (JSONObject) obj;
                System.out.println("plx" + jbj.toJSONString());
                QSS qss1 = new QSS();
                qss1._3540 = jbj.get("_3540").toString();
                qss1._3543 = jbj.get("_3543").toString();
                qss1._3545 = jbj.get("_3545").toString();
                qss1._3546 = jbj.get("_3546").toString();
                qss.add(qss1);
            }
            return qss;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<RSS> getRSS(){
        try {
            ArrayList<RSS> qss = new ArrayList<RSS>();
            for (Object obj : DATA_ARRAY) {
                JSONObject jbj = (JSONObject) obj;
                System.out.println("plx" + jbj.toJSONString());
                RSS qss1 = new RSS();
                qss1._2850 = jbj.get("_2850").toString();
                qss1._2851 = jbj.get("_2851").toString();
                qss1._2852 = jbj.get("_2852").toString();
                qss1._2853 = jbj.get("_2853").toString();
                qss1._2854 = jbj.get("_2854").toString();
                qss1._2855 = jbj.get("_2855").toString();
                qss1._2856 = jbj.get("_2856").toString();
                qss.add(qss1);
            }
            return qss;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<RSS> getRSSShort(){
        try{
            ArrayList<RSS> qss = new ArrayList<RSS>();
            for (Object obj : DATA_ARRAY) {
                JSONObject jbj = (JSONObject) obj;
                RSS rss = new RSS();
                rss._2850 = jbj.get("_2850").toString();
                rss._2851 = jbj.get("_2851").toString();
                rss._2852 = jbj.get("_2852").toString();
                rss._2856 = jbj.get("_2856").toString();
                qss.add(rss);
            }
            return qss;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<RSS> getRSSShortCustom(String K903){
        try{
            ArrayList<RSS> qss = new ArrayList<RSS>();
            for (Object obj : DATA_ARRAY) {
                JSONObject jbj = (JSONObject) obj;
                String k9xxx = jbj.get("_2857").toString();
                if(k9xxx.equals("ALL")){
                    RSS rss = new RSS();
                    rss._2850 = jbj.get("_2850").toString();
                    rss._2851 = jbj.get("_2851").toString();
                    rss._2852 = jbj.get("_2852").toString();
                    rss._2856 = jbj.get("_2856").toString();
                    qss.add(rss);
                }else{
                    k9xxx = k9xxx.replace('*','"');
                    JSONObject lc = (JSONObject)new JSONParser().parse(k9xxx);
                    if(lc.get(K903) != null){
                        RSS rss = new RSS();
                        rss._2850 = jbj.get("_2850").toString();
                        rss._2851 = jbj.get("_2851").toString();
                        rss._2852 = jbj.get("_2852").toString();
                        rss._2856 = jbj.get("_2856").toString();
                        qss.add(rss);
                    }
                }
            }
            return qss;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Circle> getCircles(){
        try {
            ArrayList<Circle> qss = new ArrayList<>();
            for (Object obj : DATA_ARRAY) {
                JSONObject jbj = (JSONObject) obj;
                System.out.println("plx" + jbj.toJSONString());
                Circle qss1 = new Circle();
                qss1.C1751 = jbj.get("C1751").toString();
                qss1.C1752 = jbj.get("C1752").toString();
                qss1.C1753 = jbj.get("C1753").toString();
                qss1.C1754 = jbj.get("C1754").toString();
                qss1.C1755 = jbj.get("C1755").toString();
                qss1.C1756 = jbj.get("C1756").toString();
                qss1.C1758 = jbj.get("C1758").toString();
                qss1.C1759 = jbj.get("C1759").toString();
                qss.add(qss1);
            }
            return qss;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Circle> getCirclesSearch(){
        try {
            ArrayList<Circle> qss = new ArrayList<>();
            for (Object obj : DATA_ARRAY) {
                JSONObject jbj = (JSONObject) obj;
                System.out.println("plx" + jbj.toJSONString());
                Circle qss1 = new Circle();
                qss1.C1751 = jbj.get("C1751").toString();
                qss1.C1752 = jbj.get("C1752").toString();
                qss1.C1753 = jbj.get("C1753").toString();
                qss1.C1754 = jbj.get("C1754").toString();
                qss1.C1758 = jbj.get("C1758").toString();
                qss1.C1757 = jbj.get("C1757").toString();
                qss1.C1759 = jbj.get("C1759").toString();
                qss.add(qss1);
            }
            return qss;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<CircleMember> getCircleMems(){
        try{
            ArrayList<CircleMember> qss = new ArrayList<>();
            for (Object obj : DATA_ARRAY) {
                JSONObject jbj = (JSONObject) obj;
                System.out.println("plx" + jbj.toJSONString());
                CircleMember qss1 = new CircleMember();
                qss1.K901 = jbj.get("K901").toString();
                qss1.K903 = jbj.get("K903").toString();
                qss1.K904 = jbj.get("K904").toString();
                qss1.PKXS_407 = jbj.get("PKXS_407").toString();
                qss.add(qss1);
            }
            return qss;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public String[] getStringArray(String crt){
        try{
            String[] arr = new String[DATA_ARRAY.size()];
            int cc = 0;
            for(Object obj:DATA_ARRAY){
                arr[cc] = ((JSONObject)obj).get(crt).toString();
                cc++;
            }
            return arr;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public void includeObj(JSONObject jbj,String[] crts,int index){
        try{
            JSONObject jbk = (JSONObject)DATA_ARRAY.get(index);
            for(int h = 0;h < jbj.size();h++){
                Log.i("CRTS",crts[h] + "--");
                jbk.put(crts[h],jbj.get(crts[h]).toString());
            }
            DATA_ARRAY.remove(index);
            DATA_ARRAY.add(index,jbk);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public JSONArray addSensitive(JSONObject jbj,String key,String val,JSONArray jarr){
        try{
            int ext = 0;
            for(int c = 0;c < jarr.size();c++){
                JSONObject jbj1 = (JSONObject)jarr.get(c);
                if(jbj1.get(key).toString().equals(val)){
                    jarr.remove(c);
                    jarr.add(c,jbj);
                    ext = 1;
                    break;
                }
            }
            if(ext == 0){
                jarr.add(jbj);
            }
            return jarr;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public JSONObject getObj(String[] keys,String[] vals){
        try{
            JSONObject jbj = new JSONObject();
            int ct = 0;
            for(String k:keys){
                jbj.put(k,vals[ct]);
                ct++;
            }
            return jbj;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public void updateObj(String key,String val,int index){
        try {
            JSONObject jbj = (JSONObject)DATA_ARRAY.get(index);
            jbj.put(key,val);
            DATA_ARRAY.remove(index);
            DATA_ARRAY.add(index,jbj);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    int NO_HIGHEST = 0;
    public JSONObject getHighestObj(JSONArray jarr,String comp_key){
        try{
            //JSONObject jbj = new JSONObject();
            int max = 0;
            int index = 0;
            for(int c = 0;c < jarr.size();c++){
                int mx = Integer.parseInt(((JSONObject)jarr.get(c)).get(comp_key).toString());
                if(c == 0){
                    max = mx;
                }else{
                    if(mx > max){
                        max = mx;
                        index = c;
                    }
                }
            }
            if(index > 0){
                return (JSONObject)jarr.get(index);
            }else{
                NO_HIGHEST = 1;
                return null;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public String getValue(String crt,int index){
        try{
            JSONObject jbj = ((JSONObject)DATA_ARRAY.get(index));
            return jbj.get(crt).toString();
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public JSONArray runTipper(JSONArray jarr,String search_key,String search_val,String tipper_key,int val){
        try{
            int nf = 0;
            for(int c = 0;c < jarr.size();c++){
                JSONObject jbj = (JSONObject)jarr.get(c);
                if(jbj.get(search_key).toString().equals(search_val)){
                    jbj.put(tipper_key,String.valueOf(Integer.parseInt(jbj.get(tipper_key).toString()) + val));
                    jarr.remove(c);
                    jarr.add(c,jbj);
                    nf = 0;
                    break;
                }else{
                    nf = 1;
                }
            }
            if(nf == 1){
                JSONObject jbj2 = new JSONObject();
                jbj2.put(search_key,search_val);
                jbj2.put(tipper_key,String.valueOf(val));
                jarr.add(jbj2);
            }
            return jarr;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public void runTipper(String search_key,String search_val,String tipper_key,int val){
        try{
            int nf = 0;
            for(int c = 0;c < DATA_ARRAY.size();c++){
                JSONObject jbj = (JSONObject)DATA_ARRAY.get(c);
                if(jbj.get(search_key).toString().equals(search_val)){
                    jbj.put(tipper_key,String.valueOf(Integer.parseInt(jbj.get(tipper_key).toString()) + val));
                    DATA_ARRAY.remove(c);
                    DATA_ARRAY.add(c,jbj);
                    nf = 0;
                    break;
                }else{
                    nf = 1;
                }
            }
            if(nf == 1){
                JSONObject jbj2 = new JSONObject();
                jbj2.put(search_key,search_val);
                jbj2.put(tipper_key,String.valueOf(val));
                DATA_ARRAY.add(jbj2);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void runTipper(String tipper_key,int tipper_val,int index){
        try{
            JSONObject jbj = (JSONObject)DATA_ARRAY.get(index);
            jbj.put(tipper_key,String.valueOf(Integer.parseInt(jbj.get(tipper_key).toString()) + tipper_key));
            DATA_ARRAY.remove(index);
            DATA_ARRAY.add(index,jbj);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public String getCleanJSON(){
        try{
            String ret = "";
            for(Object obj:DATA_ARRAY){
                if(ret.length() == 0){
                    ret = ((JSONObject)obj).toString();
                }else{
                    ret = ret + "," + ((JSONObject)obj).toString();
                }
            }
            return ret;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public JSONObject getObj(int index){
        try{
            return (JSONObject)DATA_ARRAY.get(index);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public JSONObject removeObj(String crt){
        try{
            JSONObject jbj = null;
            int c  = 0;
            for(Object obj:DATA_ARRAY){
                jbj = (JSONObject)obj;
                if(jbj.get(crt) != null){
                    DATA_ARRAY.remove(c);
                    break;
                }
                c++;
            }
            //Log.i("DATA_REM",DATA_ARRAY.toString());
            return jbj;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public void removeObj(String crt,String val){
        try{
            JSONObject jbj = null;
            int c  = 0;
            for(Object obj:DATA_ARRAY){
                jbj = (JSONObject)obj;
                if(jbj.get(crt).toString().equals(val)){
                    DATA_ARRAY.remove(c);
                    break;
                }
                c++;
            }
            //Log.i("DATA_REM",DATA_ARRAY.toString());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public JSONArray removeObj(JSONArray j_arr,String crt,String val){
        try{
            JSONObject jbj = null;
            int c  = 0;
            for(Object obj:j_arr){
                jbj = (JSONObject)obj;
                if(jbj.get(crt).toString().equals(val)){
                    j_arr.remove(c);
                    break;
                }
                c++;
            }
            return j_arr;
            //Log.i("DATA_REM",DATA_ARRAY.toString());
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public JSONArray shiftProperty(JSONArray jarr,String search_key1,String search_key2,String property_key,String shift_prop){
        try{
            for(int c = 0;c < jarr.size();c++){
                JSONObject jbj1 = (JSONObject)jarr.get(c);
                //Log.i("JEB1",jbj1.toString());
                for(Object obj:DATA_ARRAY) {
                    JSONObject jbj2 = (JSONObject) obj;
                    //Log.i("JEB2",jbj2.toString());
                    if (jbj1.get(search_key1).toString().equals(jbj2.get(search_key2).toString())) {
                        //Log.i("JEBM",jbj2.toString());
                        jbj1.put(property_key, jbj2.get(jbj1.get(shift_prop).toString()).toString());
                        jarr.remove(c);
                        jarr.add(c,jbj1);
                        DATA_ARRAY.remove(obj);
                        break;
                    }
                }
            }
            return jarr;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public JSONArray shiftProperties(JSONArray jarr,String search_key1,String search_key2,String[] property_keys,String[] shift_props){
        try{
            for(int c = 0;c < jarr.size();c++){
                JSONObject jbj1 = (JSONObject)jarr.get(c);
                //Log.i("JEB1",jbj1.toString());
                for(Object obj:DATA_ARRAY) {
                    JSONObject jbj2 = (JSONObject) obj;
                    //Log.i("JEB2",jbj2.toString());
                    if (jbj1.get(search_key1).toString().equals(jbj2.get(search_key2).toString())) {
                        //Log.i("JEBM",jbj2.toString());
                        int z = 0;
                        for(String s:property_keys) {
                            if(shift_props[z] != null) {
                                jbj1.put(s, jbj2.get(jbj1.get(shift_props[z]).toString()).toString());
                            }else{
                                jbj1.put(s, jbj2.get(s).toString());
                            }
                            z++;
                        }
                        jarr.remove(c);
                        jarr.add(c,jbj1);
                        DATA_ARRAY.remove(obj);
                        break;
                    }
                }
            }
            return jarr;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public JSONArray changeVal(){
        return null;
    }

    public ArrayList<String> parseAsArray(String data){
        int kk = data.length();
        ArrayList<String> dts = new ArrayList<>();
        String trk = "";
        for(int pp = 0;pp < kk;pp++){
            if(data.charAt(pp) != '?') {
                if (data.charAt(pp) == ',') {
                    dts.add(trk);
                    trk = "";
                }else{
                    trk = trk + Character.toString(data.charAt(pp));
                }
            }else{
                dts.add(trk);
            }
        }
        return dts;
    }
}