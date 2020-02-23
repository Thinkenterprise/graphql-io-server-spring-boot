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

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.graphqlio.gts.context.GtsContext;
import com.graphqlio.gts.tracking.GtsRecord;
import com.graphqlio.gts.tracking.GtsRecord.GtsArityType;
import com.graphqlio.gts.tracking.GtsRecord.GtsOperationType;
import com.graphqlio.gts.tracking.GtsScope;

import graphql.schema.DataFetchingEnvironment;

/**
 * Root query resolver for resolving types.
 *
 * @author Michael Schäfer
 * @author Torsten Kühnert
 * @author Dr. Edgar Müller
 */
@Component
public class RootQueryResolver implements GraphQLQueryResolver {

  private final Logger logger = LoggerFactory.getLogger(RootQueryResolver.class);

  public Date getDate(DataFetchingEnvironment env) {
    logger.info("RootQueryResolver::getDate");

    GtsContext context = env.getContext();
    GtsScope scope = context.getScope();
    scope.addRecord(
        GtsRecord.builder()
            .op(GtsOperationType.READ)
            .arity(GtsArityType.ALL)
            .dstType(Date.class.getName())
            .dstIds(new String[] {"1"})
            .dstAttrs(new String[] {"*"})
            .build());

    return new Date();
  }

  public String getJson(DataFetchingEnvironment env) {
    logger.info("RootQueryResolver::getJson");

    GtsContext context = env.getContext();
    GtsScope scope = context.getScope();
    scope.addRecord(
        GtsRecord.builder()
            .op(GtsOperationType.READ)
            .arity(GtsArityType.ALL)
            .dstType(String.class.getName())
            .dstIds(new String[] {"2"})
            .dstAttrs(new String[] {"*"})
            .build());

    return "{ a: 1, b: [2, \"3\", '4']}";
  }

  public UUID getUuid(DataFetchingEnvironment env) {
    logger.info("RootQueryResolver::getUuid");

    GtsContext context = env.getContext();
    GtsScope scope = context.getScope();
    scope.addRecord(
        GtsRecord.builder()
            .op(GtsOperationType.READ)
            .arity(GtsArityType.ALL)
            .dstType(UUID.class.getName())
            .dstIds(new String[] {"3"})
            .dstAttrs(new String[] {"*"})
            .build());

    return new UUID(25323523525L, 119867236576L);
  }

  public Void getVoid(DataFetchingEnvironment env) {
    logger.info("RootQueryResolver::getVoid");

    GtsContext context = env.getContext();
    GtsScope scope = context.getScope();
    scope.addRecord(
        GtsRecord.builder()
            .op(GtsOperationType.READ)
            .arity(GtsArityType.ALL)
            .dstType(Void.class.getName())
            .dstIds(new String[] {"4"})
            .dstAttrs(new String[] {"*"})
            .build());

    return null;
  }
}
