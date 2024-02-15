/**
 * Phoenix Software License Agreement
 *
 * Copyright (C) Cross The Road Electronics.  All rights
 * reserved.
 * 
 * Cross The Road Electronics (CTRE) licenses to you the right to 
 * use, publish, and distribute copies of CRF (Cross The Road) firmware files (*.crf) and 
 * Phoenix Software API Libraries ONLY when in use with CTR Electronics hardware products.
 * 
 * THE SOFTWARE AND DOCUMENTATION ARE PROVIDED "AS IS" WITHOUT
 * WARRANTY OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING WITHOUT
 * LIMITATION, ANY WARRANTY OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, TITLE AND NON-INFRINGEMENT. IN NO EVENT SHALL
 * CROSS THE ROAD ELECTRONICS BE LIABLE FOR ANY INCIDENTAL, SPECIAL, 
 * INDIRECT OR CONSEQUENTIAL DAMAGES, LOST PROFITS OR LOST DATA, COST OF
 * PROCUREMENT OF SUBSTITUTE GOODS, TECHNOLOGY OR SERVICES, ANY CLAIMS
 * BY THIRD PARTIES (INCLUDING BUT NOT LIMITED TO ANY DEFENSE
 * THEREOF), ANY CLAIMS FOR INDEMNITY OR CONTRIBUTION, OR OTHER
 * SIMILAR COSTS, WHETHER ASSERTED ON THE BASIS OF CONTRACT, TORT
 * (INCLUDING NEGLIGENCE), BREACH OF WARRANTY, OR OTHERWISE
 */

/**
 * Description:
 * Example plays music sourced from a MIDI file through several Talon FXs.
 * Use Tuner to convert a MIDI file into a CHRP (chirp) file. 
 * These are then placed into the deploy folder of this project so that they are copied into the robot controller (on deploy).
 * 
 * Supported Version:
 * 	- Talon FX: 20.2.3.0 or newer
 *
 * Feature Video: https://youtu.be/MTGScSS_iaQ
 */
package frc.robot;

import java.util.ArrayList;

import com.ctre.phoenix.music.Orchestra;
import com.ctre.phoenix6.hardware.ParentDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * Top level Robot class 
 */
public class Robot extends TimedRobot {

    /* The orchestra object that holds all the instruments */
    com.ctre.phoenix6.Orchestra _orchestra;

    /* Talon FXs to play music through.  
    More complex music MIDIs will contain several tracks, requiring multiple instruments.  */
    com.ctre.phoenix6.hardware.TalonFX [] _fxes =  { new com.ctre.phoenix6.hardware.TalonFX(1),
        new com.ctre.phoenix6.hardware.TalonFX(2),
        new com.ctre.phoenix6.hardware.TalonFX(3),
        new com.ctre.phoenix6.hardware.TalonFX(4)
};

    /* An array of songs that are available to be played, can you guess the song/artists? */
  String[] _songs = new String[] {
    "song1.chrp",
  };

    void LoadMusicSelection()
    {
        /* load the chirp file */
        _orchestra.loadMusic("song1.chrp"); 

    }

    //------------- robot routines --------------- //
    /**
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */
    @Override
    public void robotInit() {
        /* A list of TalonFX's that are to be used as instruments */
        ArrayList<ParentDevice> _instruments = new ArrayList<ParentDevice>();
      
        /* Initialize the TalonFX's to be used */
        for (int i = 0; i < _fxes.length; ++i) {
            _instruments.add(_fxes[i]);
        }
        
        /* Create the orchestra with the TalonFX instruments */
        _orchestra = new com.ctre.phoenix6.Orchestra(_instruments);
    }
    
    @Override
    public void teleopInit() {
        
        /* load whatever file is selected */
        LoadMusicSelection();
    }

    @Override
    public void teleopPeriodic() {
        _orchestra.play();
    }
}
