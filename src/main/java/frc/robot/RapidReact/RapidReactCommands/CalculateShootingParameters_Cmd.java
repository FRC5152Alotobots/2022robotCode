// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.RapidReact.RapidReactCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.components.vision.TargetInterpolation;

public class CalculateShootingParameters_Cmd extends CommandBase {
  /** Creates a new CalculateShootingParameters_Cmd. */
  private TargetInterpolation m_TargetInterpolation;
  private double m_Distance;

  public CalculateShootingParameters_Cmd(
    TargetInterpolation targetInterpolation,
    double distance) {

      m_TargetInterpolation = targetInterpolation;
      m_Distance = distance;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_TargetInterpolation.calcInterpIndexAndFrac(m_Distance);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_TargetInterpolation.calcInterpIndexAndFrac(m_Distance);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
