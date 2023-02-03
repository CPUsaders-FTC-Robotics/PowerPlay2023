package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class PowerPlay2023TEST extends LinearOpMode {
    private DcMotor bl, br, fl, fr, elev;
    private Servo lGrabber, rGrabber;

    @Override
    public void runOpMode() throws InterruptedException {
        bl = hardwareMap.get(DcMotor.class, "backLeft");
        br = hardwareMap.get(DcMotor.class, "backRight");
        fl = hardwareMap.get(DcMotor.class, "frontLeft");
        fr = hardwareMap.get(DcMotor.class, "frontRight");
        //elev = hardwareMap.get(DcMotor.class, "elevator");
        //lGrabber = hardwareMap.get(Servo.class, "leftGrabber");
        //rGrabber = hardwareMap.get(Servo.class, "rightGrabber");

        //Create Threads
        //Thread Drivetrain = new Drivetrain();
        //Thread Elevator = new Elevator();

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        telemetry.addData("Status", "Running");
        telemetry.update();

        //Drivetrain.start();
        telemetry.addData("Data", "after drivetrain thread");
        telemetry.update();
        //Elevator.start();
        telemetry.addData("Data", "Testing front left motor");
        telemetry.update();
        while (opModeIsActive()) {
            bl.setPower(1);
            Thread.sleep(1000);
            bl.setPower(0);

            br.setPower(1);
            Thread.sleep(1000);
            br.setPower(0);

            fl.setPower(1);
            Thread.sleep(1000);
            fl.setPower(0);

            fr.setPower(1);
            Thread.sleep(1000);
            fr.setPower(0);

            //continue;
        }
        //Drivetrain.interrupt();
        //Elevator.interrupt();
    }

    private class Drivetrain extends Thread {

        public void run() {
            if (gamepad1.left_stick_y > 0.1 || gamepad1.left_stick_y < -0.1) {
                //FWD&BWD
                bl.setPower(-accel(gamepad1.left_stick_y));
                br.setPower(accel(gamepad1.left_stick_y));
            } else if (gamepad1.right_stick_x > 0.1 || gamepad1.right_stick_x < -0.1) {
                bl.setPower(accel(gamepad1.right_stick_x));
                br.setPower(accel(gamepad1.right_stick_x));
            } else {
                bl.setPower(0);
                br.setPower(0);
            }
        }

        private float accel(float input) {
            assert input <= 1 || input >= -1;
            float value = Math.abs(input);
            if (value <= 1/3) {
                if (input == value) {
                    return value/2;
                } else {
                    return -value/2;
                }
            } else if (value <= 2/3) {
                if (input == value) {
                    Double val = new Double(1.1*value - 0.2);
                    return val.floatValue();
                } else {
                    Double val = new Double(1.1*value - 0.2);
                    return -val.floatValue();
                }
            } else {
                if (input == value) {
                    Double val = new Double(0.5*value + 0.2);
                    return val.floatValue();
                } else {
                    Double val = new Double(0.5*value + 0.2);
                    return -val.floatValue();
                }
            }
        }
    }

    /*private class Elevator extends Thread {
        public void run() {
            if (gamepad1.a) {
                lGrabber.setPosition(0);
                rGrabber.setPosition(1);
            } else {
                lGrabber.setPosition(1);
                rGrabber.setPosition(0);
            }
        }
    }*/
}


