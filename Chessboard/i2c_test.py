import smbus
import time

bus = smbus.SMBus(1)

DEV = 0x20
IODIR = 0x00
GPIOA = 0x12

bus.write_byte_data(DEV, IODIR, 0x08)

while True:
    MySwitch = bus.read_byte_data(DEV, GPIOA)

    if MySwitch &amp; 0b10000000 == 0b10000000:
        print &quot;Switch was pressed!&quot;
        time.sleep(1)