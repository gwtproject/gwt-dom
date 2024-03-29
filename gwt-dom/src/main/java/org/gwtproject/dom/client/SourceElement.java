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

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * The SOURCE element specifies one of potentially multiple source file in a media element.
 *
 * @see <a href="http://www.w3.org/TR/html5/video.html#the-source-element">W3C HTML
 *     Specification</a>
 */
@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
@TagName(SourceElement.TAG)
public class SourceElement extends Element {

  @JsOverlay public static final String TAG = "source";

  /**
   * Assert that the given {@link Element} is compatible with this class and automatically typecast
   * it.
   *
   * @param elem the element to assert is an instance of this type
   * @return the element, cast to this type
   */
  @JsOverlay
  public static SourceElement as(Element elem) {
    assert is(elem);
    return (SourceElement) elem;
  }

  /**
   * Determines whether the given object can be cast to this class. A <code>null
   * </code> object will cause this method to return <code>false</code>.
   *
   * @param o the object to check if it is an instance of this type
   * @return true of the object is an instance of this type, false otherwise
   */
  @JsOverlay
  public static boolean is(Object o) {
    if (Element.is(o)) {
      return is((Element) o);
    }
    return false;
  }

  /**
   * Determine whether the given {@link Node} can be cast to this class. A <code>null</code> node
   * will cause this method to return <code>false</code>.
   *
   * @param node the node to check if it is an instance of this type
   * @return true if the node is an instance of this type, false otherwise
   */
  @JsOverlay
  public static boolean is(Node node) {
    if (Element.is(node)) {
      return is((Element) node);
    }
    return false;
  }

  /**
   * Determine whether the given {@link Element} can be cast to this class. A <code>null</code> node
   * will cause this method to return <code>false</code>.
   *
   * @param elem the element to check if it is an instance of this type
   * @return true if the element is an instance of this type, false otherwise
   */
  @JsOverlay
  public static boolean is(Element elem) {
    return elem != null && elem.hasTagName(TAG);
  }

  protected SourceElement() {}

  /**
   * Returns the source URL for the media, or {@code null} if none is set.
   *
   * @return a String URL or {@code null}
   * @see #setSrc(String)
   */
  @JsProperty
  public final native String getSrc();

  /**
   * Returns the type of media represented by the src, or {@code null} if none is set.
   *
   * @return a String type, or {@code null}
   * @see #setType(String)
   */
  @JsProperty
  public final native String getType();

  /**
   * Sets the source URL for the media.
   *
   * @param url a String URL
   * @see #getSrc()
   */
  @JsProperty
  public final native void setSrc(String url);

  /**
   * Sets the type of media represented by the src. The browser will look at the type when deciding
   * which source files to request from the server.
   *
   * <p>The type is the format or encoding of the media represented by the source element. For
   * example, the type of an {@link AudioElement} could be one of {@value AudioElement#TYPE_OGG},
   * {@link AudioElement#TYPE_MP3}, or {@link AudioElement#TYPE_WAV}.
   *
   * <p>You can also add the codec information to the type, giving the browser even more information
   * about whether or not it can play the file (Example: " <code>audio/ogg; codec=vorbis</code>");
   *
   * @param type the media type
   * @see #getType()
   */
  @JsProperty
  public final native void setType(String type);
}
