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

import static junit.framework.TestCase.assertEquals;

import elemental2.promise.Promise;
import org.junit.Test;

/** Tests StyleInjector by looking for effects of injected CSS on DOM elements. */
public class StyleInjectorTest {

  private static final int TEST_DELAY = 1000;

  @SuppressWarnings("deprecation")
  @Test(timeout = TEST_DELAY)
  public Promise<Void> testOldMethods() {
    final DivElement elt = Document.get().createDivElement();
    elt.setId("styleInjectorTest");
    elt.setInnerHTML("Hello StyleInjector!");
    Document.get().getBody().appendChild(elt);

    StyleInjector.injectStylesheet(
        "#styleInjectorTest {position: absolute; left: 100px; width: 50px; height 50px;}");
    StyleInjector.injectStylesheetAtStart(
        "#styleInjectorTest {left: 25px; width: 100px !important;}");
    StyleInjector.injectStylesheetAtEnd("#styleInjectorTest {height: 100px;}");

    return new Promise<>(
        ((resolve, reject) -> {
          assertEquals(100, elt.getOffsetLeft());
          assertEquals(100, elt.getClientHeight());
          assertEquals(100, elt.getClientWidth());

          resolve.onInvoke((Void) null);
        }));
  }

  /** Ensure that the IE createStyleSheet compatibility code is exercised. */
  @SuppressWarnings("deprecation")
  @Test(timeout = TEST_DELAY)
  public Promise<Void> testOldMethodsWithLotsOfStyles() {
    StyleElement[] elements = new StyleElement[100];
    for (int i = 0, j = elements.length; i < j; i++) {
      elements[i] =
          StyleInjector.injectStylesheet(
              "#styleInjectorTest"
                  + i
                  + " {position: absolute; left: 100px; width: 50px; height 50px;}");
    }

    String id = "styleInjectorTest" + (elements.length - 1);
    StyleInjector.injectStylesheetAtStart("#" + id + " {left: 25px; width: 100px !important;}");
    StyleInjector.injectStylesheetAtEnd("#" + id + " {height: 100px;}");

    final DivElement elt = Document.get().createDivElement();
    elt.setId(id);
    elt.setInnerHTML("Hello StyleInjector!");
    Document.get().getBody().appendChild(elt);

    return new Promise<>(
        ((resolve, reject) -> {
          assertEquals(100, elt.getOffsetLeft());
          assertEquals(100, elt.getClientHeight());
          assertEquals(100, elt.getClientWidth());

          resolve.onInvoke((Void) null);
        }));
  }

  /*
   * Tests against issue #879: Ensure that empty history tokens do not add
   * additional characters after the '#' symbol in the URL.
   */

  @Test(timeout = TEST_DELAY)
  public Promise<Void> testStyleInjectorBatched() {
    String testName = "testStyleInjectorBatched";
    final DivElement elt = Document.get().createDivElement();
    elt.setId(testName);
    elt.setInnerHTML("Hello");
    Document.get().getBody().appendChild(elt);

    StyleInjector.inject(
        "#" + testName + " {position: absolute; left: 100px; width: 50px; height 50px;}", false);
    StyleInjector.injectAtStart("#" + testName + " {left: 25px; width: 100px !important;}", false);
    StyleInjector.injectAtEnd("#" + testName + " {height: 100px;}", false);

    // We need to allow the BatchedCommands to execute
    return new Promise<>(
        ((resolve, reject) -> {
          assertEquals(100, elt.getOffsetLeft());
          assertEquals(100, elt.getClientHeight());
          assertEquals(100, elt.getClientWidth());

          resolve.onInvoke((Void) null);
        }));
  }

  @Test
  public void testStyleInjectorImmediate() {
    String testName = "testStyleInjectorImmediate";
    final DivElement elt = Document.get().createDivElement();
    elt.setId(testName);
    elt.setInnerHTML("Hello");
    Document.get().getBody().appendChild(elt);

    StyleInjector.inject(
        "#" + testName + " {position: absolute; left: 100px; width: 50px; height 50px;}", true);
    StyleInjector.injectAtStart("#" + testName + " {left: 25px; width: 100px !important;}", true);
    StyleInjector.injectAtEnd("#" + testName + " {height: 100px;}", true);

    assertEquals(100, elt.getOffsetLeft());
    assertEquals(100, elt.getClientHeight());
    assertEquals(100, elt.getClientWidth());
  }
}
