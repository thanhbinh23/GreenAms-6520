package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.DriveBase;

public class Robot {
    private final Gamepad controller;
    private final DriveBase driveBase;
    private final Telemetry telemetry;

    public Robot(OpMode opMode) {
        controller = opMode.gamepad1;

        telemetry = opMode.telemetry;

        driveBase = new DriveBase(opMode);
    }

    public void init() {
        driveBase.init(false);
    }

    public void start() {

    }

    public void teleop() {
        driveBase.driveController(controller.left_stick_y, controller.right_stick_y);
    }
}
