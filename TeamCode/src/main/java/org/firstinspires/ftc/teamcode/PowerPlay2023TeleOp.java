package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCameraFactory;

@TeleOp

public class PowerPlay2023TeleOp extends LinearOpMode {
    private DcMotor bl, br, fl, fr, elev;
    private Servo lGrabber, rGrabber;

    @Override
    public void runOpMode() throws InterruptedException {
        bl = hardwareMap.get(DcMotor.class, "backLeft");
        br = hardwareMap.get(DcMotor.class, "backRight");
        fl = hardwareMap.get(DcMotor.class, "frontLeft");
        fr = hardwareMap.get(DcMotor.class, "frontRight");
        elev = hardwareMap.get(DcMotor.class, "elevator");
        lGrabber = hardwareMap.get(Servo.class, "leftGrabber");
        rGrabber = hardwareMap.get(Servo.class, "rightGrabber");

        //Create Threads
        Thread Drivetrain = new Drivetrain();
        Thread Elevator = new Elevator();
        Thread Grabber = new Grabber();

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        telemetry.addData("Status", "Running");
        telemetry.update();

        Drivetrain.start();
        Elevator.start();
        Grabber.start();
        while (opModeIsActive()) {
            continue;
        }
        Drivetrain.interrupt();
        Elevator.interrupt();
        Grabber.interrupt();
    }

    private class Drivetrain extends Thread {
        double speedCtrl = 0.6;
        public void run() {
            while (true) {
                if (gamepad1.left_stick_y > 0.1 || gamepad1.left_stick_y < -0.1) {
                    //FWD&BWD
                    fl.setPower(-speedCtrl * accel(gamepad1.right_stick_y));
                    fr.setPower(speedCtrl * accel(gamepad1.right_stick_y));
                    bl.setPower(-speedCtrl * accel(gamepad1.left_stick_y));
                    br.setPower(speedCtrl * accel(gamepad1.left_stick_y));
                } else if (gamepad1.right_stick_x > 0.1 || gamepad1.right_stick_x < -0.1) {
                    //Turn
                    fl.setPower(speedCtrl * accel(gamepad1.right_stick_x));
                    fr.setPower(speedCtrl * accel(gamepad1.right_stick_x));
                    bl.setPower(speedCtrl * accel(gamepad1.right_stick_x));
                    br.setPower(speedCtrl * accel(gamepad1.right_stick_x));
                } else if (gamepad1.left_stick_x > 0.1 || gamepad1.left_stick_x < -0.1) {
                    //Strafe
                    fl.setPower(speedCtrl * accel(gamepad1.left_stick_x));
                    fr.setPower(speedCtrl * accel(gamepad1.left_stick_x));
                    bl.setPower(-speedCtrl * accel(gamepad1.left_stick_x));
                    br.setPower(-speedCtrl * accel(gamepad1.left_stick_x));
                } else {
                    //Stop
                    fl.setPower(0);
                    fr.setPower(0);
                    bl.setPower(0);
                    br.setPower(0);
                }
            }
        }
    }

    private double accel(double input) {
        boolean sqrInputs = true;

        if (sqrInputs) {
            //square inputs
            return input/Math.abs(input) * Math.pow(input, 2);
        } else {
            //assert input <= 1 || input >= -1;

            double value = Math.abs(input);
            if (value <= 1 / 3) {
                if (input == value) {
                    return value / 2;
                } else {
                    return -value / 2;
                }
            } else if (value <= 2 / 3) {
                if (input == value) {
                    Double val = new Double(1.1 * value - 0.2);
                    return val.floatValue();
                } else {
                    Double val = new Double(1.1 * value - 0.2);
                    return -val.floatValue();
                }
            } else {
                if (input == value) {
                    Double val = new Double(0.5 * value + 0.2);
                    return val.floatValue();
                } else {
                    Double val = new Double(0.5 * value + 0.2);
                    return -val.floatValue();
                }
            }
        }
    }

    /**
     * Code for Elevator
     */
    private class Elevator extends Thread {
        public void run() {
            while(true) {
                if (gamepad2.left_stick_y > 0.1 || gamepad2.left_stick_y < -0.1) {
                    //elevator
                    elev.setPower(0.4 * accel(gamepad2.left_stick_y));
                } else {
                    elev.setPower(0);
                    elev.setPower(0);
                }
            }
        }
    }

    /**
     * Code for Grabber
     */
    private class Grabber extends Thread {
        boolean isClosed = true;
        public void run() {
            close();
            while (true) {
                if (gamepad2.a || gamepad2.b) {
                    changeState();
                    while (gamepad2.a || gamepad2.b) {
                        continue;
                    }
                }
            }
        }

        /**
         * Changes the State of the grabber variable isClosed
         */
        private void changeState() {
            if (isClosed) {
                open();
                isClosed = false;
            } else {
                close();
                isClosed = true;
            }
        }

        /**
         * Code for opening/closing Grabber
         */
        private void close() {
            lGrabber.setPosition(0.3);  //default 0
            rGrabber.setPosition(0.7);  //default 1
        }
        private void open() {
            lGrabber.setPosition(0.3);  //default 1
            rGrabber.setPosition(0.7);  //default 0
        }
    }
}


