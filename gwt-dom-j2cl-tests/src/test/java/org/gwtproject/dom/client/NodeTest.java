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

import java.util.Locale;
import org.junit.Test;

/** Tests the {@link Node} class. */
public class NodeTest {

  /** appendChild, insertBefore, removeChild, replaceChild. */
  @Test
  public void testAppendRemoveReplace() {
    Document doc = Document.get();
    BodyElement body = doc.getBody();

    // <div>foo<button/>bar</div>
    DivElement div = doc.createDivElement();
    Text txt0 = doc.createTextNode("foo");
    ButtonElement btn0 = doc.createPushButtonElement();
    Text txt1 = doc.createTextNode("bar");

    body.appendChild(div);
    div.appendChild(txt0);
    div.appendChild(btn0);
    div.appendChild(txt1);

    // appendChild, insertBefore
    ButtonElement btn1 = doc.createPushButtonElement();

    // <div>foo<btn0/>bar<btn1/></div>
    div.appendChild(btn1);
    assertEquals(btn1, div.getLastChild());

    // <div>foo<button/>bar<button/></div>
    div.insertBefore(btn1, txt1);
    assertEquals(4, div.getChildNodes().getLength());
    assertEquals(btn1, div.getChildNodes().getItem(2));

    // removeChild
    // <div>foo<btn0/>bar</div> (back to original)
    div.removeChild(btn1);
    assertEquals(3, div.getChildNodes().getLength());

    // replaceChild
    // <div>foo<btn1/>bar</div>
    div.replaceChild(btn1, btn0);
    assertEquals(btn1, txt0.getNextSibling());
    assertEquals(btn1, txt1.getPreviousSibling());

    // insertAfter
    // <div>foo<btn1/><btn0/>bar</div>
    div.insertAfter(btn0, btn1);
    assertEquals(btn0, btn1.getNextSibling());

    // insertFirst
    // <div><btn1/>foo<btn0/>bar</div>
    div.insertFirst(btn1);
    assertEquals(btn1, div.getFirstChild());

    // removeFromParent
    // <div>foo<btn0/>bar</div>
    btn1.removeFromParent();
    assertNull(btn1.getParentElement());
    assertEquals(txt0, div.getFirstChild());
  }

  @Test
  public void testRemoveAllChildren() {
    Document doc = Document.get();
    BodyElement body = doc.getBody();

    DivElement div = doc.createDivElement();
    div.appendChild(doc.createTextNode("foo"));
    div.appendChild(doc.createAnchorElement());
    div.appendChild(doc.createTextNode("bar"));
    body.appendChild(div);

    assertEquals("foo<a></a>bar", div.getInnerHTML());

    div.removeAllChildren();
    assertEquals("", div.getInnerHTML());
    assertEquals(0, div.getChildCount());
  }

  /** setAttribute, getAttribute, hasAttributes, hasAttribute. */
  @Test
  public void testAttributes() {
    Document doc = Document.get();
    DivElement div = doc.createDivElement();

    div.setAttribute("id", "myId");
    assertEquals("myId", div.getAttribute("id"));
  }

  /** childNodes, hasChildNodes. */
  @Test
  public void testChildNodeList() {
    Document doc = Document.get();
    BodyElement body = doc.getBody();

    // <div>foo<button/>bar</div>
    DivElement div = doc.createDivElement();
    Text txt0 = doc.createTextNode("foo");
    ButtonElement btn0 = doc.createPushButtonElement();
    Text txt1 = doc.createTextNode("bar");

    body.appendChild(div);
    div.appendChild(txt0);
    div.appendChild(btn0);
    div.appendChild(txt1);

    NodeList<Node> children = div.getChildNodes();
    assertEquals(3, children.getLength());
    assertEquals(txt0, children.getItem(0));
    assertEquals(btn0, children.getItem(1));
    assertEquals(txt1, children.getItem(2));

    assertEquals(3, div.getChildCount());
    assertEquals(txt0, div.getChild(0));
    assertEquals(btn0, div.getChild(1));
    assertEquals(txt1, div.getChild(2));

    assertFalse(txt0.hasChildNodes());
    assertTrue(div.hasChildNodes());
  }

  /** getParentElement. */
  @Test
  public void testGetParentDoesntCycle() {
    Element element = Document.get().getBody();
    int i = 0;
    while (i < 10 && element != null) {
      element = element.getParentElement();
      i++;
    }

    // If we got here we looped "forever" or passed, as no exception was thrown.
    if (i == 10) {
      fail("Cyclic parent structure detected.");
    }

    // If we get here, we pass, because we encountered no errors going to the
    // top of the parent hierarchy.
  }

  /** isOrHasChild. */
  @Test
  public void testIsOrHasChild() {
    Document doc = Document.get();
    DivElement div = doc.createDivElement();
    DivElement childDiv = doc.createDivElement();
    Text text = doc.createTextNode("foo");

    assertFalse(div.isOrHasChild(childDiv));
    assertFalse(div.isOrHasChild(text));
    assertFalse(childDiv.isOrHasChild(text));

    assertTrue(div.isOrHasChild(div));
    assertTrue(text.isOrHasChild(text));

    div.appendChild(childDiv);
    childDiv.appendChild(text);
    assertTrue(div.isOrHasChild(childDiv));
    assertTrue(div.isOrHasChild(text));

    assertFalse(childDiv.isOrHasChild(div));
    assertFalse(text.isOrHasChild(childDiv));
    assertFalse(text.isOrHasChild(div));

    BodyElement body = doc.getBody();
    body.appendChild(div);
    assertTrue(body.isOrHasChild(body));
    assertTrue(body.isOrHasChild(div));
    assertTrue(body.isOrHasChild(childDiv));
    assertTrue(body.isOrHasChild(text));

    assertTrue(div.isOrHasChild(div));
    assertTrue(div.isOrHasChild(childDiv));
    assertTrue(div.isOrHasChild(text));

    assertFalse(childDiv.isOrHasChild(div));
    assertFalse(text.isOrHasChild(div));
  }

  /** nodeName, nodeValue. */
  @Test
  public void testNodeNameAndValue() {
    Document doc = Document.get();
    DivElement div = doc.createDivElement();
    Text txt0 = doc.createTextNode("foo");

    assertEquals("div", div.getNodeName().toLowerCase(Locale.ROOT));

    assertEquals("foo", txt0.getNodeValue());
    txt0.setNodeValue("bar");
    assertEquals("bar", txt0.getNodeValue());
  }

  /** hasParentElement, getParentElement. */
  @Test
  public void testNodeParentElement() {
    Document doc = Document.get();
    BodyElement body = doc.getBody();
    DivElement div = doc.createDivElement();
    Text text = doc.createTextNode("foo");

    // An unattached node should have no parent element.
    assertFalse(text.hasParentElement());
    assertFalse(div.hasParentElement());
    assertNull(text.getParentElement());
    assertNull(div.getParentElement());

    // Test attached cases.
    body.appendChild(div);
    div.appendChild(text);
    assertTrue(div.hasParentElement());
    assertTrue(text.hasParentElement());
    assertEquals(body, div.getParentElement());
    assertEquals(div, text.getParentElement());
  }

  /** nodeType. */
  @Test
  public void testNodeType() {
    Document doc = Document.get();
    DivElement div = doc.createDivElement();
    Text txt0 = doc.createTextNode("foo");

    assertEquals(Node.DOCUMENT_NODE, doc.getNodeType());
    assertEquals(Node.ELEMENT_NODE, div.getNodeType());
    assertEquals(Node.TEXT_NODE, txt0.getNodeType());
  }

  /** getParentNode, firstChild, lastChild, nextSibling, previousSibling. */
  @Test
  public void testParentAndSiblings() {
    Document doc = Document.get();
    BodyElement body = doc.getBody();

    // <div>foo<button/>bar</div>
    DivElement div = doc.createDivElement();
    Text txt0 = doc.createTextNode("foo");
    ButtonElement btn0 = doc.createPushButtonElement();
    Text txt1 = doc.createTextNode("bar");

    body.appendChild(div);
    div.appendChild(txt0);
    div.appendChild(btn0);
    div.appendChild(txt1);

    assertEquals(div, btn0.getParentNode());

    assertEquals(txt0, div.getFirstChild());
    assertEquals(txt1, div.getLastChild());
    assertEquals(btn0, txt0.getNextSibling());
    assertEquals(btn0, txt1.getPreviousSibling());
    assertNull(txt0.getPreviousSibling());
    assertNull(txt1.getNextSibling());
  }

  /** Tests Element.is() and Element.as(). */
  @Test
  public void testIsAndAs() {
    assertTrue(Node.is(Document.get()));

    Object text = Document.get().createTextNode("foo");
    assertTrue(Node.is(text));

    // Node.is(null) is allowed and should return false.
    assertFalse(Node.is(null));
  }
}
