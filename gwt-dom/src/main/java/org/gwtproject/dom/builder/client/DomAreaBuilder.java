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
package org.gwtproject.dom.builder.client;

import org.gwtproject.dom.builder.shared.AreaBuilder;
import org.gwtproject.dom.client.AreaElement;

/** DOM-based implementation of {@link AreaBuilder}. */
public class DomAreaBuilder extends DomElementBuilderBase<AreaBuilder, AreaElement>
    implements AreaBuilder {

  DomAreaBuilder(DomBuilderImpl delegate) {
    super(delegate, true);
  }

  @Override
  public AreaBuilder accessKey(String accessKey) {
    assertCanAddAttribute().setAccessKey(accessKey);
    return this;
  }

  @Override
  public AreaBuilder alt(String alt) {
    assertCanAddAttribute().setAlt(alt);
    return this;
  }

  @Override
  public AreaBuilder coords(String coords) {
    assertCanAddAttribute().setCoords(coords);
    return this;
  }

  @Override
  public AreaBuilder href(String href) {
    assertCanAddAttribute().setHref(href);
    return this;
  }

  @Override
  public AreaBuilder shape(String shape) {
    assertCanAddAttribute().setShape(shape);
    return this;
  }

  @Override
  public AreaBuilder target(String target) {
    assertCanAddAttribute().setTarget(target);
    return this;
  }
}
