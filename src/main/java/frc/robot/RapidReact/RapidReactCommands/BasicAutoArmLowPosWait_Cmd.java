// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.RapidReact.RapidReactCommands;

import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RapidReact.Intake.IntakeArm.IntakeArmSubSys;
import frc.robot.RapidReact.Intake.IntakeArm.IntakeArmCmds.IntakeArm_Pos_Cmd;
import frc.robot.RapidReact.Intake.IntakeArm.IntakeArmCmds.IntakeArm_Spd_Cmd;
import frc.robot.RapidReact.Intake.IntakeInNOut.IntakeInNOutSubSys;
import frc.robot.RapidReact.Intake.IntakeInNOut.IntakeInNOutCmds.IntakeInNOut_Spd_Cmd;
import frc.robot.RapidReact.Intake.IntakeTriggers.IntakeTriggersSubSys;
import frc.robot.RapidReact.Intake.IntakeTriggers.IntakeTriggersCmds.IntakeTriggers_Trigger_Cmd;
import frc.robot.components.drive_subsys.DriveSubSys;
import frc.robot.components.drive_subsys.drive_cmds.DriveSubSys_Drive4Time_Cmd;
import frc.robot.components.drive_subsys.drive_cmds.DriveSubSys_ResetOdometry_Cmd;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class BasicAutoArmLowPosWait_Cmd extends SequentialCommandGroup {
  /** Creates a new BasicAuto_Cmd. */
  public BasicAutoArmLowPosWait_Cmd(
    DriveSubSys driveSubSys,
    IntakeArmSubSys intakeArmSubSys,
    IntakeInNOutSubSys intakeInNOutSubSys,
    IntakeTriggersSubSys intakeTriggersSubSys) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());

    addCommands(
      //new DriveSubSys_ResetOdometry_Cmd(driveSubSys),

      //inital backwards movement
      new edu.wpi.first.wpilibj2.command.WaitCommand(5),

      new DriveSubSys_Drive4Time_Cmd(driveSubSys, -0.4, 0, 0, 1.0, false),
    
      new IntakeArm_Pos_Cmd(intakeArmSubSys, 87)
        .withTimeout(1.0),

      new IntakeInNOut_Spd_Cmd(intakeInNOutSubSys, -0.25, 0.0)
        .withTimeout(1),

      new IntakeInNOut_Spd_Cmd(intakeInNOutSubSys, -0.36, 0.0)
        .withTimeout(3),

      new ParallelCommandGroup(
        new IntakeInNOut_Spd_Cmd(intakeInNOutSubSys, -0.35, 0.0)
        .withTimeout(1),
        
        new IntakeTriggers_Trigger_Cmd(intakeTriggersSubSys, true, true)
        .withTimeout(1)
      ),

      new DriveSubSys_Drive4Time_Cmd(driveSubSys, -0.6, 0, 0, 5.0, false)

    );
  }
}
