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

/**
 * Mozilla implementation of StandardBrowser.
 */
class DOMImplMozilla extends DOMImpl {

  @Override
  public int getAbsoluteLeft(Element elem) {
    return getAbsoluteLeftImpl(elem.getOwnerDocument().getViewportElement(),
        elem);
  }

  @Override
  public int getAbsoluteTop(Element elem) {
    return getAbsoluteTopImpl(elem.getOwnerDocument().getViewportElement(),
        elem);
  }

  private native int getAbsoluteLeftImpl(Element viewport, Element elem) /*-{
    // Firefox 3 is actively throwing errors when getBoxObjectFor() is called,
    // so we use getBoundingClientRect() whenever possible (but it's not
    // supported on older versions). If changing this code, make sure to check
    // the museum entry for issue 1932.
    // (x) | 0 is used to coerce the value to an integer
    if (Element.prototype.getBoundingClientRect) {
      return (elem.getBoundingClientRect().left + viewport.scrollLeft) | 0;
    } else {
      // We cannot use DOMImpl here because offsetLeft/Top return erroneous
      // values when overflow is not visible.  We have to difference screenX
      // here due to a change in getBoxObjectFor which causes inconsistencies
      // on whether the calculations are inside or outside of the element's
      // border.
      // If the element is in a scrollable div, getBoxObjectFor(elem) can return
      // a value that varies by 1 pixel.
      var doc = elem.ownerDocument;
      return doc.getBoxObjectFor(elem).screenX -
        doc.getBoxObjectFor(doc.documentElement).screenX;
    }
  }-*/;

  private native int getAbsoluteTopImpl(Element viewport, Element elem) /*-{
    // Firefox 3 is actively throwing errors when getBoxObjectFor() is called,
    // so we use getBoundingClientRect() whenever possible (but it's not
    // supported on older versions). If changing this code, make sure to check
    // the museum entry for issue 1932.
    // (x) | 0 is used to coerce the value to an integer
    if (Element.prototype.getBoundingClientRect) {
      return (elem.getBoundingClientRect().top + viewport.scrollTop) | 0;
    } else {
      // We cannot use DOMImpl here because offsetLeft/Top return erroneous
      // values when overflow is not visible.  We have to difference screenX
      // here due to a change in getBoxObjectFor which causes inconsistencies
      // on whether the calculations are inside or outside of the element's
      // border.
      var doc = elem.ownerDocument;
      return doc.getBoxObjectFor(elem).screenY -
        doc.getBoxObjectFor(doc.documentElement).screenY;
    }
  }-*/;

}

