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

@file:Suppress("unused")

package rt

import rt.ex.AwayMission
import rt.ex.Color
import rt.ex.CrewMember
import rt.ex.CrewMember.Division.*
import rt.ex.CrewMember.HealthStatus.Injured

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
//        ███████╗███╗   ███╗ █████╗ ██████╗ ████████╗
//        ██╔════╝████╗ ████║██╔══██╗██╔══██╗╚══██╔══╝
//        ███████╗██╔████╔██║███████║██████╔╝   ██║
//        ╚════██║██║╚██╔╝██║██╔══██║██╔══██╗   ██║
//        ███████║██║ ╚═╝ ██║██║  ██║██║  ██║   ██║
//        ╚══════╝╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝
//
//         ██████╗ █████╗ ███████╗████████╗███████╗
//        ██╔════╝██╔══██╗██╔════╝╚══██╔══╝██╔════╝
//        ██║     ███████║███████╗   ██║   ███████╗
//        ██║     ██╔══██║╚════██║   ██║   ╚════██║
//        ╚██████╗██║  ██║███████║   ██║   ███████║
//         ╚═════╝╚═╝  ╚═╝╚══════╝   ╚═╝   ╚══════╝
//
//
//
//
//
//        Documentation:
//        https://kotlinlang.org/docs/typecasts.html#smart-casts
//
//        Kotlin language specification | Type Inference
//        https://kotlinlang.org/spec/type-inference.html#smart-casts
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
//        Type inference: some type information may be omitted, but the
//          compiler can infer the type.
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
//        "Type inference is a type constraint problem and is usually
//         solved by a type constraint solver. For this reason, type
//         inference is applicable in situations when the type context
//         contains enough information…"
//
//        - Kotlin language specification | Type Inference
//          https://kotlinlang.org/spec/type-inference.html#type-inference
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
//        "Kotlin introduces a limited form of flow-sensitive typing
//         called smart casts. Flow-sensitive typing means some expressions
//         in the program may introduce changes to the compile-time
//         types of variables."
//
//        - Kotlin language specification | Smart Casts
//          https://kotlinlang.org/spec/type-inference.html#smart-casts
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
//        "Smart casts are introduced by the following Kotlin constructions.
//
//         - Conditional expressions (if)
//         - When expressions (when);
//         - Elvis operator (operator ?:);
//         - Safe navigation operator (operator ?.);
//         - Logical conjunction expressions (operator &&);
//         - Logical disjunction expressions (operator ||);
//         - Not-null assertion expressions (operator !!);
//         - Cast expressions (operator as);
//         - Type-checking expressions (operator is);
//         - Simple assignments;
//         - Platform-specific cases"
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
//        "Data-flow analysis is a technique for gathering information
//         about the possible set of values calculated at various
//         points in a computer program."
//
//        - Data-flow analysis | Wikipedia
//          https://en.wikipedia.org/wiki/Data-flow_analysis
//
//
//
//
//        "…a control-flow graph (CFG) is a representation…of all paths
//         that might be traversed through a function during its execution,
//         or control flow."
//
//        - Control-flow graph | Wikipedia
//          https://en.wikipedia.org/wiki/Control-flow_graph
//
//
//
//


//
//
//
//
//    SmartCastType = (P, N)
//    - P -> positive type info, type that expression definitely has
//    - N -> negative type info, type that expression definitely does not have
//
//
//
//


fun executeAwayMission(mission: AwayMission) {

  var injuredCrewMember: CrewMember? // Declaration/assignment; it's `null`.

  for (crewMember: CrewMember in mission.team) {

    if (crewMember.healthStatus == Injured && mission.medicalStaff != null) {
      injuredCrewMember = crewMember // Assignment, it's definitely a CrewMember

      mission.medicalStaff.treat(crewMember)

      if (injuredCrewMember is LineOfficer) {  // Type-check, `injuredCrewMember` is definitely
                                               //  a CrewMember && LineOfficer

        injuredCrewMember.passOnCommand() // Smart Cast sink: the expression that
                                          //  leverages the accumulated information
      }
      // After this `if`, we drop the `is`.
      // `injuredCrewMember` is definitely a CrewMember, but drops the extra type information.
    }
    // After this `if`, we drop the assignment information.
    // It's back to being `null` here.

    when (crewMember) {
      is ScienceOfficer -> crewMember.scanForLifeforms() // Type-check, `crewMember` is definitely
                                                         //  a ScienceOfficer, Smart Cast sink.

      is SecurityOfficer -> crewMember.checkPerimeter() // Type-check, `crewMember` is definitely
                                                        //  a SecurityOfficer; Smart Cast sink.

      else -> if (crewMember.uniformColor == Color.Red) { // `crewMember` is definitely a CrewMember,
        println("Don't die.") }                           //  is definitely not a ScienceOfficer,
                                                          //  not a SecurityOfficer
    }
  }
}