package companionbot;

import companionbot.context.ConversationContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import companionbot.observer.TopicSubject;

public class Bot {
    // Store all regular expression matches
    private final HashMap<String,String> dictionary;

    private ConversationContext conversationContext;
    
    private final TopicSubject topicSubject;
    
    // Default state to start the bot
    String level = "0";
    
    // default constructor
    public Bot(String level, ConversationContext conversationContext, TopicSubject topicSubject) {
        dictionary = new HashMap<>();
        this.level = level;
        this.conversationContext = conversationContext;
        this.topicSubject = topicSubject;
    }

    // get current state message
    public String getMessage() {
        State state = conversationContext.getParser().getState(level);
        return replaceMatches(state.getMessage()).trim();
    }

    // send user message to the bot and get the response
    public String send(String message) {

        String response = "";
        State state = conversationContext.getParser().getState(level);


        // end of the tree
        if (state.getKeywords().isEmpty()) {
            this.level = "1";
        }

        // match the keyword with given message
        Keyword match = parse(message, state.getKeywords());

        // if no keyword is matched, display one of the invalid answers
        if (match == null) {
            response = conversationContext.getParser().getInvalidAnswer();
        } else {

            // if match classname is provided, check to get the dynamic response
            if (match.className.length() > 0) {

                // check for Weather dynamic response
                //if (match.className.equals("Weather")) {
                 //   Weather weather = new Weather();
                 //   response = weather.getResponse(match.arg);
                  //  this.level = "1";
                //}
                // check for Topic dynamic response
                if (match.className.equals("Topic")) {
                    level = "0";
                    Topic t = conversationContext.getContext().getTopic(match.arg);
                    if (t.isNewTopic()) {
                        System.out.println("the current topic in Topic is  " + t.getCurrentTopic());        
                        conversationContext = ConversationContext.newConversationContext(new Context(t.getCurrentTopic()));
                        topicSubject.notifyObservers();
                    }
                }                
            } else {

                // get the new state and return the new message
                if (response.length() == 0) {

                    this.level = match.target;
                    state = conversationContext.getParser().getState(level);

                    // if it is end of the tree
                    if (state.getKeywords().isEmpty()) {
                        response = this.getMessage();
                        this.level = "1";

                    }
                }
            }
        }
        return response;
    }

    // parse the given text to find best match in the keywords
    private Keyword parse(String text, ArrayList<Keyword> keylist) {

        // set the default match to none
        int bestMatch = -1;
        Keyword match = null;

        // loop through keywords
        for (int i = 0; i < keylist.size(); i++) {

            // get number of matches of the keyword with given text
            int matches = getMatches(text, keylist.get(i));

            // if match is better than best match, replace it
            if (matches > -1 && matches > bestMatch) {
                match = keylist.get(i);
                bestMatch = matches;
            }
        }

        // add best answers regex variable value into the dictionary for future reference
        if (match != null){
            if(match.learn.length() > 0 ){

                // get training data keyword and description
                String subject = dictionary.get(match.learn);
                String result =  match.variableValue;


                // create a new state for new trained data
                 ArrayList<String> messages = new ArrayList<String>();
                messages.add(result);
                State myState = new State(String.valueOf(conversationContext.getParser().stateCounter),messages,new ArrayList());
                conversationContext.getParser().addState(myState);

                // add the new trained keyword
                Keyword keyword = new Keyword(subject, myState.getId(), "", "", "", 1, "" );
                State state = conversationContext.getParser().getState("1");
                ArrayList<Keyword> keywords = state.getKeywords();
                keywords.add(keyword);

            }else{
                if (match.variableValue.length() > 0){
                    dictionary.put(match.variable, match.variableValue);
                }
            }
        }
        return match;
    }

    // get number of matches of the given keywords in the given list
    private int getMatches(String text, Keyword keyword) {

        // no match by default
        int result = -1;

        // return 0 match when keyword is *
        if(keyword.keyword.equals("*")){
            return keyword.points;
        }

        // if regex is expected
        if(keyword.variable.length() > 0){
            String match = Regex.match(keyword.keyword, text);
            if(match.length() > 0){
                keyword.variableValue = match;
                return keyword.points;
            }
        }

        String[] words = keyword.keyword.split(" ");


        // loop through list of the keywords
        for (String word : words) {

            // if current keyword is in the text, add points
            if (text.toLowerCase().indexOf(word.toLowerCase()) >= 0) {
                result = result + keyword.points + 1;
            } else {
                // return null if one of the keywords does not exists
                return -1;
            }
        }
        return result;
    }


    // replace given text with variables in the dictionary
    public String replaceMatches(String text){
        
        // replace variables within dictionary in the text
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            text = text.replaceAll("\\["+entry.getKey() + "\\]", entry.getValue());
        }

        // remove empty variables tags
        return Regex.clear(text);
    }
}
