import smbus2
import time

bus = smbus2.SMBus(1)

DEVADDR = 0x20  # MCP Address
IODIRA = 0x00   # IO direction register of port A (1-8)
GPIOA = 0x12    # Data Port A
IOCON = 0x0A    # Config Reg

bus.write_byte_data(DEVADDR, IODIRA, 0x01)  # Set GPA1 pin to input, rest as output

while True:
    sensor = bus.read_byte_data(DEVADDR,GPIOA)

    if(sensor != 0x00): #if sensor is pressed
        bus.write_byte_data(DEVADDR, GPIOA, 0x02)   #Light LED
        time.sleep(0.1)
    else:   #else turn off LED
        bus.write_byte_data(DEVADDR, GPIOA, 0x00)
        time.sleep(0.1)

