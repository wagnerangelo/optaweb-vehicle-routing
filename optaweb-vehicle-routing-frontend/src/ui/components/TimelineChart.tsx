import moment from "moment";
import * as React from 'react';
import { connect } from 'react-redux';
import { clientOperations } from 'store/client';
import { routeOperations } from 'store/route';
import { AppState } from 'store/types';


import Timeline, {
  TimelineMarkers,
  TodayMarker,
  CustomMarker,
  CursorMarker,
  SidebarHeader,
  CustomHeader,
  TimelineHeaders,
  DateHeader
} from 'react-calendar-timeline/lib'

/* 

import Timeline, {
  TimelineHeaders,
  SidebarHeader,
  DateHeader
} from "react-calendar-timeline/lib"; */

/* var keys = {
  groupIdKey: "id",
  groupTitleKey: "title",
  groupRightTitleKey: "rightTitle",
  itemIdKey: "id",
  itemTitleKey: "title",
  itemDivTitleKey: "title",
  itemGroupKey: "group",
  itemTimeStartKey: "start",
  itemTimeEndKey: "end",
  groupLabelKey: "title"
};
 */
var keys = {
  groupIdKey: 'id',
  groupTitleKey: 'title',
  groupRightTitleKey: 'rightTitle',
  itemIdKey: 'id',
  itemTitleKey: 'title',
  itemDivTitleKey: 'title',
  itemGroupKey: 'group',
  itemTimeStartKey: 'start',
  itemTimeEndKey: 'end'
}

interface StateProps {
    groups: any[];
    items: any[],
    defaultTimeStart: any,
    defaultTimeEnd: any,    
}

interface StateInterface {
  format: boolean,
  showHeaders: boolean
}
  
interface DispatchProps {
    addVehicleHandler: typeof routeOperations.addVehicle;
    removeVehicleHandler: typeof routeOperations.deleteVehicle;
    changeVehicleCapacityHandler: typeof routeOperations.changeVehicleCapacity;
}

export type Props = StateProps & DispatchProps;

const mapStateToProps = ({ plan }: AppState): StateProps => ({
    groups: plan.groups,
    items: plan.items,
    defaultTimeStart: plan.defaultTimeStart,
    defaultTimeEnd: plan.defaultTimeEnd
});

const mapDispatchToProps: DispatchProps = {
    addVehicleHandler: routeOperations.addVehicle,
    removeVehicleHandler: routeOperations.deleteVehicle,
    changeVehicleCapacityHandler: routeOperations.changeVehicleCapacity,
};

export interface Iprops {
  getRootProps: any
}

export class TimelineChart extends React.Component<Props,StateInterface> {
  constructor(props: Props) {
    super(props)

    const defaultTimeStartVar = moment()
    .startOf('day')
    .toDate();
    console.log("Contructor timeline - defaultTimeStart:"+defaultTimeStartVar);
    const defaultTimeEndVar = moment()
    .startOf('day')
    .add(1, 'day')
    .toDate();
    console.log("Contructor timeline - defaultTimend:"+defaultTimeEndVar);

    this.state = {
      format: false,
      showHeaders: false
    }
  }
  render() {
    return (
      <Timeline
        groups={this.props.groups}
        items={this.props.items}
        keys={keys}
        sidebarContent={<div>Above The Left</div>}
        itemsSorted
        itemTouchSendsClick={false}
        stackItems
        itemHeightRatio={0.75}
        showCursorLine
        canMove={false}
        canResize={false}
        defaultTimeStart={this.props.defaultTimeStart}
        defaultTimeEnd={this.props.defaultTimeEnd}
      >
        <TimelineHeaders className="sticky">
          <SidebarHeader>
            {({ getRootProps }:Iprops) => {
              return <div {...getRootProps()}>Left</div>;
            }}
          </SidebarHeader>
          <DateHeader unit="primaryHeader" />
          <DateHeader />
        </TimelineHeaders>
      </Timeline>
    );     
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(TimelineChart);

