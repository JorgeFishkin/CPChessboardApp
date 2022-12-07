import bluetooth

nearby_devices = bluetooth.discover_devices()

for baddr in nearby_devices:
    print(bluetooth.lookup_name(baddr))
