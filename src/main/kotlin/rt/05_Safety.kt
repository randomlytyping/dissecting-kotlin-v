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

@file:Suppress("unused", "ControlFlowWithEmptyBody")

package rt

import java.net.URI

/**
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *          ███████╗ █████╗ ███████╗███████╗████████╗██╗   ██╗
 *          ██╔════╝██╔══██╗██╔════╝██╔════╝╚══██╔══╝╚██╗ ██╔╝
 *          ███████╗███████║█████╗  █████╗     ██║    ╚████╔╝
 *          ╚════██║██╔══██║██╔══╝  ██╔══╝     ██║     ╚██╔╝
 *          ███████║██║  ██║██║     ███████╗   ██║      ██║
 *          ╚══════╝╚═╝  ╚═╝╚═╝     ╚══════╝   ╚═╝      ╚═╝
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
object Safety

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
//      KotlinConf 2018 - Conference Opening Keynote by Andrey Breslav
//      https://youtu.be/PsaFVLr8t4E?si=47ALJe87RH9zosIe&t=305
//
//      What makes Kotlin a pragmatic language?
//
//      - Readability
//
//      - Reuse
//
//      - Interoperability
//
//      - Safety & Tooling
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
//      Kotlin provides safety in multiple ways:
//
//      - Strict type system:
//
//        "Kotlin has a type system with the following main properties.
//         - Hybrid static, gradual and flow type checking;
//         - Null safety;
//         - No unsafe implicit conversions;
//         - Unified top and bottom types; [kotlin.Any(⊤), kotlin.Nothing(⊥)]
//         - Nominal subtyping with bounded parametric polymorphism and
//           mixed-site variance."
//
//      - Exhaustiveness
//
//      - Unused return value checker (a.k.a. @CheckReturnValue)
//        https://github.com/Kotlin/KEEP/blob/main/proposals/KEEP-0412-unused-return-value-checker.md
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
//        Safety: eliminate certain classes of bugs or trivial bugs
//
//        - Type safety, null safety are for free in Kotlin.
//
//        - Can build an additional layer of safety by relying on
//          automatic safety, leveraging language features,
//          and by making intentioned choices.
//
//        - Regardless of who writes the code, safety provides
//          guardrails.
//
//        - Magnify/multiple good code and tech debt.
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

/**
 * An overly simple screen.
 */
data class SimpleScreen(
  val width: Int?,
  val height: Int?,
)

//
//
//
//
//
//
//
//
//        Possible combinations of null and non-null
//        values inside of a `SimpleScreen`.
//
//        +------------+------------+
//        |   width    |   height   |
//        +------------+------------+
//        | == null    | == null    |
//        ---------------------------
//        | == null    | /= null    |
//        ---------------------------
//        |  /= null   | == null    |
//        ---------------------------
//        | =/= null   | =/= null   |
//        ---------------------------
//
//        How many of these states are valid?
//
//
//
//
//
//
//
//


/**
 * A maybe not simple enough screen.
 */
data class NotSimpleScreen(
  val width: Int?,
  val height: Int?,
  val content: Content,
  val backgroundImage: URI?,
  val backgroundColor: UInt? = null,
  val isLoading: Boolean,
  val errorState: ErrorState? = null
)

/**
 * Render that not-simple-enough screen.
 */
fun NotSimpleScreen.render() {
  if (width == null && height == null) {
    // Log.d("Screen dimensions should not be both null.")
    return
  }

  if (isLoading) {
    // Show loading spinner.
  }

  // Render background.
  when {
    backgroundImage != null -> {
      // Render background image.
    }

    backgroundColor != null -> {
      // Fill with background color.
    }
    //
    // Is there a meaning to backgroundImage != null && backgroundColor != null
    //
    else -> {
      // Decide on some fallback color and render it.
    }
  }

  // Render any errors.
  errorState?.render()

  // Render content.
  content.render()
}

/**
 * More-code-but-less-invalid-states.
 */
sealed interface Screen {
  val width: Int   // Either fail earlier or provided appropriate default.
  val height: Int  // Either fail earlier or provided appropriate default.
  val background: Background? // Intentionally nullable.

  sealed interface Background {
    data class Image(val uri: URI)
    data class Color(val color: UInt)
  }
}

data class LoadingScreen(
  override val width: Int,
  override val height: Int,
  override val background: Screen.Background?,
) : Screen

data class ErrorScreen(
  override val width: Int,
  override val height: Int,
  override val background: Screen.Background?,
  val errorState: ErrorState,
) : Screen

data class ContentScreen(
  override val width: Int,
  override val height: Int,
  override val background: Screen.Background?,
  val content: Content,
) : Screen

// region // ⋅.˳˳.⋅ॱ˙˙ॱ⋅.˳˳.⋅ॱ˙˙ॱ⋅.˳˳.⋅ Here Be Dragons ⋅.˳˳.⋅ॱ˙˙ॱ⋅.˳˳.⋅ॱ˙˙ॱ⋅.˳˳.⋅ ===

data class ErrorState(val type: Type) {
  enum class Type {
    FormInvalid,
    NetworkError,
    Unknown
  }

  fun render() {}
}

data class Content(val content: String) {
  fun render() {}
}

// endregion
