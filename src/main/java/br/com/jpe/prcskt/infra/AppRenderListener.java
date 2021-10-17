package br.com.jpe.prcskt.infra;

import processing.core.PApplet;

public interface AppRenderListener extends AppObject {

    public void handleDraw(PApplet g);

}
