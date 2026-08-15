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

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import rt.ex.CrewMember.*
import rt.ex.CrewMember.Division.MedicalStaff
import rt.ex.Ship.Section.*
import rt.ex.Ship.Shuttle

const val UNKNOWN_YEAR = -1

const val UNIFORM_COLOR_COMMAND    = "0xFFCC0000"
const val UNIFORM_COLOR_OPERATIONS = "0xFFCC8800"
const val UNIFORM_COLOR_SCIENCE    = "0xFF0055BB"

// region // === Domain Types ===

// region // == Color ==

enum class Color(val hex: String) {
  Red(UNIFORM_COLOR_COMMAND),
  Blue(UNIFORM_COLOR_SCIENCE),
  Yellow(UNIFORM_COLOR_OPERATIONS),
}

// endregion

// region // == Show ==

enum class Show {
  Enterprise,
  StrangeNewWorlds,
  TOS,
  TNG,
  DS9,
  Voyager,
  LowerDecks,
}

// endregion

// region // == Holodeck Program ==

interface HolodeckProgram {
  val programmer: List<CrewMember>

  data class DixonHill(
    override val programmer: List<CrewMember> = persistentListOf(JeanLucPicard),
  ) : HolodeckProgram

  data class CafeDesArtistes(
    override val programmer: List<CrewMember> = persistentListOf(JeanLucPicard),
  ) : HolodeckProgram

  data class SherlockHolmes(
    override val programmer: List<CrewMember> = persistentListOf(GeordiLaForge),
  ) : HolodeckProgram

  data class KlingonCalisthenics(
    override val programmer: List<CrewMember> = persistentListOf(Worf),
  ) : HolodeckProgram

  data class BourbonStreetBarn(
    override val programmer: List<CrewMember> = persistentListOf(WilliamRiker),
  ) : HolodeckProgram

  data class JulianBashirSecretAgent(
    override val programmer: List<CrewMember> = persistentListOf(JulianBashir),
  ) : HolodeckProgram

  data class AlamoReconstruction(
    override val programmer: List<CrewMember> = persistentListOf(MilesOBrien, JulianBashir),
  ) : HolodeckProgram

  data class VicFontainesLasVegas(
    override val programmer: List<CrewMember> = persistentListOf(), // Felix — not a crew member
  ) : HolodeckProgram

  data class CaptainProton(
    override val programmer: List<CrewMember> = persistentListOf(TomParis),
  ) : HolodeckProgram

  data class ChezSandrine(
    override val programmer: List<CrewMember> = persistentListOf(TomParis),
  ) : HolodeckProgram

  data class FairHaven(
    override val programmer: List<CrewMember> = persistentListOf(TomParis),
  ) : HolodeckProgram

  data class JanewayLambdaOne(
    override val programmer: List<CrewMember> = persistentListOf(KathrynJaneway),
  ) : HolodeckProgram

  data class LeonardoDaVincisStudio(
    override val programmer: List<CrewMember> = persistentListOf(KathrynJaneway),
  ) : HolodeckProgram

  data class CrisisPoint(
    override val programmer: List<CrewMember> = persistentListOf(BradBoimler, BeckettMariner),
  ) : HolodeckProgram
}

// endregion

// endregion

// region // === Starship ===

data class Ship(
  val name: String,
  val shipClass: String,
  val registry: String,
  val serviceStart: Int,
  val serviceEnd: Int,
  val commandingOfficer: CrewMember,
  val firstOfficer: CrewMember,
  val sections: ImmutableList<Section>,
  val shuttles: ImmutableList<Shuttle> = persistentListOf(),
  val status: Status = Status.FullyOperational,
) {
  data class Shuttle(
    val name: String,
    val registry: String,
    val shuttleClass: String,
  )

  enum class Status {
    Damaged,
    HeavilyDamaged,
    Operational,
    FullyOperational,
    Offline,
    Destroyed,
  }

  sealed interface Section {
    data object Airlock : Section
    data object AirponicsBay : Section
    data object Arboretum : Section
    data object Armory : Section
    data object Astrometrics : Section
    data object AuxiliaryControl : Section
    data object BattleBridge : Section
    data object Bridge : Section
    data object BriefingRoom : Section
    data object Brig : Section
    data object CaptainsDiningRoom : Section
    data object CaptainsReadyRoom : Section
    data object CargoBay : Section
    data object CetaceanOps : Section
    data object Chapel : Section
    data object ComputerCore : Section
    data object ConferenceRoom : Section
    data object CounselorsOffice : Section
    data object DecontaminationChamber : Section
    data object DeflectorControl : Section
    data object Engineering : Section
    data object EnvironmentalControl : Section
    data object Galley : Section
    data object Gymnasium : Section

    data class Holodeck(val number: Int = 1) : Section {
      fun beginProgram(program: HolodeckProgram) {}
      fun endProgram() {}
      fun turnOnSafetyProtocols() {}
      fun turnOffSafetyProtocols() {}
      fun freezeProgram() {}
      fun arch(): Boolean = true
    }

    data object Infirmary : Section
    data object JefferiesTube : Section
    data object LaunchBay : Section
    data object MachineShop : Section
    data object MainShuttlebay : Section
    data object MessHall : Section
    data object NavigationalControl : Section
    data object Nursery : Section
    data object ObservationDeck : Section
    data object ObservationLounge : Section
    data object OfficersMess : Section
    data object PhaserControl : Section
    data object PhaserRange : Section
    data object PlasmaRelayRoom : Section
    data object Quarters : Section
    data object RecreationDeck : Section
    data object RecreationRoom : Section
    data object RepairBay : Section
    data object ScienceLab : Section
    data object Shuttlebay : Section
    data class Sickbay(
      val chiefMedicalOfficer: CrewMember,
      val emergencyMedicalHologram: Species.Hologram?,
    ) : Section {
      fun doRounds(): List<CrewMember> = listOf()
      fun bioScan(crewMember: CrewMember) {}

      fun activateEmergencyForceFields() {}
    }

    data object SituationRoom : Section
    data object SquashCourt : Section
    data object StellarCartography : Section
    data object TenForward : Section
    data object TorpedoBay : Section
    data object TransporterRoom : Section {
      fun energize() {}
      fun beamUp(vararg crewMembers: CrewMember) {}
      fun lockInCoordinates(crewMember: CrewMember) {}
    }

    data object WeaponsLocker : Section
  }
}

// endregion

// region // === Crew Member ===

sealed interface CrewMember {
  val name: String
  val aliases: ImmutableList<String>
  val rank: Rank
  val species: Species
  val languages: ImmutableList<String>
  val affiliations: ImmutableList<Affiliation>
  val appearances: ImmutableList<Show>
  val serviceStart: Int
  val serviceEnd: Int
  val academyGraduation: Int
  val uniformColor: Color
  val healthStatus: HealthStatus get() = HealthStatus.Healthy

  fun returnToShip() { println("Beam me up.")}
  fun reportTo(section: Ship.Section) { }
  fun checkIn() {}
  fun contactBridge() { println("<name> to bridge.")}
  fun isAllergicTo(food: String): Boolean = false

  enum class Rank {
    None,
    Crewman,
    PettyOfficer,
    ChiefPettyOfficer,
    MasterChiefPettyOfficer,
    Ensign,
    LieutenantJuniorGrade,
    Lieutenant,
    Constable,
    Major,
    LieutenantCommander,
    SubCommander,
    Commander,
    Colonel,
    Captain,
    Commodore,
    RearAdmiral,
    ViceAdmiral,
    Admiral,
    FleetAdmiral;
  }

  sealed interface Division {
    val uniformColor: Color

    sealed interface LineOfficer : Division {
      override val uniformColor: Color get() = Color.Red

      fun gatherSeniorOfficers() {}
      fun passOnCommand() {}
    }
    sealed interface FlightControl : Division {
      override val uniformColor: Color get() = Color.Red
    }

    sealed interface CommunicationsOfficer : Division {
      override val uniformColor: Color get() = Color.Yellow
      fun awaitIncomingTransmission() {}
    }
    sealed interface Engineer : Division {
      override val uniformColor: Color get() = Color.Yellow

      fun repairShuttle() {}
    }
    sealed interface SecurityOfficer : Division {
      override val uniformColor: Color get() = Color.Yellow
      fun checkPerimeter() {}

      fun armWith(weapon: Weapon) {}
    }
    sealed interface TacticalOfficer : Division {
      override val uniformColor: Color get() = Color.Yellow

      fun checkShields() {}
      fun reroutePower(from: Ship.Section, to: Ship.Section) {}
    }

    sealed interface Counselor : Division {
      override val uniformColor: Color get() = Color.Blue
    }
    sealed interface MedicalStaff : Division {
      override val uniformColor: Color get() = Color.Blue

      fun treat(crewMember: CrewMember) {}
      fun beginRounds() {}
    }
    sealed interface ScienceOfficer : Division {
      override val uniformColor: Color get() = Color.Blue
      val specialties: ImmutableList<Specialty>
      fun scanForLifeforms() {}
      fun analyzeAtmosphere() {}

      fun scanForInterstellarAnomalies() {}
    }
  }

  sealed interface Species {
    data object Aenar : Species
    data object Android : Species
    data object Bajoran : Species
    data object Betazoid : Species
    data object Bolian : Species
    data object Cardassian : Species
    data object Changeling : Species
    data object ElAurian : Species
    data object Ferengi : Species
    data class Hologram(val designation: String) : Species {
      fun initiateEmergencyMedicalHologram() {
        println("Please state the nature of the medical emergency")
      }
    }
    data object Human : Species
    data object Illyrian : Species
    data object Lanthanite : Species
    data object Klingon : Species
    data object Orion : Species
    data object Trill : Species
    data object Vulcan : Species
    data class Hybrid(val species: ImmutableList<Species>) : Species
  }

  enum class Affiliation {
    BajoranMilitia,
    BorgCollective,
    CardassianUnion,
    Federation,
    KlingonEmpire,
    Maquis,
    Starfleet,
    VulcanHighCommand,
  }

  enum class Weapon {
    Phaser,
    PhaserRifle,
    PhotonTorpedo,
    QuantumTorpedo,
    Disruptor,
    PolaronBeam,
    Batleth,
    MekLeth,
    DKtahg,
    Lirpa,
  }

  enum class Specialty {
    Astrometrics,
    Astrophysics,
    ComputerScience,
    Cybernetics,
    Exobiology,
    ExoticPropulsion,
    OrionFolkMedicine,
    SubspaceAnomalies,
  }

  enum class HealthStatus {
    Healthy,
    Injured,
    MedicalLeave,
    InRecovery,
    Sick,
  }

  enum class BridgeDutyStatus {
    ConfinedToQuarters,
    FullDuty,
    LightDuty,
    MonitoredDuty,
  }

  data object Unknown : CrewMember, Division.LineOfficer {
    override val name = "Unknown"
    override val aliases = persistentListOf<String>()
    override val rank = Rank.None
    override val species = Species.Human
    override val languages = persistentListOf<String>()
    override val affiliations = persistentListOf<Affiliation>()
    override val appearances = persistentListOf<Show>()
    override val serviceStart = UNKNOWN_YEAR
    override val serviceEnd = UNKNOWN_YEAR
    override val academyGraduation = UNKNOWN_YEAR
    override val uniformColor = Color.Red
    override fun checkIn() = Unit
    override fun gatherSeniorOfficers() = Unit
    override fun passOnCommand() = Unit
  }
}

// endregion

// region // === Crew Members ===

// region // == Crew Members: Enterprise ==

data object CharlesTucker : CrewMember, Division.Engineer {
  override val name = "Charles Tucker III"
  override val aliases = persistentListOf("Trip")
  override val rank = Rank.Commander
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard")
  override val affiliations = persistentListOf(Affiliation.Starfleet)
  override val appearances = persistentListOf(Show.Enterprise)
  override val serviceStart = 2151
  override val serviceEnd = 2161
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Yellow
  override fun repairShuttle() {}
}

data object HoshiSato : CrewMember, Division.CommunicationsOfficer {
  override val name = "Hoshi Sato"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Ensign
  override val species = Species.Human
  override val languages = persistentListOf(
    "Federation Standard",
    "Japanese",
    "Vulcan",
    "Klingon",
    "Andorian",
    "Denobulan",
  )
  override val affiliations = persistentListOf(Affiliation.Starfleet)
  override val appearances = persistentListOf(Show.Enterprise)
  override val serviceStart = 2151
  override val serviceEnd = 2161
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Yellow
  override fun awaitIncomingTransmission() {}
}

data object JonathanArcher : CrewMember, Division.LineOfficer {
  override val name = "Jonathan Archer"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Admiral
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.Enterprise)
  override val serviceStart = 2151
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Red
}

data object MalcolmReed : CrewMember, Division.TacticalOfficer {
  override val name = "Malcolm Reed"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Lieutenant
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard")
  override val affiliations = persistentListOf(Affiliation.Starfleet)
  override val appearances = persistentListOf(Show.Enterprise)
  override val serviceStart = 2151
  override val serviceEnd = 2161
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Yellow
  override fun checkShields() {}
  override fun reroutePower(from: Ship.Section, to: Ship.Section) {}
}

data object TPol : CrewMember, Division.ScienceOfficer {
  override val name = "T'Pol"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.SubCommander
  override val species = Species.Vulcan
  override val languages = persistentListOf("Vulcan", "Federation Standard", "Klingon", "Romulan")
  override val affiliations = persistentListOf(
    Affiliation.VulcanHighCommand,
    Affiliation.Starfleet,
  )
  override val appearances = persistentListOf(Show.Enterprise)
  override val serviceStart = 2151
  override val serviceEnd = 2161
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Blue
  override val specialties = persistentListOf(Specialty.Astrophysics)
  override fun scanForLifeforms() {}
  override fun analyzeAtmosphere() {}
  override fun scanForInterstellarAnomalies() {
    TODO("Not yet implemented")
  }
}

data object TravisMayweather : CrewMember, Division.FlightControl {
  override val name = "Travis Mayweather"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Ensign
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard")
  override val affiliations = persistentListOf(Affiliation.Starfleet)
  override val appearances = persistentListOf(Show.Enterprise)
  override val serviceStart = 2151
  override val serviceEnd = 2161
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Red
}

// endregion

// region // == Crew Members: Strange New Worlds ==

data object ChristineChapel : CrewMember, MedicalStaff {
  override val name = "Christine Chapel"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Lieutenant
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.TOS, Show.StrangeNewWorlds)
  override val serviceStart = 2259
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Blue
}

data object ChristopherPike : CrewMember, Division.LineOfficer {
  override val name = "Christopher Pike"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Captain
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.StrangeNewWorlds)
  override val serviceStart = 2245
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = 2245
  override val uniformColor = Color.Red
}

data object EricaOrtegas : CrewMember, Division.FlightControl {
  override val name = "Erica Ortegas"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Lieutenant
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard", "Spanish")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.StrangeNewWorlds)
  override val serviceStart = 2256
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Red
}

data object JennaMitchell : CrewMember, Division.CommunicationsOfficer {
  override val name = "Jenna Mitchell"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Lieutenant
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.StrangeNewWorlds)
  override val serviceStart = UNKNOWN_YEAR
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Yellow
  override fun awaitIncomingTransmission() {}
}

data object JamesTKirk : CrewMember, Division.LineOfficer {
  override val name = "James Tiberius Kirk"
  override val aliases = persistentListOf("Jim")
  override val rank = Rank.Captain
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard", "Klingon")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.TOS, Show.StrangeNewWorlds)
  override val serviceStart = 2254
  override val serviceEnd = 2293
  override val academyGraduation = 2254
  override val uniformColor = Color.Red
}

data object JosephMBenga : CrewMember, MedicalStaff {
  override val name = "Joseph M'Benga"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Commander
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.TOS, Show.StrangeNewWorlds)
  override val serviceStart = 2259
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Blue
}

data object LaAnNoonenSingh : CrewMember, Division.SecurityOfficer {
  override val name = "La'an Noonien-Singh"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.LieutenantCommander
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.StrangeNewWorlds)
  override val serviceStart = 2256
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Yellow
  override fun checkPerimeter() {}
  override fun armWith(weapon: Weapon) {}
}

data object MarieBatel : CrewMember, Division.LineOfficer {
  override val name = "Marie Batel"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Captain
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.StrangeNewWorlds)
  override val serviceStart = UNKNOWN_YEAR
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Red
}

data object Pelia : CrewMember, Division.Engineer {
  override val name = "Pelia"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Commander
  override val species = Species.Lanthanite
  override val languages = persistentListOf("Federation Standard")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.StrangeNewWorlds)
  override val serviceStart = UNKNOWN_YEAR
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Yellow
  override fun repairShuttle() {}
}

data object NyotaUhura : CrewMember, Division.CommunicationsOfficer {
  override val name = "Nyota Uhura"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Commander
  override val species = Species.Human
  override val languages = persistentListOf(
    "Federation Standard",
    "Swahili",
    "Vulcan",
    "Klingon",
    "Romulan",
  )
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.TOS, Show.StrangeNewWorlds)
  override val serviceStart = 2259
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = 2259
  override val uniformColor = Color.Yellow
  override fun awaitIncomingTransmission() {}
}

data object Spock : CrewMember, Division.ScienceOfficer {
  override val name = "Spock"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Commander
  override val species = Species.Hybrid(persistentListOf(Species.Vulcan, Species.Human))
  override val languages = persistentListOf(
    "Federation Standard",
    "Vulcan",
    "Klingon",
    "Romulan",
  )
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.TOS, Show.TNG, Show.StrangeNewWorlds)
  override val serviceStart = 2250
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Blue
  override val specialties = persistentListOf(Specialty.ComputerScience, Specialty.Astrophysics)
  override fun scanForLifeforms() {}
  override fun analyzeAtmosphere() {}
  override fun scanForInterstellarAnomalies() {}
}

data object UnaChinRiley : CrewMember, Division.LineOfficer {
  override val name = "Una Chin-Riley"
  override val aliases = persistentListOf("Number One")
  override val rank = Rank.Commander
  override val species = Species.Illyrian
  override val languages = persistentListOf("Federation Standard", "Illyrian")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.StrangeNewWorlds)
  override val serviceStart = 2249
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Red
}

// endregion

// region // == Crew Members: The Original Series ==

data object HikaruSulu : CrewMember, Division.FlightControl {
  override val name = "Hikaru Sulu"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Captain
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard", "Japanese")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.TOS)
  override val serviceStart = 2263
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Red
}

data object LeonardMcCoy : CrewMember, MedicalStaff {
  override val name = "Leonard Horatio McCoy"
  override val aliases = persistentListOf("Bones")
  override val rank = Rank.Commander
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.TOS)
  override val serviceStart = 2266
  override val serviceEnd = 2293
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Blue
}

data object MontgomeryScott : CrewMember, Division.Engineer {
  override val name = "Montgomery Scott"
  override val aliases = persistentListOf("Scotty")
  override val rank = Rank.Commander
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.TOS, Show.TNG, Show.StrangeNewWorlds)
  override val serviceStart = 2241
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Yellow
  override fun repairShuttle() {}
}

data object PavelChekov : CrewMember, Division.FlightControl {
  override val name = "Pavel Andreievich Chekov"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Commander
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard", "Russian")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.TOS)
  override val serviceStart = 2267
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Red
}

// endregion

// region // == Crew Members: The Next Generation ==

data object BeverlyCrusher : CrewMember, MedicalStaff {
  override val name = "Beverly Cheryl Crusher"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Commander
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.TNG)
  override val serviceStart = 2350
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Blue
}

data object Data : CrewMember, Division.CommunicationsOfficer {
  override val name = "Data"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.LieutenantCommander
  override val species = Species.Android
  override val languages = persistentListOf(
    "Federation Standard",
    "Klingon",
    "Romulan",
    "Vulcan",
  )
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.TNG)
  override val serviceStart = 2345
  override val serviceEnd = 2379
  override val academyGraduation = 2345
  override val uniformColor = Color.Yellow
  override fun awaitIncomingTransmission() {}
}

data object DeannaTroi : CrewMember, Division.Counselor {
  override val name = "Deanna Troi"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Commander
  override val species = Species.Hybrid(persistentListOf(Species.Human, Species.Betazoid))
  override val languages = persistentListOf("Federation Standard", "Betazoid")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.TNG, Show.LowerDecks)
  override val serviceStart = 2359
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Blue
}

data object GeordiLaForge : CrewMember, Division.Engineer {
  override val name = "Geordi La Forge"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.LieutenantCommander
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.TNG)
  override val serviceStart = 2357
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Yellow
  override fun repairShuttle() {}
}

data object JeanLucPicard : CrewMember, Division.LineOfficer {
  override val name = "Jean-Luc Picard"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Captain
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard", "French", "Klingon")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.TNG)
  override val serviceStart = 2327
  override val serviceEnd = 2399
  override val academyGraduation = 2327
  override val uniformColor = Color.Red
}

data object MilesOBrien : CrewMember, Division.Engineer {
  override val name = "Miles Edward O'Brien"
  override val aliases = persistentListOf("Chief")
  override val rank = Rank.MasterChiefPettyOfficer
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.TNG, Show.DS9)
  override val serviceStart = 2350
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Yellow
  override fun repairShuttle() {}
}

data object WilliamRiker : CrewMember, Division.LineOfficer {
  override val name = "William Thomas Riker"
  override val aliases = persistentListOf("Number One")
  override val rank = Rank.Captain
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.TNG, Show.LowerDecks)
  override val serviceStart = 2357
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = 2357
  override val uniformColor = Color.Red
}

data object Worf : CrewMember, Division.SecurityOfficer {
  override val name = "Worf, son of Mogh"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Commander
  override val species = Species.Klingon
  override val languages = persistentListOf("Federation Standard", "Klingon")
  override val affiliations = persistentListOf(
    Affiliation.Starfleet,
    Affiliation.Federation,
    Affiliation.KlingonEmpire,
  )
  override val appearances = persistentListOf(Show.TNG, Show.DS9)
  override val serviceStart = 2361
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = 2361
  override val uniformColor = Color.Yellow
  override fun checkPerimeter() {}
  override fun armWith(weapon: Weapon) {}
}

// endregion

// region // == Crew Members: Deep Space Nine ==

data object BenjaminSisko : CrewMember, Division.LineOfficer {
  override val name = "Benjamin Lafayette Sisko"
  override val aliases = persistentListOf("The Emissary")
  override val rank = Rank.Captain
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.DS9)
  override val serviceStart = 2354
  override val serviceEnd = 2375
  override val academyGraduation = 2354
  override val uniformColor = Color.Red
}

data object JadziaDax : CrewMember, Division.ScienceOfficer {
  override val name = "Jadzia Dax"
  override val aliases = persistentListOf("Old Man")
  override val rank = Rank.LieutenantCommander
  override val species = Species.Trill
  override val languages = persistentListOf(
    "Federation Standard",
    "Klingon",
    "Bajoran",
    "Vulcan",
  )
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.DS9)
  override val serviceStart = 2369
  override val serviceEnd = 2374
  override val academyGraduation = 2368
  override val uniformColor = Color.Blue
  override val specialties = persistentListOf(Specialty.Exobiology, Specialty.Astrophysics)
  override fun scanForLifeforms() {}
  override fun analyzeAtmosphere() {}
  override fun scanForInterstellarAnomalies() {}
}

data object JulianBashir : CrewMember, MedicalStaff {
  override val name = "Julian Subatoi Bashir"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Lieutenant
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard", "Cardassian")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.DS9)
  override val serviceStart = 2368
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = 2368
  override val uniformColor = Color.Blue
}

data object KiraNerys : CrewMember, Division.LineOfficer {
  override val name = "Kira Nerys"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Colonel
  override val species = Species.Bajoran
  override val languages = persistentListOf("Bajoran", "Federation Standard")
  override val affiliations = persistentListOf(Affiliation.BajoranMilitia, Affiliation.Federation)
  override val appearances = persistentListOf(Show.DS9)
  override val serviceStart = 2369
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Red
}

data object Odo : CrewMember, Division.SecurityOfficer {
  override val name = "Odo"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Constable
  override val species = Species.Changeling
  override val languages = persistentListOf("Federation Standard", "Bajoran", "Cardassian")
  override val affiliations = persistentListOf(Affiliation.BajoranMilitia, Affiliation.Federation)
  override val appearances = persistentListOf(Show.DS9)
  override val serviceStart = 2365
  override val serviceEnd = 2375
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Yellow
  override fun checkPerimeter() {}
  override fun armWith(weapon: Weapon) {}
}

// endregion

// region // == Crew Members: Voyager ==

data object BElannaTorres : CrewMember, Division.Engineer {
  override val name = "B'Elanna Torres"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Lieutenant
  override val species = Species.Hybrid(persistentListOf(Species.Human, Species.Klingon))
  override val languages = persistentListOf("Federation Standard", "Klingon")
  override val affiliations = persistentListOf(
    Affiliation.Maquis,
    Affiliation.Starfleet,
    Affiliation.Federation,
  )
  override val appearances = persistentListOf(Show.Voyager)
  override val serviceStart = 2371
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Yellow
  override fun repairShuttle() {}
}

data object Chakotay : CrewMember, Division.LineOfficer {
  override val name = "Chakotay"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Commander
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard")
  override val affiliations = persistentListOf(
    Affiliation.Starfleet,
    Affiliation.Federation,
    Affiliation.Maquis,
  )
  override val appearances = persistentListOf(Show.Voyager)
  override val serviceStart = 2358
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = 2358
  override val uniformColor = Color.Red
}

data object HarryKim : CrewMember, Division.CommunicationsOfficer {
  override val name = "Harry S.L. Kim"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Ensign
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.Voyager)
  override val serviceStart = 2371
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = 2371
  override val uniformColor = Color.Yellow
  override fun awaitIncomingTransmission() {}
}

data object KathrynJaneway : CrewMember, Division.LineOfficer {
  override val name = "Kathryn Janeway"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Admiral
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.Voyager, Show.LowerDecks)
  override val serviceStart = 2355
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = 2355
  override val uniformColor = Color.Red
}

data object SevenOfNine : CrewMember, Division.ScienceOfficer {
  override val name = "Seven of Nine"
  override val aliases = persistentListOf("Annika Hansen")
  override val rank = Rank.None
  override val species = Species.Human
  override val languages = persistentListOf(
    "Federation Standard",
    "Klingon",
    "Romulan",
    "Cardassian",
    "Vulcan",
  )
  override val affiliations = persistentListOf(Affiliation.BorgCollective, Affiliation.Federation)
  override val appearances = persistentListOf(Show.Voyager)
  override val serviceStart = 2374
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Blue
  override val specialties = persistentListOf(Specialty.Astrometrics, Specialty.Cybernetics)
  override fun scanForLifeforms() {}
  override fun analyzeAtmosphere() {}
  override fun scanForInterstellarAnomalies() {}
}

data object TomParis : CrewMember, Division.FlightControl {
  override val name = "Thomas Eugene Paris"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Lieutenant
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard")
  override val affiliations = persistentListOf(
    Affiliation.Starfleet,
    Affiliation.Maquis,
    Affiliation.Federation,
  )
  override val appearances = persistentListOf(Show.Voyager)
  override val serviceStart = 2368
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = 2368
  override val uniformColor = Color.Red
}

data object Tuvok : CrewMember, Division.SecurityOfficer {
  override val name = "Tuvok"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.LieutenantCommander
  override val species = Species.Vulcan
  override val languages = persistentListOf("Federation Standard", "Vulcan", "Klingon")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.Voyager)
  override val serviceStart = 2293
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Yellow
  override fun checkPerimeter() {}
  override fun armWith(weapon: Weapon) {}
}

// endregion

// region // == Crew Members: Lower Decks ==

data object BeckettMariner : CrewMember, Division.LineOfficer {
  override val name = "Beckett Mariner"
  override val aliases = persistentListOf("Mariner")
  override val rank = Rank.Ensign
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.LowerDecks)
  override val serviceStart = 2373
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = 2373
  override val uniformColor = Color.Red
}

data object BradBoimler : CrewMember, Division.LineOfficer {
  override val name = "Brad Boimler"
  override val aliases = persistentListOf("Boimler")
  override val rank = Rank.Lieutenant
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.LowerDecks)
  override val serviceStart = 2380
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = 2380
  override val uniformColor = Color.Red
}

data object CarolFreeman : CrewMember, Division.LineOfficer {
  override val name = "Carol Freeman"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Captain
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.LowerDecks)
  override val serviceStart = 2355
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Red
}

data object DVanaTendi : CrewMember, Division.ScienceOfficer {
  override val name = "D'Vana Tendi"
  override val aliases = persistentListOf("Tendi")
  override val rank = Rank.Lieutenant
  override val species = Species.Orion
  override val languages = persistentListOf("Federation Standard", "Orion")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.LowerDecks)
  override val serviceStart = 2380
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = 2380
  override val uniformColor = Color.Blue
  override val specialties = persistentListOf(Specialty.ExoticPropulsion, Specialty.OrionFolkMedicine)
  override fun scanForLifeforms() {}
  override fun analyzeAtmosphere() {}
  override fun scanForInterstellarAnomalies() {}
}

data object JackRansom : CrewMember, Division.LineOfficer {
  override val name = "Jack Ransom"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Commander
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.LowerDecks)
  override val serviceStart = 2360
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Red
}

data object SamRutherford : CrewMember, Division.Engineer {
  override val name = "Sam Rutherford"
  override val aliases = persistentListOf("Rutherford")
  override val rank = Rank.Ensign
  override val species = Species.Human
  override val languages = persistentListOf("Federation Standard")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.LowerDecks)
  override val serviceStart = 2380
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = 2380
  override val uniformColor = Color.Yellow
  override fun repairShuttle() {}
}

data object Shaxs : CrewMember, Division.SecurityOfficer {
  override val name = "Shaxs"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Lieutenant
  override val species = Species.Bajoran
  override val languages = persistentListOf("Federation Standard", "Bajoran")
  override val affiliations = persistentListOf(Affiliation.Starfleet, Affiliation.Federation)
  override val appearances = persistentListOf(Show.LowerDecks)
  override val serviceStart = 2365
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Yellow
  override fun checkPerimeter() {}
  override fun armWith(weapon: Weapon) {}
}

data object TLyn : CrewMember, Division.ScienceOfficer {
  override val name = "T'Lyn"
  override val aliases = persistentListOf<String>()
  override val rank = Rank.Ensign
  override val species = Species.Vulcan
  override val languages = persistentListOf("Federation Standard", "Vulcan")
  override val affiliations = persistentListOf(
    Affiliation.VulcanHighCommand,
    Affiliation.Starfleet,
    Affiliation.Federation,
  )
  override val appearances = persistentListOf(Show.LowerDecks)
  override val serviceStart = 2380
  override val serviceEnd = UNKNOWN_YEAR
  override val academyGraduation = UNKNOWN_YEAR
  override val uniformColor = Color.Blue
  override val specialties = persistentListOf(Specialty.SubspaceAnomalies)
  override fun scanForLifeforms() {}
  override fun analyzeAtmosphere() {}
  override fun scanForInterstellarAnomalies() {}
}

// endregion

val allCrewMembers: ImmutableList<CrewMember> = persistentListOf(
  // Enterprise
  CharlesTucker,
  HoshiSato,
  JonathanArcher,
  MalcolmReed,
  TPol,
  TravisMayweather,
  // Strange New Worlds
  ChristineChapel,
  ChristopherPike,
  EricaOrtegas,
  JennaMitchell,
  JamesTKirk,
  JosephMBenga,
  LaAnNoonenSingh,
  MarieBatel,
  Pelia,
  NyotaUhura,
  Spock,
  UnaChinRiley,
  // The Original Series
  HikaruSulu,
  LeonardMcCoy,
  MontgomeryScott,
  PavelChekov,
  // The Next Generation
  BeverlyCrusher,
  Data,
  DeannaTroi,
  GeordiLaForge,
  JeanLucPicard,
  MilesOBrien,
  WilliamRiker,
  Worf,
  // Deep Space Nine
  BenjaminSisko,
  JadziaDax,
  JulianBashir,
  KiraNerys,
  Odo,
  // Voyager
  BElannaTorres,
  Chakotay,
  HarryKim,
  KathrynJaneway,
  SevenOfNine,
  TomParis,
  Tuvok,
  // Lower Decks
  BeckettMariner,
  BradBoimler,
  CarolFreeman,
  DVanaTendi,
  JackRansom,
  SamRutherford,
  Shaxs,
  TLyn,
)

// endregion


// region // === Ships ===

// region // == Ships: Enterprise ==

val EnterpriseNX01 = Ship(
  name = "Enterprise",
  shipClass = "NX",
  registry = "NX-01",
  serviceStart = 2151,
  serviceEnd = 2161,
  commandingOfficer = JonathanArcher,
  firstOfficer = TPol,
  sections = persistentListOf(
    Armory,
    Astrometrics,
    AuxiliaryControl,
    Bridge,
    Brig,
    CaptainsDiningRoom,
    CaptainsReadyRoom,
    CargoBay,
    ComputerCore,
    DecontaminationChamber,
    Engineering,
    Galley,
    Gymnasium,
    JefferiesTube,
    LaunchBay,
    MachineShop,
    MessHall,
    ObservationDeck,
    Quarters,
    ScienceLab,
    Sickbay(chiefMedicalOfficer = Unknown, emergencyMedicalHologram = null),
    SituationRoom,
    TorpedoBay,
    TransporterRoom,
  ),
  shuttles = persistentListOf(
    Shuttle(name = "Shuttlepod 1", registry = "NX-01/1",  shuttleClass = "NX-class Shuttlepod"),
    Shuttle(name = "Shuttlepod 2", registry = "NX-01/2",  shuttleClass = "NX-class Shuttlepod"),
  ),
)

val enterpriseShips: ImmutableList<Ship> = persistentListOf(
  EnterpriseNX01,
)

// endregion

// region // == Ships: Strange New Worlds ==

val Cayuga = Ship(
  name = "USS Cayuga",
  shipClass = "Constitution",
  registry = "NCC-1557",
  serviceStart = 2245,
  serviceEnd = 2260,
  commandingOfficer = MarieBatel,
  firstOfficer = Unknown,
  sections = persistentListOf(
    Bridge,
    CaptainsReadyRoom,
    Engineering,
    MessHall,
    Quarters,
    Sickbay(chiefMedicalOfficer = Unknown, emergencyMedicalHologram = null),
    TransporterRoom,
  ),
)
val Enterprise = Ship(
  name = "USS Enterprise",
  shipClass = "Constitution",
  registry = "NCC-1701",
  serviceStart = 2245,
  serviceEnd = 2285,
  commandingOfficer = JamesTKirk,
  firstOfficer = Spock,
  sections = persistentListOf(
    Armory,
    AuxiliaryControl,
    BriefingRoom,
    Bridge,
    Brig,
    CaptainsReadyRoom,
    CargoBay,
    Chapel,
    ComputerCore,
    Engineering,
    EnvironmentalControl,
    Galley,
    Gymnasium,
    Holodeck(number = 1), // SNW-era prototype; deactivated after "A Space Adventure Hour"
    JefferiesTube,
    MessHall,
    ObservationDeck,
    PhaserControl,
    Quarters,
    RecreationRoom,
    ScienceLab,
    Shuttlebay,
    Sickbay(chiefMedicalOfficer = LeonardMcCoy, emergencyMedicalHologram = null),
    TransporterRoom,
    WeaponsLocker,
  ),
  shuttles = persistentListOf(
    Shuttle(name = "Galileo",     registry = "NCC-1701/7",  shuttleClass = "Class F"),
    Shuttle(name = "Galileo II",  registry = "NCC-1701/7",  shuttleClass = "Class F"),
    Shuttle(name = "Columbus",    registry = "NCC-1701/2",  shuttleClass = "Class F"),
    Shuttle(name = "Copernicus",  registry = "NCC-1701/12", shuttleClass = "Class F"),
    Shuttle(name = "Einstein",    registry = "NCC-1701/16", shuttleClass = "Class F"),
  ),
)
val Farragut = Ship(
  name = "USS Farragut",
  shipClass = "Bellerophon",
  registry = "NCC-1647",
  serviceStart = 2243,
  serviceEnd = UNKNOWN_YEAR,
  commandingOfficer = Unknown,
  firstOfficer = Unknown,
  sections = persistentListOf(
    Bridge,
    Engineering,
    Quarters,
    Sickbay(chiefMedicalOfficer = Unknown, emergencyMedicalHologram = null),
    TransporterRoom,
  ),
)

val strangeNewWorldsShips: ImmutableList<Ship> = persistentListOf(
  Cayuga,
  Enterprise,
  Farragut,
)

// endregion

// region // == Ships: The Original Series ==

val EnterpriseA = Ship(
  name = "USS Enterprise-A",
  shipClass = "Constitution II",
  registry = "NCC-1701-A",
  serviceStart = 2286,
  serviceEnd = 2293,
  commandingOfficer = JamesTKirk,
  firstOfficer = Spock,
  sections = persistentListOf(
    Armory,
    AuxiliaryControl,
    BriefingRoom,
    Bridge,
    Brig,
    CaptainsReadyRoom,
    CargoBay,
    Chapel,
    ComputerCore,
    ConferenceRoom,
    Engineering,
    EnvironmentalControl,
    Galley,
    Gymnasium,
    JefferiesTube,
    MessHall,
    ObservationDeck,
    OfficersMess,
    PhaserControl,
    Quarters,
    RecreationDeck,
    RecreationRoom,
    ScienceLab,
    Shuttlebay,
    Sickbay(chiefMedicalOfficer = LeonardMcCoy, emergencyMedicalHologram = null),
    TorpedoBay,
    TransporterRoom,
    WeaponsLocker,
  ),
  shuttles = persistentListOf(
    Shuttle(name = "Galileo Five", registry = "NCC-1701-A/05", shuttleClass = "Class F"),
    Shuttle(name = "Copernicus",   registry = "NCC-1701-A/12", shuttleClass = "Class F"),
  ),
)
val Excelsior = Ship(
  name = "USS Excelsior",
  shipClass = "Excelsior",
  registry = "NCC-2000",
  serviceStart = 2285,
  serviceEnd = UNKNOWN_YEAR,
  commandingOfficer = HikaruSulu,
  firstOfficer = Unknown,
  sections = persistentListOf(
    BattleBridge,
    Bridge,
    CargoBay,
    ComputerCore,
    DeflectorControl,
    Engineering,
    EnvironmentalControl,
    Gymnasium,
    MainShuttlebay,
    MessHall,
    ObservationLounge,
    Quarters,
    ScienceLab,
    Sickbay(chiefMedicalOfficer = Unknown, emergencyMedicalHologram = null),
    TorpedoBay,
  ),
)

val theOriginalSeriesShips: ImmutableList<Ship> = persistentListOf(
  Enterprise,
  EnterpriseA,
  Excelsior,
)

// endregion

// region // == Ships: The Next Generation ==

val EnterpriseD = Ship(
  name = "USS Enterprise-D",
  shipClass = "Galaxy",
  registry = "NCC-1701-D",
  serviceStart = 2363,
  serviceEnd = 2371,
  commandingOfficer = JeanLucPicard,
  firstOfficer = WilliamRiker,
  sections = persistentListOf(
    Airlock,
    Arboretum,
    Armory,
    AuxiliaryControl,
    BattleBridge,
    Bridge,
    Brig,
    CaptainsDiningRoom,
    CaptainsReadyRoom,
    CargoBay,
    CetaceanOps,
    Chapel,
    ComputerCore,
    ConferenceRoom,
    CounselorsOffice,
    Engineering,
    Gymnasium,
    Holodeck(number = 1),
    Holodeck(number = 2),
    Holodeck(number = 3),
    Holodeck(number = 4),
    Holodeck(number = 5),
    Holodeck(number = 6),
    Holodeck(number = 7),
    Holodeck(number = 8),
    Holodeck(number = 9),
    Holodeck(number = 10),
    JefferiesTube,
    MainShuttlebay,
    MessHall,
    Nursery,
    ObservationLounge,
    OfficersMess,
    PhaserRange,
    Quarters,
    ScienceLab,
    Shuttlebay,
    Sickbay(chiefMedicalOfficer = BeverlyCrusher, emergencyMedicalHologram = null),
    SituationRoom,
    StellarCartography,
    TenForward,
    TorpedoBay,
    TransporterRoom,
  ),
  shuttles = persistentListOf(
    Shuttle(name = "Galileo",   registry = "NCC-1701-D/05", shuttleClass = "Type-6"),
    Shuttle(name = "Goddard",   registry = "NCC-1701-D/06", shuttleClass = "Type-6"),
    Shuttle(name = "Hawking",   registry = "NCC-1701-D/07", shuttleClass = "Type-6"),
    Shuttle(name = "Magellan",  registry = "NCC-1701-D/09", shuttleClass = "Type-7"),
    Shuttle(name = "Sakharov",  registry = "NCC-1701-D/01", shuttleClass = "Type-15"),
    Shuttle(name = "Onizuka",   registry = "NCC-1701-D/05", shuttleClass = "Type-15"),
    Shuttle(name = "El-Baz",    registry = "NCC-1701-D/12", shuttleClass = "Type-15"),
    Shuttle(name = "Calypso",   registry = "NCC-1701-D",    shuttleClass = "Captain's Yacht"),
  ),
)
val EnterpriseE = Ship(
  name = "USS Enterprise-E",
  shipClass = "Sovereign",
  registry = "NCC-1701-E",
  serviceStart = 2372,
  serviceEnd = UNKNOWN_YEAR,
  commandingOfficer = JeanLucPicard,
  firstOfficer = WilliamRiker,
  sections = persistentListOf(
    Armory,
    Astrometrics,
    Bridge,
    CaptainsReadyRoom,
    DeflectorControl,
    Engineering,
    EnvironmentalControl,
    Holodeck(number = 1),
    Holodeck(number = 2),
    Holodeck(number = 3),
    Holodeck(number = 4),
    MainShuttlebay,
    ObservationLounge,
    OfficersMess,
    PhaserControl,
    Quarters,
    RecreationDeck,
    Shuttlebay,
    Sickbay(chiefMedicalOfficer = BeverlyCrusher, emergencyMedicalHologram = Species.Hologram("EMH Mark II")),
    StellarCartography,
    TorpedoBay,
  ),
  shuttles = persistentListOf(
    Shuttle(name = "Argo",     registry = "NCC-1701-E/01", shuttleClass = "Argo"),
    Shuttle(name = "Cousteau", registry = "NCC-1701-E",    shuttleClass = "Captain's Yacht"),
  ),
)

val theNextGenerationShips: ImmutableList<Ship> = persistentListOf(
  EnterpriseD,
  EnterpriseE,
)

// endregion

// region // == Ships: Deep Space Nine ==

val Defiant = Ship(
  name = "USS Defiant",
  shipClass = "Defiant",
  registry = "NX-74205",
  serviceStart = 2370,
  serviceEnd = 2373,
  commandingOfficer = BenjaminSisko,
  firstOfficer = JadziaDax,
  sections = persistentListOf(
    Airlock,
    Armory,
    AuxiliaryControl,
    Bridge,
    CaptainsReadyRoom,
    CargoBay,
    ComputerCore,
    ConferenceRoom,
    Engineering,
    JefferiesTube,
    MessHall,
    PhaserControl,
    Quarters,
    RecreationRoom,
    Shuttlebay,
    Sickbay(chiefMedicalOfficer = JulianBashir, emergencyMedicalHologram = null),
    SituationRoom,
    TorpedoBay,
    TransporterRoom,
  ),
)
val SaoPaulo = Ship(
  name = "USS São Paulo",
  shipClass = "Defiant",
  registry = "NCC-75633",
  serviceStart = 2375,
  serviceEnd = UNKNOWN_YEAR,
  commandingOfficer = BenjaminSisko,
  firstOfficer = KiraNerys,
  sections = persistentListOf(
    // Defiant-class: no holodecks.
    Airlock,
    Armory,
    AuxiliaryControl,
    Bridge,
    CaptainsReadyRoom,
    CargoBay,
    ComputerCore,
    ConferenceRoom,
    Engineering,
    JefferiesTube,
    MessHall,
    PhaserControl,
    Quarters,
    RecreationRoom,
    Shuttlebay,
    Sickbay(chiefMedicalOfficer = JulianBashir, emergencyMedicalHologram = Species.Hologram("EMH Mark IV")),
    SituationRoom,
    TorpedoBay,
    TransporterRoom,
  ),
)

val deepSpaceNineShips: ImmutableList<Ship> = persistentListOf(
  Defiant,
  SaoPaulo,
)

// endregion

// region // == Ships: Voyager ==

val Voyager = Ship(
  name = "USS Voyager",
  shipClass = "Intrepid",
  registry = "NCC-74656",
  serviceStart = 2371,
  serviceEnd = 2378,
  commandingOfficer = KathrynJaneway,
  firstOfficer = Chakotay,
  sections = persistentListOf(
    AirponicsBay,
    Arboretum,
    Armory,
    Astrometrics,
    BriefingRoom,
    Bridge,
    Brig,
    CaptainsDiningRoom,
    CaptainsReadyRoom,
    CargoBay,
    ComputerCore,
    ConferenceRoom,
    DeflectorControl,
    Engineering,
    EnvironmentalControl,
    Galley,
    Gymnasium,
    Holodeck(number = 1),
    Holodeck(number = 2),
    JefferiesTube,
    MessHall,
    NavigationalControl,
    Nursery,
    PlasmaRelayRoom,
    Quarters,
    RecreationRoom,
    ScienceLab,
    Shuttlebay,
    Sickbay(chiefMedicalOfficer = Unknown, emergencyMedicalHologram = Species.Hologram("The Doctor")),
    TorpedoBay,
    TransporterRoom,
    WeaponsLocker,
  ),
  shuttles = persistentListOf(
    Shuttle(name = "Cochrane",       registry = "NCC-74656/01", shuttleClass = "Type-9"),
    Shuttle(name = "Sacajawea",      registry = "NCC-74656/02", shuttleClass = "Type-9"),
    Shuttle(name = "Tereshkova",     registry = "NCC-74656/03", shuttleClass = "Type-9"),
    Shuttle(name = "Drake",          registry = "NCC-74656/04", shuttleClass = "Type-8"),
    Shuttle(name = "Delta Flyer",    registry = "Unregistered", shuttleClass = "Delta Flyer (custom)"),
    Shuttle(name = "Delta Flyer II", registry = "Unregistered", shuttleClass = "Delta Flyer (custom)"),
    Shuttle(name = "Aeroshuttle",    registry = "NCC-74656",    shuttleClass = "Aeroshuttle"),
  ),
)

val voyagerShips: ImmutableList<Ship> = persistentListOf(
  Voyager,
)

// endregion

// region // == Ships: Lower Decks ==

val Cerritos = Ship(
  name = "USS Cerritos",
  shipClass = "California",
  registry = "NCC-75567",
  serviceStart = 2371,
  serviceEnd = UNKNOWN_YEAR,
  commandingOfficer = CarolFreeman,
  firstOfficer = JackRansom,
  sections = persistentListOf(
    Airlock,
    Bridge,
    Brig,
    CaptainsReadyRoom,
    CetaceanOps,
    ConferenceRoom,
    Engineering,
    Holodeck(number = 1),
    Holodeck(number = 2),
    Holodeck(number = 3),
    MessHall,
    PhaserRange,
    Quarters,
    RepairBay,
    ScienceLab,
    Shuttlebay,
    Sickbay(chiefMedicalOfficer = Unknown, emergencyMedicalHologram = Species.Hologram("EMH Mark III")),
    SquashCourt,
    TransporterRoom,
  ),
  shuttles = persistentListOf(
    Shuttle(name = "Yosemite", registry = "NCC-75567/05", shuttleClass = "Type-6"),
    Shuttle(name = "Sequoia",  registry = "NCC-75567/02", shuttleClass = "Type-6"),
  ),
)
val Merced = Ship(
  name = "USS Merced",
  shipClass = "California",
  registry = "NCC-87297",
  serviceStart = UNKNOWN_YEAR,
  serviceEnd = UNKNOWN_YEAR,
  commandingOfficer = Unknown,
  firstOfficer = Unknown,
  sections = persistentListOf(
    Bridge,
    Holodeck(number = 1),
    Holodeck(number = 2),
    Holodeck(number = 3),
  ),
)
val Rubidoux = Ship(
  name = "USS Rubidoux",
  shipClass = "California",
  registry = "NCC-75605",
  serviceStart = UNKNOWN_YEAR,
  serviceEnd = UNKNOWN_YEAR,
  commandingOfficer = Unknown,
  firstOfficer = Unknown,
  sections = persistentListOf(
    Bridge,
    Holodeck(number = 1),
    Holodeck(number = 3),
  ),
)
val Solvang = Ship(
  name = "USS Solvang",
  shipClass = "California",
  registry = "NCC-73483",
  serviceStart = UNKNOWN_YEAR,
  serviceEnd = 2380,
  commandingOfficer = Unknown,
  firstOfficer = Unknown,
  sections = persistentListOf(
    Bridge,
    Holodeck(number = 1),
    Holodeck(number = 2),
    Holodeck(number = 3),
  ),
)
val Carlsbad = Ship(
  name = "USS Carlsbad",
  shipClass = "California",
  registry = "NCC-58418",
  serviceStart = UNKNOWN_YEAR,
  serviceEnd = UNKNOWN_YEAR,
  commandingOfficer = Unknown,
  firstOfficer = Unknown,
  sections = persistentListOf(
    Bridge,
    Holodeck(number = 1),
    Holodeck(number = 2),
    Holodeck(number = 3),
  ),
)
val Inglewood = Ship(
  name = "USS Inglewood",
  shipClass = "California",
  registry = "NCC-75207",
  serviceStart = UNKNOWN_YEAR,
  serviceEnd = UNKNOWN_YEAR,
  commandingOfficer = Unknown,
  firstOfficer = Unknown,
  sections = persistentListOf(
    Bridge,
    Holodeck(number = 1),
    Holodeck(number = 2),
    Holodeck(number = 3),
  ),
)
val Titan = Ship(
  name = "USS Titan",
  shipClass = "Luna",
  registry = "NCC-80102",
  serviceStart = 2379,
  serviceEnd = UNKNOWN_YEAR,
  commandingOfficer = WilliamRiker,
  firstOfficer = Unknown,
  sections = persistentListOf(
    Bridge,
    CaptainsReadyRoom,
    ConferenceRoom,
    Engineering,
    Holodeck(number = 1),
    Holodeck(number = 2),
    Holodeck(number = 3),
    MessHall,
    Quarters,
    Sickbay(chiefMedicalOfficer = Unknown, emergencyMedicalHologram = Species.Hologram("EMH Mark IV")),
    TransporterRoom,
  ),
)

val lowerDecksShips: ImmutableList<Ship> = persistentListOf(
  Cerritos,
  Merced,
  Rubidoux,
  Solvang,
  Carlsbad,
  Inglewood,
  Titan,
)

// endregion

// region // === Away Mission ===

data class AwayMission(
  val leader: CrewMember,
  val type: Type,
  val team: ImmutableList<CrewMember>,
  val medicalStaff: MedicalStaff? = null,
  val transport: Ship? = null,
) {
  enum class Type {
    Diplomatic,
    Exploration,
    FirstContact,
    GeologicalSurvey,
    Medical,
    Reconnaissance,
    Repair,
    Rescue,
    Scientific,
    Tactical,
  }

  fun land() {
    println("${leader.name}: Away team has landed.")
  }

  fun secureArea() {
    println("${leader.name}: Securing the area.")
  }

  fun checkIn() {
    println("${leader.name} to ${transport?.name ?: "ship"}: away team checking in.")
  }

  fun returnToShip() {
    transport?.let { println("${leader.name}: Energize. Beaming back to ${it.name}.") }
    team.forEach { it.returnToShip() }
  }
}

// endregion

// endregion
