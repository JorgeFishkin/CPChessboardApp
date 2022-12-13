import time
import board

from adafruit_motorkit import MotorKit
kit = MotorKit()

ONE_INCH = 0
DIAG_MOVE = 0

def move_Xmotor(x):
    dir = stepper.FORWARD
    if(x < 0):
        x = -x
        dir = stepper.BACKWARD
    for k in range(x):
        kit.stepper1.onestep(direction=dir)

def move_Ymotor(y):
    dir = stepper.FORWARD
    if(y < 0):
        y = -y
        dir = stepper.BACKWARD
    for k in range(y):
        kit.stepper2.onestep()

def move_Diag(d, xdir, ydir):
    
    for k in range(d):
        kit.stepper1.onestep()
        kit.stepper2.onestep()
