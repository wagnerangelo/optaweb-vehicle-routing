/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
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

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.optaweb.vehiclerouting.domain.Project;

/**
 * {@link Project} representation convenient for marshalling.
 */
class PortableProject {
    @JsonProperty(value = "name", required = true)
	private final String name;

    static PortableProject fromProject(Project project) {
       // Objects.requireNonNull(project, "project must not be null");
       if (project != null) {
          return new PortableProject(project.getName());
       } else {
          return null;
       }
    }

    @JsonCreator
    PortableProject(@JsonProperty(value = "name") String name) {
        this.name = name;
    }

    public String getName() {
		return this.name;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PortableProject that = (PortableProject) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "PortableProject{" +
                "id= not defined" +
                ", name='" + name;
    }
}
