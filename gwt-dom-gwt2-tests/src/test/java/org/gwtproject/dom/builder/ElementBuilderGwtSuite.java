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
package org.gwtproject.dom.builder;

import org.gwtproject.dom.builder.client.*;
import org.gwtproject.dom.builder.shared.GwtHtmlBuilderImplTest;
import org.gwtproject.dom.builder.shared.GwtHtmlStylesBuilderTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/** Tests of the dom and HTML implementation of element builders. */
@Suite.SuiteClasses({
  // Html implementation tests.
  GwtHtmlBuilderImplTest.class,
  GwtHtmlStylesBuilderTest.class,

  // DOM implementation tests.
  GwtDomBuilderImplTest.class,
  GwtDomStylesBuilderTest.class,

  // Element builder tests.
  GwtAnchorBuilderTest.class,
  GwtAreaBuilderTest.class,
  GwtAudioBuilderTest.class,
  GwtBaseBuilderTest.class,
  GwtBodyBuilderTest.class,
  GwtBRBuilderTest.class,
  GwtButtonBuilderTest.class,
  GwtCanvasBuilderTest.class,
  GwtDivBuilderTest.class,
  GwtDListBuilderTest.class,
  GwtFieldSetBuilderTest.class,
  GwtFormBuilderTest.class,
  GwtFrameBuilderTest.class,
  GwtFrameSetBuilderTest.class,
  GwtHeadBuilderTest.class,
  GwtHeadingBuilderTest.class,
  GwtHRBuilderTest.class,
  GwtIFrameBuilderTest.class,
  GwtImageBuilderTest.class,
  GwtInputBuilderTest.class,
  GwtLabelBuilderTest.class,
  GwtLegendBuilderTest.class,
  GwtLIBuilderTest.class,
  GwtLinkBuilderTest.class,
  GwtMapBuilderTest.class,
  GwtMetaBuilderTest.class,
  GwtOListBuilderTest.class,
  GwtOptGroupBuilderTest.class,
  GwtOptionBuilderTest.class,
  GwtParagraphBuilderTest.class,
  GwtParamBuilderTest.class,
  GwtPreBuilderTest.class,
  GwtQuoteBuilderTest.class,
  GwtScriptBuilderTest.class,
  GwtSelectBuilderTest.class,
  GwtSourceBuilderTest.class,
  GwtSpanBuilderTest.class,
  GwtStyleBuilderTest.class,
  GwtTableCaptionBuilderTest.class,
  GwtTableCellBuilderTest.class,
  GwtTableColBuilderTest.class,
  GwtTableBuilderTest.class,
  GwtTableRowBuilderTest.class,
  GwtTableSectionBuilderTest.class,
  GwtTextAreaBuilderTest.class,
  GwtTitleBuilderTest.class,
  GwtUListBuilderTest.class,
  GwtVideoBuilderTest.class,
})
@RunWith(Suite.class)
public class ElementBuilderGwtSuite {}
