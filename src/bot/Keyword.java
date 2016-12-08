/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot;

/**
 *
 * @author James
 */
public class Keyword {
    public String keyword = "";
    public String target = "";
    public String className = "";
    public String arg = "";
    public String variable = "";
    public String variableValue = "";
    public String learn = "";
    public int points = 0;

    // default constructor, constructs a keyword object
    public Keyword(String keyword, String target, String className, String arg, String variable, int points, String learn) {
        this.keyword = keyword;
        this.target = target;
        this.className = className;
        this.arg = arg;
        this.variable = variable;
        this.points = points;
        this.learn = learn;
    }
    
}
