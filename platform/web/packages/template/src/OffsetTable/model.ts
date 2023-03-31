import {f2} from "verified-emission-reduction-registry-project-f2-domain"

export interface PageQuery extends f2.dsl.cqrs.page.PageQueryDTO {}
export interface PageQueryResult<MODEL> extends f2.dsl.cqrs.page.PageQueryResultDTO<MODEL> {}
export interface OffsetPagination extends f2.dsl.cqrs.page.OffsetPaginationDTO {}

const firstPage: OffsetPagination = {
  limit: 10,
  offset: 0
}

export const Offset = {
  default: firstPage
}

