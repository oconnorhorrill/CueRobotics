
package companionbot.smalltalk;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Night {
    
    private String userID = "Olivia";
    private static String time;
    private static String today;
    private String schedule;
    public static String output;
    private static String output2;

    public void late () {
        
        //check to see the user from camera feed
        
        //check to see if we just had this conversation.
        
        //Check the scheduler
        
        //Check the time of day
        currentTime();
        int intT = Integer.valueOf(time);
        if (userID != null)
                if (intT > 5 && intT < 12) {
                output = ("Good morning to you too " + userID + ".");
                } 
                else if (intT > 12 && intT < 18) {
                output = ("I think you mean good afternoon " + userID + ".");
                }
                else if (intT > 18 && intT < 21 ) {
                output = ("I think you mean good Evening " + userID + ".");   
                }
                else if (intT > 21 && intT < 24 ) {
                output = ("Good night " + userID + ".");   
                }
                else if (intT > 0 && intT < 5 ) {
                output = ("It is the middle of the night. " + userID + ". Shouldn't you be sleeping.");   
                }
        else {
                    if (userID == null)
                    output = "Good Night. I'm sorry I can't detect who you are.";
        }
        //use the voice but not to the bot
        Voiceout voiceout = new Voiceout();
        voiceout.speakout(output);

        currentDay();
        output2 = ("I hope you sleep well this " + today + " night");
        //use the voice but not send to the bot
        voiceout.speakout(output2);
    }
    
    public static String currentTime () {
        Date now = new Date();
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("HH");
        time = (simpleDateformat.format(now));
        return time;
    }
    
    public static String currentDay () {
        Date now = new Date();
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
        today = (simpleDateformat.format(now));
        return today;        
    }
    
    public void getSchedule () {
    }
}    

