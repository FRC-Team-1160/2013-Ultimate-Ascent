package com.team1160.feathersMcGraw.api;


/*
 * The constants file is basically what it sounds like
 * it contains all of the numbers that remain constant
 * through out the robot code. These help with being 
 * able to at a glance remember what is what and more
 * importantly only having to change a value once when 
 * a silly mech plugs something in wrong.
 * 
 * @Author Wallace
 */

/*
 * Also in our code every one is refered to by a code name
 * (haha see what i did there... no? fine any way...)
 * 
 * Wallace: Ethan Rooke
 * Gromit: Spencer Rooke
 * Preston: Kavi Gupta
 * Shaun: Mark Liang
 * The Farmer: Zach Kaufman
 * 
 * Non programming names:
 * 
 * Hutch: Refers to driver
 * Cooker: Refers to the technician
 * 
 */


public interface Constants {

	//Quick side note, this class was written back before i knew that we were going to have two digital
	//Side cars. I feel like the way we solved this was a bit brutish but I was tired 
	
	//Outputs
	
		//Drive train
			int DT_LEFT_JAG_CHAN = 2;
			int DT_LEFT_JAG_CAR = 2;
			int DT_RIGHT_JAG_CHAN = 3;
			int DT_RIGHT_JAG_CAR = 1;
		//Pulley System
			// Top Pulley
				int P_TOP_JAG_CHAN = 1;
				int P_TOP_JAG_CAR = 1;
				int P_TOP_SERVO_CHAN = 6;
				int P_TOP_SERVO_CAR = 1;
				int P_TOP_LOCK_CHAN = 7;       // Not actually used as of now
				int P_TOP_LOCK_CAR = 1;          // Fix if they throw a lock on 
				double P_TOP_LOCK_LOCKED = .9;
				double P_TOP_LOCK_OPEN = .1;
				double P_TOP_HOME = .30;
				double P_TOP_MAX = .9;
				double P_TOP_MIN = .2;
			// Right Pulley
				int P_RIGHT_JAG_CHAN = 2;
				int P_RIGHT_JAG_CAR = 1;
				int P_RIGHT_SERVO_CHAN = 4;
				int P_RIGHT_SERVO_CAR = 1;
				int P_RIGHT_LOCK_CHAN = 5;
				int P_RIGHT_LOCK_CAR = 1;
				double P_RIGHT_LOCK_LOCKED = .24;
				double P_RIGHT_LOCK_OPEN = .36;
				double P_RIGHT_HOME = .27;
				double P_RIGHT_MAX = .9;
				double P_RIGHT_MIN = .2;
			// Left Pulley
				int P_LEFT_JAG_CHAN = 3;
				int P_LEFT_JAG_CAR = 2;
				int P_LEFT_SERVO_CHAN = 1;
				int P_LEFT_SERVO_CAR = 2;
				int P_LEFT_LOCK_CHAN = 4;
				int P_LEFT_LOCK_CAR = 2;
				double P_LEFT_LOCK_LOCKED = .56;
				double P_LEFT_LOCK_OPEN = .4;
				double P_LEFT_HOME = .56;
				double P_LEFT_MAX =.9;
				double P_LEFT_MIN = .2;
		// Arm system
			// Arm
				int A_MOTOR_CHAN = 8;                    // also bs
				int A_MOTOR_CAR =1;                        // just to stop a pwm error
				double A_SERVO_HOME = .5;
				double A_SERVO_MAX = .9;
				double A_SERVO_MIN = .2;
				double A_SERVO_PICK = .3;
				double A_SERVO_DUMP = .8;
			// Griper
				int G_SERVO_CHAN = 9;
				int G_SERVO_CAR = 1;
				double G_SERVO_GRIP = .9;	
				double G_SERVO_OPEN = .1;

	//Input
		// Joysticks
				int HUTCH_STICK = 1;
				int COOKER_STICK_1 = 2;
				int COOKER_STICK_2 = 3;
		//Pot Constants
				int TOP_PULLEY_CHAN = 2;
				double TOP_PULLEY_MIN = 0;     //Made up for now
				double TOP_PULLEY_MAX = 100;   // TODO TEST

				int LEFT_PULLEY_CHAN = 3;
				double LEFT_PULLEY_MIN = 0;
				double LEFT_PULLEY_MAX = 100;
				
				int RIGHT_PULLEY_CHAN = 4;
				double RIGHT_PULLEY_MIN = 0;
				double RIGHT_PULLEY_MAX = 100;
		//Gyro
				int GYRO_CHAN = 1;   // Made up for now... TODO make sure these channels match
}		
