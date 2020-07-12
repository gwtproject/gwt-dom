/*
 * Copyright © 2019 The GWT Project Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gwtproject.dom.client;

import static junit.framework.TestCase.*;

import com.google.j2cl.junit.apt.J2clTestInput;
import java.util.Locale;
import org.gwtproject.core.client.JavaScriptObject;
import org.gwtproject.core.client.Scheduler;
import org.junit.Test;

/** Element tests (many stolen from DOMTest). */
@J2clTestInput(ElementTest.class)
public class ElementTest {

  @Test
  public void testAddClassName() {
    DivElement div = Document.get().createDivElement();
    div.setClassName("foo");

    assertTrue(div.addClassName("bar"));
    assertEquals("foo bar", div.getClassName());

    assertTrue(div.addClassName("baz"));
    assertEquals("foo bar baz", div.getClassName());

    assertFalse(div.addClassName("baz"));
    assertEquals("foo bar baz", div.getClassName());
  }

  @Test
  public void testRemoveClassName() {
    DivElement div = Document.get().createDivElement();
    div.setClassName("foo bar baz");

    assertTrue(div.removeClassName("bar"));
    assertEquals("foo baz", div.getClassName());

    assertFalse(div.removeClassName("bar"));
    assertEquals("foo baz", div.getClassName());

    assertTrue(div.removeClassName("baz"));
    assertEquals("foo", div.getClassName());

    assertTrue(div.removeClassName("foo"));
    assertEquals("", div.getClassName());
  }

  @Test
  public void testHasClassName() {
    DivElement div = Document.get().createDivElement();
    div.setClassName("foo bar");

    assertTrue(div.hasClassName("bar"));
    assertTrue(div.hasClassName("foo"));

    div.setClassName("bar");
    assertFalse(div.hasClassName("foo"));
    assertTrue(div.hasClassName("bar"));
  }

  @Test
  public void testToggleClassName() {
    DivElement div = Document.get().createDivElement();
    div.setClassName("foo bar baz");

    div.toggleClassName("bar");
    assertEquals("foo baz", div.getClassName());

    div.toggleClassName("bar");
    assertEquals("foo baz bar", div.getClassName());
  }

  @Test
  public void testReplaceClassName() {
    DivElement div = Document.get().createDivElement();
    div.setClassName("foo bar baz");

    div.replaceClassName("bar", "tintin");
    assertEquals("foo baz tintin", div.getClassName());

    div.replaceClassName("bar", "tintin2");
    assertEquals("foo baz tintin tintin2", div.getClassName());
  }

  @Test
  public void testIndexOfName() {
    assertEquals(-1, Element.indexOfName("", "foo"));

    assertEquals(-1, Element.indexOfName("foo", "fo"));
    assertEquals(-1, Element.indexOfName("foo", "fool"));

    assertEquals(-1, Element.indexOfName("bar fool", "foo"));
    assertEquals(-1, Element.indexOfName("bar fool baz", "foo"));

    assertEquals(0, Element.indexOfName("foo", "foo"));
    assertEquals(0, Element.indexOfName("foo bar", "foo"));
    assertEquals(4, Element.indexOfName("bar foo", "foo"));
    assertEquals(4, Element.indexOfName("bar foo baz", "foo"));
  }

  /** firstChildElement, nextSiblingElement, previousSiblingElement. */
  @Test
  public void testChildElements() {
    Document doc = Document.get();
    DivElement parent = doc.createDivElement();
    DivElement div0 = doc.createDivElement();
    DivElement div1 = doc.createDivElement();

    parent.appendChild(doc.createTextNode("foo"));
    parent.appendChild(div0);
    parent.appendChild(doc.createTextNode("bar"));
    parent.appendChild(div1);

    Element fc = parent.getFirstChildElement();
    Element ns = fc.getNextSiblingElement();
    Element ps = ns.getPreviousSiblingElement();
    assertEquals(div0, fc);
    assertEquals(div1, ns);
    assertEquals(div0, ps);

    assertNull(fc.getPreviousSiblingElement());
    assertNull(ns.getNextSiblingElement());
  }

  /** Test round-trip of the 'disabled' property. */
  @Test
  public void testDisabled() {
    ButtonElement button = Document.get().createPushButtonElement();
    assertFalse(button.isDisabled());
    button.setDisabled(true);
    assertTrue(button.isDisabled());

    InputElement input = Document.get().createTextInputElement();
    assertFalse(input.isDisabled());
    input.setDisabled(true);
    assertTrue(input.isDisabled());

    SelectElement select = Document.get().createSelectElement();
    assertFalse(select.isDisabled());
    select.setDisabled(true);
    assertTrue(select.isDisabled());

    OptGroupElement optgroup = Document.get().createOptGroupElement();
    assertFalse(optgroup.isDisabled());
    optgroup.setDisabled(true);
    assertTrue(optgroup.isDisabled());
  }

  /** [get|set|remove]Attribute. */
  @Test
  public void testElementAttribute() {
    DivElement div = Document.get().createDivElement();
    div.setAttribute("class", "testClass");
    String cssClass = div.getAttribute("class");
    assertEquals("testClass", cssClass);
    div.removeAttribute("class");
    cssClass = div.getAttribute("class");
    assertEquals("", cssClass);
  }

  /**
   * Ensure that the return type of an attribute is always a string. IE should not return a numeric
   * attribute based on the element property. See issue 3238.
   */
  @Test
  public void testElementAttributeNumeric() {
    DivElement div = Document.get().createDivElement();
    Document.get().getBody().appendChild(div);
    div.setInnerText("Hello World");
    div.getAttribute("offsetWidth").length();
    div.getAttribute("offsetWidth").trim().length();
    Document.get().getBody().removeChild(div);
  }

  @Test
  public void testEmptyClassNameAssertion() {
    DivElement div = Document.get().createDivElement();

    if (getClass().desiredAssertionStatus()) {
      div.setClassName("primary");
      try {
        div.addClassName("");
        throw new Error();
      } catch (AssertionError e) {
        // This *should* throw.
      }

      try {
        div.addClassName(" ");
        throw new Error();
      } catch (AssertionError e) {
        // This *should* throw.
      }

      try {
        div.addClassName(null);
        throw new Error();
      } catch (AssertionError e) {
        // This *should* throw.
      }

      try {
        div.removeClassName("");
        throw new Error();
      } catch (AssertionError e) {
        // This *should* throw.
      }

      try {
        div.removeClassName(" ");
        throw new Error();
      } catch (AssertionError e) {
        // This *should* throw.
      }

      try {
        div.removeClassName(null);
        throw new Error();
      } catch (AssertionError e) {
        // This *should* throw.
      }

      assertEquals("primary", div.getClassName());
    }
  }

  /** getAbsolute[Left|Top|Right|Bottom]. */
  @Test
  public void testGetAbsolutePosition() {
    final int border = 8;
    final int margin = 9;
    final int padding = 10;

    final int top = 15;
    final int left = 14;
    final int width = 128;
    final int height = 64;

    final Document doc = Document.get();
    final DivElement elem = doc.createDivElement();
    doc.getBody().appendChild(elem);

    elem.getStyle().setProperty("position", "absolute");
    elem.getStyle().setProperty("border", border + "px solid #000");
    elem.getStyle().setProperty("padding", padding + "px");
    elem.getStyle().setProperty("margin", margin + "px");

    elem.getStyle().setPropertyPx("top", top - doc.getBodyOffsetLeft());
    elem.getStyle().setPropertyPx("left", left - doc.getBodyOffsetTop());
    elem.getStyle().setPropertyPx("width", width);
    elem.getStyle().setPropertyPx("height", height);

    Scheduler.get()
        .scheduleDeferred(
            () -> {
              int absLeft = left + margin;
              int absTop = top + margin;
              int interiorDecorations = (border * 2) + (padding * 2);

              assertEquals(absLeft, elem.getAbsoluteLeft());
              assertEquals(absTop, elem.getAbsoluteTop());

              assertEquals(absLeft + width + interiorDecorations, elem.getAbsoluteRight());
              assertEquals(absTop + height + interiorDecorations, elem.getAbsoluteBottom());
            });
  }

  /** getElementsByTagName. */
  @Test
  public void testGetElementsByTagName() {
    DivElement div = Document.get().createDivElement();
    div.setInnerHTML("<span><button>foo</button><span><button>bar</button></span></span>");

    NodeList<Element> nodes = div.getElementsByTagName("button");
    assertEquals(2, nodes.getLength());
    assertEquals("foo", nodes.getItem(0).getInnerText());
    assertEquals("bar", nodes.getItem(1).getInnerText());
  }

  @Test
  public void testHasAttribute() {
    DivElement div = Document.get().createDivElement();

    // Assert that a raw element doesn't incorrectly report that it has any
    // unspecified built-in attributes (this is a problem on IE<8 if you're not
    // careful in implementing hasAttribute()).
    assertFalse(div.hasAttribute("class"));
    assertFalse(div.hasAttribute("style"));
    assertFalse(div.hasAttribute("title"));
    assertFalse(div.hasAttribute("id"));

    // Ensure that setting HTML-defined attributes is properly reported by
    // hasAttribute().
    div.setId("foo");
    assertTrue(div.hasAttribute("id"));

    // Ensure that setting *custom* attributes is properly reported by
    // hasAttribute().
    assertFalse(div.hasAttribute("foo"));
    div.setAttribute("foo", "bar");
    assertTrue(div.hasAttribute("foo"));

    // Ensure that a null attribute argument always returns null.
    assertFalse(div.hasAttribute(null));
  }

  @Test
  public void testHasTagName() {
    DivElement div = Document.get().createDivElement();

    // hasTagName is case-insensitive
    assertTrue(div.hasTagName("div"));
    assertTrue(div.hasTagName("DIV"));
    assertTrue(div.hasTagName(DivElement.TAG));
    assertTrue(div.hasTagName(div.getTagName()));

    assertFalse(div.hasTagName("dove"));
  }

  /** Tests HeadingElement.as() (it has slightly more complex assertion logic than most). */
  @Test
  public void testHeadingElementAs() {
    DivElement placeHolder = Document.get().createDivElement();

    for (int i = 0; i < 6; ++i) {
      placeHolder.setInnerHTML("<H" + (i + 1) + "/>");
      assertNotNull(HeadingElement.as(placeHolder.getFirstChildElement()));
    }

    if (getClass().desiredAssertionStatus()) {
      Element notHeading = Document.get().createDivElement();
      try {
        HeadingElement.as(notHeading);
        throw new Error("Expected assertion failure");
      } catch (AssertionError e) {
        // this *should* happen.
      }
    }
  }

  /** Tests Element.is() and Element.as(). */
  @Test
  public void testIsAndAs() {
    assertFalse(Element.is(Document.get()));

    Node div = Document.get().createDivElement();
    assertTrue(Element.is(div));
    assertEquals("div", Element.as(div).getTagName().toLowerCase(Locale.ROOT));

    // Element.is(null) is allowed and should return false.
    assertFalse(Element.is(null));

    // Element sub-classes like DivElement have is(...) and as(...) too
    assertFalse(DivElement.is(Document.get()));
    assertTrue(DivElement.is(div));
    assertEquals("div", DivElement.as(div).getTagName().toLowerCase(Locale.ROOT));
    assertFalse(DivElement.is(null));
  }

  /** Document.createElement('ns:tag'), getTagName(). */
  @Test
  public void testNamespaces() {
    Element elem = Document.get().createElement("myns:elem");
    assertEquals("myns:elem", elem.getTagName().toLowerCase(Locale.ROOT));
  }

  /** className, id, tagName, title, dir, lang. */
  @Test
  public void testNativeProperties() {
    DivElement div = Document.get().createDivElement();

    assertEquals("div", div.getTagName().toLowerCase(Locale.ROOT));

    div.setClassName("myClass");
    assertEquals("myClass", div.getClassName());

    div.setId("myId");
    assertEquals("myId", div.getId());

    div.setTitle("myTitle");
    assertEquals("myTitle", div.getTitle());

    div.setDir("rtl");
    assertEquals("rtl", div.getDir());

    div.setLang("fr-FR");
    assertEquals("fr-FR", div.getLang());
  }

  /** offset[Left|Top|Width|Height], offsetParent. */
  @Test
  public void testOffsets() {
    DivElement outer = Document.get().createDivElement();
    DivElement middle = Document.get().createDivElement();
    DivElement inner = Document.get().createDivElement();

    Document.get().getBody().appendChild(outer);
    outer.appendChild(middle);
    middle.appendChild(inner);

    outer.getStyle().setProperty("position", "absolute");
    inner.getStyle().setProperty("position", "relative");
    inner.getStyle().setPropertyPx("left", 19);
    inner.getStyle().setPropertyPx("top", 23);
    inner.getStyle().setPropertyPx("width", 29);
    inner.getStyle().setPropertyPx("height", 31);

    assertEquals(outer, inner.getOffsetParent());
    assertEquals(19, inner.getOffsetLeft());
    assertEquals(23, inner.getOffsetTop());
    assertEquals(29, inner.getOffsetWidth());
    assertEquals(31, inner.getOffsetHeight());
  }

  /** setProperty*, getProperty*. */
  @Test
  public void testProperties() {
    DivElement div = Document.get().createDivElement();

    div.setPropertyString("foo", "bar");
    assertEquals("bar", div.getPropertyString("foo"));

    div.setPropertyInt("foo", 42);
    assertEquals(42, div.getPropertyInt("foo"));

    div.setPropertyBoolean("foo", true);
    div.setPropertyBoolean("bar", false);
    assertTrue(div.getPropertyBoolean("foo"));
    assertFalse(div.getPropertyBoolean("bar"));

    Object obj = new Object();
    div.setPropertyObject("baz", obj);
    assertEquals(obj, div.getPropertyObject("baz"));

    JavaScriptObject jso = JavaScriptObject.createObject();
    div.setPropertyJSO("tintin", jso);
    assertEquals(jso, div.getPropertyJSO("tintin"));

    assertNull(div.getPropertyObject("inexistant_property_at_least_we_hope_so"));
    div.setPropertyObject("baz", Boolean.TRUE);
    assertEquals(Boolean.TRUE, div.getPropertyObject("baz"));
    div.setPropertyObject("baz", Boolean.FALSE);
    assertEquals(Boolean.FALSE, div.getPropertyObject("baz"));
    div.setPropertyObject("baz", 42.0);
    assertEquals(42.0, div.getPropertyObject("baz"));
    div.setPropertyObject("baz", 0.0);
    assertEquals(0.0, div.getPropertyObject("baz"));
    div.setPropertyObject("baz", Double.NaN);
    assertTrue(Double.isNaN((Double) div.getPropertyObject("baz")));
    div.setPropertyObject("baz", "tintin");
    assertEquals("tintin", div.getPropertyObject("baz"));
    div.setPropertyObject("baz", "");
    assertEquals("", div.getPropertyObject("baz"));
  }

  /** innerHTML. */
  @Test
  public void testSetInnerHTML() {
    DivElement div = Document.get().createDivElement();
    div.setInnerHTML("<button><img src='foo.gif'></button>");

    Element button = div.getFirstChildElement();
    Element img = button.getFirstChildElement();

    assertEquals("button", button.getTagName().toLowerCase(Locale.ROOT));
    assertEquals("img", img.getTagName().toLowerCase(Locale.ROOT));
    assertTrue(((ImageElement) img).getSrc().endsWith("foo.gif"));
  }

  /** innerText. */
  @Test
  public void testSetInnerText() {
    Document doc = Document.get();

    TableElement tableElem = doc.createTableElement();
    TableRowElement trElem = doc.createTRElement();
    TableCellElement tdElem = doc.createTDElement();
    tdElem.setInnerText("Some Table Heading Data");

    // Add a <em> element as a child to the td element
    Element emElem = doc.createElement("em");
    emElem.setInnerText("Some emphasized text");
    tdElem.appendChild(emElem);

    trElem.appendChild(tdElem);
    tableElem.appendChild(trElem);
    doc.getBody().appendChild(tableElem);
    tdElem.setInnerText(null);

    // Once we set the inner text on an element to null, all of the element's
    // child nodes should be deleted, including any text nodes, for all
    // supported browsers.
    assertEquals(0, tdElem.getChildNodes().getLength());
  }

  /** style. */
  @Test
  public void testStyle() {
    DivElement div = Document.get().createDivElement();

    div.getStyle().setProperty("color", "black");
    assertEquals("black", div.getStyle().getProperty("color"));

    div.getStyle().setPropertyPx("width", 42);
    assertEquals("42px", div.getStyle().getProperty("width"));
  }

  /** Test that styles only allow camelCase. */
  @Test
  public void testStyleCamelCase() {
    DivElement div = Document.get().createDivElement();

    // Use a camelCase property
    div.getStyle().setProperty("backgroundColor", "black");
    assertEquals("black", div.getStyle().getProperty("backgroundColor"));
    div.getStyle().setPropertyPx("marginLeft", 10);
    assertEquals("10px", div.getStyle().getProperty("marginLeft"));

    // Use a hyphenated style
    if (Style.class.desiredAssertionStatus()) {
      try {
        div.getStyle().setProperty("background-color", "red");
        throw new Error("Expected assertion error: background-color should be in camelCase");
      } catch (AssertionError e) {
        // expected
      }
      try {
        div.getStyle().setPropertyPx("margin-left", 20);
        throw new Error("Expected assertion error: margin-left should be in camelCase");
      } catch (AssertionError e) {
        // expected
      }
      try {
        div.getStyle().getProperty("margin-right");
        throw new Error("Expected assertion error: margin-right should be in camelCase");
      } catch (AssertionError e) {
        // expected
      }
    }
  }
}
