import RPi.GPIO as GPIO


def gpio_setup():
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(4, GPIO.OUT, initial=0)

def mag_on():
    GPIO.output(4,1)

def mag_off():
    GPIO.output(4,0)


gpio_setup()

try:
    while True:
        GPIO.output(4,1)

except KeyboardInterrupt: 
    GPIO.cleanup

    