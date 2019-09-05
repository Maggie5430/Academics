package week9lab;

import java.util.ArrayList;

/**
 * CSSSKL: 162 Winter 2018, Lab 9.
 * 
 * @author Margaret Connor
 */
public class QuizTracker implements Cloneable{
    ArrayList<QuizScore> tracker = new ArrayList();
    
    /**
     * Constructor.
     */
    public QuizTracker(){
        
    }
    
    /**
     * Constructor.
     * @param newTracker
     */
    public QuizTracker(QuizTracker newTracker){
        
    }
    
    /**
     * adds new score to list.
     * @param score
     */
    public void add(QuizScore score){
        tracker.add(score);
    }
    
    /**
     * changes score at index.
     * @param index
     * @param score
     */
    public void set(int index, QuizScore score){
        tracker.set(index, score);
    }
    
    /**
     * returns score at index
     * @param index
     * @return
     */
    public QuizScore get(int index){
        return new QuizScore (tracker.get(index));
    }
    
    @Override
    public QuizTracker clone(){
        QuizTracker cloneTracker = new QuizTracker();
        for (QuizScore q: tracker){
            cloneTracker.add(q);
        }
        return new QuizTracker(cloneTracker);
    }
}
