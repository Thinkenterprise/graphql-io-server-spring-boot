/**
 * *****************************************************************************
 *
 * <p>Design and Development by msg Applied Technology Research Copyright (c) 2019-2020 msg systems
 * ag (http://www.msg-systems.com/) All Rights Reserved.
 *
 * <p>Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * <p>The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * <p>THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * <p>****************************************************************************
 */
package com.graphqlio.server.resolvers;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.graphqlio.gts.context.GtsContext;
import com.graphqlio.gts.tracking.GtsRecord;
import com.graphqlio.gts.tracking.GtsRecord.GtsArityType;
import com.graphqlio.gts.tracking.GtsRecord.GtsOperationType;
import com.graphqlio.gts.tracking.GtsScope;
import com.graphqlio.server.domain.Flight;
import com.graphqlio.server.domain.RouteRepository;
import com.graphqlio.server.domain.UpdateRouteInput;

import graphql.schema.DataFetchingEnvironment;

/**
 * Root mutation resolver for resolving updateRoute.
 *
 * @author Michael Schäfer
 * @author Torsten Kühnert
 */
@Component
public class MutationResolver implements GraphQLMutationResolver {

  private RouteRepository routeRepository;

  public MutationResolver(RouteRepository routeRepository) {
    this.routeRepository = routeRepository;
  }

  @Transactional
  public Flight updateRoute(
      String flightNumber, UpdateRouteInput input, DataFetchingEnvironment env) {
    Flight flight = routeRepository.getByFlightNumber(flightNumber);

    flight.setFlightNumber(input.getFlightNumber());
    flight.setDeparture(input.getDeparture());
    flight.setDestination(input.getDestination());

    Flight modifiedRoute = null;
    modifiedRoute = routeRepository.save(flight);

    GtsContext context = env.getContext();
    GtsScope scope = context.getScope();
    scope.addRecord(
        GtsRecord.builder()
            .op(GtsOperationType.UPDATE)
            .arity(GtsArityType.ONE)
            .dstType(Flight.class.getName())
            .dstIds(new String[] {modifiedRoute.getId().toString()})
            .dstAttrs(new String[] {"*"})
            .build());

    return modifiedRoute;
  }
}
