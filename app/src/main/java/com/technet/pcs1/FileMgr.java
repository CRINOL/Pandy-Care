package com.technet.pandy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class FileMgr{

    String DIR;
    Context CONTEXT;
    InputStream IS;
    public FileMgr(String dir,Context di){
        DIR = dir;
        CONTEXT = di;
    }

    public FileMgr(InputStream IS){
        this.IS = IS;
    }

    public String getStr(){
        try{
            FileInputStream fIn = CONTEXT.openFileInput(DIR);
            int c;
            String str = "";
            while((c = fIn.read()) != -1){
                str = str + Character.toString((char)c);
            }
            fIn.close();
            return str;
        }catch(Exception e){
            e.printStackTrace();
            return "";
        }
    }

    public int writeStr(String cnt){
        try{
            FileOutputStream fOut = CONTEXT.openFileOutput(DIR,Context.MODE_PRIVATE);
            fOut.write(cnt.getBytes());
            fOut.close();
            return 1;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public int writeStr(String cnt,boolean appx){
        try{
            FileOutputStream fOut = CONTEXT.openFileOutput(DIR,Context.MODE_APPEND);
            fOut.write(cnt.getBytes());
            fOut.close();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public int writeBmp(Bitmap bmp,String file_name){
        try {
            if (!new File(DIR).exists()) {
                new File(DIR).mkdirs();
            }
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 50, bOut);
            FileOutputStream fOut = new FileOutputStream(new File(new File(DIR),file_name));
            fOut.write(bOut.toByteArray());
            fOut.close();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public String readLine(){
        try{
            int c;
            String str = "";
            while((c = IS.read()) != -1){
                str = str + Character.toString((char)c);
            }
            return str;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    public Bitmap getBmp(){
        try{
            return BitmapFactory.decodeFile(DIR);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /*public Bitmap getBmp(){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
    }*/
}