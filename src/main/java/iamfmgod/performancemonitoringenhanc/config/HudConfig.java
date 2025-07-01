package iamfmgod.performancemonitoringenhanc.config;

import net.minecraftforge.common.config.Configuration;
import java.io.File;

public class HudConfig {
    private static Configuration config;

    public static boolean SHOW_FPS               = true;
    public static boolean SHOW_ONE_PERCENT_LOW   = true;
    public static boolean SHOW_ZERO_ZERO_ONE_LOW = false;
    public static boolean SHOW_TPS               = true;
    public static boolean SHOW_PING              = true;

    public static void init(File configDir) {
        if (config == null) {
            config = new Configuration(new File(configDir, "hud.cfg"));
        }
        sync(); // load defaults or existing values
    }

    public static void sync() {
        config.load();
        SHOW_FPS               = config.getBoolean("showFps",              Configuration.CATEGORY_GENERAL, SHOW_FPS,               "Show current FPS");
        SHOW_ONE_PERCENT_LOW   = config.getBoolean("showOnePercentLow",    Configuration.CATEGORY_GENERAL, SHOW_ONE_PERCENT_LOW,   "Show 1% low FPS");
        SHOW_ZERO_ZERO_ONE_LOW = config.getBoolean("show0001PercentLow",   Configuration.CATEGORY_GENERAL, SHOW_ZERO_ZERO_ONE_LOW, "Show 0.01% low FPS");
        SHOW_TPS               = config.getBoolean("showTps",              Configuration.CATEGORY_GENERAL, SHOW_TPS,               "Show server TPS");
        SHOW_PING              = config.getBoolean("showPing",             Configuration.CATEGORY_GENERAL, SHOW_PING,              "Show ping (ms)");

        if (config.hasChanged()) {
            config.save();
        }
    }

    public static void save() {
        // write in-memory flags into the config and save
        config.get(Configuration.CATEGORY_GENERAL, "showFps",              SHOW_FPS).set(SHOW_FPS);
        config.get(Configuration.CATEGORY_GENERAL, "showOnePercentLow",    SHOW_ONE_PERCENT_LOW).set(SHOW_ONE_PERCENT_LOW);
        config.get(Configuration.CATEGORY_GENERAL, "show0001PercentLow",   SHOW_ZERO_ZERO_ONE_LOW).set(SHOW_ZERO_ZERO_ONE_LOW);
        config.get(Configuration.CATEGORY_GENERAL, "showTps",              SHOW_TPS).set(SHOW_TPS);
        config.get(Configuration.CATEGORY_GENERAL, "showPing",             SHOW_PING).set(SHOW_PING);

        config.save();
    }
}