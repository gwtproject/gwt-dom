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

import org.gwtproject.safehtml.shared.SafeUri;
import org.gwtproject.safehtml.shared.annotations.IsSafeUri;

/** HTML-based implementation of {@link QuoteBuilder}. */
public class HtmlQuoteBuilder extends HtmlElementBuilderBase<QuoteBuilder> implements QuoteBuilder {

  HtmlQuoteBuilder(HtmlBuilderImpl delegate) {
    super(delegate);
  }

  @Override
  public QuoteBuilder cite(SafeUri cite) {
    return cite(cite.asString());
  }

  @Override
  public QuoteBuilder cite(@IsSafeUri String cite) {
    return trustedAttribute("cite", cite);
  }
}
