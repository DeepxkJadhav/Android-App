TapTag

Tag the Moment. Tap the World.

TapTag is a lightweight social tagging app that allows users to leave short messages ("tags") attached to physical locations using GPS, NFC (optional), or QR code. Tags can be anonymous or identified, temporary or permanent.


---

Features

Leave short text-based tags bound to your current location

Scan or generate QR codes to share tag content

View your tag history

Basic gamification potential (achievements, leaderboards)



---

Screens

1. Tag Creator: Input field to drop a tag with location data


2. Tag List: View tags you've dropped previously


3. QR Generator: Converts your tag to a scannable QR code




---

Built With

React Native (Expo)

Expo Location

React Native QRCode SVG



---

Getting Started

Prerequisites

Node.js

Expo CLI

Android/iOS emulator or physical device


Installation

npm install -g expo-cli
expo init TapTagApp
# Choose blank template (JavaScript)
cd TapTagApp
# Replace App.js content with the TapTag code
npm install react-native-qrcode-svg expo-location
expo start


---

TODO

Add NFC tag support

Geo-lock tags with radius

Anonymous mode toggle

Authentication & cloud sync



---

License

This project is licensed under the MIT License — see the LICENSE file for details.


---

Author :- Deepak Jadhav
