import time
import board

from adafruit_motorkit import MotorKit
kit = MotorKit()

kit.stepper1.onestep()

for i in range(810):
    kit.stepper1.onestep()
    time.sleep(0.01)
