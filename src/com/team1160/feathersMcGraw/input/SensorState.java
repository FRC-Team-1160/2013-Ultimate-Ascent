/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team1160.feathersMcGraw.input;

/**
 * This class will hold all of the input
 * from the robot... as of now it looks
 * like it will hold the value of a gyro 
 * two encoders and three pots
 * 
 * @author Wallace
 */
public class SensorState {
    
    public double tapeLengthRight;
    public double tapeLengthLeft;
    public double tapeLengthTop;
    public double robotAngle;
    
    public String toString(){
        String output = "";
        output+= "----Sensor States\n";
        output+="Right length: " + tapeLengthRight + "\n";
        output+=" Left Length: "+tapeLengthLeft+"\n";
        output+="  Top Length:"+ tapeLengthTop + "\n";
        output+="Robot Angle: " + robotAngle + "\n";
        return output;
    }
    
}
