package br.com.jpe.prcskt.infra.abstractions;

import processing.core.PApplet;
import processing.event.MouseEvent;

public interface MouseListener {

    public default void mousePressed(PApplet applet, MouseEvent event) {
    }

    public default void mouseReleased(PApplet applet, MouseEvent event) {
    }

    public default void mouseClicked(PApplet applet, MouseEvent event) {
    }

    public default void mouseMoved(PApplet applet, MouseEvent event) {
    }

    public default void mouseDragged(PApplet applet, MouseEvent event) {
    }

}
