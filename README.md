# WhatsApp Number Checker 📱

<div align="center">

  ![Android](https://img.shields.io/badge/Platform-Android-brightgreen)
  ![Kotlin](https://img.shields.io/badge/Language-Kotlin-blue)
  ![MinSDK](https://img.shields.io/badge/MinSDK-24-orange)
  ![TargetSDK](https://img.shields.io/badge/TargetSDK-34-orange)

  An elegant Android application to check if a phone number exists on WhatsApp

</div>

## 📖 About

WhatsApp Number Checker is a simple yet powerful Android application that allows users to verify if a phone number is registered on WhatsApp. The app features a clean, modern UI with Material Design 3 components and provides a seamless user experience.

## ✨ Features

- 🌍 **Country Code Selection** - Browse through 70+ countries with flag emojis
- 📞 **Phone Number Validation** - Smart validation for 10-15 digit phone numbers
- 🎨 **Modern UI** - Clean, professional design with WhatsApp-inspired colors
- 🚀 **Splash Screen** - Beautiful animated splash screen on app launch
- 🔄 **WhatsApp Integration** - Direct integration using wa.me API
- ⚡ **Instant Verification** - Quick check if number exists on WhatsApp
- 🔙 **Double Back to Exit** - Confirmation dialog before closing the app
- 📱 **Responsive Design** - Works on all screen sizes
- 🎯 **Material Design 3** - Latest Material Design components

## 📸 Screenshots

### App Flow
1. **Splash Screen** - Shows app logo with loading indicator
2. **Main Screen** - Country code dropdown + phone number input
3. **Validation** - Real-time input validation
4. **WhatsApp Redirect** - Opens WhatsApp or browser if number exists

## 🛠️ Technical Stack

- **Language:** Kotlin
- **Minimum SDK:** 24 (Android 7.0)
- **Target SDK:** 34 (Android 14)
- **Architecture:** Single Activity with ViewBinding
- **UI Components:** Material Design 3
- **Build System:** Gradle 8.0
- **IDE:** Android Studio

## 📋 Prerequisites

Before building the project, ensure you have:

- **Android Studio** (Latest version recommended - Hedgehog or newer)
- **JDK 17** or higher
- **Android SDK** with API level 34
- **Gradle 8.0** or higher (included in wrapper)

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/govindndivekar/UnknownNumberWhatsApp.git
cd UnknownNumberWhatsApp
```

### 2. Open in Android Studio

1. Launch Android Studio
2. Click on **File > Open**
3. Navigate to the cloned directory
4. Select the project folder and click **OK**
5. Wait for Gradle sync to complete

### 3. Configure Android SDK

1. Open **File > Project Structure > SDK Location**
2. Set your Android SDK path
3. Ensure SDK Platform API 34 is installed
4. Go to **Tools > SDK Manager** and install required components:
   - Android SDK Platform 34
   - Android SDK Build-Tools
   - Android Emulator (if needed)

### 4. Build the Project

#### Using Android Studio:
1. Click **Build > Make Project** or press `Ctrl+F9` (Windows/Linux) or `Cmd+F9` (Mac)
2. Wait for the build to complete

#### Using Command Line:
```bash
# Make gradlew executable (Linux/Mac only)
chmod +x gradlew

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease
```

The APK will be generated at:
- Debug: `app/build/outputs/apk/debug/app-debug.apk`
- Release: `app/build/outputs/apk/release/app-release-unsigned.apk`

### 5. Run the App

#### On Emulator:
1. Create an AVD (Android Virtual Device) from **Tools > Device Manager**
2. Start the emulator
3. Click **Run > Run 'app'** or press `Shift+F10`

#### On Physical Device:
1. Enable **Developer Options** on your Android device
2. Enable **USB Debugging**
3. Connect device via USB
4. Click **Run > Run 'app'** and select your device

## 📱 How to Use

1. **Launch the App** - Wait for splash screen (2 seconds)
2. **Select Country Code** - Tap the country dropdown and select your country
3. **Enter Phone Number** - Type the 10-15 digit phone number (without country code)
4. **Click "Check Number"** - App validates and checks WhatsApp
5. **Result**:
   - ✅ If number exists: Opens WhatsApp (or browser if WhatsApp not installed)
   - ❌ If number doesn't exist: Shows error message

## 🎨 Color Scheme

The app uses WhatsApp-inspired professional colors:

- **Primary:** `#128C7E` (Teal Green)
- **Primary Dark:** `#075E54` (Dark Teal)
- **Accent:** `#25D366` (WhatsApp Green)
- **Error:** `#D32F2F` (Red)
- **Text Primary:** `#212121` (Dark Gray)
- **Background:** `#FFFFFF` (White)

## 📂 Project Structure

```
UnknownNumberWhatsApp/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/whatsappchecker/
│   │       │   ├── SplashActivity.kt      # Splash screen
│   │       │   ├── MainActivity.kt        # Main app logic
│   │       │   └── CountryCode.kt         # Country data model
│   │       ├── res/
│   │       │   ├── layout/
│   │       │   │   ├── activity_splash.xml
│   │       │   │   └── activity_main.xml
│   │       │   ├── drawable/              # Vector assets
│   │       │   ├── values/
│   │       │   │   ├── colors.xml         # Color definitions
│   │       │   │   ├── strings.xml        # String resources
│   │       │   │   └── themes.xml         # App themes
│   │       │   └── mipmap/                # App icons
│   │       └── AndroidManifest.xml
│   └── build.gradle
├── gradle/
│   └── wrapper/
├── build.gradle
├── settings.gradle
└── README.md
```

## 🔧 Key Components

### SplashActivity
- Displays app logo and branding
- 2-second delay before navigating to MainActivity
- Clean, professional splash screen design

### MainActivity
- Country code dropdown with 70+ countries
- Phone number input with validation
- WhatsApp integration via wa.me API
- Double back press to exit with confirmation dialog
- Material Design 3 components

### CountryCode Data Model
- Contains 70+ countries with codes and flags
- Sorted alphabetically for easy browsing
- Default selection: India (+91)

## 🌐 WhatsApp Integration

The app uses WhatsApp's official `wa.me` API:

```kotlin
val whatsappUrl = "https://wa.me/$countryCode$phoneNumber"
```

**How it works:**
1. User enters phone number
2. App constructs wa.me URL
3. Opens URL via Intent
4. WhatsApp (or browser) opens
5. If number exists: Shows chat screen
6. If number doesn't exist: Shows error

## ⚙️ Configuration

### Change Default Country
Edit `MainActivity.kt:56`:
```kotlin
val defaultCountry = countries.find { it.code == "+91" } // Change +91 to your code
```

### Modify Splash Duration
Edit `SplashActivity.kt:13`:
```kotlin
private val splashTimeOut: Long = 2000 // Change to desired milliseconds
```

### Customize Colors
Edit `app/src/main/res/values/colors.xml`

## 🐛 Troubleshooting

### Gradle Sync Failed
```bash
./gradlew clean
./gradlew build --refresh-dependencies
```

### SDK Not Found
1. Open `local.properties`
2. Add: `sdk.dir=/path/to/your/android/sdk`

### ViewBinding Not Found
Ensure ViewBinding is enabled in `app/build.gradle`:
```gradle
buildFeatures {
    viewBinding true
}
```

### WhatsApp Not Opening
- Check internet connection
- Ensure WhatsApp is installed
- Verify phone number format

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Developer

**Govind Nandkishor Divekar**

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📧 Support

For issues and questions, please open an issue on GitHub.

## 🙏 Acknowledgments

- Material Design 3 by Google
- WhatsApp wa.me API
- Android Jetpack components
- Kotlin programming language

---

<div align="center">
  Made with ❤️ for Android
</div>
