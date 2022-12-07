import RPi.GPIO as GPIO
GPIO.setmode(GPIO.BCM)
GPIO.setup(4, GPIO.OUT, initial=0)

try:
    while True:
        GPIO.output(4,1)

except KeyboardInterrupt: 
    GPIO.cleanup

    