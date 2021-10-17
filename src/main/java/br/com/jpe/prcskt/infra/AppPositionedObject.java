package br.com.jpe.prcskt.infra;

public interface AppPositionedObject extends AppObject {

    public void move(int x, int y);

    public void grow();

    public void shrink();

}
