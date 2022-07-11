package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "DriveTesting")
public class TeleOp extends OpMode {

    private Robot robot;

    @Override
    public void init() {
        robot = Robot.getInstance(this);
        robot.init();
        telemetry.update();
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        robot.teleop();
    }
}
