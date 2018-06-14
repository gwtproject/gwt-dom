package org.gwtproject.canvas.dom.client;

import org.gwtproject.dom.client.CanvasElement;
import org.gwtproject.dom.client.Document;
import org.gwtproject.dom.client.Element;

/**
 * Simple "widget"-like class allowing us to reuse the old gwt2 test cases with minimal changes.
 */
public class Canvas {
    public static boolean isSupported() {
        return true;
    }

    public static Canvas createIfSupported() {
        return new Canvas();
    }

    private CanvasElement element = Document.get().createCanvasElement();

    public Element getElement() {
        return element;
    }

    public CanvasElement getCanvasElement() {
        return element;
    }

    public void setHeight(String s) {
        getElement().getStyle().setProperty("height", s);
    }

    public void setWidth(String s) {
        getElement().getStyle().setProperty("width", s);
    }

    public void setCoordinateSpaceHeight(int i) {
        getCanvasElement().setHeight(i);
    }

    public void setCoordinateSpaceWidth(int i) {
        getCanvasElement().setWidth(i);
    }

    public int getOffsetHeight() {
        return getElement().getPropertyInt("offsetHeight");
    }

    public int getOffsetWidth() {
        return getElement().getPropertyInt("offsetWidth");
    }

    public String toDataUrl() {
        return getCanvasElement().toDataUrl();
    }

    public String toDataUrl(String type) {
        return getCanvasElement().toDataUrl(type);
    }

    public Context2d getContext2d() {
        return getCanvasElement().getContext2d();
    }

    public int getCoordinateSpaceHeight() {
        return getCanvasElement().getHeight();
    }

    public int getCoordinateSpaceWidth() {
        return getCanvasElement().getWidth();
    }


}
