package br.com.jpe.prcskt.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.jpe.prcskt.infra.abstractions.MouseListener;
import processing.core.PApplet;
import processing.event.MouseEvent;

public class AppMouseController {

    private static final Map<PApplet, AppMouseController> _instances = new HashMap<>();

    private final PApplet applet;
    private final List<MouseListener> listeners = new ArrayList<>(1024);

    private AppMouseController(PApplet applet) {
        this.applet = applet;
    }

    public static final AppMouseController forSketch(PApplet applet) {
        return _instances.computeIfAbsent(applet, (key) -> new AppMouseController(key));
    }

    public AppMouseController register(MouseListener listener) {
        this.listeners.add(listener);
        return this;
    }

    public void mousePressed(MouseEvent event) {
        for (final var listener : listeners) {
            listener.mousePressed(applet, event);
        }
    }

    public void mouseReleased(MouseEvent event) {
        for (final var listener : listeners) {
            listener.mouseReleased(applet, event);
        }
    }

    public void mouseClicked(MouseEvent event) {
        for (final var listener : listeners) {
            listener.mouseClicked(applet, event);
        }
    }

    public void mouseMoved(MouseEvent event) {
        for (final var listener : listeners) {
            listener.mouseMoved(applet, event);
        }
    }

    public void mouseDragged(MouseEvent event) {
        for (final var listener : listeners) {
            listener.mouseDragged(applet, event);
        }
    }

}
