package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Constants;

public class Spinner {
	public DcMotor spinnerMotor;
	public HardwareMap hardwareMap;
	public Spinner(OpMode opmode) {
		hardwareMap = opmode.hardwareMap;
	}
	
	public void initSpinner() {
		spinnerMotor = hardwareMap.dcMotor.get("Spinner");
	}
	
	public void drive() {
		spinnerMotor.setPower(Constants.MOTOR.SPEEDSPINNER);
	}
}
