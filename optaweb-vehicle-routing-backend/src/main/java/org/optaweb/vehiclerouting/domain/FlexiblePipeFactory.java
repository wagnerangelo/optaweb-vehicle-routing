/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaweb.vehiclerouting.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Creates {@link TimeWindowedOffshoreTask} instances.
 */
public class FlexiblePipeFactory {

    private static final Logger logger = LoggerFactory.getLogger(WellFactory.class);

    private FlexiblePipeFactory() {
        throw new AssertionError("Utility class");
    }

    /**
     * Create a new flexiblePipe with the given diameter, length, invoice
     * @param diameter insiide diameter mm
     * @param lenght lenght meters
     * @param invoiceNumber
     */
    public static FlexiblePipe createSimpleFlexibePipe(int diameter, long length, long invoiceNumber, RoutingProblem routingProblem) {

        if (routingProblem == null) {
            throw new NullPointerException("routingProblem is null, Can't create well, without a routingProblem associated");
        }

        FlexiblePipe flexiblePipeCandidate = new FlexiblePipe(diameter, length, invoiceNumber);

        routingProblem.getFlexiblePipes().add(flexiblePipeCandidate);

        return flexiblePipeCandidate;
    }
}
