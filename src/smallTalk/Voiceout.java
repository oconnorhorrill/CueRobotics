
package smallTalk;

import com.sun.speech.freetts.*;

public class Voiceout extends Thread {
    
    private static final String VOICENAME="kevin16";
    
    public void speakout (String output) {

    Voice voice;
            VoiceManager vm= VoiceManager.getInstance();
            voice=vm.getVoice(VOICENAME);
            
            voice.allocate();
            
            try{
                voice.speak(output);
            }catch(Exception e){
        }
        voice.deallocate();
    }
}
