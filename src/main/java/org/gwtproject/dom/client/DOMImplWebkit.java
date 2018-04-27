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

import elemental2.dom.ClientRect;

/**
 * WebKit based implementation of {@link com.google.gwt.user.client.impl.DOMImpl}.
 */
class DOMImplWebkit extends DOMImpl {

  private static native double getAbsoluteLeftUsingOffsets(Element elem) /*-{
    // Unattached elements and elements (or their ancestors) with style
    // 'display: none' have no offsetLeft.
    if (elem.offsetLeft == null) {
      return 0;
    }

    var left = 0;
    var doc = elem.ownerDocument;
    var curr = elem.parentNode;
    if (curr) {
      // This intentionally excludes body which has a null offsetParent.
      while (curr.offsetParent) {
        left -= curr.scrollLeft;

        // In RTL mode, offsetLeft is relative to the left edge of the
        // scrollable area when scrolled all the way to the right, so we need
        // to add back that difference.
        if (doc.defaultView.getComputedStyle(curr, '').getPropertyValue('direction') == 'rtl') {
          left += (curr.scrollWidth - curr.clientWidth);
        }

        curr = curr.parentNode;
      }
    }

    while (elem) {
      left += elem.offsetLeft;

      if (doc.defaultView.getComputedStyle(elem, '')['position'] == 'fixed') {
        left += doc.body.scrollLeft;
        return left;
      }

      // Safari 3 does not include borders with offsetLeft, so we need to add
      // the borders of the parent manually.
      var parent = elem.offsetParent;
      if (parent && $wnd.devicePixelRatio) {
        left += parseInt(doc.defaultView.getComputedStyle(parent, '').getPropertyValue('border-left-width'));
      }

      // Safari bug: a top-level absolutely positioned element includes the
      // body's offset position already.
      if (parent && (parent.tagName == 'BODY') &&
          (elem.style.position == 'absolute')) {
        break;
      }

      elem = parent;
    }
    return left;
  }-*/;

  private static native double getAbsoluteTopUsingOffsets(Element elem) /*-{
    // Unattached elements and elements (or their ancestors) with style
    // 'display: none' have no offsetTop.
    if (elem.offsetTop == null) {
      return 0;
    }

    var top = 0;
    var doc = elem.ownerDocument;
    var curr = elem.parentNode;
    if (curr) {
      // This intentionally excludes body which has a null offsetParent.
      while (curr.offsetParent) {
        top -= curr.scrollTop;
        curr = curr.parentNode;
      }
    }

    while (elem) {
      top += elem.offsetTop;

      if (doc.defaultView.getComputedStyle(elem, '')['position'] == 'fixed') {
        top += doc.body.scrollTop;
        return top;
      }

      // Safari 3 does not include borders with offsetTop, so we need to add the
      // borders of the parent manually.
      var parent = elem.offsetParent;
      if (parent && $wnd.devicePixelRatio) {
        top += parseInt(doc.defaultView.getComputedStyle(parent, '').getPropertyValue('border-top-width'));
      }

      // Safari bug: a top-level absolutely positioned element includes the
      // body's offset position already.
      if (parent && (parent.tagName == 'BODY') &&
          (elem.style.position == 'absolute')) {
        break;
      }

      elem = parent;
    }
    return top;
  }-*/;

  private static native ClientRect getBoundingClientRect(Element element) /*-{
    return element.getBoundingClientRect && element.getBoundingClientRect();
  }-*/;

  @Override
  public int getAbsoluteLeft(Element elem) {
    ClientRect rect = getBoundingClientRect(elem);
    return toInt32(rect.left + getScrollLeft(elem.getOwnerDocument()));
  }

  @Override
  public int getAbsoluteTop(Element elem) {
    ClientRect rect = getBoundingClientRect(elem);
    return toInt32(rect.top + getScrollTop(elem.getOwnerDocument()));
  }

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

  protected native boolean isRTL(Element elem) /*-{
    return elem.ownerDocument.defaultView.getComputedStyle(elem, '').direction == 'rtl';
  }-*/;

}

