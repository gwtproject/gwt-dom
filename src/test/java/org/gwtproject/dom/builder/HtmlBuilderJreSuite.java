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
package org.gwtproject.dom.builder;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.gwtproject.dom.builder.shared.*;

/**
 * Tests of the html implementation of element builders.
 */
public class HtmlBuilderJreSuite {

  public static Test suite() {
    TestSuite suite = new TestSuite("JRE tests for all html builders");

    // Element builders.
    suite.addTestSuite(HtmlAnchorBuilderTest.class);
    suite.addTestSuite(HtmlAreaBuilderTest.class);
    suite.addTestSuite(HtmlAudioBuilderTest.class);
    suite.addTestSuite(HtmlBaseBuilderTest.class);
    suite.addTestSuite(HtmlBodyBuilderTest.class);
    suite.addTestSuite(HtmlBRBuilderTest.class);
    suite.addTestSuite(HtmlButtonBuilderTest.class);
    suite.addTestSuite(HtmlCanvasBuilderTest.class);
    suite.addTestSuite(HtmlDivBuilderTest.class);
    suite.addTestSuite(HtmlDListBuilderTest.class);
    suite.addTestSuite(HtmlFieldSetBuilderTest.class);
    suite.addTestSuite(HtmlFormBuilderTest.class);
    suite.addTestSuite(HtmlFrameBuilderTest.class);
    suite.addTestSuite(HtmlFrameSetBuilderTest.class);
    suite.addTestSuite(HtmlHeadBuilderTest.class);
    suite.addTestSuite(HtmlHeadingBuilderTest.class);
    suite.addTestSuite(HtmlHRBuilderTest.class);
    suite.addTestSuite(HtmlIFrameBuilderTest.class);
    suite.addTestSuite(HtmlImageBuilderTest.class);
    suite.addTestSuite(HtmlInputBuilderTest.class);
    suite.addTestSuite(HtmlLabelBuilderTest.class);
    suite.addTestSuite(HtmlLegendBuilderTest.class);
    suite.addTestSuite(HtmlLIBuilderTest.class);
    suite.addTestSuite(HtmlLinkBuilderTest.class);
    suite.addTestSuite(HtmlMapBuilderTest.class);
    suite.addTestSuite(HtmlMetaBuilderTest.class);
    suite.addTestSuite(HtmlOListBuilderTest.class);
    suite.addTestSuite(HtmlOptGroupBuilderTest.class);
    suite.addTestSuite(HtmlOptionBuilderTest.class);
    suite.addTestSuite(HtmlParagraphBuilderTest.class);
    suite.addTestSuite(HtmlParamBuilderTest.class);
    suite.addTestSuite(HtmlPreBuilderTest.class);
    suite.addTestSuite(HtmlQuoteBuilderTest.class);
    suite.addTestSuite(HtmlScriptBuilderTest.class);
    suite.addTestSuite(HtmlSelectBuilderTest.class);
    suite.addTestSuite(HtmlSourceBuilderTest.class);
    suite.addTestSuite(HtmlSpanBuilderTest.class);
    suite.addTestSuite(HtmlStyleBuilderTest.class);
    suite.addTestSuite(HtmlTableCaptionBuilderTest.class);
    suite.addTestSuite(HtmlTableCellBuilderTest.class);
    suite.addTestSuite(HtmlTableColBuilderTest.class);
    suite.addTestSuite(HtmlTableBuilderTest.class);
    suite.addTestSuite(HtmlTableRowBuilderTest.class);
    suite.addTestSuite(HtmlTableSectionBuilderTest.class);
    suite.addTestSuite(HtmlTextAreaBuilderTest.class);
    suite.addTestSuite(HtmlTitleBuilderTest.class);
    suite.addTestSuite(HtmlUListBuilderTest.class);
    suite.addTestSuite(HtmlVideoBuilderTest.class);

    return suite;
  }
}
