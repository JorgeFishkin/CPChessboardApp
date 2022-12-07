import time
import board

from adafruit_motorkit import MotorKit
kit = MotorKit()


print("Forward Motor Drive")
for i in range(815):
    kit.stepper1.onestep()
    kit.stepper2.onestep()
    time.sleep(0.008)

#