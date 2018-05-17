/*
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.gwtproject.dom.client;

import com.google.gwt.core.client.GWT;
import org.gwtproject.core.client.JsArray;
import elemental2.dom.*;
import elemental2.dom.CSSProperties.OpacityUnionType;
import jsinterop.base.Any;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;

class DOMImpl {

  static final DOMImpl impl = GWT.create(DOMImpl.class);

  /**
   * Fast helper method to convert small doubles to 32-bit int.
   *
   * <p>Note: you should be aware that this uses JavaScript rounding and thus
   * does NOT provide the same semantics as <code>int b = (int) someDouble;</code>.
   * In particular, if x is outside the range [-2^31,2^31), then toInt32(x) would return a value
   * equivalent to x modulo 2^32, whereas (int) x would evaluate to either MIN_INT or MAX_INT.
   */
  protected static int toInt32(double val) {
    return Js.coerceToInt(val);
  }

  public void buttonClick(ButtonElement button) {
    HTMLButtonElement elt = Js.uncheckedCast(button);
    elt.click();
  }

  public ButtonElement createButtonElement(Document doc, String type) {
    HTMLButtonElement e = (HTMLButtonElement) Js.<HTMLDocument>uncheckedCast(doc).createElement("BUTTON");
    e.type = type;
    return Js.uncheckedCast(e);
  }

  public InputElement createCheckInputElement(Document doc) {
    HTMLInputElement e = (HTMLInputElement) Js.<HTMLDocument>uncheckedCast(doc).createElement("INPUT");
    e.type = "checkbox";
    e.value = "on";
    return Js.uncheckedCast(e);
  }

  public Element createElement(Document doc, String tag) {
    return (Element) (Object) Js.<HTMLDocument>uncheckedCast(doc).createElement(tag);
  }

  public NativeEvent createHtmlEvent(Document doc, String type,
                                            boolean canBubble, boolean cancelable) {
    EventInit details = EventInit.create();
    details.setBubbles(canBubble);
    details.setCancelable(cancelable);
    Event evt = new Event(type, details);
    return (NativeEvent) (Object) evt;
  }

  public InputElement createInputElement(Document doc, String type) {
    HTMLInputElement e = (HTMLInputElement) ((HTMLDocument) (Object) doc).createElement("INPUT");
    e.type = type;
    return Js.uncheckedCast(e);
  }

  public InputElement createInputRadioElement(Document doc, String name) {
    HTMLInputElement elem = (HTMLInputElement) ((HTMLDocument) (Object) doc).createElement("INPUT");
    elem.type = "radio";
    elem.name = name;
    elem.value = "on";
    return Js.uncheckedCast(elem);
  }

  public NativeEvent createKeyCodeEvent(Document document,
                                                 String type, boolean ctrlKey, boolean altKey, boolean shiftKey,
                                                 boolean metaKey, int keyCode) {
    KeyboardEventInit init = KeyboardEventInit.create();
    init.setCtrlKey(ctrlKey);
    init.setAltKey(altKey);
    init.setShiftKey(shiftKey);
    init.setMetaKey(metaKey);
    Js.<JsPropertyMap<Integer>>uncheckedCast(init).set("keyCode", keyCode);

    KeyboardEvent event = new KeyboardEvent(type, init);
    return Js.uncheckedCast(event);
  }

  @Deprecated
  public NativeEvent createKeyEvent(Document doc, String type,
      boolean canBubble, boolean cancelable, boolean ctrlKey, boolean altKey,
      boolean shiftKey, boolean metaKey, int keyCode, int charCode) {
    KeyboardEventInit init = KeyboardEventInit.create();
    init.setBubbles(canBubble);
    init.setCancelable(cancelable);
    init.setCtrlKey(ctrlKey);
    init.setAltKey(altKey);
    init.setShiftKey(shiftKey);
    init.setMetaKey(metaKey);
    Js.<JsPropertyMap<Integer>>uncheckedCast(init).set("keyCode", keyCode);
    Js.<JsPropertyMap<Integer>>uncheckedCast(init).set("charCode", charCode);

    KeyboardEvent event = new KeyboardEvent(type, init);

    return Js.uncheckedCast(event);
  }

  public NativeEvent createKeyPressEvent(Document document,
      boolean ctrlKey, boolean altKey, boolean shiftKey, boolean metaKey,
      int charCode) {
    final KeyboardEventInit init = KeyboardEventInit.create();
    init.setCtrlKey(ctrlKey);
    init.setAltKey(altKey);
    init.setShiftKey(shiftKey);
    init.setMetaKey(metaKey);
    Js.<JsPropertyMap<Integer>>uncheckedCast(init).set("charCode", charCode);

    KeyboardEvent event = new KeyboardEvent("keypress", init);
    return Js.uncheckedCast(event);
  }

  public NativeEvent createMouseEvent(Document doc, String type,
                                             boolean canBubble, boolean cancelable, int detail, int screenX,
                                             int screenY, int clientX, int clientY, boolean ctrlKey, boolean altKey,
                                             boolean shiftKey, boolean metaKey, int button, Element relatedTarget) {
    // Because Event.getButton() returns bitfield values [1, 4, 2] for [left,
    // middle, right], we need to translate them to the standard [0, 1, 2]
    // button constants.
    if (button == 1) {
      button = 0;
    } else if (button == 4) {
      button = 1;
    } else {
      button = 2;
    }

    MouseEventInit init = MouseEventInit.create();
    init.setButton(button);
    init.setBubbles(canBubble);
    init.setCancelable(cancelable);
    init.setDetail(detail);
    init.setScreenX(screenX);
    init.setScreenY(screenY);
    init.setClientX(clientX);
    init.setClientY(clientY);
    init.setCtrlKey(ctrlKey);
    init.setAltKey(altKey);
    init.setShiftKey(shiftKey);
    init.setMetaKey(metaKey);
    init.setRelatedTarget(Js.uncheckedCast(relatedTarget));
    return Js.uncheckedCast(new MouseEvent(type, init));
  }

  public ScriptElement createScriptElement(Document doc, String source) {
    ScriptElement elem = (ScriptElement) createElement(doc, "script");
    elem.setText(source);
    return elem;
  }

  public void cssClearOpacity(Style style) {
    Js.<CSSStyleDeclaration>uncheckedCast(style).opacity = OpacityUnionType.of("");
  }

  public String cssFloatPropertyName() {
    return "cssFloat";
  }

  public void cssSetOpacity(Style style, double value) {
    Js.<CSSStyleDeclaration>uncheckedCast(style).opacity = OpacityUnionType.of(value);
  }

  public void dispatchEvent(Element target, NativeEvent evt) {
    Js.<HTMLElement>uncheckedCast(target).dispatchEvent(Js.uncheckedCast(evt));
  }

  public boolean eventGetAltKey(NativeEvent evt) {
    return Js.isTruthy((Js.<JsPropertyMap<Any>>uncheckedCast(evt).get("altKey")));
  }

  public int eventGetButton(NativeEvent evt) {
      // All modern browsers return 0, 1, and 2 for left, middle, and right,
      // respectively. Because eventGetButton() is expected to return the IE
      // bitfield norms of 1, 4, and 2, we translate them here.
      int button = Js.<JsPropertyMap<Any>>uncheckedCast(evt).get("button").asInt();
      if (button == 1) {
          return 4;
      } else if (button == 2) {
          return 2;
      }
      return 1;
  }

  public int eventGetCharCode(NativeEvent evt) {
    return Js.coerceToInt(Js.<JsPropertyMap<Any>>uncheckedCast(evt).get("charCode"));
  }

  public int eventGetClientX(NativeEvent evt) {
    return toInt32(eventGetSubPixelClientX(evt));
  }

  public int eventGetClientY(NativeEvent evt) {
    return toInt32(eventGetSubPixelClientY(evt));
  }

  public boolean eventGetCtrlKey(NativeEvent evt) {
    return Js.isTruthy((Js.<JsPropertyMap<Any>>uncheckedCast(evt).get("ctrlKey")));
  }

  public EventTarget eventGetCurrentTarget(NativeEvent event) {
    return Js.uncheckedCast(Js.<Event>uncheckedCast(event).currentTarget);
  }

  public final int eventGetKeyCode(NativeEvent evt) {
    return Js.coerceToInt(Js.<JsPropertyMap<Any>>uncheckedCast(evt).get("keyCode"));
  }

  public boolean eventGetMetaKey(NativeEvent evt) {
    return Js.isTruthy((Js.<JsPropertyMap<Any>>uncheckedCast(evt).get("metaKey")));
  }

  public int eventGetMouseWheelVelocityY(NativeEvent evt) {
    WheelEvent wheelEvent = Js.uncheckedCast(evt);
    return toInt32(wheelEvent.deltaY);
  }

  public EventTarget eventGetRelatedTarget(NativeEvent evt) {
    return Js.uncheckedCast(Js.<MouseEvent>uncheckedCast(evt).relatedTarget);
  }

  public double eventGetRotation(NativeEvent evt) {
    return Js.<JsPropertyMap<Any>>uncheckedCast(evt).get("rotation").asDouble();
  }

  public double eventGetScale(NativeEvent evt) {
    return Js.<JsPropertyMap<Any>>uncheckedCast(evt).get("scale").asDouble();
  }

  public int eventGetScreenX(NativeEvent evt) {
    return toInt32(eventGetSubPixelScreenX(evt));
  }

  public int eventGetScreenY(NativeEvent evt) {
    return toInt32(eventGetSubPixelScreenY(evt));
  }

  public boolean eventGetShiftKey(NativeEvent evt) {
    return Js.isTruthy((Js.<JsPropertyMap<Any>>uncheckedCast(evt).get("shiftKey")));
  }

  public EventTarget eventGetTarget(NativeEvent evt) {
    return (EventTarget) Js.<Event>uncheckedCast(evt).target;
  }

  public final String eventGetType(NativeEvent evt) {
    return Js.<Event>uncheckedCast(evt).type;
  }

  public void eventPreventDefault(NativeEvent evt) {
    Js.<Event>uncheckedCast(evt).preventDefault();
  }

  public void eventSetKeyCode(NativeEvent evt, char key) {
    Js.<JsPropertyMap<Any>>uncheckedCast(evt).set("keyCode", Js.asAny(key));
  }

  public void eventStopPropagation(NativeEvent evt) {
    Js.<Event>uncheckedCast(evt).stopPropagation();
  }

  public String eventToString(NativeEvent evt) {
    return Js.<Event>uncheckedCast(evt).toString();
  }

  public int getAbsoluteLeft(Element elem) {
    return toInt32(getSubPixelAbsoluteLeft(elem));
  }

  public int getAbsoluteTop(Element elem) {
    return toInt32(getSubPixelAbsoluteTop(elem));
  }

  public String getAttribute(Element elem, String name) {
    String value = Js.<HTMLElement>uncheckedCast(elem).getAttribute(name);
    return value != null ? value : "";
  }

  public int getBodyOffsetLeft(Document doc) {
    return Js.<HTMLDocument>uncheckedCast(doc).documentElement.clientLeft;
  }

  public int getBodyOffsetTop(Document doc) {
    return Js.<HTMLDocument>uncheckedCast(doc).documentElement.clientTop;
  }

  public JsArray<Touch> getChangedTouches(NativeEvent evt) {
    return Js.uncheckedCast(Js.<TouchEvent>uncheckedCast(evt).changedTouches);
  }

  public Element getFirstChildElement(Element elem) {
    elemental2.dom.Node child = Js.<HTMLElement>uncheckedCast(elem).firstChild;
    while (child != null && child.nodeType != 1)
      child = child.nextSibling;
    return Js.uncheckedCast(child);
  }

  public String getInnerHTML(Element elem) {
    return Js.<HTMLElement>uncheckedCast(elem).innerHTML;
  }

  /*
   * textContent is used over innerText for two reasons:
   * 1 - It is consistent across browsers. textContent
   *     does not convert <br>'s to new lines.
   * 2 - textContent is faster on retreival because WebKit
   *     does not recalculate styles as it does for innerText.
   */
  public String getInnerText(Element elem) {
      return Js.<HTMLElement>uncheckedCast(elem).textContent;
  }

  public Element getNextSiblingElement(Element elem) {
    elemental2.dom.Node sib = Js.<HTMLElement>uncheckedCast(elem).nextSibling;
    while (sib != null && sib.nodeType != 1) {
      sib = sib.nextSibling;
    }
    return Js.uncheckedCast(sib);
  }

  public int getNodeType(Node node) {

    try {
      return Js.<HTMLElement>uncheckedCast(node).nodeType;
    } catch (Exception e) {
      // Workaround for firefox bug:
      //
      // Give up on 'Permission denied to get property HTMLDivElement.nodeType'
      // '0' is not a valid node type, which is appropriate in this case, since
      // the node in question is completely inaccessible.
      //
      // See https://bugzilla.mozilla.org/show_bug.cgi?id=208427
      // and http://code.google.com/p/google-web-toolkit/issues/detail?id=1909
      return 0;
    }
  }

  /**
   * Returns a numeric style property (such as zIndex) that may need to be
   * coerced to a string.
   */
  public String getNumericStyleProperty(Style style, String name) {
    return getStyleProperty(style, name);
  }

  public Element getParentElement(Node node) {
    elemental2.dom.Node parent = Js.<elemental2.dom.Node>uncheckedCast(node).parentNode;
    if (parent == null || parent.nodeType != 1) {
      parent = null;
    }
    return Js.uncheckedCast(parent);
  }

  public Element getPreviousSiblingElement(Element elem) {
    elemental2.dom.Node sib = Js.<HTMLElement>uncheckedCast(elem).previousSibling;
    while (sib != null && sib.nodeType != 1)
      sib = sib.previousSibling;
    return Js.uncheckedCast(sib);
  }

  public int getScrollLeft(Document doc) {
    return ensureDocumentScrollingElement(doc).getScrollLeft();
  }

  public int getScrollLeft(Element elem) {
    return toInt32(getSubPixelScrollLeft(elem));
  }

  public int getScrollTop(Document doc) {
    return ensureDocumentScrollingElement(doc).getScrollTop();
  }

  public String getStyleProperty(Style style, String name) {
    return Js.<JsPropertyMap<String>>uncheckedCast((style)).get(name);
  }

  public int getTabIndex(Element elem) {
    return Js.<HTMLElement>uncheckedCast(elem).tabIndex;
  }

  public String getTagName(Element elem) {
    return Js.<HTMLElement>uncheckedCast(elem).tagName;
  }

  public JsArray<Touch> getTargetTouches(NativeEvent evt) {
    return Js.uncheckedCast(Js.<TouchEvent>uncheckedCast(evt).targetTouches);
  }

  public JsArray<Touch> getTouches(NativeEvent evt) {
    return Js.uncheckedCast(Js.<TouchEvent>uncheckedCast(evt).touches);
  }

  public boolean hasAttribute(Element elem, String name) {
    return Js.<HTMLElement>uncheckedCast(elem).hasAttribute(name);
  }

  public boolean isOrHasChild(Node parent, Node child) {
    return Js.<HTMLElement>uncheckedCast(parent).contains(Js.<HTMLElement>uncheckedCast(child));
  }

  public void scrollIntoView(Element elem) {
    HTMLElement e = Js.uncheckedCast(elem);
    double left = e.offsetLeft, top = e.offsetTop;
    double width = e.offsetWidth, height = e.offsetHeight;

    if (e.parentNode != e.offsetParent && e.parentNode.nodeType == 1) {
      left -= Js.<HTMLElement>uncheckedCast(e.parentNode).offsetLeft;
      top -= Js.<HTMLElement>uncheckedCast(e.parentNode).offsetTop;
    }

    elemental2.dom.Node cur = e.parentNode;
    while (cur != null && (cur.nodeType == 1)) {
      HTMLElement curEl = Js.uncheckedCast(cur);
      if (left < curEl.scrollLeft) {
        curEl.scrollLeft = left;
      }
      if (left + width > curEl.scrollLeft + curEl.clientWidth) {
        curEl.scrollLeft = (left + width) - curEl.clientWidth;
      }
      if (top < curEl.scrollTop) {
        curEl.scrollTop = top;
      }
      if (top + height > curEl.scrollTop + curEl.clientHeight) {
        curEl.scrollTop = (top + height) - curEl.clientHeight;
      }

      double offsetLeft = curEl.offsetLeft, offsetTop = curEl.offsetTop;
      if (curEl.parentNode != curEl.offsetParent && curEl.parentNode.nodeType == 1) {
        offsetLeft -= Js.<HTMLElement>uncheckedCast(curEl.parentNode).offsetLeft;
        offsetTop -= Js.<HTMLElement>uncheckedCast(curEl.parentNode).offsetTop;
      }

      left += offsetLeft - curEl.scrollLeft;
      top += offsetTop - curEl.scrollTop;
      cur = curEl.parentNode;
    }
  }

  public void selectAdd(SelectElement select, OptionElement option,
      OptionElement before) {
    Js.<HTMLSelectElement>uncheckedCast(select).add(Js.uncheckedCast(option), Js.uncheckedCast(before));
  }

  public void selectClear(SelectElement select) {
    Js.<HTMLSelectElement>uncheckedCast(select).options.length = 0;
  }

  public int selectGetLength(SelectElement select) {
    return Js.<HTMLSelectElement>uncheckedCast(select).options.length;
  }

  public NodeList<OptionElement> selectGetOptions(SelectElement select) {
    return Js.uncheckedCast(Js.<HTMLSelectElement>uncheckedCast(select).options);
  }

  public void selectRemoveOption(SelectElement select, int index) {
    Js.<HTMLSelectElement>uncheckedCast(select).remove(index);
  }

  public void setDraggable(Element elem, String draggable) {
    //TODO this is at least less wrong than the current implementation...
    Js.<HTMLElement>uncheckedCast(elem).draggable = Element.DRAGGABLE_TRUE.equalsIgnoreCase(draggable);
  }

  /*
   * See getInnerText for why textContent is used instead of innerText.
   */
  public void setInnerText(Element elem, String text) {
    Js.<HTMLElement>uncheckedCast(elem).textContent = text != null ? text : "";
  }

  public void setScrollLeft(Document doc, int left) {
    ensureDocumentScrollingElement(doc).setScrollLeft(left);
  }

  public void setScrollLeft(Element elem, int left) {
    Js.<HTMLElement>uncheckedCast(elem).scrollLeft = left;
  }

  public void setScrollTop(Document doc, int top) {
    ensureDocumentScrollingElement(doc).setScrollTop(top);
  }

  public String toString(Element elem) {
    //TODO HTMLElement is missing outerHTML
    return Js.<JsPropertyMap<String>>uncheckedCast(elem).get("outerHTML");
  }

  private Element ensureDocumentScrollingElement(Document document) {
    // In some case (e.g SVG document and old Webkit browsers), getDocumentScrollingElement can
    // return null. In this case, default to documentElement.
    Element scrollingElement = getDocumentScrollingElement(document);
    return scrollingElement != null ? scrollingElement : document.getDocumentElement();
  }

  private Element getDocumentScrollingElement(Document doc) {
    // Uses http://dev.w3.org/csswg/cssom-view/#dom-document-scrolling element to
    // avoid trying to guess about browser behavior.
    if (getNativeDocumentScrollingElement(doc) != null) {
      return getNativeDocumentScrollingElement(doc);
    }

    return getLegacyDocumentScrollingElement(doc);
  }

  Element getLegacyDocumentScrollingElement(Document doc) {
    return doc.getViewportElement();
  }

  final Element getNativeDocumentScrollingElement(Document doc) {
      return Js.uncheckedCast(Js.<HTMLDocument>uncheckedCast(doc).scrollingElement);
  }

  public int touchGetClientX(Touch touch) {
    return toInt32(touchGetSubPixelClientX(touch));
  }

  public int touchGetClientY(Touch touch) {
    return toInt32(touchGetSubPixelClientY(touch));
  }

  public int touchGetIdentifier(Touch touch) {
    return Js.<elemental2.dom.Touch>uncheckedCast(touch).identifier;
  }

  public int touchGetPageX(Touch touch) {
    return toInt32(touchGetSubPixelPageX(touch));
  }

  public int touchGetPageY(Touch touch) {
    return toInt32(touchGetSubPixelPageY(touch));
  }

  public int touchGetScreenX(Touch touch) {
    return toInt32(touchGetSubPixelScreenX(touch));
  }

  public int touchGetScreenY(Touch touch) {
    return toInt32(touchGetSubPixelScreenY(touch));
  }

  public EventTarget touchGetTarget(Touch touch) {
    return Js.uncheckedCast(Js.<elemental2.dom.Touch>uncheckedCast(touch).target);
  }

  private double getSubPixelAbsoluteLeft(Element elem) {
    HTMLElement e = Js.uncheckedCast(elem);
    return e.getBoundingClientRect().left + getScrollLeft(elem.getOwnerDocument());
  }

  private double getSubPixelAbsoluteTop(Element elem) {
    HTMLElement e = Js.uncheckedCast(elem);
    return e.getBoundingClientRect().top + getScrollTop(elem.getOwnerDocument());
  }

  private double eventGetSubPixelScreenX(NativeEvent evt) {
    return Js.<MouseEvent>uncheckedCast(evt).screenX;
  }

  private double eventGetSubPixelScreenY(NativeEvent evt) {
    return Js.<MouseEvent>uncheckedCast(evt).screenY;
  }

  private double getSubPixelScrollLeft(Element elem) {
    return Js.<HTMLElement>uncheckedCast(elem).scrollLeft;
  }

  private double touchGetSubPixelClientX(Touch touch) {
    return Js.<elemental2.dom.Touch>uncheckedCast(touch).clientX;
  }

  private double touchGetSubPixelClientY(Touch touch) {
    return Js.<elemental2.dom.Touch>uncheckedCast(touch).clientY;
  }

  private double touchGetSubPixelPageX(Touch touch) {
    return Js.<elemental2.dom.Touch>uncheckedCast(touch).pageX;
  }

  private double touchGetSubPixelPageY(Touch touch) {
    return Js.<elemental2.dom.Touch>uncheckedCast(touch).pageY;
  }

  private double touchGetSubPixelScreenX(Touch touch) {
    return Js.<elemental2.dom.Touch>uncheckedCast(touch).screenX;
  }

  private double touchGetSubPixelScreenY(Touch touch) {
    return Js.<elemental2.dom.Touch>uncheckedCast(touch).screenY;
  }

  private double eventGetSubPixelClientX(NativeEvent evt) {
    return Js.<MouseEvent>uncheckedCast(evt).clientX;
  }

  private double eventGetSubPixelClientY(NativeEvent evt) {
    return Js.<MouseEvent>uncheckedCast(evt).clientY;
  }
}
