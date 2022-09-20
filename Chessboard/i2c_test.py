import smbus
import time

bus = smbus.SMBus(1)

DEVADDR = 0x20  # MCP Address
IODIRA = 0x00   # IO direction of port A (1-8)
GPIOA = 0x12    # Data Port A
IOCON = 0x0A    # Config Reg

bus.write_byte_data(DEVADDR, IODIRA, 0x00)  # Set GPA pins to outputs
bus.write_byte_data(DEVADDR, GPIOA, 0x00)   # Set outputs to off

while True:
    bus.write_byte_data(DEVADDR, GPIOA, 0x01)
    time.sleep(0.5)
    bus.write_byte_data(DEVADDR, GPIOA, 0x02)
    time.sleep(0.5)
    bus.write_byte_data(DEVADDR, GPIOA, 0x03)
    time.sleep(0.5)
    bus.write_byte_data(DEVADDR, GPIOA, 0x04)
    time.sleep(0.5)
    bus.write_byte_data(DEVADDR, GPIOA, 0x05)
    time.sleep(0.5)
    bus.write_byte_data(DEVADDR, GPIOA, 0x06)
    time.sleep(0.5)
    bus.write_byte_data(DEVADDR, GPIOA, 0x07)
    time.sleep(0.5)
    bus.write_byte_data(DEVADDR, GPIOA, 0x08)
    time.sleep(0.5)
    bus.write_byte_data(DEVADDR, GPIOA, 0x08)
    time.sleep(0.25)
    bus.write_byte_data(DEVADDR, GPIOA, 0x08)
    time.sleep(0.25)
    bus.write_byte_data(DEVADDR, GPIOA, 0x08)
    time.sleep(0.25)
