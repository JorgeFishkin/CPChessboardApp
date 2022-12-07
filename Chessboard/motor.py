from adafruit_motorkit import MotorKit
kit = MotorKit(i2c=board.I2C())

kit.stepper1.onestep()

for i in range(100):
    kit.stepper1.onestep()
    time.sleep(0.01)
