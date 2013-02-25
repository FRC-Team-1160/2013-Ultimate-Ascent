package com.team1160.feathersMcGraw.teleop;

import com.team1160.feathersMcGraw.api.Constants;
import com.team1160.feathersMcGraw.commands.PulleyCommand;
import com.team1160.feathersMcGraw.commands.RobotCommand;
import com.team1160.feathersMcGraw.input.InputState;
import java.lang.Math;

public class TeleopManager {
/*
 * 
 * I think i fixed it bout to find out :D
 */
	private static TeleopManager _INSTANCE;
	
	private RobotCommand currentCommand;
	private InputState inputState;
	
            
	protected double k1;
	protected double k2;
	protected double k3;
	protected double k4;
	protected double k5;
	protected double a;
	protected double b;
	protected double c;
	protected double x2;
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
	}
	
	public RobotCommand getCommand(InputState inputState){
		this.inputState = inputState;
		forgeArmCommand();
		forgeDriveCommand();
		forgePulleySystemCommand();
		return currentCommand;
	}
        
	
		
	private void forgeDriveCommand(){
		if(inputState.toggleBoard.driveToggle[0]){
			currentCommand.drivetrainCommand.left = inputState.driveStick.x + inputState.driveStick.y;
			currentCommand.drivetrainCommand.right = inputState.driveStick.x - inputState.driveStick.y;
		}else{
			currentCommand.drivetrainCommand.left = 0;
			currentCommand.drivetrainCommand.right = 0;
		}
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
				m2= Math.atan((k2-x2)/(k3+k4-k5*x2));
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
		currentCommand.pulleySystemCommand.left = forgePulleyCommand(inputState.toggleBoard.leftPulleyToggle[0], inputState.toggleBoard.leftLockToggle[0], inputState.leftArmStick.y, inputState.toggleBoard.leftAutoClimbToggle[0], false, inputState.sensorState.tapeLengthLeft);
		currentCommand.pulleySystemCommand.right = forgePulleyCommand(inputState.toggleBoard.rightPulleyToggle[0], inputState.toggleBoard.rightLockToggle[0], inputState.rightArmStick.y, inputState.toggleBoard.rightAutoClimbToggle[0], false, inputState.sensorState.tapeLengthRight);
		if(inputState.toggleBoard.pulleyToggle[0]){
			currentCommand.pulleySystemCommand.top = forgePulleyCommand(inputState.toggleBoard.topPulleyToggle[0], false, inputState.driveStick.y, inputState.toggleBoard.topAutoClimbToggle[0],true, inputState.sensorState.tapeLengthTop);
		}
	}
        
	
	private PulleyCommand forgePulleyCommand(boolean pulleyMode, boolean lock, double y, boolean auto, boolean middle, double T){
		PulleyCommand newCommand = new PulleyCommand();
		if(!auto){
			if(!pulleyMode){
				newCommand.angle = (y+1)/2;
				newCommand.velocity = 0;
			}else{
				newCommand.velocity = -y;
				newCommand.angle = 1337;
			}
			newCommand.locked = lock;
			
		}else{
			newCommand.angle = getServoAngle(getTapeAngle(inputState.toggleBoard.floorToggle[0], middle, inputState.sensorState.robotAngle,T), T, middle);
			newCommand.velocity = -y;
		}
		return newCommand;
	}
        
	private void forgeArmCommand(){
		if(inputState.toggleBoard.cookerArmToggle[0]){
			currentCommand.armCommand.angle = (inputState.driveStick.y+1)/2;
		}
		currentCommand.armCommand.grip = inputState.toggleBoard.gripToggle[0];
	}
	
        
	public String toString(){
		String output = "";
		output+= currentCommand;
		return output;
	}
	
}