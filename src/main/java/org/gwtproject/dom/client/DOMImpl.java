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
import elemental2.dom.*;
import jsinterop.base.Any;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;

class DOMImpl {

  static final DOMImpl impl = GWT.create(DOMImpl.class);

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

  public void eventSetKeyCode(NativeEvent evt, char key) {
    Js.<JsPropertyMap<Any>>uncheckedCast(evt).set("keyCode", Js.asAny(key));
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

  public int getScrollLeft(Element elem) {
    return Js.coerceToInt(Js.<HTMLElement>uncheckedCast(elem).scrollLeft);
  }

  public void setScrollLeft(Element elem, int left) {
    Js.<HTMLElement>uncheckedCast(elem).scrollLeft = left;
  }


}
