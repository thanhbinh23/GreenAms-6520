package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.Constants.ARM;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm {
    private HardwareMap hardwareMap;
    private Telemetry telemetry;
    private DcMotor turretMotor, armMotor, clawMotor;

    public Arm(OpMode opMode) {
        this.telemetry = opMode.telemetry;
        this.hardwareMap = opMode.hardwareMap;
    }

    public void initArm() {
        turretMotor = hardwareMap.dcMotor.get(ARM.TURRET_MOTOR_ID);
        armMotor = hardwareMap.dcMotor.get(ARM.ROTATE_MOTOR_ID);
        clawMotor = hardwareMap.dcMotor.get(ARM.CLAW_MOTOR_ID);

        turretMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        clawMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }
}
