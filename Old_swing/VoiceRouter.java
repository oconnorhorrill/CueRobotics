
package bot;

import static bot.Consumer.voice;

class VoiceRouter {
    private final Main main;
    //public VoiceRouter () {   
    //}
   public VoiceRouter (Main aMain){
        main = aMain;
    }
    public void pass(){
        //main.listen2();
        System.out.println("from router: " + voice + '\n');
    }
}


