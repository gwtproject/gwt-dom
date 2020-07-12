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
package org.gwtproject.dom;

import org.gwtproject.canvas.dom.CanvasSuite;
import org.gwtproject.dom.builder.ElementBuilderGwtSuite;
import org.gwtproject.dom.builder.HtmlBuilderJreSuite;
import org.gwtproject.dom.client.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/** Tests for the DOM package. */
@Suite.SuiteClasses({
  DocumentTest.class,
  NodeTest.class,
  ElementTest.class,
  FormTests.class,
  FrameTests.class,
  MapTests.class,
  SelectTests.class,
  StyleInjectorTest.class,
  TableTests.class,
  TextTest.class,
  StyleTest.class,
  ElementBuilderGwtSuite.class,
  HtmlBuilderJreSuite.class,
  CanvasSuite.class
})
@RunWith(Suite.class)
public class DOMGwtTestSuite {}
