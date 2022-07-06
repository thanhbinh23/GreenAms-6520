package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import static org.firstinspires.ftc.teamcode.Constants.ARM.*;

public class Robot {
    public Arm arm;
    public Gamepad gamepad;

    public enum TurretState {
        REACHED, NOT_REACHED
    }
    public Robot(OpMode opmode) {
        arm = new Arm(opmode);
        gamepad = opmode.gamepad1;
    }

    public void init() {
        arm.initArm();
        arm.desiredPosition = 0;
    }

    public void teleop() {
        if (!arm.isBusy()) {
            if (gamepad.square) {
                arm.desiredPosition = 1;
            }
        }
        arm.setPosition();
        if (gamepad.left_bumper) {
            arm.setTurretMotor(arm_speed);
        }
        else if (gamepad.right_bumper) {
            arm.setTurretMotor(-arm_speed);
        }
    }
}
