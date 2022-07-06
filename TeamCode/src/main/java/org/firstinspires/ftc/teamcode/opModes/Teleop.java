package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Robot;

public class Teleop extends OpMode {
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
git