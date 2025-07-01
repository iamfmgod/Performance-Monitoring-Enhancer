package iamfmgod.performancemonitoringenhanc.hud;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class HudManager {
    private static boolean visible = true;
    private static boolean mouseEnabled = false;
    private static int x = 10;
    private static int y = 10;

    private static float tps = 20f;
    private static int ping = 0;

    private static final int MAX_SAMPLES = 200;
    private static final Deque<Integer> fpsSamples = new ArrayDeque<>(MAX_SAMPLES);
    private static long lastCalcTime = 0L;
    private static int currentFps = 0;
    private static int onePercentLowFps = 0;
    private static int pointZeroZeroOnePercentLowFps = 0;

    // Position & visibility
    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static void setPosition(int newX, int newY) {
        x = newX;
        y = newY;
    }

    public static boolean isVisible() {
        return visible;
    }

    public static void toggleVisibility() {
        visible = !visible;
    }

    // Mouse-drag mode toggle
    public static boolean isMouseEnabled() {
        return mouseEnabled;
    }

    public static void toggleMouseEnabled() {
        mouseEnabled = !mouseEnabled;
    }

    // Server-synced stats
    public static void updateTpsPing(float newTps, int newPing) {
        tps = newTps;
        ping = newPing;
    }

    public static float getTps() {
        return tps;
    }

    public static int getPing() {
        return ping;
    }

    // FPS sampling
    public static void addFpsSample(int fps) {
        currentFps = fps;
        if (fpsSamples.size() >= MAX_SAMPLES) {
            fpsSamples.removeFirst();
        }
        fpsSamples.addLast(fps);

        long now = System.currentTimeMillis();
        if (now - lastCalcTime >= 500L) {
            recalcPercentLows();
            lastCalcTime = now;
        }
    }

    private static void recalcPercentLows() {
        if (fpsSamples.isEmpty()) {
            onePercentLowFps = currentFps;
            pointZeroZeroOnePercentLowFps = currentFps;
            return;
        }

        List<Integer> sorted = new ArrayList<>(fpsSamples);
        Collections.sort(sorted);

        // 1% low
        int idx1 = (int)(sorted.size() * 0.01);
        onePercentLowFps = sorted.get(Math.max(0, idx1 - 1));

        // 0.01% low
        int idx001 = (int)(sorted.size() * 0.0001);
        pointZeroZeroOnePercentLowFps = sorted.get(Math.max(0, idx001 - 1));
    }

    public static int getCurrentFps() {
        return currentFps;
    }

    public static int getOnePercentLowFps() {
        return onePercentLowFps;
    }

    public static int getPointZeroZeroOnePercentLowFps() {
        return pointZeroZeroOnePercentLowFps;
    }
}