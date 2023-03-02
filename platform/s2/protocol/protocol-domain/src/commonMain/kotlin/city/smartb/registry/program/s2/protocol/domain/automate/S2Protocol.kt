package city.smartb.registry.program.s2.protocol.domain.automate

import city.smartb.registry.program.s2.protocol.domain.command.ProtocolUpdateCommand
import city.smartb.registry.program.s2.protocol.domain.model.Protocol
import city.smartb.registry.program.s2.protocol.domain.model.ProtocolId
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import s2.dsl.automate.Evt
import s2.dsl.automate.S2Command
import s2.dsl.automate.S2InitCommand
import s2.dsl.automate.S2Role
import s2.dsl.automate.S2State
import s2.dsl.automate.WithId
import s2.dsl.automate.builder.s2

val s2Protocol = s2 {
	name = "Protocol"
	init<ProtocolUpdateCommand> {
		to = ProtocolState.UNDER_VALIDATION
		role = ProtocolRole.ProgramManager
	}
	transaction<ProtocolUpdateCommand> {
		from = ProtocolState.UNDER_VALIDATION
		to = ProtocolState.VALIDATION_REQUESTED
		role = ProtocolRole.Expert
	}
//	transaction<ProtocolUpdateCommand> {
//		from = ProtocolState.VALIDATION_REQUESTED
//		to = ProtocolState.REPORT_PUBLISHED
//		role = ProtocolRole.Verifier
//	}
//	transaction<ProtocolUpdateCommand> {
//		from = ProtocolState.VALIDATION_REQUESTED
//		to = ProtocolState.REPORT_PUBLISHED
//		role = ProtocolRole.Verifier
//	}
//	transaction<ProtocolUpdateCommand> {
//		from = ProtocolState.REPORT_PUBLISHED
//		to = ProtocolState.ASSESSMENT_REQUESTED
//		role = ProtocolRole.ProjectDeveloper
//	}
//	transaction<ProtocolUpdateCommand> {
//		from = ProtocolState.ASSESSMENT_REQUESTED
//		to = ProtocolState.VERIFICATION_DOCUMENTATION_PROVIDED
//		role = ProtocolRole.VVB
//	}
//	transaction<ProtocolUpdateCommand> {
//		from = ProtocolState.VERIFICATION_DOCUMENTATION_PROVIDED
//		to = ProtocolState.VERIFIED
//		role = ProtocolRole.ProgramManager
//	}
	transaction<ProtocolUpdateCommand> {
		from = ProtocolState.VALIDATION_REQUESTED
		to = ProtocolState.VERIFIED
		role = ProtocolRole.Verifier
	}
}

/**
 * @d2 model
 * @parent [city.smartb.registry.program.s2.protocol.domain.D2ProtocolPage]
 * @visual automate ./platform/s2/protocol/protocol-domain/build/s2-documenter/Protocol.json
 */
@Serializable
enum class ProtocolState(override val position: Int): S2State {
	UNDER_VALIDATION(0),
	VALIDATION_REQUESTED(1),
//	REPORT_PUBLISHED(2),
//	ASSESSMENT_REQUESTED(3),
//	VERIFICATION_DOCUMENTATION_PROVIDED(4),
	VERIFIED(5)
}

enum class ProtocolRole(val value: String): S2Role {
	ProjectDeveloper("project_developer"),
	ProgramManager("Program_manager"),
	Expert("expert"),
	Verifier("verifier"),
	VVB("vvb");
	override fun toString() = value
}

@JsExport
@JsName("ProtocolInitCommand")
interface ProtocolInitCommand: S2InitCommand

@JsExport
@JsName("ProtocolCommand")
interface ProtocolCommand: S2Command<ProtocolId>

@JsExport
@JsName("ProtocolEvent")
interface ProtocolEvent: Evt, WithId<ProtocolId>
