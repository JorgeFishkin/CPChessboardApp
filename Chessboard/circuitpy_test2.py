import time
import board
import digitalio
import busio

from adafruit_mcp230xx.mcp23017 import MCP23017

i2c = busio.I2C(board.SCL, board.SDA)

mcp1 = MCP23017(i2c, address=0x20)
mcp2 = MCP23017(i2c, address=0x21)

pin1_0 = mcp1.get_pin(0)
pin2_0 = mcp2.get_pin(0)

pin2_0.direction = digitalio.Direction.INPUT
pin2_0.pull = digitalio.Pull.UP


pin1_0.value = False
try:
    while True:
        if(pin2_0.value == True):
            pin1_0.value = True
            print("Sensor pressed")
        else:
            pin1_0.value = False

        
finally:
    pin1_0.value = False