package com.team1160.feathersMcGraw.input;

import com.team1160.feathersMcGraw.api.Constants;
import com.team1160.feathersMcGraw.input.inputStates.ArmStick;
import com.team1160.feathersMcGraw.input.inputStates.DriveStick;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;

/*
 * The input manager takes input from both human
 * players and the robot. It then stores in the
 * input state and passes it on to to the manager
 * in charge of that particular stage for further
 * processing
 * 
 * @Author wallace
 */

public class InputManager {

	//Singleton code
	
	/*
	 * Quick note on singletons.
	 * The singleton design is for objects that having
	 * more then one would risk your code in some way.
	 * We do this on all the managers becuase you do
	 * not want multiple managers creating commands and
	 * such. (especially with output, if you double assign
	 * pwm port your code will just stop) 
	 * 
	 * We do this by making the constructor of the class private
	 * this will prevent it ever being constructed by not the class
	 * We give the class a static version of itself (we use the name
	 * _INSTANCE but I do not know of a reason you couldnt use a 
	 * different name) you then make a public static function that returns an
	 * object of that type called getInstance() or something along those 
	 * lines. All this chunk of code does is check to see if instance has
	 * been constructed if not it constructs it and then it unconditionally
	 * returns it. So instead of constructing your object you ask the class
	 * for the one and only copy of it. 
	 *
	 * alright that was a bit longer then expected but hopefully it made sense
	 */
	
	private static InputManager _INSTANCE;
	
	private InputState currentInputState;
	
	private Joystick js1;
	private Joystick js2;
	private Joystick js3;
	
	private Gyro gyro;
	
	private AnalogChannel middle;
	private AnalogChannel right;
	private AnalogChannel left;
	
	public static InputManager getInstance(){
		if(_INSTANCE == null){
			_INSTANCE = new InputManager();
		}
		return _INSTANCE;
	}
	
	private InputManager(){
		currentInputState = new InputState();
		
		gyro = new Gyro(Constants.GYRO_CHAN);
		middle = new AnalogChannel(Constants.TOP_PULLEY_CHAN);
		right = new AnalogChannel(Constants.RIGHT_PULLEY_CHAN);
		left = new AnalogChannel(Constants.LEFT_PULLEY_CHAN);
		
		js1 = new Joystick(1);
		js2 = new Joystick(2);
		js3 = new Joystick(3);
	}
		
	public InputState getInputState(){
		forgeArmJoystick(js2, currentInputState.rightArmStick, -1);
		forgeArmJoystick(js3, currentInputState.leftArmStick, 1);
		forgeDriveJoystick(js1, currentInputState.driveStick);
		forgeSensorState(currentInputState.sensorState);
		currentInputState.toggleBoard.toggleTheThings(currentInputState);	
		return currentInputState;
	}
	
	private void forgeArmJoystick(Joystick js, ArmStick armStick, int multiplier){
		armStick.x = js.getX();
		armStick.y = js.getY()*multiplier;
		armStick.setLockRelease(js.getRawButton(2));
		armStick.setAutoClimbRelease(js.getRawButton(3));
		armStick.setPulleyRelease(js.getRawButton(1));
	}
	
	private void forgeDriveJoystick(Joystick js, DriveStick driveStick){
		driveStick.x = js.getX();
		driveStick.y = js.getY();
		driveStick.setClimbRelease(js.getRawButton(10));
		driveStick.setPulleyRelease(js.getRawButton(1));
		driveStick.setArmRelease(js.getRawButton(6));                  
		driveStick.setAutoClimbRelease(js.getRawButton(3));
		driveStick.setDriveRelease(js.getRawButton(4));
		driveStick.setGripRelease(js.getRawButton(5));
	}

	private void forgeSensorState(SensorState ss){
		ss.robotAngle = gyro.getAngle();
		ss.tapeLengthLeft = tapeLength(left,3);
		ss.tapeLengthRight = tapeLength(right,1);
		ss.tapeLengthTop = tapeLength(middle,2);
	}
	
	private double tapeLength(AnalogChannel sensor, int side){   // A helper function to compute the length of the tape based on a pots vale
		/*Side
		 * 1 = right
		 * 2 = middle
		 * 3 = left
		 */
		double v = sensor.getVoltage(); // all my equations where in terms of v so faster to code
		if(side == 1){
			return (16.3*v - 3.19 + 4.5);
		}else if(side == 2){
			return (.077*v*v) + (15.54*v) + 4.72 + 4.5;
		}else if(side == 3){
			return (-.6296*v*v)-(12.2*v)+69.62+4.5;
		}
		return -10;
	}
	public String toString(){
		String output = "";
		output+= currentInputState;
		return output;
	}
}
