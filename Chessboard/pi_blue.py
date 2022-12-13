import bluetooth

target_name ="DESKTOP-E3SIV59"
target_address = None

def bluetooth_poll():
    print("Polling nearby devices...")
    nearby_devices = bluetooth.discover_devices()
    for baddr in nearby_devices:
        if target_name == bluetooth.lookup_name( baddr ):
            target_address = baddr
            print("Device "+ target_name + " (" + target_address +") Found!")
        print("Device: " + bluetooth.lookup_name(baddr))
