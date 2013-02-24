/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team1160.feathersMcGraw.input.inputStates;

/**
 * Basically my toggle code got really messy
 * so this represents all of our toggle real 
 * or other wise
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
    private boolean[] autoClimbToggle;
    private boolean[] hutchArmToggle;
    
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
        
        autoClimbToggle = new boolean[]{false, true};
    }
	
    public void toggleTheThings(){    // Will be the magic toggle function TODO fix this functions
    	
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
