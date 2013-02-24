/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team1160.feathersMcGraw.drive;

import com.team1160.feathersMcGraw.api.Constants;
import com.team1160.feathersMcGraw.commands.DrivetrainCommand;
import com.team1160.feathersMcGraw.model.Model;
import com.team1160.feathersMcGraw.model.ModelNotifier;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;

/**
 *
 * @author Wallace
 */
public class DriveTrain implements ModelNotifier {

    protected Jaguar left;
    protected Jaguar right;
    
    protected Encoder rightEncoder;
    protected Encoder leftEncoder;
    
    protected Gyro gyro;
    
    static private DriveTrain _INSTANCE;
    
    private DriveTrain(){
        left = new Jaguar(Constants.DT_LEFT_JAG_CAR, Constants.DT_LEFT_JAG_CHAN);
        right = new Jaguar(Constants.DT_RIGHT_JAG_CAR, Constants.DT_RIGHT_JAG_CHAN);
    }
    
    public DriveTrain getInstance(){
        if(_INSTANCE == null){
            _INSTANCE = new DriveTrain();
        }
        return _INSTANCE;
    }
    
    public void setDriveTrain(DrivetrainCommand command){
        left.set(command.left);
        left.set(command.right);
    }
    
    public void resetDriveTrain(){
        gyro.reset();
        rightEncoder.reset();
        leftEncoder.reset();
    }
    
    public void updateModel(Model model) {
        
    }
}
