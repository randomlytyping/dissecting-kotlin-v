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

@file:Suppress("unused", "AssignedValueIsNeverRead", "VariableNeverRead")

package rt

import rt.ex.Clickable
import rt.ex.UiElement
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

object Contracts
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//         ██████╗ ██████╗ ███╗   ██╗████████╗██████╗  █████╗  ██████╗████████╗███████╗
//        ██╔════╝██╔═══██╗████╗  ██║╚══██╔══╝██╔══██╗██╔══██╗██╔════╝╚══██╔══╝██╔════╝
//        ██║     ██║   ██║██╔██╗ ██║   ██║   ██████╔╝███████║██║        ██║   ███████╗
//        ██║     ██║   ██║██║╚██╗██║   ██║   ██╔══██╗██╔══██║██║        ██║   ╚════██║
//        ╚██████╗╚██████╔╝██║ ╚████║   ██║   ██║  ██║██║  ██║╚██████╗   ██║   ███████║
//         ╚═════╝ ╚═════╝ ╚═╝  ╚═══╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝   ╚═╝   ╚══════╝
//
//
//
//
//
//
//        2.2.20: Experimental, Improved Contracts, -Xopt-in=kotlin.contracts.ExperimentalContracts
//        1.3: Stable, stdlib
//
//
//        [KEEP]:
//        https://kotlinlang.org/docs/whatsnew13.html#contracts
//
//
//
//
//
//
//
//
//
//
//
//
//

//
//
//
//
//
//
//
//
//
//
//
//
//        "Contracts allow a function to explicitly describe
//         its behavior in a way which is understood by the compiler."
//
//
//         - Kotlin | What's new in Kotlin 1.3: Contracts
//           https://kotlinlang.org/docs/whatsnew13.html#contracts
//
//
//
//
//
//

/**
 * Smart casts
 */
fun clickAt(x: Int, y: Int) {
  val uiElement = getElementAt(x, y)

  if (uiElement is Clickable) {
    uiElement.onClick()
  }

  if (uiElement.isClickable()) {
    uiElement.onClick()
//    (uiElement as Clickable).onClick()
  }
}

//fun UiElement?.isClickable(): Boolean = this is Clickable

// region // So how can Contracts help?


@OptIn(ExperimentalContracts::class)
fun UiElement?.isClickable(): Boolean {

  contract {
    returns(true) implies (this@isClickable is Clickable)
  }
  return this is Clickable
}



// endregion


//
//
//
//
//
//
//
//
// There are many examples of contracts inside of the stdlib.
//
//
//
//
//
//
//
//

fun intialize(json: String) {
  val uiElement: UiElement?
  run {
    uiElement = parseUiElement(json)
  }
}


// region // ⋅.˳˳.⋅ॱ˙˙ॱ⋅.˳˳.⋅ॱ˙˙ॱ⋅.˳˳.⋅ Here Be Dragons ⋅.˳˳.⋅ॱ˙˙ॱ⋅.˳˳.⋅ॱ˙˙ॱ⋅.˳˳.⋅ ===

inline fun <R> myRun(block: () -> R): R {
  return block()
}

fun parseUiElement(json: String): UiElement? {
  val elements: List<UiElement?> = listOf(
    UiElement.Button(id = "btn_json", label = "Button"),
    UiElement.TextField(id = "txt_json", label = "Text Field", text = json),
    UiElement.TextInput(id = "input_json", label = "Text Input", value = json),
    UiElement.Switch(id = "switch_json", label = "Switch"),
    UiElement.RadioButton(id = "radio_json", label = "Radio Button"),
    UiElement.Checkbox(id = "check_json", label = "Checkbox"),
    null,
  )
  return elements.random()
}

fun getElementAt(x: Int, y: Int): UiElement? {
  val elements: List<UiElement?> = listOf(
    UiElement.Button(id = "btn_$x$y", label = "Button"),
    UiElement.TextField(id = "txt_$x$y", label = "Text Field", text = "Text"),
    UiElement.TextInput(id = "input_$x$y", label = "Text Input"),
    UiElement.Switch(id = "switch_$x$y", label = "Switch"),
    UiElement.RadioButton(id = "radio_$x$y", label = "Radio Button"),
    UiElement.Checkbox(id = "check_$x$y", label = "Checkbox"),
    null,
  )
  return elements.random()
}

// endregion