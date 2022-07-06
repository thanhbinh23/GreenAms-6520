package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Robot;
public class TeleOp extends OpMode {
    public Robot robot = new Robot(this);
    @Override
    public void init() {
    }

    @Override
    public void loop() {
        robot.teleop();
    }
}
