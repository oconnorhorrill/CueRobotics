package companionbot.context;

import companionbot.Context;
import companionbot.DataParser;

public class ConversationContext {
    
    private Context context;
    private DataParser parser;
    
    private ConversationContext(Context context) {
        this.context = context;
        this.parser = new DataParser(context);
    }

    public Context getContext() {
        return context;
    }

    public DataParser getParser() {
        return parser;
    }
    
    public static ConversationContext newConversationContext(Context context) {
        return new ConversationContext(context);
    }
}
