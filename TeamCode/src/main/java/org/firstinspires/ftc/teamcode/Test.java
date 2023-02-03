package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class Test extends LinearOpMode {
    private DcMotor testMotor;
    private Servo leftGrabber;
    private Servo rightGrabber;

    @Override
    public void runOpMode() {
        //testMotor = hardwareMap.get(DcMotor.class,"testMotor");
        //leftGrabber = hardwareMap.get(Servo.class,"leftGrabber");
        //rightGrabber = hardwareMap.get(Servo.class,"rightGrabber");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        telemetry.addData("Status", "Running");
        telemetry.update();
        //testMotor.setPower(1);
        //Thread.sleep(1000);
        //testMotor.setPower(0);

        while(opModeIsActive()) {
            /*if (gamepad1.a) {
                leftGrabber.setPosition(0);
                rightGrabber.setPosition(1);
            } else {
                leftGrabber.setPosition(1);
                rightGrabber.setPosition(0);
            }*/
        }
        stop();
    }
}
