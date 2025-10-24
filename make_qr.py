import qrcode

# Data to encode in the QR code
data = "WIFI:S:West Art Guest;T:WPA;P:livingroom;H:false;;"

img = qrcode.make(data)

# Save the image to a file
img.save("my_qrcode.png")

