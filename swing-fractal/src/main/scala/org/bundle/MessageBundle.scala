package org.bundle

import java.util.{Locale, ResourceBundle}

trait MessageBundle {
  
  private val bundleName = "bundle.messages"

  def msg(key: String, locale: Locale = Locale.GERMAN) = {
    val bundle = ResourceBundle.getBundle(bundleName, locale)
    bundle.getString(key)
  }
}
