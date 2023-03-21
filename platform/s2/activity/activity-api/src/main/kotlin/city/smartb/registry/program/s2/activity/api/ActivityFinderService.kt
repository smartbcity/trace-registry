package city.smartb.registry.program.s2.activity.api

import city.smartb.registry.program.api.commons.exception.NotFoundException
import f2.dsl.cqrs.filter.Match
import city.smartb.registry.program.s2.activity.api.entity.ActivityEntity
import f2.dsl.cqrs.page.OffsetPagination
import f2.dsl.cqrs.page.PageDTO
import f2.dsl.cqrs.page.map
import city.smartb.registry.program.s2.activity.api.entity.ActivityRepository
import city.smartb.registry.program.s2.activity.api.entity.toActivity
import city.smartb.registry.program.s2.activity.api.query.ActivityPageQueryDB
import city.smartb.registry.program.s2.activity.domain.ActivityFinder
import city.smartb.registry.program.s2.activity.domain.model.Activity
import city.smartb.registry.program.s2.activity.domain.model.ActivityId
import org.springframework.stereotype.Service

@Service
class ActivityFinderService(
	private val activityPageQueryDB: ActivityPageQueryDB,
	private val activityRepository: ActivityRepository
): ActivityFinder {

	override suspend fun getOrNull(id: ActivityId): Activity? {
		return activityRepository.findById(id).orElse(null)?.toActivity()
	}

	override suspend fun get(id: ActivityId): Activity {
		return getOrNull(id) ?: throw NotFoundException("Activity", id)
	}

	override suspend fun page(
		id: Match<ActivityId>?,
		offset: OffsetPagination?
	): PageDTO<Activity> {
		return activityPageQueryDB.execute(
			id = id,
			offset = offset
		).map(ActivityEntity::toActivity)
	}
}
