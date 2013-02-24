package com.team1160.feathersMcGraw.input;

import com.team1160.feathersMcGraw.api.Constants;
import com.team1160.feathersMcGraw.input.inputStates.ArmStick;
import com.team1160.feathersMcGraw.input.inputStates.DriveStick;

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
	
	public static InputManager getInstance(){
		if(_INSTANCE == null){
			_INSTANCE = new InputManager();
		}
		return _INSTANCE;
	}
	
	private InputManager(){
		currentInputState = new InputState();
		js1 = new Joystick(1);
		js2 = new Joystick(2);
		js3 = new Joystick(3);
	}
		
	public InputState getInputState(){
		forgeArmJoystick(js2, currentInputState.armStickOne);
		forgeArmJoystick(js3, currentInputState.armStickTwo);
		forgeDriveJoystick(js1, currentInputState.driveStick);
		return currentInputState;
	}
	
	private void forgeArmJoystick(Joystick js, ArmStick armStick){
		armStick.x = js.getX();
		armStick.y = js.getY();
		armStick.setLockStatus(js.getRawButton(2));
		armStick.setReleaseStatus(js.getRawButton(1));
	}
	
	private void forgeDriveJoystick(Joystick js, DriveStick driveStick){
		driveStick.x = js.getX();
		driveStick.y = js.getY();
		driveStick.setClimbRelease(js.getRawButton(10));
		driveStick.setPulleyRelease(js.getRawButton(1));
                                     driveStick.setArmRelease(js.getRawButton(6));                  
                   }

	public String toString(){
		return currentInputState.toString();
	}
}
