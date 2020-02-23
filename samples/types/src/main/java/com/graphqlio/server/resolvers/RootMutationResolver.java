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

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.graphqlio.gts.context.GtsContext;
import com.graphqlio.gts.tracking.GtsRecord;
import com.graphqlio.gts.tracking.GtsRecord.GtsArityType;
import com.graphqlio.gts.tracking.GtsRecord.GtsOperationType;
import com.graphqlio.gts.tracking.GtsScope;

import graphql.schema.DataFetchingEnvironment;

/**
 * Root query resolver for resolving counter.
 *
 * @author Michael Schäfer
 * @author Torsten Kühnert
 */
@Component
public class RootMutationResolver implements GraphQLMutationResolver {

  private final Logger logger = LoggerFactory.getLogger(RootMutationResolver.class);

  public Date updateDate(Date date, Date input, DataFetchingEnvironment env) {
    logger.info("RootMutationResolver::updateDate::date = " + date);

    GtsContext context = env.getContext();
    GtsScope scope = context.getScope();
    scope.addRecord(
        GtsRecord.builder()
            .op(GtsOperationType.UPDATE)
            .arity(GtsArityType.ALL)
            .dstType(Date.class.getName())
            .dstIds(new String[] {"1"})
            .dstAttrs(new String[] {"*"})
            .build());

    return input;
  }

  public String updateJson(String json, String input, DataFetchingEnvironment env) {
    logger.info("RootMutationResolver::updateJson::json = " + json);

    GtsContext context = env.getContext();
    GtsScope scope = context.getScope();
    scope.addRecord(
        GtsRecord.builder()
            .op(GtsOperationType.UPDATE)
            .arity(GtsArityType.ALL)
            .dstType(String.class.getName())
            .dstIds(new String[] {"2"})
            .dstAttrs(new String[] {"*"})
            .build());

    return input;
  }

  public UUID updateUuid(UUID uuid, UUID input, DataFetchingEnvironment env) {
    logger.info("RootMutationResolver::updateUuid::uuid = " + uuid);

    GtsContext context = env.getContext();
    GtsScope scope = context.getScope();
    scope.addRecord(
        GtsRecord.builder()
            .op(GtsOperationType.UPDATE)
            .arity(GtsArityType.ALL)
            .dstType(UUID.class.getName())
            .dstIds(new String[] {"3"})
            .dstAttrs(new String[] {"*"})
            .build());

    return input;
  }

  public Void updateVoid(Void vOID, Void input, DataFetchingEnvironment env) {
    logger.info("RootMutationResolver::updateVoid::vOID = " + vOID);

    GtsContext context = env.getContext();
    GtsScope scope = context.getScope();
    scope.addRecord(
        GtsRecord.builder()
            .op(GtsOperationType.UPDATE)
            .arity(GtsArityType.ALL)
            .dstType(Void.class.getName())
            .dstIds(new String[] {"4"})
            .dstAttrs(new String[] {"*"})
            .build());

    return input;
  }
}
