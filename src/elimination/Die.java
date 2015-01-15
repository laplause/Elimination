/*Christopher Sosa*/
package elimination;
import java.util.Random;

public class Die 
{   
    public Die(int maxRoll)
    {
        m_generator = new Random();
        m_maxRoll = maxRoll;
    }
    
    // Creates a random number between 1 and m_maxRoll
    public int Roll()
    {
        return (m_generator.nextInt(m_maxRoll) % m_maxRoll) + 1;
    }
    
    final private Random m_generator;
    private int m_maxRoll;
}
