package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Spinner;
	public class Robot {
		public Spinner spinner;
		public Gamepad gamepad;
		public Intake intake;
		//public HardwareMap hardwareMap;
		public Robot(OpMode opmode) {
			spinner = new Spinner(opmode);
			intake = new Intake(opmode);
			gamepad = opmode.gamepad1;
		}

		public void teleop() {
			if(gamepad.a) {
				spinner.spinnerMotor.setDirection(DcMotorSimple.Direction.FORWARD);
				spinner.drive();
			}
			else if (gamepad.b) {
				spinner.spinnerMotor.setDirection(DcMotorSimple.Direction.REVERSE);
				spinner.drive();
			} else if (gamepad.cross){
				intake.intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
				intake.drive();
			}
			else if (gamepad.circle){
				intake.intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
				intake.drive();
}}}
