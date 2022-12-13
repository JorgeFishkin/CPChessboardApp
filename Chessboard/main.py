import sensor
import motor
import magnet
import pi_blue
import time
import RPi.GPIO as GPIO


# Initial Setup
magnet.gpio_setup()
sensor.setupSensors()


# Main loop
try:
    while(1):
        comm=input("Enter command:")

        if(comm == "mov"):
            x=input("X-pos: ")
            y=input("Y-pos: ")
            print("Moving...")
            motor.move_Xmotor(x)
            motor.move_Ymotor(y)
        elif(comm == "mag on"):
            magnet.mag_on()
            print("Magnet on")
        elif(comm == "mag off"):
            magnet.mag_off()
            print("Magnet off")
        elif(comm == "bluetooth"):
            pi_blue.bluetooth_poll()
        elif(comm == "sense"):
            for k in range(60):
                sensor.readSensors()
                time.sleep(0.5)
        elif(comm == "quit"):
            print("Quitting... Goodbye!")
            break

        else:
            print("Not a command, try again...")

except KeyboardInterrupt: 
    print("\nKeyboard Interrupt; Closing")
    GPIO.cleanup

finally:
    GPIO.cleanup
        
