package com.ntu.igts.resource;

import javax.annotation.Resource;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.ntu.igts.constants.Constants;
import com.ntu.igts.enums.ActiveStateEnum;
import com.ntu.igts.enums.OrderByEnum;
import com.ntu.igts.enums.SortByEnum;
import com.ntu.igts.model.Commodity;
import com.ntu.igts.model.container.CommodityQueryResult;
import com.ntu.igts.model.container.Query;
import com.ntu.igts.service.CommodityService;
import com.ntu.igts.utils.JsonUtil;

@Component
@Path("commodity")
public class CommodityResource {

    @Resource
    private CommodityService commodityService;

    @PUT
    @Path("activestate/{requeststate}/{commodityid}")
    public String updateCommodityActiveState(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token,
                    @PathParam("requeststate") ActiveStateEnum activeState, @PathParam("commodityid") String commodityId) {
        Commodity updatedCommodity = commodityService.updateCommodityActiveState(token, activeState, commodityId);
        return JsonUtil.getJsonStringFromPojo(updatedCommodity);

    }

    @GET
    @Path("detail/{commodityid}")
    public String getCommodityById(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token,
                    @PathParam("commodityid") String commodityId) {
        Commodity returnCommodity = commodityService.getCommodityById(token, commodityId);
        return JsonUtil.getJsonStringFromPojo(returnCommodity);
    }

    @GET
    @Path("search_term")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCommoditiesBySearchTermForAdmin(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token,
                    @BeanParam Query query) {
        if (query != null) {
            if (query.getActiveYN() != null) {
                query.setActiveYN(null);
            }
            if (query.getSortBy() == null) {
                query.setSortBy(SortByEnum.LAST_UPDATED_TIME);
            }
            if (query.getOrderBy() == null) {
                query.setOrderBy(OrderByEnum.DESC);
            }
        }

        CommodityQueryResult commodityQueryResult = commodityService.getCommoditiesBySearchTerm(token, query);
        return JsonUtil.getJsonStringFromPojo(commodityQueryResult);
    }

    @GET
    @Path("totalcount")
    @Produces(MediaType.TEXT_PLAIN)
    public int getTotalCount(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token) {
        return commodityService.getTotalCount(token);
    }
}
