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

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import org.gwtproject.core.client.JavaScriptObject;

/**
 * Represents the target of a JavaScript event.
 *
 * <p>This type is returned from methods such as {@link NativeEvent#getEventTarget()}, and must
 * usually be cast to another type using methods such as {@link Element#is(Object)} and
 * {@link Element#as(JavaScriptObject)}.
 *
 * <p>This class intentionally does <em>not</em> specify the methods from the DOM IDL
 * (dispatchEvent, addEventListener, and removeEventListener).
 */
@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
public class EventTarget extends JavaScriptObject {

  protected EventTarget() {}
}
