package com.saver.shower;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class history {

    public void saveHistory(int goalSecs, int actualSecs){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd:HH");
        Preferences prefs = Gdx.app.getPreferences("test1");
        int numLines = prefs.getInteger("numLines", 0);
        Date date = new Date();
        String entry = dateFormat.format(date) + " " + goalSecs + " " + actualSecs;
        prefs.putString(Integer.toString(numLines),  entry);
        prefs.putInteger("numLines",  numLines + 1);
        prefs.flush();
    }
    
    //array of "yyyy/mm/dd goalSecs actualSecs" 
    public String[] getHistory(){
        Preferences prefs = Gdx.app.getPreferences("test1");
        int numLines = prefs.getInteger("numLines", 0);
        String[] res = new String[numLines];
        for(int i = 0; i < numLines; i++){
            res[i] = prefs.getString(Integer.toString(i));
        }
        return res;
    }
	
}
