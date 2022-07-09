package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;

@TeleOp(name = "ArmTest")
public class NotTeleOp extends OpMode {

    Robot robot = new Robot(this);

    @Override
    public void init() {
        robot.init();

    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {
        robot.teleop();
    }
}