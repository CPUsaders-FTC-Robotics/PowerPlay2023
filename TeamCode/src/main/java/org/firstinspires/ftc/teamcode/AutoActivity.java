package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class AutoActivity extends LinearOpMode {
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

        waitForStart();

        /*
        The commands you can write are this
        forward(Insert distance you want it to move forward);
        backward(Insert distance you want it to move backward);
        turnLeft(Insert how much you want it to turn left);
        turnRight(Insert how much you want it to turn right);
        elevatorUp(Insert how much you want the elevator to move up);
        elevatorDown(Insert how much you want the elevator to move down);
        grabberClose(); This makes the grabber close
        grabberOpen(); This makes the grabber open
         */

        //Your code starts here



        //Your code ends here
    }

    double speedCtrl = 0.6;
    void forward(int seconds) throws InterruptedException {
        fl.setPower(-speedCtrl);
        fr.setPower(speedCtrl);
        bl.setPower(-speedCtrl);
        br.setPower(speedCtrl);

        Thread.sleep(seconds * 1000);

        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
    }

    void forward(int seconds, double speed) throws InterruptedException {
        fl.setPower(-speed);
        fr.setPower(speed);
        bl.setPower(-speed);
        br.setPower(speed);

        Thread.sleep(seconds * 1000);

        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
    }

    void backward(int seconds) throws InterruptedException {
        fl.setPower(speedCtrl);
        fr.setPower(-speedCtrl);
        bl.setPower(speedCtrl);
        br.setPower(-speedCtrl);

        Thread.sleep(seconds * 1000);

        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
    }

    void backward(int seconds, double speed) throws InterruptedException {
        fl.setPower(speed);
        fr.setPower(-speed);
        bl.setPower(speed);
        br.setPower(-speed);

        Thread.sleep(seconds * 1000);

        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
    }

    void turnLeft(int seconds) throws InterruptedException {
        fl.setPower(speedCtrl);
        fr.setPower(speedCtrl);
        bl.setPower(speedCtrl);
        br.setPower(speedCtrl);

        Thread.sleep(seconds * 1000);

        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
    }

    void turnLeft(int seconds, double speed) throws InterruptedException {
        fl.setPower(speed);
        fr.setPower(speed);
        bl.setPower(speed);
        br.setPower(speed);

        Thread.sleep(seconds * 1000);

        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
    }

    void turnRight(int seconds) throws InterruptedException {
        fl.setPower(-speedCtrl);
        fr.setPower(-speedCtrl);
        bl.setPower(-speedCtrl);
        br.setPower(-speedCtrl);

        Thread.sleep(seconds * 1000);

        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
    }

    void turnRight(int seconds, double speedControl) throws InterruptedException {
        fl.setPower(-speedControl);
        fr.setPower(-speedControl);
        bl.setPower(-speedControl);
        br.setPower(-speedControl);

        Thread.sleep(seconds * 1000);

        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
    }

    void elevatorUp(int seconds) throws InterruptedException {
        double speed = 0.4;
        elev.setPower(speed);

        Thread.sleep(seconds * 1000);

        elev.setPower(0);
    }

    void elevatorUp(int seconds, double speed) throws InterruptedException {
        elev.setPower(speed);

        Thread.sleep(seconds * 1000);

        elev.setPower(0);
    }

    void grabberOpen() {
        lGrabber.setPosition(0.3);
        rGrabber.setPosition(0.7);
    }

    void grabberClose() {
        lGrabber.setPosition(0.3);
        rGrabber.setPosition(0.7);
    }
}


