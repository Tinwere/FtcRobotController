package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Second: Omni Op mode", group = "Linear Op Mode")

public class myFirstOpMode extends LinearOpMode {



    private ElapsedTime runtime = new ElapsedTime();  //Establishing motors and servos
    private DcMotor rightFrontMotor = null;
    private DcMotor rightBackMotor = null;
    private DcMotor leftFrontMotor = null;
    private DcMotor leftBackMotor = null;
    private Servo testservo;
    private DcMotor motorArm = null;
    //rtyrthrthytjutd Hi peter was here
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        motorArm = hardwareMap.get(DcMotor.class,"motorArm");
        testservo = hardwareMap.get(Servo.class, "testservo");  //Gets the devices from configurations
        leftFrontMotor  = hardwareMap.get(DcMotor.class, "leftFrontMotor");
        leftBackMotor  = hardwareMap.get(DcMotor.class, "leftBackMotor");
        rightFrontMotor = hardwareMap.get(DcMotor.class, "rightFrontMotor");
        rightBackMotor = hardwareMap.get(DcMotor.class, "rightBackMotor");


        testservo.setDirection(Servo.Direction.REVERSE);
        testservo.setPosition(0);

        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);  //Direction establishement
        leftBackMotor.setDirection(DcMotor.Direction.FORWARD);
        rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        rightBackMotor.setDirection(DcMotor.Direction.REVERSE);
        motorArm.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();



        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            double max;

            // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
            double axial   = -gamepad1.left_stick_y * 0.3;  // Note: pushing stick forward gives negative value
            double lateral =  gamepad1.left_stick_x * 0.3;
            double yaw     =  gamepad1.right_stick_x * 0.3;

            // Combine the joystick requests for each axis-motion to determine each wheel's power.
            // Set up a variable for each drive wheel to save the power level for telemetry.
            double leftFrontPower  = axial + lateral + yaw;
            double rightFrontPower = axial - lateral - yaw;
            double leftBackPower   = axial - lateral + yaw;
            double rightBackPower  = axial + lateral - yaw;

            // Normalize the values so no wheel power exceeds 100%
            // This ensures that the robot maintains the desired motion.
            max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(rightBackPower));

            if (max > 1.0) {
                leftFrontPower  /= max;
                rightFrontPower /= max;
                leftBackPower   /= max;
                rightBackPower  /= max;
            }


            // Send calculated power to wheels
            leftFrontMotor.setPower(leftFrontPower);
            rightFrontMotor.setPower(rightFrontPower);
            leftBackMotor.setPower(leftBackPower);
            rightBackMotor.setPower(rightBackPower);


            //Servo part
            if(gamepad1.a) {
                testservo.setPosition(1);
            } else if (gamepad1.b) {
                testservo.setPosition(0.5);
            }

            //arm part

            if(gamepad1.dpad_up){
                motorArm.setPower(0.2);
            }
            else if(gamepad1.dpad_down){
                motorArm.setPower(-0.2);
            }
            

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Front left/Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
            telemetry.addData("Back  left/Right", "%4.2f, %4.2f", leftBackPower, rightBackPower);
            telemetry.addData("servo_position", testservo.getPosition());

            telemetry.update();

        }

    }}