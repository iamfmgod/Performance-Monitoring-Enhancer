Performance Monitoring Enhancer
A lightweight Forge mod that displays real-time performance metrics (FPS, 1 % low, 0.01 % low, TPS, ping) as an on-screen overlay. Includes hotkeys to toggle visibility, drag the HUD, and configure which stats to show.

Features
- Displays current FPS, 1 % low FPS, 0.01 % low FPS
- Displays server TPS and player ping (ms)
- Updates twice per second for percent-low calculations
- Hotkey (H) to toggle the HUD on/off
- Hotkey (G) to enter “drag mode” (pause game, ungrab mouse, reposition HUD)
- Hotkey (K) to open a settings GUI with checkboxes for each stat
- Auto-resizing background box based on enabled stats
- Config file (hud.cfg) for persistence

Installation
- Drop the compiled JAR into your Forge mods/ folder.
- Launch Minecraft with the matching Forge version (tested on 1.12.2).
- On first run, a hud.cfg file will be created in your config/ directory.

Usage
- H: Toggle the HUD overlay on or off.
- G: Enter/exit HUD drag mode. While in drag mode, the game is paused and you can click-drag the HUD. Press G again to resume.
- K: Open the HUD settings GUI. Tick/untick which metrics to display, then click Done.
Changes are saved immediately to config/hud.cfg.

Configuration
Located at config/hud.cfg. Available options:
# Show current FPS
showFps=true

# Show 1% low FPS
showOnePercentLow=true

# Show 0.01% low FPS
show0001PercentLow=false

# Show server TPS
showTps=true

# Show ping (ms)
showPing=true


Toggle any of these and relaunch (or open Settings GUI with K) to apply.

Commented Directory Structure
<project-root>/
├── build.gradle                   # Gradle build script
├── settings.gradle                # Gradle settings
├── src/
│   ├── main/
│   │   ├── java/                  # Java source files
│   │   │   └── iamfmgod/
│   │   │       └── performancemonitoringenhanc/
│   │   │           ├── core/      # Mod entry point & sided proxies
│   │   │           │   ├── ModMain.java
│   │   │           │   ├── CommonProxy.java
│   │   │           │   └── ClientProxy.java
│   │   │           ├── config/    # Config loader & saver
│   │   │           │   └── HudConfig.java
│   │   │           ├── hud/       # HUD state & overlay rendering
│   │   │           │   ├── HudManager.java
│   │   │           │   └── HudOverlay.java
│   │   │           ├── input/     # Key+mouse handlers (client‐only)
│   │   │           │   ├── KeyHandler.java
│   │   │           │   └── MouseDragHandler.java
│   │   │           ├── gui/       # Drag‐mode & settings screens
│   │   │           │   ├── HudDragScreen.java
│   │   │           │   └── HudSettingsGui.java
│   │   │           ├── network/   # TPS/ping packet & server tick handler
│   │   │           │   ├── NetworkHandler.java
│   │   │           │   ├── PacketTpsPing.java
│   │   │           │   └── ServerTickHandler.java
│   │   │           └── util/      # Rendering utilities
│   │   │               └── RenderUtils.java
│   │   └── resources/             # Non‐code resources
│   │       ├── mcmod.info         # 1.12.2 mod metadata (mcmod.info JSON)
│   │       └── assets/
│   │           └── performancemonitoringenhanc/
│   │               ├── logo.png   # Mod icon
│   │               └── lang/
│   │                   └── en_us.lang  # Localization keys/values
│   └── test/                      # (optional) unit tests
└── config/                        # Runtime config directory
    └── hud.cfg                    # Created/updated by HudConfig at first run



Credits
- Developed by iamfmgod
- Built for Forge 1.12.2 (should be adaptable to other versions)
- Inspired by community demand for lightweight, configurable performance overlay
