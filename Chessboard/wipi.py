import wiringpi
from time import sleep
pin_base1 = 65
pin_base2 = 81
pin_base3 = 97
pin_base4 = 113

EXP1 = 0x20
EXP2 = 0x21
EXP3 = 0x22
EXP4 = 0x23
ON = 1
OFF = 0

wiringpi.wiringPiSetup()
wiringpi.mcp23017Setup(pin_base1, EXP1)
wiringpi.mcp23017Setup(pin_base2, EXP2)

wiringpi.pinMode(pin_base1, 0)

try:
    while True:
        if not wiringpi.digitalRead(pin_base1):
            wiringpi.digitalWrite(pin_base2, ON)
        elif not wiringpi.digitalRead(pin_base1+1):
            wiringpi.digitalWrite(pin_base2, OFF)
        sleep(0.05)
finally:
    wiringpi.digitalWrite(pin_base2, OFF)
    wiringpi.pinMode(65)