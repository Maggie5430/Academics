package week9lab;

/**
 * CSSSKL: 162 Winter 2018, Lab 9.
 * 
 * @author Margaret Connor
 */
public class QuizScore implements Cloneable{
    private int score;
    
    /**
     * Constructor.
     * @param score
     */
    public QuizScore(int score){
        this.score = score;
    }
    
    /**
     * Constructor.
     * @param quiz
     */
    public QuizScore(QuizScore quiz){
        this.score = quiz.score;
    }
    
    @Override
    public QuizScore clone(){
        return new QuizScore(this.score);
    }
}
