package br.com.jpe.prcskt;

import br.com.jpe.prcskt.application.AppGraphicsController;
import br.com.jpe.prcskt.application.AppGraphicsHolder;
import br.com.jpe.prcskt.application.AppMouseController;
import br.com.jpe.prcskt.infra.Button;
import processing.core.PApplet;
import processing.event.MouseEvent;

/**
 * Runs the application itself
 */
public class Application extends PApplet {

    public static void main(final String[] args) {
        PApplet.main(Application.class, args);
    }

    public static final int SCREEN_HEIGHT = 1024;
    public static final int SCREEN_WIDTH = 768;

    public static final int middleX;
    public static final int middleY;

    static {
        middleX = SCREEN_WIDTH / 2;
        middleY = SCREEN_HEIGHT / 2;
    }

    AppMouseController appMouseController;
    AppGraphicsController appGraphicsController;

    @Override
    public void settings() {
        size(SCREEN_WIDTH, SCREEN_HEIGHT);

        AppGraphicsHolder.startGraphics(this);
        appMouseController = AppMouseController.forSketch(this);
        appGraphicsController = AppGraphicsController.forSketch(this);
    }

    @Override
    public void setup() {
        stroke(255);

        final var button = new Button(20, 20, "haha");
        final var playbutton = new Button(160, 20, "Play");

        appMouseController.register(button).register(playbutton);
        appGraphicsController.register(button).register(playbutton);
    }

    // static int SHAPE_CIRCLE = 1;

    final int WHITE = color(255, 255, 255);

    @Override
    public void draw() {
        // Draw background
        background(0);
        // BEGIN-HUD
        // stroke(color(100, 200, 200));
        // fill(color(100, 200, 200));

        // rect(0, 0, SCREEN_WIDTH, 40);
        // line(x0, y0, x1, y1);
        // line(x1, y1, x2, y2);
        // line(x2, y2, x3, y3);
        // line(x3, y3, x0, y0);

        appGraphicsController.render();

        // END-HUD
        stroke(WHITE);
        fill(WHITE);

        final int circleRadius = 125;

        ellipse(middleX, middleY, circleRadius, circleRadius);
        // // square
        // line(x0, y0, x1, y1);
        // line(x1, y1, x2, y2);
        // line(x2, y2, x3, y3);
        // line(x3, y3, x0, y0);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        appMouseController.mousePressed(event);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        appMouseController.mouseReleased(event);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        appMouseController.mouseClicked(event);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        appMouseController.mouseMoved(event);
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        appMouseController.mouseDragged(event);
    }

}