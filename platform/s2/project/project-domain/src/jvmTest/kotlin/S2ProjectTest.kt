package city.smartb.registry.program.s2.activity.domain

import city.smartb.registry.program.s2.project.domain.automate.ProjectState
import city.smartb.registry.program.s2.project.domain.automate.s2Project
import city.smartb.registry.program.s2.project.domain.model.OrganizationRef
import city.smartb.registry.program.s2.project.domain.model.Project
import java.util.UUID
import java.util.concurrent.TimeUnit
import kotlinx.datetime.DateTimeUnit
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.datafaker.Faker
import org.junit.jupiter.api.Test
import s2.automate.documenter.S2Documenter
import s2.automate.documenter.writeS2Automate


class S2ProjectTest {

    private val json = Json {
        prettyPrint = true
    }

//    @Test
    fun json() {

        val faker = Faker()
        val country = faker.country()
        val project = Project(
            id = UUID.randomUUID().toString(),
            name = faker.mountain().name(),
            country = country.name(),
            creditingPeriodStartDate = faker.date().past(3, TimeUnit.HOURS).time,
            creditingPeriodEndDate = faker.date().future(3, TimeUnit.HOURS).time,
            description = "Descrption of the project ${faker.mountain().name()}",
            dueDate = faker.date().future(6, TimeUnit.HOURS).time,
            estimatedReduction = faker.number().positive().toString(),
            localization = country.capital(),
            proponentAccount = OrganizationRef(
                id = faker.idNumber().valid(),
                name = faker.company().name()
            ),
            proponent = "Description about the proponent",
            type = "Type of the project",
            referenceYear = faker.date().future(1, TimeUnit.HOURS).toLocalDateTime().year.toString(),
            registrationDate = faker.date().past(1, TimeUnit.HOURS).time,
            status = if(faker.random().nextBoolean()) ProjectState.STAMPED else ProjectState.STAMPED,
            vintage = faker.date().future(6, TimeUnit.HOURS).toLocalDateTime().year.toDouble(),
            slug = "slug",

            creationDate = faker.date().past(6, TimeUnit.HOURS).toInstant().epochSecond,
            lastModificationDate = faker.date().past(6, TimeUnit.HOURS).toInstant().epochSecond,
        )

        println(json.encodeToString(project))
    }
    @Test
    fun s2Documenter() {
        S2Documenter()
            .writeS2Automate(s2Project)
    }

}