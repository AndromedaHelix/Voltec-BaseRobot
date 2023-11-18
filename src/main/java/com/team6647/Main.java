/**
 * Team 6647
 * 
 * Base robot code
 * 
 * Written by Juan Pablo Gutiérrez
 * 
 * This is the main class of the robot. It is the entry point of the program.
 */

package com.team6647;

import edu.wpi.first.wpilibj.RobotBase;

public final class Main {
  private Main() {
  }

  public static void main(String... args) {
    RobotBase.startRobot(Robot::new);
  }
}
