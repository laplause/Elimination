/*Christopher Sosa*/
package elimination;
import java.util.Arrays;
import java.util.Scanner;

public class EliminationGame 
{
    public EliminationGame()
    {
        m_numbersEliminated = new NumberStatus[12];
        Arrays.fill(m_numbersEliminated, NumberStatus.NOT_ELIMINATED);
        m_die = new Die(6);
        
        m_currentScore = m_lowestScoreAchieved = 78;
        m_dieValueOne = m_dieValueTwo = 0;
        m_gameOver = false;
        m_keepPlaying = true;
    }
    
    public void Run()
    {
        while(m_keepPlaying)
        {
            Scanner s = new Scanner(System.in);
            int numberChoice = 0;
            
            System.out.println("Starting a new game of Elimination ready:");
        
            while(!m_gameOver)
            {
                // Roll the dice
                m_dieValueOne = m_die.Roll();
                m_dieValueTwo = m_die.Roll();
                
                System.out.println("You rolled a " + m_dieValueOne + " and a " + m_dieValueTwo + ".");
                System.out.println("Choose a valid number to eliminate.");
                numberChoice = s.nextInt();
                
                if(!GameOver())
                {
                    while(!ValidChoice(numberChoice))
                    {
                        System.out.println("Choose a valid number to eliminate.");
                        numberChoice = s.nextInt();
                    }
                    
                    EliminateNumber(numberChoice);
                    m_currentScore -= numberChoice;
                    System.out.println("CurrentScore: " + m_currentScore);
                }
                
                m_gameOver = GameOver();
            }
            
            System.out.println("The game has ended");
            System.out.println("Score:" + m_currentScore);
            
            if(m_currentScore < m_lowestScoreAchieved)
            {
                m_lowestScoreAchieved = m_currentScore;
                System.out.println("New low score achieved " + m_lowestScoreAchieved + ".");
            }
        }
    }
    
    public int GetCurrentScore()
    {
        return m_currentScore;
    }
    
    public int GetLowestScoreAchieved()
    {
        return m_lowestScoreAchieved;
    }
    
    public int[] GetDieValues()
    {
        return new int[]{m_dieValueOne, m_dieValueTwo};
    }
    
    public NumberStatus[] GetEliminatedNumbers()
    {
        return m_numbersEliminated;
    }
    
    public static enum NumberStatus
    {
        NOT_ELIMINATED,
        ELIMINATED
    }
    
    private boolean RolledDoubles()
    {
        return m_dieValueOne == m_dieValueTwo;
    }
    
    private void EliminateNumber(int number)
    {
        m_numbersEliminated[number - 1] = NumberStatus.ELIMINATED;
    }
    
    private boolean ValidChoice(int numberChoice)
    {   
        if(m_numbersEliminated[m_dieValueOne - 1] == NumberStatus.ELIMINATED && numberChoice == m_dieValueOne)
            return false;
        if(m_numbersEliminated[m_dieValueTwo - 1] == NumberStatus.ELIMINATED && numberChoice == m_dieValueTwo)
            return false;
        if(m_numbersEliminated[(m_dieValueOne+m_dieValueTwo) - 1] == NumberStatus.ELIMINATED && numberChoice == m_dieValueOne+m_dieValueTwo)
            return false;
        
        return (numberChoice == m_dieValueOne || numberChoice == m_dieValueTwo || numberChoice == m_dieValueOne+m_dieValueTwo); 
    }
    
    private boolean GameOver()
    {
        return m_numbersEliminated[m_dieValueOne - 1] == NumberStatus.ELIMINATED &&
               m_numbersEliminated[m_dieValueTwo - 1] == NumberStatus.ELIMINATED &&
               m_numbersEliminated[(m_dieValueOne+m_dieValueTwo) - 1] == NumberStatus.ELIMINATED;
                
    }
   
    private Die m_die;
    private NumberStatus[] m_numbersEliminated;
    private int m_dieValueOne;
    private int m_dieValueTwo;
    private int m_currentScore;
    private int m_lowestScoreAchieved;
    private boolean m_gameOver;
    private boolean m_keepPlaying;
}
