package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="blueAllianceLeft")
public class BlueAllianceLeft extends LinearOpMode {
    private Robot robot;



    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        robot = Robot.getInstance(this);
        robot.init();
        robot.blueAllianceLeft();
    }}