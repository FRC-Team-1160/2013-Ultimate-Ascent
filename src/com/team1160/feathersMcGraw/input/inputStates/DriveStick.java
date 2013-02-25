package com.team1160.feathersMcGraw.input.inputStates;

/*
 * 
 * 
 * @Author Wallace
 */

public class DriveStick {

	public boolean driveReleased;
	public boolean climbReleased;
		public boolean pulleyRelease;
	public boolean armRelease;
	public boolean autoClimbRelease;
    public boolean gripRelease;    
        
	public double x;
	public double y;
	
	public DriveStick() {
		x = 0;
		y = 0;
		climbReleased = true;
		pulleyRelease = true;
		armRelease = true;
        }
	
	public void setClimbRelease(boolean button){
		climbReleased = setRelease(climbReleased, button);
	}
	
	public void setPulleyRelease(boolean button){
		pulleyRelease = setRelease(pulleyRelease, button);
	}

	public void setArmRelease(boolean button){
		armRelease = setRelease(armRelease, button);
	}
	
	public void setAutoClimbRelease(boolean button){
		autoClimbRelease = setRelease(autoClimbRelease, button);
	}
	
	public void setDriveRelease(boolean button){
		driveReleased = setRelease(driveReleased, button);
	}
	
	public void setGripRelease(boolean button){
		gripRelease = setRelease(gripRelease, button);
	}
	
	
    protected boolean setRelease(boolean check, boolean button){
   		if(button && check){
   			return false;
        }else if(!button && !check){
             return 	true;
        }	
        return check;
    }    	
    
    
	public String toString(){
		String output = "";
		output+="--Drive Stick\n ";
		output+="Climb Release:   " + climbReleased + "\n";
		output+="Pulley Released: " + pulleyRelease + "\n";
		output+="X:               " + x + "\n";
		output+="Y:               " + y + "\n";
		return output;
	}
	
}
