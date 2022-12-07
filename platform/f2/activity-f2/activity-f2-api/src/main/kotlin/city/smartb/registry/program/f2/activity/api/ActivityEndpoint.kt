package city.smartb.registry.program.f2.activity.api

import f2.dsl.cqrs.page.OffsetPagination
import f2.dsl.fnc.f2Function
import city.smartb.registry.program.f2.activity.api.service.ActivityF2AggregateService
import city.smartb.registry.program.f2.activity.api.service.ActivityF2FinderService
import city.smartb.registry.program.f2.activity.api.service.ActivityPoliciesEnforcer
import city.smartb.registry.program.f2.activity.domain.ActivityCommandApi
import city.smartb.registry.program.f2.activity.domain.ActivityQueryApi
import city.smartb.registry.program.f2.activity.domain.command.ActivityCreateFunction
import city.smartb.registry.program.f2.activity.domain.command.ActivityUpdateFunction
import city.smartb.registry.program.f2.activity.domain.query.ActivityGetFunction
import city.smartb.registry.program.f2.activity.domain.query.ActivityGetResult
import city.smartb.registry.program.f2.activity.domain.query.ActivityPageFunction
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import s2.spring.utils.logger.Logger

@RestController
@RequestMapping
@Configuration
class ActivityEndpoint(
    private val activityF2FinderService: ActivityF2FinderService,
    private val activityF2AggregateService: ActivityF2AggregateService,
    private val activityPoliciesEnforcer: ActivityPoliciesEnforcer
): ActivityQueryApi, ActivityCommandApi {

    private val logger by Logger()

    @Bean
    override fun activityGet(): ActivityGetFunction = f2Function { query ->
        logger.info("activityGet: $query")
        activityF2FinderService.getOrNull(query.id).let(::ActivityGetResult)
    }

    @Bean
    override fun activityPage(): ActivityPageFunction = f2Function { query ->
        logger.info("activityPage: $query")
        activityPoliciesEnforcer.checkList()

        activityF2FinderService.page(
            offset = OffsetPagination(
                offset = query.page * query.size,
                limit = query.size
            )
        )
    }

    @Bean
    override fun activityCreate(): ActivityCreateFunction = f2Function { command ->
        logger.info("activityCreate: $command")
        activityPoliciesEnforcer.checkCreate()
        activityF2AggregateService.create(command)
    }


    @Bean
    override fun activityUpdate(): ActivityUpdateFunction = f2Function { command ->
        logger.info("activityUpdateDetails: $command")
        activityPoliciesEnforcer.checkUpdate(command.id)
        activityF2AggregateService.updateDetails(command)
    }

//    @Bean
//    override fun activityDelete(): ActivityDeleteFunction = f2Function { command ->
//        logger.info("activityDelete: $command")
//        activityPoliciesEnforcer.checkDelete(command.id)
//        activityF2AggregateService.delete(command)
//    }

}
