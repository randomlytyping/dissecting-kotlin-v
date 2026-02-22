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
import rt.ex.CrewMember.Division.*
import rt.ex.CrewMember.Species
import rt.ex.CrewMember.Unknown
import rt.ex.CrewMember.Weapon.Batleth
import rt.ex.JeanLucPicard
import rt.ex.Ship

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
 *     ██████╗ ██╗   ██╗ █████╗ ██████╗ ██████╗
 *    ██╔════╝ ██║   ██║██╔══██╗██╔══██╗██╔══██╗
 *    ██║  ███╗██║   ██║███████║██████╔╝██║  ██║
 *    ██║   ██║██║   ██║██╔══██║██╔══██╗██║  ██║
 *    ╚██████╔╝╚██████╔╝██║  ██║██║  ██║██████╔╝
 *     ╚═════╝  ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═════╝
 *
 *     ██████╗ ██████╗ ███╗   ██╗██████╗ ██╗████████╗██╗ ██████╗ ███╗   ██╗███████╗
 *    ██╔════╝██╔═══██╗████╗  ██║██╔══██╗██║╚══██╔══╝██║██╔═══██╗████╗  ██║██╔════╝
 *    ██║     ██║   ██║██╔██╗ ██║██║  ██║██║   ██║   ██║██║   ██║██╔██╗ ██║███████╗
 *    ██║     ██║   ██║██║╚██╗██║██║  ██║██║   ██║   ██║██║   ██║██║╚██╗██║╚════██║
 *    ╚██████╗╚██████╔╝██║ ╚████║██████╔╝██║   ██║   ██║╚██████╔╝██║ ╚████║███████║
 *     ╚═════╝ ╚═════╝ ╚═╝  ╚═══╝╚═════╝ ╚═╝   ╚═╝   ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚══════╝
 *
 *
 *
 *
 *
 *
 *    2.1.0: Experimental, -Xwhen-guards
 *    2.2.0: Stable
 *
 * 
 *    Documentation:
 *    https://kotlinlang.org/docs/control-flow.html#guard-conditions-in-when-expressions
 *
 *    [KEEP]:
 *    https://github.com/Kotlin/KEEP/blob/master/proposals/guards.md
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
//        Using a `when` with a subject provides advantages:
//        - exhaustiveness
//        - semantics of "this code branches on this subject"
//
//
//        However, for a `when` with a subject, each branch can only
//        depend on one condition over the subject.
//
//
//        Guards allow for additional conditions, delineated by an `if`.
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
 * `when` without subject.
 *
 * Readable but:
 * - Loses sealed hierarchy exhaustiveness.
 * - Obfuscates the fact that the code is branching on the `crewMember`.
 */
fun startAShift(crewMember: CrewMember, section: Ship.Section) {
  when {
    crewMember is ScienceOfficer && section is Bridge
      -> crewMember.scanForLifeforms()

    crewMember is ScienceOfficer && section is Astrometrics
      -> crewMember.scanForInterstellarAnomalies()

    crewMember is CommunicationsOfficer && section is Bridge
      -> crewMember.awaitIncomingTransmission()

    crewMember is SecurityOfficer && section is Gymnasium
        && crewMember.species == Species.Klingon ->
      crewMember.armWith(Batleth)
  }
}

/**
 * `when` with subject with guards.
 *
 * Style note: parentheses can improve readability even though they are
 * unnecessary.
 */
fun startShift(crewMember: CrewMember, section: Ship.Section) {
  when (crewMember) {
    is ScienceOfficer if section is Bridge -> crewMember.scanForLifeforms()
    is ScienceOfficer if section is Astrometrics -> crewMember.scanForInterstellarAnomalies()
    is CommunicationsOfficer if section is Bridge -> crewMember.awaitIncomingTransmission()
    is SecurityOfficer if section is Gymnasium && crewMember.species == Species.Klingon ->
      crewMember.armWith(Batleth)
    is SecurityOfficer -> crewMember.armWith(CrewMember.Weapon.Phaser)
    else -> crewMember.checkIn()
  }
}

/**
 * Plus:
 * - Guard conditions can leverage smart casting.
 * - `else` entries can also have guards.
 *
 * Minus:
 * - Cannot use commas to group branches.
 */
fun preferredBeverage(crewMember: CrewMember): String =
  when (val species = crewMember.species) {
    is Species.Hybrid if Species.Klingon in species.species -> "Bloodwine"
    Species.Klingon -> "Bloodwine"
    Species.Vulcan -> "Vulcan brandy"
    Species.Human if crewMember == JeanLucPicard -> "Earl Grey, hot"
    Species.Human -> "Black coffee"
    else if !crewMember.isAllergicTo("H20") -> "H20"
    else -> "Unknown"
  }


/**
 * Branches with guards do not count towards exhaustiveness.
 */
fun statusReport(ship: Ship): String =
  when (ship.status) {
    Ship.Status.Destroyed -> "Ship lost."
    Ship.Status.Offline -> "Computers down."
    Ship.Status.HeavilyDamaged if ship.shuttles.isEmpty() -> "Heavily damaged; crew stranded on ship."
    Ship.Status.HeavilyDamaged -> "Heavily damaged; crew evacuated and abandoned ship."
    Ship.Status.Damaged if ship.commandingOfficer == Unknown -> "Damaged. Command officer status unknown."
    Ship.Status.Damaged -> "Damaged but functional."
    Ship.Status.Operational -> "Operational."
    Ship.Status.FullyOperational -> "All systems nominal."
  }

//
//
// Guard KEEP:
//
// "Clarity over power"
// https://github.com/Kotlin/KEEP/blob/master/proposals/guards.md#clarity-over-power
//
//

fun startShifting(member: CrewMember, section: Ship.Section) {
  when(member) {
    is ScienceOfficer -> when(section) {
      Bridge -> member.scanForLifeforms()
      Astrometrics -> member.scanForInterstellarAnomalies()
      else -> member.checkIn()
    }
    is CommunicationsOfficer -> when(section) {
      Bridge -> member.awaitIncomingTransmission()
      else -> member.checkIn()
    }
    is SecurityOfficer -> when(section) {
      Gymnasium if member.species == Species.Klingon -> member.armWith(Batleth)
      Gymnasium -> member.armWith(CrewMember.Weapon.Phaser)
      else -> member.checkIn()
    }

    is MedicalStaff -> member.beginRounds()

    is LineOfficer -> member.gatherSeniorOfficers()

    else -> member.checkIn()
  }
}
