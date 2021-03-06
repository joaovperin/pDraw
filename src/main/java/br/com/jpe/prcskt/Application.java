package br.com.jpe.prcskt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.swing.SwingUtilities;

import br.com.jpe.prcskt.application.AppCircleShape;
import br.com.jpe.prcskt.application.AppColor;
import br.com.jpe.prcskt.application.AppRectangleShape;
import br.com.jpe.prcskt.application.AppRectangularButton;
import br.com.jpe.prcskt.domain.PdCanvas;
import br.com.jpe.prcskt.domain.PdColorPicker;
import br.com.jpe.prcskt.domain.PdHud;
import br.com.jpe.prcskt.infra.AppMouseListener;
import br.com.jpe.prcskt.infra.AppObject;
import br.com.jpe.prcskt.infra.AppPositionedObject;
import br.com.jpe.prcskt.infra.AppRenderListener;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

/**
 * Runs the application itself
 */
public class Application extends PApplet {

    private final List<AppObject> appObjects = new ArrayList<>();

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

    @Override
    public void settings() {
        size(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    int appFixedListSize;

    PdHud hud;
    PdCanvas canvas;
    PdColorPicker colorPicker;
    AppRectangularButton drawCircleButton;
    AppRectangularButton drawRectangleButton;
    AppRectangularButton drawClearScreenButton;
    AppRectangularButton undoButton;

    Optional<AppPositionedObject> drawingShape = Optional.empty();

    @Override
    public void setup() {
        // Window setup
        SwingUtilities.invokeLater(() -> {
            getSurface().setTitle("pDraw");
        });

        hud = PdHud.builder().width(SCREEN_WIDTH).height(80).build();

        final int colorPickerStart = middleX, colorPickerMaxWid = 300;
        colorPicker = PdColorPicker.builder().x(colorPickerStart).y(10).height(60).maxWidth(colorPickerMaxWid).build();

        canvas = PdCanvas.builder().width(SCREEN_WIDTH).height(SCREEN_HEIGHT - hud.getHeight()).x(0).y(hud.getHeight())
                .onClick(() -> {
                    drawingShape.ifPresent(shape -> drawingShape = Optional.empty());
                }).build();

        drawCircleButton = AppRectangularButton.builder().backgroundColor(AppColor.blue).x(20).y(20).text("Bolinha")
                .onClick(evt -> {
                    drawingShape.ifPresentOrElse(shape -> {
                        drawingShape = Optional.empty();
                        appObjects.remove(shape);
                    }, () -> {
                        final var shape = AppCircleShape.builder().backgroundColor(colorPicker.getSelectColorBottom())
                                .strokeColor(colorPicker.getSelectColorTop()).x(evt.getX()).y(evt.getY()).radius(60)
                                .build();
                        drawingShape = Optional.of(shape);
                        appObjects.add(shape);
                    });
                }).build();

        drawRectangleButton = AppRectangularButton.builder().backgroundColor(AppColor.blue).x(140).y(20)
                .text("Quadrado").onClick(evt -> {
                    drawingShape.ifPresentOrElse(shape -> {
                        drawingShape = Optional.empty();
                        appObjects.remove(shape);
                    }, () -> {
                        final var shape = AppRectangleShape.builder()
                                .backgroundColor(colorPicker.getSelectColorBottom())
                                .strokeColor(colorPicker.getSelectColorTop()).x(evt.getX()).y(evt.getY()).build();
                        drawingShape = Optional.of(shape);
                        appObjects.add(shape);
                    });
                }).build();

        drawClearScreenButton = AppRectangularButton.builder().backgroundColor(AppColor.blue).x(270).y(10).width(80)
                .height(25).text("Limpar").onClick(evt -> {
                    int size;
                    while ((size = appObjects.size()) > appFixedListSize) {
                        appObjects.remove(size - 1);
                    }
                }).build();

        undoButton = AppRectangularButton.builder().backgroundColor(AppColor.blue).x(270).y(45).width(80).height(25)
                .text("Desfazer").onClick(evt -> {
                    final var size = appObjects.size();
                    if (size > appFixedListSize) {
                        appObjects.remove(size - 1);
                    }
                }).build();

        appObjects.addAll(List.of(hud, colorPicker, canvas, drawRectangleButton, drawCircleButton,
                drawClearScreenButton, undoButton));

        appFixedListSize = appObjects.size();
    }

    @Override
    public void draw() {
        background(0);
        stroke(255);

        appObjectsStream().filter(e -> e instanceof AppRenderListener)
                .forEach(e -> ((AppRenderListener) e).handleDraw(this));
    }

    @Override
    public void mousePressed(MouseEvent event) {
        appObjectsParallelStream().filter(e -> e instanceof AppMouseListener)
                .forEach(e -> ((AppMouseListener) e).handleMousePressed(this, event));
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        appObjectsParallelStream().filter(e -> e instanceof AppMouseListener)
                .forEach(e -> ((AppMouseListener) e).handleMouseReleased(this, event));

    }

    @Override
    public void mouseClicked(MouseEvent event) {
        appObjectsParallelStream().filter(e -> e instanceof AppMouseListener)
                .forEach(e -> ((AppMouseListener) e).handleMouseClicked(this, event));
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        appObjectsParallelStream().filter(e -> e instanceof AppMouseListener)
                .forEach(e -> ((AppMouseListener) e).handleMouseMoved(this, event));

        drawingShape.ifPresent(shape -> shape.move(event.getX(), event.getY()));
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        appObjectsParallelStream().filter(e -> e instanceof AppMouseListener)
                .forEach(e -> ((AppMouseListener) e).handleMouseDragged(this, event));
    }

    @Override
    public void keyPressed(KeyEvent event) {
        final var key = event.getKey();
        if (event.isControlDown()) {
            if (key == '+') {
                drawingShape.ifPresent(shape -> shape.grow());
            } else if (key == '-') {
                drawingShape.ifPresent(shape -> shape.shrink());
            }
        }
    }

    private Stream<AppObject> appObjectsStream() {
        return new ArrayList<>(this.appObjects).stream();
    }

    private Stream<AppObject> appObjectsParallelStream() {
        return new ArrayList<>(this.appObjects).parallelStream();
    }

}