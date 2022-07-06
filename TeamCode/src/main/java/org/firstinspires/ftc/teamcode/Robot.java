package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.subsystems.Arm;

public class Robot {
    private Arm arm;
    private Gamepad gamepad;

    public enum TurretState {
        REACHED, NOT_REACHED
    }

    public Robot(OpMode opmode) {
        arm = new Arm(opmode);
        gamepad = opmode.gamepad1;
    }

    public void init() {
        arm.initArm();
    }

    public void teleop() {
        arm.moveArm(gamepad.left_bumper, gamepad.right_bumper, gamepad.square);
    }
}
