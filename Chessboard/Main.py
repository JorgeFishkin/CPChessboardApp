import wiringpi
from time import sleep

pin_base1 = 65     # 1st pin address
pin_base2 = 81     # 17th
pin_base3 = 97     # 33rd
pin_base4 = 113    # 49th

EXP1 = 0x20         # 1st MCP23017
EXP2 = 0x21         # 2nd
EXP3 = 0x22         # 3rd
EXP4 = 0x23         # 4th
ON = 1
OFF = 0        

def readSensors():
    for i in range(pin_base1, pin_base4 + 16):
        if not wiringpi.digitalRead(i):
            return i
    return 0



wiringpi.wiringPiSetup()
wiringpi.mcp23017Setup(pin_base1, EXP1)
wiringpi.mcp23017Setup(pin_base2, EXP2)

wiringpi.pinMode(pin_base1, 0)

try:
    while True:
        sense = readSensors()
        sense2 = 0
        if sense != 0:
            while sense2 == 0:
                sense2 = readSensors()
            

        sleep(0.05)
finally:
    wiringpi.digitalWrite(pin_base2, OFF)
    wiringpi.pinMode(65)

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



