/*
 * Copyright (c) 2026 Randomly Typing
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package rt.ex

// region // === DOMAIN TYPES ===

// region // == BUTTON STYLE ==

enum class ButtonStyle {
  Primary,
  Secondary,
  Destructive,
}

// endregion

// region // == INPUT VALIDATION ==

sealed interface ValidationState {
  data object Valid : ValidationState
  data object None : ValidationState
  sealed interface Invalid : ValidationState {
    data object Required : Invalid
    data class TooShort(val min: Int) : Invalid
    data class TooLong(val max: Int) : Invalid
    data class InvalidFormat(val message: String) : Invalid
    data class Custom(val message: String) : Invalid
  }
}

// endregion

// endregion

// region // === UI ELEMENT ===

sealed interface Clickable {
  fun onClick() {}
}

sealed interface Checkable {
  val isChecked: Boolean get() = false
  fun toggle() {}
}

sealed interface Editable {
  fun startEdit() {}
  fun finishEdit() {}
}

sealed interface UiElement {
  val id: String
  val label: String
  val isEnabled: Boolean
  val isVisible: Boolean

  data class Button(
    override val id: String,
    override val label: String,
    override val isEnabled: Boolean = true,
    override val isVisible: Boolean = true,
    val style: ButtonStyle = ButtonStyle.Primary,
  ) : UiElement, Clickable

  data class TextField(
    override val id: String,
    override val label: String,
    override val isEnabled: Boolean = true,
    override val isVisible: Boolean = true,
    val text: String,
  ) : UiElement, Editable

  data class TextInput(
    override val id: String,
    override val label: String,
    override val isEnabled: Boolean = true,
    override val isVisible: Boolean = true,
    val value: String = "",
    val placeholder: String = "",
    val maxLength: Int = Int.MAX_VALUE,
    val validation: ValidationState = ValidationState.None,
  ) : UiElement

  data class TextInputV2(
    override val id: String,
    override val label: String,
    override val isEnabled: Boolean = true,
    override val isVisible: Boolean = true,
    val value: String = "",
    val placeholder: String = "",
    val maxLength: Int = Int.MAX_VALUE,
    val isValid: Boolean = true,
    val errorMessage: String? = null,
  ) : UiElement

  data class Switch(
    override val id: String,
    override val label: String,
    override val isEnabled: Boolean = true,
    override val isVisible: Boolean = true,
    val isChecked: Boolean = false,
  ) : UiElement

  data class RadioButton(
    override val id: String,
    override val label: String,
    override val isEnabled: Boolean = true,
    override val isVisible: Boolean = true,
    override val isChecked: Boolean = false,
  ) : UiElement, Checkable, Clickable

  data class Checkbox(
    override val id: String,
    override val label: String,
    override val isEnabled: Boolean = true,
    override val isVisible: Boolean = true,
    override val isChecked: Boolean = false,
  ) : UiElement, Checkable, Clickable
}

// endregion
