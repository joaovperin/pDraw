package br.com.jpe.prcskt.infra;

import br.com.jpe.prcskt.application.constants.Constants;
import br.com.jpe.prcskt.infra.abstractions.MouseListener;
import br.com.jpe.prcskt.infra.abstractions.Renderable;
import lombok.Data;
import lombok.Getter;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.event.MouseEvent;

@Data
@Getter
public class Button implements Renderable, MouseListener {

    private final int x;
    private final int y;

    private final int width;
    private final int height;

    private final String text;

    // cache fields
    private final int endX;
    private final int endY;

    public Button(int x, int y) {
        this(x, y, null);
    }

    public Button(int x, int y, int width, int height) {
        this(x, y, 120, 40, null);
    }

    public Button(int x, int y, String text) {
        this(x, y, 120, 40, text);
    }

    public Button(int x, int y, int width, int height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        // computed fields
        endX = x + width;
        endY = y + height;
    }

    private boolean isClicked = false;
    private boolean isHovered = false;

    public void draw(PApplet g) {
        g.pushStyle();
        int colorToPaint;

        if (isClicked) {
            colorToPaint = Constants.Colors.blue;
        } else if (isHovered) {
            colorToPaint = Constants.Colors.blueDark;
        } else {
            colorToPaint = Constants.Colors.blueLight;
        }

        g.stroke(g.color(0, 0, 0));
        g.fill(colorToPaint);
        g.rect(x, y, width, height);

        if (text != null) {
            PFont font = Constants.Fonts.georgia;
            g.textFont(font);
            g.textSize(20);

            g.stroke(Constants.Colors.white);
            g.fill(Constants.Colors.white);
            g.textAlign(PConstants.CENTER, PConstants.CENTER);

            final var tX = x + ((endX - x) / 2);
            final var tY = y + ((endY - y) / 2);

            g.text(text, tX, tY);
        }

        g.popStyle();
    }

    @Override
    public void mousePressed(PApplet applet, MouseEvent event) {
        isHovered = false;
        isClicked = intersects(event);
    }

    @Override
    public void mouseReleased(PApplet applet, MouseEvent event) {
        isClicked = false;
        isHovered = intersects(event);
    }

    @Override
    public void mouseClicked(PApplet applet, MouseEvent event) {
        // isClicked = intersects(event);
    }

    @Override
    public void mouseMoved(PApplet applet, MouseEvent event) {
        isHovered = intersects(event);
    }

    private boolean intersects(MouseEvent event) {
        if (event.getX() >= x && event.getX() <= endX) {
            if (event.getY() >= y && event.getY() <= endY) {
                return true;
            }
        }
        return false;
    }

}
