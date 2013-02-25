package com.team1160.feathersMcGraw.teleop;

import com.team1160.feathersMcGraw.api.Constants;
import com.team1160.feathersMcGraw.commands.PulleyCommand;
import com.team1160.feathersMcGraw.commands.RobotCommand;
import com.team1160.feathersMcGraw.input.InputState;
import java.lang.Math;

public class TeleopManager {
/*
 * 
 * this is ugly as fuck... working on a back up method to clean it up...
 * should work hopefull
 */
	private static TeleopManager _INSTANCE;
	
	private RobotCommand currentCommand;
	private InputState inputState;
	
            
	private boolean[] driveToggle;               // Toggle arrays are used to toggle things
	private boolean[] topPulleyToggle;           // the first value is essentially the toggle
	private boolean[] rightPulleyToggle;         // the second is if the last time we went through the loop
	private boolean[] leftPulleyToggle;          // the toggle button was pressed... i think this should work
	private boolean[] rightLockToggle;           // how ever the code got kinda abstract along the way so i am 
	private boolean[] leftLockToggle;            // having a bit of trouble following it any more >.< ah well
                   private boolean[] gripToggle;
                   private boolean[] armToggle;
                   private boolean[] pulleyToggle;
                   private boolean[] autoClimbToggle;
                  
                   protected double k1;
                   protected double k2;
                   protected double k3;
                   protected double k4;
                   protected double k5;
                   protected double a;
                   protected double b;
                   protected double c;
                   protected double x1;
                   protected double x2;
                   protected double m1;
                   protected double m2;
                   protected double beta;
                   
	public static TeleopManager getInstance(){
		if(_INSTANCE == null){
			_INSTANCE =  new TeleopManager();
		}
		return _INSTANCE;
	}
	
	private TeleopManager() {
		currentCommand = new RobotCommand();
		inputState = new InputState();
		k2 = 30;
		k3 = 12.2;
		driveToggle = new boolean[]{true,true};        
	    pulleyToggle = new boolean[]{false, true};
	    armToggle = new boolean[]{false,true};
	    gripToggle = new boolean[]{false, true};           
	    topPulleyToggle = new boolean[]{false,true};
		rightLockToggle = new boolean[]{false,true};
		leftPulleyToggle = new boolean[]{false,true};
		leftLockToggle = new boolean[]{false,true};     //We start of unlocked 
		autoClimbToggle = new boolean[]{false, true};
                  }
	
	public RobotCommand getCommand(InputState inputState){
		this.inputState = inputState;
		findStates();                                                    // Seeing as the drivers stick can be in three states we need to logic that shi-
		forgeArmCommand();
		forgeDriveCommand();
		forgePulleySystemCommand();
		return currentCommand;
	}
        
                  private void findStates(){
                  /*
                   * This code basically toggles the state of the drivers stick
                   * we dont want it to be in more then one state and it has to
                   * be in at least one... this code makes sure that happens
                   * another case of a brutish fix but fuck it... less then 6 days 
                   * to comp got to make it work
                   */
                                            
                  if(driveToggle[0]){
                      pulleyToggle = toggle(pulleyToggle, true);
                      armToggle = toggle(armToggle, true);
                      if(pulleyToggle[0]){
                          driveToggle[0] = false;
                          armToggle[0] = false;
                      }else if(armToggle[0]){
                          driveToggle[0] = false;
                      }
                  }else if(pulleyToggle[0]){
                      driveToggle = toggle(pulleyToggle,true);
                      armToggle = toggle(armToggle,true);
                      if(driveToggle[0]){
                          pulleyToggle[0] = false;
                          armToggle[0] = false;
                      }else if(armToggle[0]){
                          pulleyToggle[0] = false;
                      }
                  }else if(armToggle[0]){
                      driveToggle = toggle(driveToggle,true);
                      pulleyToggle = toggle(pulleyToggle,true);
                      if(driveToggle[0]){
                          armToggle[0] = false;
                          pulleyToggle[0] = false;
                      }else if(armToggle[0]){
                          pulleyToggle[0] = false;
                      }
                  }
                      
                      
                  }
	
	private void forgeDriveCommand(){
		if(driveToggle[0] ){
			currentCommand.drivetrainCommand.left = inputState.driveStick.x + inputState.driveStick.y;
			currentCommand.drivetrainCommand.right = inputState.driveStick.x - inputState.driveStick.y;
		}else{
			currentCommand.drivetrainCommand.left = 0;
			currentCommand.drivetrainCommand.right = 0;
		}
	}
	
                  private void forgeAutoPulleySystemCommand(boolean floor){
                	  currentCommand.pulleySystemCommand.left.velocity = inputState.rightArmStick.y;
                	  currentCommand.pulleySystemCommand.right.velocity = inputState.leftArmStick.y;
                	  
                	  currentCommand.pulleySystemCommand.top.velocity = inputState.driveStick.y;
                	  
                	  currentCommand.pulleySystemCommand.left.angle = getServoAngle(getTapeAngle(floor, false, inputState.sensorState.robotAngle,inputState.sensorState.tapeLengthLeft), inputState.sensorState.tapeLengthLeft, false);
                	  currentCommand.pulleySystemCommand.right.angle = getServoAngle(getTapeAngle(floor, false, inputState.sensorState.robotAngle,inputState.sensorState.tapeLengthRight), inputState.sensorState.tapeLengthRight, false);
                	  currentCommand.pulleySystemCommand.top.angle = getServoAngle(getTapeAngle(floor, true, inputState.sensorState.robotAngle, inputState.sensorState.tapeLengthTop), inputState.sensorState.tapeLengthTop, true);
                  }
                  
                  private double getTapeAngle(boolean floor, boolean middle, double frameAngle, double T){ 
                      double rFrameAngle = Math.toRadians(frameAngle);
                	  if(floor){
                    	  if(middle){
                    		  return 0;   //TODO might need to put not 0
                    	  }else if(T < 11.1){    // The break free condition
                    		return 90-frameAngle;  
                    	  }else{                //If you havent broken free
                    		  if(frameAngle <= 20){
                    			  return Math.toDegrees(Math.asin((k2-12.3*Math.sin(rFrameAngle))/T));  // Rear wheel touching
                    		  }else{
                    			  return Math.toDegrees(Math.asin((k2-18.9*Math.sin(rFrameAngle))/T));  // rear bumper
                         }
                     }
                      }else{   // Between rungs... not floor
                    	  
                    	  // Breaking free 
                    	  if(T<16.3){    //TODO add angle condition once found
                    		  return 90-frameAngle;
                    	  }
                    	  if(middle){
                    		  k1 = 9.5;
                    		}
                    	  else{
                    		  k1 = 1.25;                    		  
                    	  }
                    	  if(frameAngle == 90){
                    		  m2 = Math.acos((k3+k1)/T);
                    	  }else if(frameAngle ==0){
                    		  if(T<=Math.sqrt(Math.pow(k2-k1, 2) + Math.pow(k3+k1, 2))){
                    			  m2 = Math.asin((k2-k1)/T);
                    		  }else{
                    			  m2 = Math.acos((k3+k1)/T);
                    		  }
                    	  }else{    //Ugly quad stuff no need to do unless not 90 or 0 (frame angle)
                    		  k5 = 1/Math.tan(rFrameAngle);
                    		  a = (1+Math.pow(k5, 2));
                    		  k4 = k1/Math.sin(rFrameAngle);
                    		  b = -2*(k2 + k4*k5 + k3*k5);
                    		  c = Math.pow(k2, 2)+Math.pow(k3, 2)+Math.pow(k4, 2)+2*(k3*k4) - Math.pow(T,2);
                    		  x2 = (-b-Math.sqrt((Math.pow(b,2)-4*a*c)))/2*a;
                    		  m2 = Math.atan((k2-x2)/(k3+k4-k5*x2));
                    	  }
                    	  return Math.toDegrees(m2)-frameAngle;
                      }
                      
                  	}
                  
                  private double getServoAngle(double tapeAngle, double T, boolean middle){
                	  double rTapeAngle = Math.toRadians(tapeAngle);
                	  if(middle){
                		  return 40-Math.toDegrees(Math.atan((4.5-T*Math.sin(rTapeAngle)/T*Math.cos(rTapeAngle))+1.52*T));
                	  }else{
                		  return 59-Math.toDegrees(Math.atan((4.6-T*Math.sin(rTapeAngle)/(3+T*Math.cos(rTapeAngle)))+1.94*T));
                	  }
                  }
                  
                  private void forgePulleySystemCommand(){
                	  if(autoClimbToggle[0]){
                		  forgeAutoPulleySystemCommand(true);
                	  }else{
                		 forgeTeleopPulleySystemCommand();
                	  }
                  }
        
	private void forgeTeleopPulleySystemCommand(){ 
		if(!driveToggle[0] && !armToggle[0]){
			topPulleyToggle = toggle(topPulleyToggle, inputState.driveStick.pulleyRelease);
			currentCommand.pulleySystemCommand.top = forgePulleyCommand(topPulleyToggle[0], false, inputState.driveStick.y);  // Sets the output... currently there is no lock on the top one so it is just set to false to make the program work.... TODO if lock gets added fix this shit
		}else{
			currentCommand.pulleySystemCommand.top.velocity = 0; // Stop the pulley from extending if we are not in drive mode;
		}
		rightPulleyToggle = toggle(rightPulleyToggle, inputState.leftArmStick.pulleyRelease);
		leftPulleyToggle = toggle(leftPulleyToggle, inputState.rightArmStick.pulleyRelease);
		rightLockToggle = toggle(rightLockToggle, inputState.leftArmStick.lockRelease);
		leftLockToggle = toggle(leftLockToggle, inputState.rightArmStick.lockRelease);
		currentCommand.pulleySystemCommand.left = forgePulleyCommand(leftPulleyToggle[0], leftLockToggle[0], inputState.rightArmStick.y);
                                      currentCommand.pulleySystemCommand.right = forgePulleyCommand(rightPulleyToggle[0], rightLockToggle[0], inputState.leftArmStick.y);
        }
	
	private PulleyCommand forgePulleyCommand(boolean pulleyMode, boolean lock, double y){
		PulleyCommand newCommand = new PulleyCommand();
                                      if(pulleyMode){
                                          newCommand.angle = (y+1)/2;
                                          newCommand.velocity = 0;
                                      }else{
                                          newCommand.velocity = -y;
                                          newCommand.angle = 1337;
                                      }
                                      newCommand.locked = lock;
                                      return newCommand;
	}
        
                    private void forgeArmCommand(){
                                    if(armToggle[0]){
                                        currentCommand.armCommand.angle = (inputState.driveStick.y+1)/2;
                                        gripToggle = toggle(gripToggle, inputState.driveStick.pulleyRelease);
                                    }
                                    currentCommand.armCommand.grip = gripToggle[0];
                    }
	
	private boolean[] toggle(boolean[] toggle, boolean input){
		if(toggle[1] && !input){
			toggle[1] = false;
			if(toggle[0]){
				toggle[0] = false;
			}else{
				toggle[0] = true;
			}
		}else if(!toggle[1] && input){
			toggle[1] = true;
		}	
		return toggle;
                  }
        
        
	public String toString(){
		String output = "";
		output+= currentCommand;
		return output;
	}
	
}