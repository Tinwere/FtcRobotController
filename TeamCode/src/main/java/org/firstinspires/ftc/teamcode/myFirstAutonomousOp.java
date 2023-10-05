package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "Automous ", group = "Linear Op Mode")
public class myFirstAutonomousOp extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();  //Establishing motors and servos
    private DcMotor rightFrontMotor = null;
    private DcMotor rightBackMotor = null;
    private DcMotor leftFrontMotor = null;
    private DcMotor leftBackMotor = null;

    @Override
    public void runOpMode() throws InterruptedException {
        leftFrontMotor  = hardwareMap.get(DcMotor.class, "leftFrontMotor");
        leftBackMotor  = hardwareMap.get(DcMotor.class, "leftBackMotor");
        rightFrontMotor = hardwareMap.get(DcMotor.class, "rightFrontMotor");
        rightBackMotor = hardwareMap.get(DcMotor.class, "rightBackMotor");


        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);  //Direction establishement
        leftBackMotor.setDirection(DcMotor.Direction.FORWARD);
        rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        rightBackMotor.setDirection(DcMotor.Direction.REVERSE);


        leftBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rightBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        waitForStart();
        leftBackMotor.setPower(0.5);
        leftBackMotor.setTargetPosition(1000);

        leftFrontMotor.setPower(0.5);
        leftFrontMotor.setTargetPosition(1000);

        rightBackMotor.setPower(0.5);
        rightBackMotor.setTargetPosition(1000);

        rightFrontMotor.setPower(0.5);
        rightFrontMotor.setTargetPosition(1000);


        telemetry.addData("servo_position", rightBackMotor.getCurrentPosition());
        telemetry.addData("servo_position", rightFrontMotor.getCurrentPosition());
        telemetry.addData("servo_position", leftFrontMotor.getCurrentPosition());
        telemetry.addData("servo_position", leftBackMotor.getCurrentPosition());
    }
}
