package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Robot;
import static org.firstinspires.ftc.teamcode.Constants.AUTONOMOUS.*;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="autoForTheWinner")
public class Autonomous extends LinearOpMode{
    private Robot robot;



    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        robot = Robot.getInstance(this);
        robot.init();
        robot.auto1();
    }
}
