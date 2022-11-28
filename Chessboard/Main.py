import time
import board
import digitalio
import busio
import numpy

from adafruit_mcp230xx.mcp23017 import MCP23017

i2c = busio.I2C(board.SCL, board.SDA)

mcp1 = MCP23017(i2c, address=0x20)
#mcp2 = MCP23017(i2c, address=0x21)
#mcp3 = MCP23017(i2c, address=0x22)
#mcp4 = MCP23017(i2c, address=0x23)


sense_array = [[]]


def readSensors():
    for i in range(0,7):
        for j in range(0,7):
            print(str(i) + "," + str(j) + "Val: " + str(sense_array[i][j].value))
            if(sense_array[i][j].value):
                print(str(i) + "," + str(j))
                return [i,j]
                
            else:
                return 0
    return 0

def setupSensors():
    # Set up each of the pins
    pin = 0
    for i in range(0, 7):
        for j in range(0,7):
            if(i == 0 | i == 1):
                sense_array[i][j] = mcp1.get_pin(pin)
                sense_array[i][j].direction = digitalio.Direction.INPUT
                sense_array[i][j].pull = digitalio.Pull.UP
                print(str(sense_array[i][j].value))
            #elif(i == 2 | i ==3):
            #    sense_array[i][j] = mcp2.get_pin(pin)
            #    sense_array[i][j].direction = digitalio.Direction.INPUT
            #    sense_array[i][j].pull = digitalio.Pull.UP
            #elif(i == 4 | i == 5):
            #    sense_array[i][j] = mcp3.get_pin(pin)
            #    sense_array[i][j].direction = digitalio.Direction.INPUT
            #    sense_array[i][j].pull = digitalio.Pull.UP
            #elif(i == 6 | i ==7):
            #    sense_array[i][j] = mcp4.get_pin(pin)
            #    sense_array[i][j].direction = digitalio.Direction.INPUT
            #    sense_array[i][j].pull = digitalio.Pull.UP
            else:
                print("Sensors failed to map")
                return 0

            if(i % 2 == 1 & j == 7):
                pin = 0
            else:
                pin = pin + 1
    print("Sensors successfully mapped")
    return 1


setupSensors()

try:
    while True:
        readSensors()

        
finally:
    sense_array[0][0].direction = digitalio.Direction.OUTPUT
    

    # Receive turn checker from phone

    # If Phone turn
    # Receive move from phone
    # Move piece to position
        # If taking a piece
            # Move magnet to taken piece position
            # Turn magnet on
            # Move piece to outside of the board
            # Turn magnet off
        
        # Move magnet to position
        # Turn magnet on
        # Move magnet to new position
        # Turn magnet off

    # If Board turn

    # Check for a piece pushed down *

    # Store position/piece info *
    #      time.sleep(0.01)
    # Loop until the piece is put back down *
    # Check move legality



