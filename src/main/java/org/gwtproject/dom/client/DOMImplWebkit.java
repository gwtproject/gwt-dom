/*
 * Copyright 2011 Google Inc.
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

import elemental2.dom.HTMLElement;
import elemental2.dom.ViewCSS;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;

import java.util.Objects;

/**
 * WebKit based implementation of {@link com.google.gwt.user.client.impl.DOMImpl}.
 */
class DOMImplWebkit extends DOMImpl {

  @Override
  public int getScrollLeft(Element elem) {
    if (!(elem.hasTagName(BodyElement.TAG) || elem.hasTagName("HTML")) && isRTL(elem)) {
      return super.getScrollLeft(elem)
          - (elem.getScrollWidth() - elem.getClientWidth());
    }
    return super.getScrollLeft(elem);
  }

  @Override
  public void setScrollLeft(Element elem, int left) {
    if (!(elem.hasTagName(BodyElement.TAG) || elem.hasTagName("HTML")) && isRTL(elem)) {
      left += elem.getScrollWidth() - elem.getClientWidth();
    }
    super.setScrollLeft(elem, left);
  }

  private boolean isRTL(Element elem) {
    HTMLElement e = Js.uncheckedCast(elem);
    //TODO when Document has defaultView, clean this up
    //TODO also see about not referencing a class which doesn't exist in the browser (ViewCSS)
    ViewCSS defaultView = Js.uncheckedCast(Js.<JsPropertyMap<?>>uncheckedCast(e.ownerDocument).get("defaultView"));
    return Objects.equals(defaultView.getComputedStyle(e, "").direction, "rtl");
  }

}

