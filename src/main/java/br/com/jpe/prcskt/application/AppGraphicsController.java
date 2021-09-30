package br.com.jpe.prcskt.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.jpe.prcskt.infra.abstractions.Renderable;
import processing.core.PApplet;

public class AppGraphicsController {

    private static final Map<PApplet, AppGraphicsController> _instances = new HashMap<>();

    private final PApplet applet;
    private final List<Renderable> renderables = new ArrayList<>(1024);

    private AppGraphicsController(PApplet applet) {
        this.applet = applet;
    }

    public static final AppGraphicsController forSketch(PApplet applet) {
        return _instances.computeIfAbsent(applet, (key) -> new AppGraphicsController(key));
    }

    public AppGraphicsController register(Renderable listener) {
        this.renderables.add(listener);
        return this;
    }

    public void render() {
        for (final var renderable : renderables) {
            renderable.draw(applet);
        }
    }

}
