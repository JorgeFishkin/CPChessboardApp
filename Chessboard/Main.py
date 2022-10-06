import smbus2
import time

bus = smbus2.SMBus(1)

# Set addresses/data for each of the four I2C expanders

DEVADDR1 = 0x20  # MCP Address
IODIRA1 = 0x00   # IO direction register of port A (1-8)
GPIOA1 = 0x12    # Data Port A
IOCON1 = 0x0A    # Config Reg

# Connect to phone via bluetooth



bus.write_byte_data(DEVADDR1, IODIRA1, 0x01)  # Set GPA1 pin to input, rest as outputimport smbus2
import time

bus = smbus2.SMBus(1)

# Set addresses/data for each of the four I2C expanders

DEVADDR1 = 0x20  # MCP Address
IODIRA1 = 0x00   # IO direction register of port A (1-8)
GPIOA1 = 0x12    # Data Port A
IOCON1 = 0x0A    # Config Reg

bus.write_byte_data(DEVADDR1, IODIRA1, 0x01)  # Set GPA1 pin to input, rest as output

while True:
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

    # Check for a piece pushed down
    sensor = bus.read_byte_data(DEVADDR1,GPIOA1)

    if(sensor != 0x00): # if sensor is pressed
                        # Store position/piece info
        time.sleep(0.01)
        # Loop until the piece is put back down
    # Check move legality



