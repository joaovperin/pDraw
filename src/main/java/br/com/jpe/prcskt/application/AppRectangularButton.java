package br.com.jpe.prcskt.application;

import java.util.Optional;

import br.com.jpe.prcskt.infra.AppMouseListener;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.event.MouseEvent;

@Getter
@SuperBuilder
@ToString(callSuper = true)
public class AppRectangularButton extends AppRectangleShape implements AppMouseListener {

    @Builder.Default
    final int height = 40;
    @Builder.Default
    final int width = 100;

    final String text;

    public final Optional<String> getText() {
        return Optional.ofNullable(text);
    }

    @Builder.Default
    private boolean isClicked = false;
    @Builder.Default
    private boolean isHovered = false;

    @Override
    public void handleDraw(PApplet g) {
        g.pushStyle();

        AppColor _textColor = textcolor;
        AppColor _bgColor = backgroundColor;

        if (isClicked) {
            _bgColor = _bgColor.darker();
        } else if (isHovered) {
            _bgColor = _bgColor.lighter();
        }

        g.stroke(g.color(0, 0, 0));
        g.fill(_bgColor.toRgb());
        g.rect(x, y, width, height);

        if (text != null) {

            final int endX = x + width;
            final int endY = y + height;

            final PFont georgia = g.createFont("Georgia", 18);
            g.textFont(georgia);
            g.textSize(20);

            g.stroke(_textColor.toRgb());
            g.fill(_textColor.toRgb());
            g.textAlign(PConstants.CENTER, PConstants.CENTER);

            final var tX = x + ((endX - x) / 2);
            final var tY = y + ((endY - y) / 2);

            g.text(text, tX, tY);
        }

        g.popStyle();
    }

    public void handleMousePressed(PApplet applet, MouseEvent event) {
        isHovered = false;
        isClicked = intersects(event);
    }

    public void handleMouseReleased(PApplet applet, MouseEvent event) {
        final var intersects = intersects(event);
        isClicked = false;
        isHovered = intersects;
        if (intersects) {
            getOnClick().ifPresent(e -> e.onMouse(event));
        }
    }

    public void handleMouseMoved(PApplet applet, MouseEvent event) {
        isHovered = intersects(event);
    }

    private boolean intersects(MouseEvent event) {
        final int endX = x + width;
        final int endY = y + height;

        if (event.getX() >= x && event.getX() <= endX) {
            if (event.getY() >= y && event.getY() <= endY) {
                return true;
            }
        }
        return false;
    }

}
