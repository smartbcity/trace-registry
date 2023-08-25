package city.smartb.registry.program.ver.test

import cccev.s2.concept.domain.InformationConceptId
import cccev.s2.concept.domain.InformationConceptIdentifier
import cccev.s2.unit.domain.DataUnitId
import city.smartb.im.organization.domain.model.Organization
import city.smartb.registry.program.f2.activity.domain.model.ActivityId
import city.smartb.registry.program.s2.asset.domain.automate.AssetPoolId
import city.smartb.registry.program.s2.asset.domain.automate.AssetTransactionId
import city.smartb.registry.program.s2.order.domain.OrderId
import city.smartb.registry.program.s2.project.domain.model.ProjectId
import org.springframework.stereotype.Component
import s2.bdd.auth.AuthedUser
import s2.bdd.data.TestContext
import s2.bdd.data.TestContextKey

@Component
class VerTestContext: TestContext() {
    val activityIds = testEntities<TestContextKey, ActivityId>("Activity")
    val assetPoolIds = testEntities<TestContextKey, AssetPoolId>("AssetPool")
    val transactionIds = testEntities<TestContextKey, AssetTransactionId>("Transaction")
    val orderIds = testEntities<TestContextKey, OrderId>("Order")
    val projectIds = testEntities<TestContextKey, ProjectId>("Project")

    val cccevConceptIds = testEntities<TestContextKey, InformationConceptId>("CCCEV InformationConcept")
    val cccevConceptIdentifiers = testEntities<TestContextKey, InformationConceptIdentifier>("CCCEV InformationConcept")
    val cccevUnitIds = testEntities<TestContextKey, DataUnitId>("CCCEV DataUnit")

    val organizations = testEntities<TestContextKey, Organization>("Organization")
    val users = testEntities<TestContextKey, AuthedUser>("User")
}
