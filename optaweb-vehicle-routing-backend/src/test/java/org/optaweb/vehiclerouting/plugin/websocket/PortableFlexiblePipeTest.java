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

package org.optaweb.vehiclerouting.plugin.websocket;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.optaweb.vehiclerouting.domain.FlexiblePipe;
import org.springframework.boot.test.json.JacksonTester;
import static org.assertj.core.api.Assertions.assertThat;

class PortableFlexiblePipeTest {

    private JacksonTester<PortableFlexiblePipe> json;

    @BeforeEach
    void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void marshall_to_json() throws IOException {
        int diameter = 150; //milimiters
        long length = 2500; //meters
        long invoiceNumber = 88888888;
        PortableFlexiblePipe portableFlexiblePipe = new PortableFlexiblePipe(diameter, length, invoiceNumber);
        String jsonTemplate = "{\"diameter\":%d,\"length\":%d,\"invoiceNumber\":%d}";
        assertThat(json.write(portableFlexiblePipe)).isEqualToJson(
                String.format(jsonTemplate, diameter, length , invoiceNumber)
        );
    }

    @Test
    void fromVehicle() {
        int diameter = 150; //milimiters
        long length = 2500; //meters
        long invoiceNumber = 88888888;
        FlexiblePipe flexiblePipe  = new FlexiblePipe(diameter, length, invoiceNumber);
        PortableFlexiblePipe portableFlexiblePipe = PortableFlexiblePipe.createFrom(flexiblePipe);

        assertThat(portableFlexiblePipe.getDiameter()).isEqualTo(diameter);
        assertThat(portableFlexiblePipe.getLength()).isEqualTo(length);
        assertThat(portableFlexiblePipe.getInvoiceNumber()).isEqualTo(invoiceNumber);
    }
}
