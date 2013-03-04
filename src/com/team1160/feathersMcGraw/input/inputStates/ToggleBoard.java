/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team1160.feathersMcGraw.input.inputStates;

import com.team1160.feathersMcGraw.input.InputState;

/**
 * Basically my toggle code got really messy
 * so this represents all of our toggle real 
 * or other w	ise
 * 
 * @author Wallace
 */
public class ToggleBoard {

	public boolean[] driveToggle;               
	public boolean[] topPulleyToggle;           
	public boolean[] rightPulleyToggle;         
	public boolean[] leftPulleyToggle;          
	public boolean[] rightLockToggle;         
	public boolean[] leftLockToggle;            
    public boolean[] gripToggle;
    public boolean[] cookerArmToggle;
    public boolean[] pulleyToggle;
    public boolean[] hutchArmToggle;

    public boolean[] leftAutoClimbToggle;
    public boolean[] rightAutoClimbToggle;
    public boolean[] topAutoClimbToggle;
    
    public boolean[] floorToggle;
    public ToggleBoard(){
    	driveToggle = new boolean[]{true,true};        
        pulleyToggle = new boolean[]{false, true};
        hutchArmToggle = new boolean[]{false,true};
        gripToggle = new boolean[]{false, true};           
        topPulleyToggle = new boolean[]{false,true};
        
        cookerArmToggle = new boolean[]{false, true};
        rightPulleyToggle = new boolean[]{false,true};
        rightLockToggle = new boolean[]{false,true};
        
        leftPulleyToggle = new boolean[]{false,true};
        leftLockToggle = new boolean[]{false,true};  
        
        leftAutoClimbToggle = new boolean[]{false, true};
        rightAutoClimbToggle = new boolean[]{false, true};
        topAutoClimbToggle = new boolean[]{false, true};
        
        floorToggle = new boolean[]{true, true};
    }
	
    public void toggleTheThings(InputState is){   
    	findMode(is);
    	if(pulleyToggle[0]){
    		topAutoClimbToggle = toggle(topAutoClimbToggle, is.driveStick.autoClimbRelease);
    			if(topAutoClimbToggle[0]){
    				topPulleyToggle[0] = false;
    			}else{
    	    		topPulleyToggle = toggle(topPulleyToggle, is.driveStick.pulleyRelease);
    			}
    	}else if(hutchArmToggle[0]){
    		gripToggle = toggle(gripToggle, is.driveStick.gripRelease);
    	}else{
    		topAutoClimbToggle[0] = false;
    	}
    	leftAutoClimbToggle = toggle(leftAutoClimbToggle, is.leftArmStick.autoClimbRelease);
    	rightAutoClimbToggle = toggle(rightAutoClimbToggle, is.rightArmStick.autoClimbRelease);
    	
    	leftLockToggle = toggle(leftLockToggle, is.leftArmStick.lockRelease);
    	rightLockToggle = toggle(rightLockToggle, is.rightArmStick.lockRelease);
    	
    	if(leftAutoClimbToggle[0]){
    		leftPulleyToggle[0] = true;
    	}else{
    		leftPulleyToggle = toggle(leftPulleyToggle, is.leftArmStick.pulleyRelease);
    	}
    	
    	if(rightAutoClimbToggle[0]){
    		rightPulleyToggle[0] = true;
    	}else{
    		rightPulleyToggle = toggle(rightPulleyToggle,is.rightArmStick.pulleyRelease);
    	}
    }
    
    public void findMode(InputState is){
        
    	if(driveToggle[0]){
    		pulleyToggle = toggle(pulleyToggle, is.driveStick.climbReleased);
    		hutchArmToggle = toggle(hutchArmToggle, is.driveStick.armRelease);
    		if(pulleyToggle[0]){
    			driveToggle[0] = false;
    			hutchArmToggle[0] = false;
    		}else if(hutchArmToggle[0]){
    			driveToggle[0] = false;
    		}
    	}else if(pulleyToggle[0]){
    		driveToggle = toggle(driveToggle,is.driveStick.driveReleased);
    		hutchArmToggle = toggle(hutchArmToggle,is.driveStick.armRelease);
    		if(driveToggle[0]){
    			pulleyToggle[0] = false;
    			hutchArmToggle[0] = false;
    		}else if(hutchArmToggle[0]){
    			pulleyToggle[0] = false;
    		}
    	}else if(hutchArmToggle[0]){
    		driveToggle = toggle(driveToggle,is.driveStick.driveReleased);
    		pulleyToggle = toggle(pulleyToggle,is.driveStick.climbReleased);
    		if(driveToggle[0]){
    		hutchArmToggle[0] = false;
        	pulleyToggle[0] = false;
    		}else if(pulleyToggle[0]){
    			hutchArmToggle[0] = false;
    		}
    	}
   
    }
	
	public boolean[] toggle(boolean[] toggle, boolean input){  // in the toggle array you have two values
		if(toggle[1] && !input){                                  // [0] which is wether or not the toggle is on
			toggle[1] = false;                                    // [1] which holds if the button was pressed last loop
				if(toggle[0]){                                    // the input is if the button is pressed now
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
		output += "/---- Toggle States\n";
		output += "Hutch's Mode: \n";
		output += "    Drive: " + driveToggle[0] + "\n";
		output += "    Auto: " + topAutoClimbToggle[0] + "\n";
		output += "    Pulley: " + pulleyToggle[0] + "\n";
		output += "          Angle: " + !topPulleyToggle[0] + "\n";
		output += "          Velocity: " + topPulleyToggle[0] + "\n";
		output += "    Arm: " + hutchArmToggle[0] + "\n";
		output += "          Grip: " + gripToggle[0] + "\n";
		output += "Cooker 1 mode:\n";
		output += "Angle: " + !leftPulleyToggle[0] + "\n";
		output += "Velocity: " + leftPulleyToggle[0] + "\n";
		output += "Lock: " + leftLockToggle[0] + "\n";
		output += "Auto: " + leftAutoClimbToggle[0] + "\n";
		output += "Cooker 2 mode:\n";
		output += "Angle: " + !rightPulleyToggle[0] + "\n";
		output += "Velocity: "+ rightPulleyToggle[0] + "\n";
		output += "Auto: " + rightAutoClimbToggle[0] + "\n";
		output += "Lock: " + rightLockToggle[0] + "\n";
		return output;
	}
}
