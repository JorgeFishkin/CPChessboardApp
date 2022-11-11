import time
import board
import digitalio
import busio

from adafruit_mcp230xx.mcp23017 import MCP23017

i2c = busio.I2C(board.SCL, board.SDA)

mcp1 = MCP23017(i2c, address=0x20)
#mcp2 = MCP23017(i2c, address=0x21)

pin1_0 = mcp1.get_pin(0)
#pin2_0 = mcp2.get_pin(0)

pin1_0.direction = digitalio.Direction.INPUT
pin1_0.pull = digitalio.Pull.UP



try:
    while True:
        if(pin1_0.value == False):
            #pin1_0.value = True
            print("Sensor pressed")
            time.sleep(0.05)
        else:
            print("Sensor not pressed")
            time.sleep(0.05)

        
finally:
    #pin1_0.value = False