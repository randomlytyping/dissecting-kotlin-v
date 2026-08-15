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
@file:Suppress("unused", "SameParameterValue", "CanSealedSubClassBeObject", "UnusedVariable")

package rt

import rt.Action.Direction
import rt.Key.*
import rt.Key.Number
import rt.ex.CrewMember
import rt.ex.Voyager
import rt.ex.allCrewMembers

object WhenExpression
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
//        ██╗    ██╗██╗  ██╗███████╗███╗   ██╗
//        ██║    ██║██║  ██║██╔════╝████╗  ██║
//        ██║ █╗ ██║███████║█████╗  ██╔██╗ ██║
//        ██║███╗██║██╔══██║██╔══╝  ██║╚██╗██║
//        ╚███╔███╔╝██║  ██║███████╗██║ ╚████║
//         ╚══╝╚══╝ ╚═╝  ╚═╝╚══════╝╚═╝  ╚═══╝
//
//
//
//
//
//
//        Kotlin Language Specification | When Expressions
//        https://kotlinlang.org/spec/expressions.html#when-expressions
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
//
//
//
//        "When expression… allows one of several different
//         control structure bodies (cases) to be evaluated,
//         depending on some boolean conditions…
//
//         …when expressions may include several different conditions
//         with their corresponding control structure bodies."
//
//         - Kotlin language specification | 8.6 When expressions
//           https://kotlinlang.org/spec/expressions.html#when-expressions
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
//
//
//
//        Expression vs. Statement
//
//
//
//
//        Expression: a piece of code that may be evaluated to
//                    determine its value
//
//
//        Statement: a piece of code that performs an action;
//                   smallest unit of execution
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
//
//
//
//
//
//
//
//        "In Kotlin, an expression may be used as a statement
//         or used as an expression depending on the context.
//         As all expressions are valid statements, standalone
//         expressions may be used as single statements or
//         inside code blocks."
//
//         - Kotlin language specification | Expressions
//           https://kotlinlang.org/spec/expressions.html#expressions
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


fun isThisAStatement() {
  val sum = 2 + 2
}


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
//        "When expression has two different forms:
//         with bound value and without it."
//
//         Also can be referred to as
//         "With Subject" or "Without Subject"
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

enum class SimpleEnum {
  A, B, C, D;
}

/**
 * `when` without bound value / without subject
 */
fun printValidSelections(selections: List<SimpleEnum>) {
  when {
    selections.isEmpty() -> println("No valid selections provided")
    ValidValues.isEmpty() -> println("No valid values defined")
    else -> println("Selections: ${selections.joinToString()}")
  }
}

/**
 * `when` with bound value / with subject
 */
fun getSelectedAction(value: SimpleEnum): Action =
  when (value) {
    SimpleEnum.A -> Action.Crouch
    SimpleEnum.B -> Action.Dash
    SimpleEnum.C -> Action.Jump
    SimpleEnum.D -> Action.Walk
  }

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
//        'when' whenSubject? '{' whenEntry+ '}'
//
//          whenSubject:
//            '(' ('val' variableDeclaration '=')? expression ')'
//
//          whenEntry:
//            whenCondition (',' whenCondition)* ','?
//                '->' controlStructureBody
//            | 'else' '->' controlStructureBody
//
//            whenCondition:
//              expression
//              | rangeTest
//              | typeTest
//
//            controlStructureBody:
//              '{' statements '}'
//              | statement
//
//
//
//
//
//
//
//
/**
 * Simplified `when` grammar .
 *
 * https://kotlinlang.org/grammar/#whenExpression
 */
fun executeAction() {
  //
  // 'when' whenSubject? '{' whenEntry+ '}'
  //
  // whenSubject:
  //  '(' (annotation* 'val' variableDeclaration '=')? expression ')'
  //
  when (val key: Key = getTappedButton()) {
    //
    // whenEntry:
    //   whenCondition (',' whenCondition)* ','? '->' controlStructureBody
    //

    // whenCondition: expression
    Alpha.X -> dash()
    Alpha.Y -> jump()
    Alpha.W, Arrow.Up -> walk(Direction.Up)
    Alpha.A, Arrow.Left -> walk(Direction.Up)
    Alpha.S, Arrow.Down -> walk(Direction.Up)
    Alpha.D, Arrow.Right -> crouch()

    // whenCondition: rangeTest
    in listOf(Number.Num1, Number.Num2) -> selectMenuItem(key)

    // whenCondition: typeTest
    is Control -> return

    // else -> controlStructureBody
    else -> println("Pressed $key")
  }
}

/**
 * *whenCondition*: expression
 */
fun matchCrewMember(crewMember: CrewMember) {
  when (crewMember) {
    getLatestJoined() -> println("Most recent crew member.")
    getFavorite() -> println("Favorite crew member.")
    Voyager.commandingOfficer -> println("Janeway, Arr")
    else -> println("No match.")
  }
}

/**
 * *whenCondition*: rangeTest
 *
 * Containment-checking expressions
 * https://kotlinlang.org/spec/expressions.html#containment-checking-expressions
 *
 * `in` operator
 * https://kotlinlang.org/spec/expressions.html#type-checking-expressions
 */
fun convertLevel(level: Int): PowerLevel =
  when (level) {
    in 1..10 -> PowerLevel.Low
    in 11..50 -> PowerLevel.Medium
    in 51..9000 -> PowerLevel.High
    in 9001..Int.MAX_VALUE -> PowerLevel.Super
    in Int.MIN_VALUE..0 -> PowerLevel.Low
    else -> PowerLevel.Low
  }


/**
 * whenCondition: typeTest
 *
 * Type-checking expression
 * https://kotlinlang.org/spec/expressions.html#type-checking-expressions
 *
 * Checks with `is` and `!is` operators
 * https://kotlinlang.org/docs/typecasts.html#is-and-is-operators
 */
fun toKeyTypeDescription(key: Key) =
  when (key) {
    is Alpha -> "Alpha Keys"
    is Arrow -> "Arrow Keys"
    is Number -> "Number Keys"
    Control.ESC -> "Esc Key"
  }

val letsTakeALook = BytecodeBreak










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
//        ███████ ██   ██ ██   ██  █████  ██    ██ ███████ ████████ ██ ██    ██ ███████
//        ██       ██ ██  ██   ██ ██   ██ ██    ██ ██         ██    ██ ██    ██ ██
//        █████     ███   ███████ ███████ ██    ██ ███████    ██    ██ ██    ██ █████   █████
//        ██       ██ ██  ██   ██ ██   ██ ██    ██      ██    ██    ██  ██  ██  ██
//        ███████ ██   ██ ██   ██ ██   ██  ██████  ███████    ██    ██   ████   ███████
//
//
//        ███    ██ ███████ ███████ ███████
//        ████   ██ ██      ██      ██
//        ██ ██  ██ █████   ███████ ███████
//        ██  ██ ██ ██           ██      ██
//        ██   ████ ███████ ███████ ███████
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
//    A `when` expression or statement being exhaustive
//    means that every possible value of a subject is
//    covered by at least one branch.
//
//
//    A `when` MUST be exhaustive:
//
//    - when used as an expression
//    - if the subject is sealed, enum, or boolean
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
//    How the compiler checks for exhaustiveness:
//
//
//
//
//    A `when` expression is exhaustive if at least one of the
//    following is true:
//
//
//    - It has an `else` entry
//
fun printOnlyA(value: SimpleEnum) {
  when (value) {
    SimpleEnum.A -> println("Selected A")
    else -> Unit
  }
}

//
//    - It has a bound value/subject, and at least one of
//      the following is true:
//
//      - The bound expression is a `Boolean` and the conditions contain:
//        - A constant expression that evaluates to `true`.
//        - A constant expression that evaluates to `false`.
//

fun whenThisOrThat(value: Boolean) {
  when (value) {
    true -> println("This")
    false -> println("That")
  }
}


//
//    - The bound expression is of a sealed class or interface S,
//      and all of its direct non-sealed subtypes T1, …, Tn
//      are covered.
//
//                              S
//                              |
//        +---------+----------------+-----------+-----+
//        |         |                |           |     |
//        T         S                S           S     S
//                  |                |           |
//          +-------+-------+        S        +--+--+--+
//          |       |       |        |        |     |  |
//          T       S       T      +-+-+      S     T  T
//                  |              |   |      |
//               +--+--+           T   T    +-+-+
//               |     |                    |   |
//               T     S                    T   T
//                     |
//                  +--+--+
//                  |     |
//                  T     T
//
sealed interface S
class T1 : S
sealed interface S1 : S {
  class T2 : S1
  class T3 : S1
  class T4 : S1
}
sealed interface S2 : S {
  class T5 : S2
  class T6 : S2
}
sealed interface S3
enum class E : S {
  One, Two, Three;
}

//
//    For a direct non-sealed subtype Ti:
//
//    - There is a type check on Sj <: S (Sj is a subtype of S),
//      and Ti <: Sj.
//    - There is a not-a type check on Sj, where Ti is not a
//      subtype of Sj, but there is another subtype Tk that is a
//      subtype of Sj, meaning that a sibling type is getting
//      filtered out.
//    - If there is an `enum class` subtype, Ei, of S, and that
//      enum is covered: if all of its values are checked via
//      constant expressions.
//
fun verifySealedHierarchy(value: S) {
  when (value) {
    E.One, E.Two, E.Three -> println("Is a member of E")
    is T1 -> println("Is a T1")
    is S1 -> println("Is a S1")
    !is S1 -> println("Is not a S1")
  }
}


//
//
//    - The bound expression is an `enum class`, and that enum
//      has all of its values checked via constant expressions.
//
//
fun isItA(value: SimpleEnum) {
  when (value) {
    SimpleEnum.A -> println("Selected A")
    SimpleEnum.B, SimpleEnum.C, SimpleEnum.D -> println("Not A")
  }
}

//
//
//    - The bound expression is of a nullable type T?:
//      - One of the cases above is met for its nun-nullable
//        counterpart, T.
//      - Includes another condition which checks the bound
//        value for equality with null.
//
//

fun verifyNullable(value: S?) {
  when (value) {
    is S -> println("Is a S")
    null -> println("Value is null")
  }
}

/**
 * This is a `when` statement and so does not have to be exhaustive.
 */
fun selectMenuItem(itemName: String) {
  when (itemName) {
    "A" -> selectMenuItem(Alpha.A)
    "B" -> selectMenuItem(Alpha.B)
    "C" -> selectMenuItem(Alpha.C)
    "D" -> selectMenuItem(Alpha.D)
  }
}

/**
 * This is a `when` expression and so must be exhaustive.
 */
fun convertTextToKey(text: String): Alpha =
  when (text) {
    "A" -> Alpha.A
    "B" -> Alpha.B
    "C" -> Alpha.C
    "D" -> Alpha.D
    else -> Alpha.X
  }


// region // ⋅.˳˳.⋅ॱ˙˙ॱ⋅.˳˳.⋅ॱ˙˙ॱ⋅.˳˳.⋅ Here Be Dragons ⋅.˳˳.⋅ॱ˙˙ॱ⋅.˳˳.⋅ॱ˙˙ॱ⋅.˳˳.⋅ ===



private val DEFAULT_VALUE = SimpleEnum.D
private val ValidValues = listOf(SimpleEnum.A, SimpleEnum.C)
private fun getOverrideValues() = listOf(SimpleEnum.B)
private fun getLastSelected(): SimpleEnum = SimpleEnum.entries.random()


enum class Action {
  Up,
  Down,
  Start,
  End,
  Dash,
  Jump,
  Crouch,
  PickUp,
  Drop,
  Throw,
  Unspecified,
  Walk,
  Exit,
  ;

  enum class Direction {
    Up, Down, Start, End;
  }
}

sealed interface Key {
  val action: Action

  enum class Control(
    override val action: Action
  ) : Key {
    ESC(Action.Exit)
  }

  enum class Number(
    override val action: Action
  ) : Key {
    Num1(Action.Unspecified),
    Num2(Action.Unspecified),
    Num3(Action.Unspecified),
    Num4(Action.Unspecified),
    Num5(Action.Unspecified),
    Num6(Action.Unspecified),
    Num7(Action.Unspecified),
    Num8(Action.Unspecified),
    Num9(Action.Unspecified),
    ;
  }

  enum class Alpha(
    override val action: Action
  ) : Key {
    W(Action.Up),
    A(Action.Start),
    S(Action.Down),
    D(Action.End),

    B(Action.Dash),
    C(Action.Jump),
    E(Action.PickUp),
    F(Action.Drop),
    G(Action.Throw),

    X(Action.Unspecified),
    Y(Action.Unspecified),
    Z(Action.Crouch),
    ;

  }

  enum class Arrow(
    override val action: Action
  ) : Key {

    Up(Action.Up),
    Left(Action.Start),
    Down(Action.Down),
    Right(Action.End),
    ;
  }
}


private val DISABLED_KEYS = listOf(Alpha.X)

private fun walk(direction: Direction) = Unit
private fun stop() = Unit
private fun dash() = Unit
private fun jump() = Unit
private fun crouch() = Unit
private fun selectMenuItem(key: Key) = Unit
private fun getTappedButton(): Key =
  (Alpha.entries + Arrow.entries).random()

enum class PowerLevel {
  Low,
  Medium,
  High,
  Super,
  ;
}

fun getLatestJoined(): CrewMember = allCrewMembers.last()
fun getFavorite(): CrewMember = allCrewMembers.random()

// endregion