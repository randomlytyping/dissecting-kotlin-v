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

import rt.ex.CrewMember
import rt.ex.CrewMember.HealthStatus


object DataFlowBasedExhaustiveness
//
//
//
//
//
//
//
//
//        ██████╗  █████╗ ████████╗ █████╗     ███████╗██╗      ██████╗ ██╗    ██╗
//        ██╔══██╗██╔══██╗╚══██╔══╝██╔══██╗    ██╔════╝██║     ██╔═══██╗██║    ██║
//        ██║  ██║███████║   ██║   ███████║    █████╗  ██║     ██║   ██║██║ █╗ ██║
//        ██║  ██║██╔══██║   ██║   ██╔══██║    ██╔══╝  ██║     ██║   ██║██║███╗██║
//        ██████╔╝██║  ██║   ██║   ██║  ██║    ██║     ███████╗╚██████╔╝╚███╔███╔╝
//        ╚═════╝ ╚═╝  ╚═╝   ╚═╝   ╚═╝  ╚═╝    ╚═╝     ╚══════╝ ╚═════╝  ╚══╝╚══╝
//
//        ██████╗  █████╗ ███████╗███████╗██████╗
//        ██╔══██╗██╔══██╗██╔════╝██╔════╝██╔══██╗
//        ██████╔╝███████║███████╗█████╗  ██║  ██║
//        ██╔══██╗██╔══██║╚════██║██╔══╝  ██║  ██║
//        ██████╔╝██║  ██║███████║███████╗██████╔╝
//        ╚═════╝ ╚═╝  ╚═╝╚══════╝╚══════╝╚═════╝
//
//        ███████╗██╗  ██╗██╗  ██╗ █████╗ ██╗   ██╗███████╗████████╗██╗██╗   ██╗███████╗
//        ██╔════╝╚██╗██╔╝██║  ██║██╔══██╗██║   ██║██╔════╝╚══██╔══╝██║██║   ██║██╔════╝
//        █████╗   ╚███╔╝ ███████║███████║██║   ██║███████╗   ██║   ██║██║   ██║█████╗█████╗
//        ██╔══╝   ██╔██╗ ██╔══██║██╔══██║██║   ██║╚════██║   ██║   ██║╚██╗ ██╔╝██╔══╝╚════╝
//        ███████╗██╔╝ ██╗██║  ██║██║  ██║╚██████╔╝███████║   ██║   ██║ ╚████╔╝ ███████╗
//        ╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝ ╚══════╝   ╚═╝   ╚═╝  ╚═══╝  ╚══════╝
//
//        ███╗   ██╗███████╗███████╗███████╗
//        ████╗  ██║██╔════╝██╔════╝██╔════╝
//        ██╔██╗ ██║█████╗  ███████╗███████╗
//        ██║╚██╗██║██╔══╝  ╚════██║╚════██║
//        ██║ ╚████║███████╗███████║███████║
//        ╚═╝  ╚═══╝╚══════╝╚══════╝╚══════╝
//
//
//
//
//
//
//
//
//        2.2.20: Experimental, -Xdata-flow-based-exhaustiveness
//        2.3.0:  Stable
//
//        [KEEP]:
//        https://github.com/Kotlin/KEEP/blob/main/proposals/KEEP-0442-dfa-exhaustiveness.md
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
//      "The exhaustiveness algorithm for `when` statements works,
//       is safe, but only "works in a local fashion, by inspecting
//       the branches of each when separately, and comparing it with
//       the (known) type of the subject.
//
//       Alas, this analysis fails to consider cases where it is
//       statically known that a subject may not have a certain
//       form (usually because of an early return).
//
//      - Data flow-based exhaustiveness checking | Motivation
//      https://github.com/Kotlin/KEEP/blob/main/proposals/KEEP-0442-dfa-exhaustiveness.md
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

enum class BridgeDutyStatus {
  ConfinedToQuarters,
  FullDuty,
  LightDuty,
  MonitoredDuty,
}

/**
 * Before.
 */
fun fitForBridgeDuty(crewMember: CrewMember): BridgeDutyStatus {
  val healthStatus = crewMember.healthStatus

  if (healthStatus == HealthStatus.Sick || healthStatus == HealthStatus.MedicalLeave) {
    return BridgeDutyStatus.ConfinedToQuarters
  }

  return when (healthStatus) {
    HealthStatus.Healthy -> BridgeDutyStatus.FullDuty
    HealthStatus.Injured -> BridgeDutyStatus.LightDuty
    HealthStatus.InRecovery -> BridgeDutyStatus.MonitoredDuty
    else -> BridgeDutyStatus.ConfinedToQuarters
  }
}

//
//
// The data flow-based analysis for Smart Casting can be leveraged
// to augment exhaustiveness checking with the negative information.
//
//

/**
 * After.
 */
fun fitForBridgeDuties(crewMember: CrewMember): BridgeDutyStatus {
  val healthStatus: HealthStatus = crewMember.healthStatus // Assignment.

  if (healthStatus == HealthStatus.Sick || healthStatus == HealthStatus.MedicalLeave) {
    return BridgeDutyStatus.ConfinedToQuarters
  } // `healthStatus` != Sick, != MedicalLeave

  return when (healthStatus) {
    HealthStatus.Healthy -> BridgeDutyStatus.FullDuty
    // `healthStatus` != Sick, != MedicalLeave, != Healthy
    HealthStatus.Injured -> BridgeDutyStatus.LightDuty
    // `healthStatus` != Sick, != MedicalLeave, != Healthy, != Injured
    HealthStatus.InRecovery -> BridgeDutyStatus.MonitoredDuty
    // `healthStatus`!= Healthy, != Injured, != InRecovery, + != Sick, != MedicalLeave,
  }
}

//
//
//        (As I understand it), K2 helps make this possible.
//
//