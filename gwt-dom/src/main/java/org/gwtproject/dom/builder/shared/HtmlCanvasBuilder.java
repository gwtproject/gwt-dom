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
package org.gwtproject.dom.builder.shared;

/** HTML-based implementation of {@link CanvasBuilder}. */
public class HtmlCanvasBuilder extends HtmlElementBuilderBase<CanvasBuilder>
    implements CanvasBuilder {

  HtmlCanvasBuilder(HtmlBuilderImpl delegate) {
    super(delegate);
  }

  @Override
  public CanvasBuilder height(int height) {
    return trustedAttribute("height", height);
  }

  @Override
  public CanvasBuilder width(int width) {
    return trustedAttribute("width", width);
  }
}
