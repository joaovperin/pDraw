package br.com.jpe.prcskt.application;

import java.util.Optional;

import processing.core.PApplet;

public class AppGraphicsHolder {

    private AppGraphicsHolder() {
    }

    private static final ThreadLocal<PApplet> applets = new InheritableThreadLocal<>();

    public static final void startGraphics(PApplet applet) {
        Optional.ofNullable(applets.get()).ifPresentOrElse(old -> {
            throw new IllegalStateException(
                    "Applet is already started for current thread! Name: " + old.getClass().getName());
        }, () -> {
            applets.set(applet);
        });
    }

    public static final PApplet getGraphics() {
        return Optional.ofNullable(applets.get())
                .orElseThrow(() -> new IllegalStateException("Current thread does not have a loaded Applet!"));
    }

}
