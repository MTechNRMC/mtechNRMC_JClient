import java.util.HashSet;
import java.util.Set;

/* Input Interface
 * @author Mack Sutherland
 * @date 2/8/2106
 * 
 * The purpose of this interface is to allow grouping of different controllers 
 * Children must implement classes.
 */

public class InputInterface
// ## Implementation preserve start class extends. 
// ## Implementation preserve end class extends. 

// ## Implementation preserve start class inheritence. 
// ## Implementation preserve end class inheritence. 

{
    /** Attributes */
    public boolean isActive;
    protected Controller controller;
    protected Set<Character> moving = new HashSet<Character>();
    // ## Implementation preserve start class attributes. 
    // ## Implementation preserve end class attributes. 
    // ## Implementation preserve start class associations. 
    // ## Implementation preserve end class associations. 
    /**
     * Operation
     *
     * @return String
     */
    public String getCurrentMotions (  )
    {
		return null;
        // ## Implementation preserve start class method.getCurrentMotions@String@@ 
        // ## Implementation preserve end class method.getCurrentMotions@String@@ 
    }
    /**
     * Operation
     *
     */
    public void activate (  )
    {
        // ## Implementation preserve start class method.activate@void@@ 
        // ## Implementation preserve end class method.activate@void@@ 
    }
    /**
     * Operation
     *
     */
    public void deactivate (  )
    {
        // ## Implementation preserve start class method.deactivate@void@@ 
        // ## Implementation preserve end class method.deactivate@void@@ 
    }
    /**
     * Operation
     *
     * @return boolean
     */
    public boolean getStatus (  )
    {
		return false;
       
    }
    /**
     * Operation
     *
     */
    public void emergencyStop (  )
    {
        // ## Implementation preserve start class method.emergencyStop@void@@ 
        // ## Implementation preserve end class method.emergencyStop@void@@ 
    }
    // ## Implementation preserve start class other.operations. 
    // ## Implementation preserve end class other.operations. 
}

// ## Implementation preserve start class closing. 
// ## Implementation preserve end class closing. 
