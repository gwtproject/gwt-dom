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
package org.gwtproject.dom.client;

import elemental2.dom.HTMLMediaElement;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;
import org.gwtproject.media.dom.client.MediaError;
import org.gwtproject.media.dom.client.TimeRanges;

/**
 * Common superclass for Audio and Video elements.
 *
 * See {@link <a href="http://www.w3.org/TR/html5/video.html">W3C HTML5 Video and Audio</a>}
 */
@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
public class MediaElement extends Element {

  /**
   * Constant returned from {@link #canPlayType(String)}.
   */
  @JsOverlay
  public static final String CAN_PLAY_PROBABLY = "probably";

  /**
   * Constant returned from {@link #canPlayType(String)}.
   */
  @JsOverlay
  public static final String CAN_PLAY_MAYBE = "maybe";

  /**
   * Constant returned from {@link #canPlayType(String)}.
   */
  @JsOverlay
  public static final String CANNOT_PLAY = "";

  /**
   * Constant returned from {@link #getReadyState()}.
   */
  public static int HAVE_NOTHING;

  /**
   * Constant returned from {@link #getReadyState()}.
   */
  public static int HAVE_METADATA;

  /**
   * Constant returned from {@link #getReadyState()}.
   */
  public static int HAVE_CURRENT_DATA;

  /**
   * Constant returned from {@link #getReadyState()}.
   */
  public static int HAVE_FUTURE_DATA;

  /**
   * Constant returned from {@link #getReadyState()}.
   */
  public static int HAVE_ENOUGH_DATA;

  /**
   * Constant returned from {@link #getNetworkState}.
   */
  public static int NETWORK_EMPTY;

  /**
   * Constant returned from {@link #getNetworkState}.
   */
  public static int NETWORK_IDLE;

  /**
   * Constant returned from {@link #getNetworkState}.
   */
  public static int NETWORK_LOADING;

  /**
   * Constant returned from {@link #getNetworkState}.
   */
  public static int NETWORK_NO_SOURCE;

  /**
   * Constant used by {@link #getPreload()} and {@link #setPreload(String)}.
   */
  @JsOverlay
  public static final String PRELOAD_AUTO = "auto";

  /**
   * Constant used by {@link #getPreload()} and {@link #setPreload(String)}.
   */
  @JsOverlay
  public static final String PRELOAD_METADATA = "metadata";

  /**
   * Constant used by {@link #getPreload()} and {@link #setPreload(String)}.
   */
  @JsOverlay
  public static final String PRELOAD_NONE = "none";

  protected MediaElement() {
  }

  /**
   * Returns {@code true} if the native player is capable of playing content of
   * the given MIME type.
   *
   * @param type a String representing a MIME type
   * @return one of {@link #CAN_PLAY_PROBABLY}, {@link #CAN_PLAY_MAYBE}, or
   *         {@link #CANNOT_PLAY}
   */
  @JsOverlay
  public final String canPlayType(String type) {
    String canPlayType = Js.<HTMLMediaElement>uncheckedCast(this).canPlayType(type);
     // Some browsers report "no" instead of the empty string.
     // See http://gwt-voices.appspot.com/
    return "no".equals(canPlayType) ? CANNOT_PLAY : canPlayType;
  }

  /**
   * Returns a {@link TimeRanges} object indicating which portions of the
   * source have been buffered locally.
   *
   * @return a {@link TimeRanges} instance, or {@code null}.
   */
  @JsProperty
  public final native TimeRanges getBuffered();

  /**
   * Returns the URL of the current media source, or the empty String
   * if no source is set.
   *
   * @return a String URL
   */
  @JsProperty
  public final native String getCurrentSrc();

  /**
   * Returns the current time within the source media stream.
   *
   * @return the time, in seconds, as a double
   *
   * @see #setCurrentTime(double)
   */
  @JsProperty
  public final native double getCurrentTime();

  /**
   * Returns the default playback rate, where 1.0 corresponds to normal
   * playback. If no rate has been set, 1.0 is returned.
   *
   * @return the current default playback rate, or 1.0 if it has not been set
   *
   * @see #setDefaultPlaybackRate(double)
   */
  @JsOverlay
  public final double getDefaultPlaybackRate() {
    return getDoubleAttr("defaultPlaybackRate", 1.0);
  }

  /**
   * Returns the duration of the source media stream, in seconds.  If the
   * duration is unknown, {@link Double#NaN} is returned.  For unbounded media
   * streams, {@link Double#POSITIVE_INFINITY} is returned.
   *
   * @return a positive duration in seconds, NaN, or Infinity
   */
  @JsProperty
  public final native double getDuration();

  /**
   * Returns the type of error that has occurred while attempting to load
   * and play the media.  If no error has occurred, {@code null} is returned.
   *
   * @return a {@link MediaError} instance, or {@code null}
   */
  @JsOverlay
  public final MediaError getError() {
    return Js.isTruthy(this.error) ? this.error : null;
  }

  @JsProperty
  private MediaError error;

  /**
   * Returns the time to which the media stream was seeked at the time it was
   * loaded, in seconds, or 0.0 if the position is unknown.
   *
   * @return the initial time, or 0.0 if unknown
   */
  @JsOverlay
  public final double getInitialTime() {
    return getDoubleAttr("initialTime", 0.0);
  }

  /**
   * Returns the network state, one of {@link #NETWORK_EMPTY},
   * {@link #NETWORK_IDLE}, {@link #NETWORK_LOADING}, or
   * {@link #NETWORK_NO_SOURCE}.
   *
   * @return an integer constant indicating the network state
   *
   * @see #NETWORK_EMPTY
   * @see #NETWORK_IDLE
   * @see #NETWORK_LOADING
   * @see #NETWORK_NO_SOURCE
   */
  @JsProperty
  public final native int getNetworkState();

  /**
   * Returns the playback rate, where 1.0 corresponds to normal
   * playback.  If the rate has not been set, 1.0 is returned.
   *
   * @return the playback rate, if known, otherwise 1.0
   *
   * @see #setPlaybackRate(double)
   */
  @JsOverlay
  public final double getPlaybackRate() {
    Object rate = this.playbackRate;
    if (rate != null && "number".equals(Js.typeof(rate))) {
      return (double) rate;
    }
    return 1.0d;
  }

  @JsProperty
  private Object playbackRate;

  /**
   * Returns a {@link TimeRanges} object indicating which portions of the
   * source have been played.
   *
   * @return a {@link TimeRanges} instance, or {@code null}.
   */
  @JsProperty
  public final native TimeRanges getPlayed();

  /**
   * Returns the preload setting, one of {@link #PRELOAD_AUTO},
   * {@link #PRELOAD_METADATA}, or {@link #PRELOAD_NONE}.
   *
   * @return the preload setting
   *
   * @see #setPreload(String)
   * @see #PRELOAD_AUTO
   * @see #PRELOAD_METADATA
   * @see #PRELOAD_NONE
   */
  @JsProperty
  public final native String getPreload();

  /**
   * Returns the current state of the media with respect to rendering the
   * current playback position, as one of the constants
   * {@link #HAVE_CURRENT_DATA}, {@link #HAVE_ENOUGH_DATA},
   * {@link #HAVE_FUTURE_DATA}, {@link #HAVE_METADATA}, or {@link #HAVE_NOTHING}
   * .
   *
   * @return an integer constant indicating the ready state
   *
   * @see #HAVE_CURRENT_DATA
   * @see #HAVE_ENOUGH_DATA
   * @see #HAVE_FUTURE_DATA
   * @see #HAVE_METADATA
   * @see #HAVE_NOTHING
   */
  @JsProperty
  public final native int getReadyState();

  /**
   * Returns a {@link TimeRanges} object indicating which portions of the
   * source are seekable.
   *
   * @return a {@link TimeRanges} instance, or {@code null}.
   */
  @JsProperty
  public final native TimeRanges getSeekable();

  /**
   * Returns the source URL for the media, or {@code null} if none is set.
   *
   * @return a String URL or {@code null}
   *
   * @see #setSrc(String)
   */
  @JsOverlay
  public final String getSrc() {
    return this.getAttribute("src");
  }

  /**
   * Returns the time corresponding to the zero time in the media timeline,
   * measured in seconds since midnight, January 1 1970 UTC, or
   * {@link Double#NaN} if none is specified.
   *
   * @return the start time
   */
  @JsOverlay
  public final double getStartOffsetTime() {
    return getDoubleAttr("startOffsetTime", Double.NaN);
  }

  /**
   * Returns the current audio volume setting for the media, as a number
   * between 0.0 and 1.0.
   *
   * @return a number between 0.0 (silent) and 1.0 (loudest)
   *
   * @see #setVolume(double)
   */
  @JsProperty
  public final native double getVolume();

  /**
   * Returns {@code true} if the media player should display interactive
   * controls (for example, to control play/pause, seek position, and volume),
   * {@code false} otherwise.
   *
   * @return whether controls should be displayed
   *
   * @see #setControls(boolean)
   */
  @JsOverlay
  public final boolean hasControls() {
    return this.hasAttribute("controls");
  }

  /**
   * Returns {@code true} if playback has reached the end of the media, {@code
   * false} otherwise.
   *
   * @return whether playback has ended
   */
  @JsProperty(name = "ended")
  public final native boolean hasEnded();

  /**
   * Returns {@code true} if autoplay is enabled, {@code false} otherwise. When
   * autoplay is enabled, the user agent will begin playback automatically as
   * soon as it can do so without stopping.
   *
   * @return the autoplay setting
   *
   * @see #setAutoplay(boolean)
   */
  @JsOverlay
  public final boolean isAutoplay() {
    return this.hasAttribute("autoplay");
  }

  /**
   * Returns {@code true} if the user agent is to seek back to the start of the
   * media once playing has ended, {@code false} otherwise.
   *
   * @return the loop setting
   *
   * @see #setLoop(boolean)
   */
  @JsOverlay
  public final boolean isLoop() {
    return this.hasAttribute("loop");
  }

  /**
   * Returns {@code true} if the volume is to be muted (overriding the normal
   * volume setting), {@code false} otherwise.
   *
   * @return the muting setting
   *
   * @see #setMuted(boolean)
   * @see #getVolume()
   * @see #setVolume(double)
   */
  @JsOverlay
  public final boolean isMuted() {
    return Js.isTruthy(Js.<HTMLMediaElement>uncheckedCast(this).muted);
  }

  /**
   * Returns {@code true} if playback is paused, {@code false} otherwise.
   *
   * @return the paused setting
   *
   * @see #pause()
   * @see #play()
   */
  @JsOverlay
  public final boolean isPaused() {
    return Js.isTruthy(Js.<HTMLMediaElement>uncheckedCast(this).paused);
  }

  /**
   * Returns {@code true} if the playback position is in the process of changing
   * discontinuously, e.g., by use of the interactive controls, {@code false}
   * otherwise.
   *
   * @return the seeking status
   *
   * @see #setControls(boolean)
   * @see #hasControls()
   */
  @JsOverlay
  public final boolean isSeeking() {
    return Js.isTruthy(Js.<HTMLMediaElement>uncheckedCast(this).seeking);
  }

  /**
   * Causes the resource to be loaded.
   */
  public final native void load();

  /**
   * Causes playback of the resource to be paused.
   */
  public final native void pause();

  /**
   * Causes playback of the resource to be started or resumed.
   */
  public final native void play();

  /**
   * Enables or disables autoplay of the resource.
   *
   * @param autoplay if {@code true}, enable autoplay
   *
   * @see #isAutoplay()
   */
  @JsOverlay
  public final void setAutoplay(boolean autoplay) {
    setBooleanAttr("autoplay", autoplay);
  }

  /**
   * Enables or disables interactive controls.
   *
   * @param controls if {@code true}, enable controls
   *
   * @see #hasControls()
   */
  @JsOverlay
  public final void setControls(boolean controls) {
    setBooleanAttr("controls", controls);
  }

  /**
   * Sets the current playback time within the media stream, in seconds.
   *
   * @param time a number within the ranges given by {@link #getSeekable()}
   *
   * @see #getCurrentTime()
   */
  @JsProperty
  public final native void setCurrentTime(double time);

  /**
   * Sets the default playback rate.
   *
   * @param rate a double value
   *
   * @see #getDefaultPlaybackRate()
   */
  @JsProperty
  public final native void setDefaultPlaybackRate(double rate);

  /**
   * Enables or disables looping.
   *
   * @param loop if {@code true}, enable looping
   *
   * @see #isLoop()
   */
  @JsOverlay
  public final void setLoop(boolean loop) {
    setBooleanAttr("loop", loop);
  }

  /**
   * Enables or disables muting.
   *
   * @param muted if {@code true}, enable muting
   *
   * @see #isMuted()
   */
  @JsProperty
  public final native void setMuted(boolean muted);

  /**
   * Sets the playback rate.
   *
   * @param rate a double value
   *
   * @see #getPlaybackRate()
   */
  @JsProperty
  public final native void setPlaybackRate(double rate);

  /**
   * Changes the preload setting to one of {@link #PRELOAD_AUTO},
   * {@link #PRELOAD_METADATA}, or {@link #PRELOAD_NONE}.
   *
   * @param preload a String constants
   *
   * @see #getPreload()
   * @see #setPreload(String)
   * @see #PRELOAD_AUTO
   * @see #PRELOAD_METADATA
   * @see #PRELOAD_NONE
   */
  @JsProperty
  public final native void setPreload(String preload);

  /**
   * Sets the source URL for the media.
   *
   * @param url a String URL
   *
   * @see #getSrc()
   */
  @JsProperty
  public final native void setSrc(String url);

  /**
   * Sets the playback volume.
   *
   * @param volume a value between 0.0 (silent) and 1.0 (loudest)
   *
   * @see #getVolume()
   */
  @JsProperty
  public final native void setVolume(double volume);

  @JsOverlay
  private double getDoubleAttr(String name, double def) {
    String value = this.getAttribute(name);
    if (!"number".equals(Js.typeof(value))) {
      return def;
    }
    return Js.uncheckedCast(value);
  }

  @JsOverlay
  private void setBooleanAttr(String name, boolean value) {
    if (value) {
      setAttribute(name, "");
    } else {
      removeAttribute(name);
    }
  }
}
