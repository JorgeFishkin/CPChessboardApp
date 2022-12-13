import time
import board

from adafruit_motorkit import MotorKit
kit = MotorKit()

ONE_INCH = 0
DIAG_MOVE = 0

def move_Xmotor(x):
    x = int(x)
    if(x < 0):
        x = -x
        for k in range(x):
            kit.stepper1.onestep(direction=stepper.BACKWARD)
    else:
        for k in range(x):
            kit.stepper1.onestep(direction=stepper.FORWARD)

def move_Ymotor(y):
    y = int(y)
    if(y < 0):
        y = -y
        for k in range(y):
            kit.stepper2.onestep(direction=stepper.BACKWARD)
    else:
        for k in range(y):
            kit.stepper2.onestep(direction=stepper.FORWARD)

def move_Diag(d, xdir, ydir):
    d = int(d)
    for k in range(d):
        kit.stepper1.onestep()
        kit.stepper2.onestep()
