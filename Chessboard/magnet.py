import RPi.GPIO as GPIO
GPIO.setmode(GPIO.BCM)
GPIO.setup(18, GPIO.OUT, initial=0)

try:
    while True:
        GPIO.output(18,1)

except KeyboardInterrupt: 
    GPIO.cleanup

    