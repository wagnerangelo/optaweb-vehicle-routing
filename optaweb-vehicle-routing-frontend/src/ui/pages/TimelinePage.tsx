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

import {
  Button,
  DataList,
  GutterSize,
  Split,
  SplitItem,
  Text,
  TextContent,
  TextVariants,
} from '@patternfly/react-core';
import * as React from 'react';
import { connect } from 'react-redux';
import { routeOperations } from 'store/route';
import { Vehicle } from 'store/route/types';
import { AppState } from 'store/types';
import TimelineChart from 'ui/components/TimelineChart';
import "./chartStyleSticky/chartStyleSticky.css";

interface StateProps {
  vehicles: Vehicle[];
  groups: any[];
  items: any[]
}

interface DispatchProps {
  addVehicleHandler: typeof routeOperations.addVehicle;
  removeVehicleHandler: typeof routeOperations.deleteVehicle;
  changeVehicleCapacityHandler: typeof routeOperations.changeVehicleCapacity;
}

export type Props = StateProps & DispatchProps;

const mapStateToProps = ({ plan }: AppState): StateProps => ({
  vehicles: plan.vehicles,
  groups: plan.groups,
  items: plan.items

});

const mapDispatchToProps: DispatchProps = {
  addVehicleHandler: routeOperations.addVehicle,
  removeVehicleHandler: routeOperations.deleteVehicle,
  changeVehicleCapacityHandler: routeOperations.changeVehicleCapacity,
};

export const TimelinePage: React.FC<Props> = ({
  vehicles, groups, items, addVehicleHandler, removeVehicleHandler, changeVehicleCapacityHandler,
}) => (
  <>
        <TextContent>
          <Text component={TextVariants.h1}>Timeline</Text>
         
        </TextContent>
        <Button>
           buttontext
        </Button>
        <Split gutter={GutterSize.md}>         
          <SplitItem isFilled style={{
            overflowY: 'auto',
            overflowX: 'auto',
            height: '100%',
            width: '100%'
          }}>
            <TimelineChart/>
          </SplitItem>
          <SplitItem
            isFilled={false}
            style={{ display: 'flex', flexDirection: 'column' }}
          >
          </SplitItem>
          TESTE
          <TextContent>

            <Text component={TextVariants.h1}>Groups: {groups.length}</Text>
            <Text component={TextVariants.h1}>items: {items.length}</Text>
          </TextContent>
          
        </Split>
      </>
);

export default connect(mapStateToProps, mapDispatchToProps)(TimelinePage);



/* 


import {
  GutterSize,
  Split,
  SplitItem,
  Text,
  TextContent,
  TextVariants,
} from '@patternfly/react-core';
import * as React from 'react';
import { RouteWithTrack } from 'store/route/types';


export interface TimelineProps {
  
  routes: RouteWithTrack[];
  groups: any[];
  items: any[];
}

const TimelinePage: React.FC<TimelineProps> = ({
  routes,
  groups,
  items
}) => {
  const [clicked, setClicked] = React.useState(false);
  console.log("TimelinePage Render this.props.groups");
  console.log(groups);
  return (
    <div>
        <TextContent>
          <Text component={TextVariants.h1}>Timeline</Text>
        </TextContent>
        <Split gutter={GutterSize.md}>
          <SplitItem isFilled style={{
            overflowY: 'auto',
            overflowX: 'auto',
            height: '100%',
            width: '100%'
          }}>
            {console.log("groups inside the return of render of timeline page")}>
            {console.log(groups)}
            <p>Groups: {groups}</p>
            {/* <TimelineChart/>             }
          </SplitItem>
          <SplitItem
            isFilled={false}
            style={{ display: 'flex', flexDirection: 'column' }}
          >
          </SplitItem>
        </Split>
      </div>
  );
};

export default TimelinePage;


 */