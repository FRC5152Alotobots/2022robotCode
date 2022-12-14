// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.RapidReact.RapidReactCommands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.RapidReact.Intake.IntakeArm.IntakeArmSubSys;
import frc.robot.RapidReact.Intake.IntakeArm.IntakeArmCmds.IntakeArm_PosHold_Cmd;
import frc.robot.RapidReact.Intake.IntakeArm.IntakeArmCmds.IntakeArm_PosSupplier_Cmd;
import frc.robot.RapidReact.Intake.IntakeInNOut.IntakeInNOutSubSys;
import frc.robot.RapidReact.Intake.IntakeInNOut.IntakeInNOutCmds.IntakeInNOut_SpdSupplier_Cmd;
import frc.robot.RapidReact.Intake.IntakeInNOut.IntakeInNOutCmds.IntakeInNOut_Spd_Cmd;
import frc.robot.components.drive_subsys.DriveSubSys;
import frc.robot.components.drive_subsys.drive_cmds.DriveSubSys_SeekRotate2LimeLightTarget_Cmd;
import frc.robot.components.vision.LimeLightSubSys;
import frc.robot.components.vision.TargetInterpolation;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShooterAutoTarget_Cmd extends ParallelCommandGroup {
  /** Creates a new ShooterAutoTarget_Cmd. */

  private TargetInterpolation m_TargetInterpolation = new TargetInterpolation();

  public ShooterAutoTarget_Cmd(
    DriveSubSys driveSubSys,
    IntakeArmSubSys intakeArmSubSys,
    IntakeInNOutSubSys intakeInNOutSubSys,
    LimeLightSubSys limeLightSubSys  
  ) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new IntakeArm_PosSupplier_Cmd(
        intakeArmSubSys, 
        () -> m_TargetInterpolation.interpCalcArmAngle(
          () -> limeLightSubSys.m_TargetDistance)),

      new IntakeInNOut_SpdSupplier_Cmd(
        intakeInNOutSubSys,
        () -> m_TargetInterpolation.interpCalcInNOutVel(
          () -> limeLightSubSys.m_TargetDistance),
        () -> m_TargetInterpolation.interpCalcInNOutLwrVel(
          () -> limeLightSubSys.m_TargetDistance))      
    );
  }
}
