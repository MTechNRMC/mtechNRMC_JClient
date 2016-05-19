import java.awt.event.KeyEvent;

/* AutomatedControl Class
 * @author Mack Sutherland
 * @date 2/8/2106
 * 
 * Allows automated control of robot. 
 * Call the motion you want, then tell it to stop when desired position is reached.
 * 
 */


public class AutomatedControl extends InputInterface


{
    
    /**
     * Operation
     *
     */
    public void stop (  )
    {
       controller.handleCommand(KeyEvent.VK_P, true);
    }
    /**
     * Operation
     *
     */
    public void forward (  )
    {
    	controller.handleCommand(KeyEvent.VK_W, true);
    }
    /**
     * Operation
     *
     */
    public void turnLeft (  )
    {
    	controller.handleCommand(KeyEvent.VK_A, true);
    }
    /**
     * Operation
     *
     */
    public void backup (  )
    {
    	controller.handleCommand(KeyEvent.VK_S, true);
    }
    /**
     * Operation
     *
     */
    public void turnRight (  )
    {
    	controller.handleCommand(KeyEvent.VK_D, true);
    }
    /**
     * Operation
     *
     */
    public void speedUp (  )
    {
    	controller.handleCommand(KeyEvent.VK_EQUALS, true);
    }
    /**
     * Operation
     *
     */
    public void speedDown (  )
    {
    	controller.handleCommand(KeyEvent.VK_MINUS, true);
    }
    /**
     * Operation
     *
     */
    public void mainArmUp (  )
    {
    	controller.handleCommand(KeyEvent.VK_UP, true);
    }
    /**
     * Operation
     *
     */
    public void mainArmDown (  )
    {
    	controller.handleCommand(KeyEvent.VK_DOWN, true);
    }
    /**
     * Operation
     *
     */
    public void hopperDown (  )
    {
    	controller.handleCommand(KeyEvent.VK_OPEN_BRACKET, true);
    }
    /**
     * Operation
     *
     * @return 
     */
    public void  hopperUp (  )
    {
    	controller.handleCommand(KeyEvent.VK_CLOSE_BRACKET, true);
    }
    /**
     * Operation
     *
     */
    public void leftActuatorUp (  )
    {
    	controller.handleCommand(KeyEvent.VK_U, true);
    }
    /**
     * Operation
     *
     */
    public void rightActuatorUp (  )
    {
    	controller.handleCommand(KeyEvent.VK_O, true);
    }
    /**
     * Operation
     *
     */
    public void leftActuatorDown (  )
    {
    	controller.handleCommand(KeyEvent.VK_J, true);
    }
    /**
     * Operation
     *
     */
    public void rightActuatorDown (  )
    {
    	controller.handleCommand(KeyEvent.VK_L, true);
    }
    /**
     * Operation
     *
     */
    public void bucketIn (  )
    {
    	controller.handleCommand(KeyEvent.VK_LEFT, true);
    }
    /**
     * Operation
     *
     */
    public void bucketOut (  )
    {
    	controller.handleCommand(KeyEvent.VK_RIGHT, true);
    }
    
    
    public void stopForward (  )
    {
    	controller.handleCommand(KeyEvent.VK_W, false);
    }
    /**
     * Operation
     *
     */
    public void stopTurnLeft (  )
    {
    	controller.handleCommand(KeyEvent.VK_A, false);
    }
    /**
     * Operation
     *
     */
    public void stopBackup (  )
    {
    	controller.handleCommand(KeyEvent.VK_S, false);
    }
    /**
     * Operation
     *
     */
    public void stopTurnRight (  )
    {
    	controller.handleCommand(KeyEvent.VK_D, false);
    }
    /**
     * Operation
     *
     */
   
    public void stopMainArmUp (  )
    {
    	controller.handleCommand(KeyEvent.VK_UP, false);
    }
    /**
     * Operation
     *
     */
    public void stopMainArmDown (  )
    {
    	controller.handleCommand(KeyEvent.VK_DOWN, false);
    }
    /**
     * Operation
     *
     */
    public void stopHopperDown (  )
    {
    	controller.handleCommand(KeyEvent.VK_OPEN_BRACKET, false);
    }
    /**
     * Operation
     *
     * @return 
     */
    public void  stopHopperUp (  )
    {
    	controller.handleCommand(KeyEvent.VK_CLOSE_BRACKET, false);
    }
    /**
     * Operation
     *
     */
    public void stopLeftActuatorUp (  )
    {
    	controller.handleCommand(KeyEvent.VK_U, false);
    }
    /**
     * Operation
     *
     */
    public void stopRightActuatorUp (  )
    {
    	controller.handleCommand(KeyEvent.VK_O, false);
    }
    /**
     * Operation
     *
     */
    public void stopLeftActuatorDown (  )
    {
    	controller.handleCommand(KeyEvent.VK_J, false);
    }
    /**
     * Operation
     *
     */
    public void stopRightActuatorDown (  )
    {
    	controller.handleCommand(KeyEvent.VK_L, false);
    }
    /**
     * Operation
     *
     */
    public void stopBucketIn (  )
    {
    	controller.handleCommand(KeyEvent.VK_LEFT, false);
    }
    /**
     * Operation
     *
     */
    public void stopBucketOut (  )
    {
    	controller.handleCommand(KeyEvent.VK_RIGHT, false);
    }
   
}

// ## Implementation preserve start class closing. 
// ## Implementation preserve end class closing. 
