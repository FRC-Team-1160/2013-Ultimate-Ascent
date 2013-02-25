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

	private boolean[] driveToggle;               
	private boolean[] topPulleyToggle;           
	private boolean[] rightPulleyToggle;         
	private boolean[] leftPulleyToggle;          
	private boolean[] rightLockToggle;         
	private boolean[] leftLockToggle;            
    private boolean[] gripToggle;
    private boolean[] cookerArmToggle;
    private boolean[] pulleyToggle;
    private boolean[] hutchArmToggle;

    private boolean[] leftAutoClimbToggle;
    private boolean[] rightAutoClimbToggle;
    private boolean[] topAutoClimbToggle;
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
        leftLockToggle = new boolean[]{false,true};     //We start of unlocked 
        
        leftAutoClimbToggle = new boolean[]{false, true};
        rightAutoClimbToggle = new boolean[]{false, true};
        topAutoClimbToggle = new boolean[]{false, true};
    }
	
    public void toggleTheThings(InputState is){    // Will be the magic toggle function TODO fix this functions
    	findMode(is);
    	if(pulleyToggle[0]){
    		topPulleyToggle = toggle(topPulleyToggle, is.driveStick.pulleyRelease);
    		topAutoClimbToggle = toggle(topAutoClimbToggle, is.driveStick.autoClimbRelease);
    	}else if(hutchArmToggle[0]){
    		gripToggle = toggle(gripToggle, is.driveStick.gripRelease);
    	}else{
    		topAutoClimbToggle[0] = false;
    	}
    	leftAutoClimbToggle = toggle(leftAutoClimbToggle, is.leftArmStick.autoClimbRelease);
    	rightAutoClimbToggle = toggle(rightAutoClimbToggle, is.rightArmStick.autoClimbRelease);
    	
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
    		cookerArmToggle = toggle(cookerArmToggle, is.driveStick.armRelease);
    		if(pulleyToggle[0]){
    			driveToggle[0] = false;
    			cookerArmToggle[0] = false;
    		}else if(cookerArmToggle[0]){
    			driveToggle[0] = false;
    		}
    	}else if(pulleyToggle[0]){
    		driveToggle = toggle(pulleyToggle,is.driveStick.climbReleased);
    		cookerArmToggle = toggle(cookerArmToggle,is.driveStick.armRelease);
    		if(driveToggle[0]){
    			pulleyToggle[0] = false;
    			cookerArmToggle[0] = false;
    		}else if(cookerArmToggle[0]){
    			pulleyToggle[0] = false;
    		}
    	}else if(cookerArmToggle[0]){
    		driveToggle = toggle(driveToggle,is.driveStick.driveReleased);
    		pulleyToggle = toggle(pulleyToggle,is.driveStick.climbReleased);
    		if(driveToggle[0]){
        	cookerArmToggle[0] = false;
        	pulleyToggle[0] = false;
    		}else if(cookerArmToggle[0]){
    			pulleyToggle[0] = false;
    		}
    	}
   
    }
	
	protected boolean[] toggle(boolean[] toggle, boolean input){  // in the toggle array you have two values
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
}
