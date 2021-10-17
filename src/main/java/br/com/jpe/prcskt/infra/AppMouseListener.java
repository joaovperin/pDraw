package br.com.jpe.prcskt.infra;

import processing.core.PApplet;
import processing.event.MouseEvent;

public interface AppMouseListener extends AppObject {

    public default void handleMousePressed(PApplet applet, MouseEvent event) {
    }

    public default void handleMouseReleased(PApplet applet, MouseEvent event) {
    }

    public default void handleMouseClicked(PApplet applet, MouseEvent event) {
    }

    public default void handleMouseMoved(PApplet applet, MouseEvent event) {
    }

    public default void handleMouseDragged(PApplet applet, MouseEvent event) {
    }

}
