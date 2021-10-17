package br.com.jpe.prcskt.infra;

import processing.event.MouseEvent;

@FunctionalInterface
public interface OnMouseCallback {

    public void onMouse(MouseEvent event);

}
