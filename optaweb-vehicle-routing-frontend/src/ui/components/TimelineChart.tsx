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

//Workaround to use typescript with timelinechart
export interface HeaderContext {
  intervals: any
}
export interface HeaderDataInput {
  headerContext: HeaderContext, 
  getRootProps: any
  getIntervalProps: any, 
  showPeriod: any,
  data: any 
}
export interface IntervalRendererInput {
  getIntervalProps: any, 
  intervalContext: any, 
  data: any
}

export interface CustomMakerStyleInput {
  styles: any
}
  //end of workaround 
  

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

  handleCanvasClick = (groupId: any, time: string | number | void | moment.Moment | Date | moment.MomentInputObject | (string | number)[] | undefined) => {
    console.log('Canvas clicked', groupId, moment(time).format())
  }

  handleCanvasDoubleClick = (groupId: any, time: string | number | void | moment.Moment | Date | moment.MomentInputObject | (string | number)[] | undefined) => {
    console.log('Canvas double clicked', groupId, moment(time).format())
  }

  handleCanvasContextMenu = (group: any, time: string | number | void | moment.Moment | Date | moment.MomentInputObject | (string | number)[] | undefined) => {
    console.log('Canvas context menu', group, moment(time).format())
  }

  handleItemClick = (itemId: string, _: any, time: string | number | void | moment.Moment | Date | moment.MomentInputObject | (string | number)[] | undefined) => {
    console.log('Clicked: ' + itemId, moment(time).format())
  }

  handleItemSelect = (itemId: string, _: any, time: string | number | void | moment.Moment | Date | moment.MomentInputObject | (string | number)[] | undefined) => {
    console.log('Selected: ' + itemId, moment(time).format())
  }

  handleItemDoubleClick = (itemId: string, _: any, time: string | number | void | moment.Moment | Date | moment.MomentInputObject | (string | number)[] | undefined) => {
    console.log('Double Click: ' + itemId, moment(time).format())
  }

  handleItemContextMenu = (itemId: string, _: any, time: string | number | void | moment.Moment | Date | moment.MomentInputObject | (string | number)[] | undefined) => {
    console.log('Context Menu: ' + itemId, moment(time).format())
  }

  //TODO FAZER A ACTIONS PARA FAZER O DISPATCH, NÃO BASTA O SETSTATE.

/* 
  handleItemMove = (itemId: any, dragTime: number, newGroupOrder: string | number) => {
    const { items, groups } = this.props

    const group = groups[newGroupOrder]

    this.setState({
      items: items.map(
        (        item: { id: any; end: number; start: number; }) =>
          item.id === itemId
            ? Object.assign({}, item, {
                start: dragTime,
                end: dragTime + (item.end - item.start),
                group: group.id
              })
            : item
      )
    })

    console.log('Moved', itemId, dragTime, newGroupOrder)
  }

  handleItemResize = (itemId: any, time: any, edge: string) => {
    const { items } = this.state

    this.setState({
      items: items.map(
        (        item: { id: any; start: any; end: any; }) =>
          item.id === itemId
            ? Object.assign({}, item, {
                start: edge === 'left' ? time : item.start,
                end: edge === 'left' ? item.end : time
              })
            : item
      )
    })

    console.log('Resized', itemId, time, edge)
  }

  // this limits the timeline to -6 months ... +6 months
  handleTimeChange = (visibleTimeStart: number, visibleTimeEnd: number, updateScrollCanvas: (arg0: number, arg1: any) => void) => {
    if (visibleTimeStart < minTime && visibleTimeEnd > maxTime) {
      updateScrollCanvas(minTime, maxTime)
    } else if (visibleTimeStart < minTime) {
      updateScrollCanvas(minTime, minTime + (visibleTimeEnd - visibleTimeStart))
    } else if (visibleTimeEnd > maxTime) {
      updateScrollCanvas(maxTime - (visibleTimeEnd - visibleTimeStart), maxTime)
    } else {
      updateScrollCanvas(visibleTimeStart, visibleTimeEnd)
    }
  }
 */
  moveResizeValidator = (action: any, item: any, time: number) => {
    if (time < new Date().getTime()) {
      var newTime =
        Math.ceil(new Date().getTime() / (15 * 60 * 1000)) * (15 * 60 * 1000)
      return newTime
    }

    return time
  }
/* 
  handleClickChangeHeaders = () => {
    this.setState(state => ({
      showHeaders: !state.showHeaders
    }))
  } */
  
  render() {
    const { groups, items, defaultTimeStart, defaultTimeEnd } = this.props;
    const { format, showHeaders } = this.state;

    return (
    
          
      <Timeline
        groups={groups}
        items={items}
        keys={keys}
        sidebarWidth={150}
        sidebarContent={<div>Above The Left</div>}
        canMove
        canResize="right"
        canSelect
        itemsSorted
        itemTouchSendsClick={false}
        stackItems
        itemHeightRatio={0.75}
        defaultTimeStart={defaultTimeStart}
        defaultTimeEnd={defaultTimeEnd}
        onCanvasClick={this.handleCanvasClick}
        onCanvasDoubleClick={this.handleCanvasDoubleClick}
        onCanvasContextMenu={this.handleCanvasContextMenu}
        onItemClick={this.handleItemClick}
        onItemSelect={this.handleItemSelect}
        onItemContextMenu={this.handleItemContextMenu}
/*         onItemMove={this.handleItemMove}
        onItemResize={this.handleItemResize}
 */        onItemDoubleClick={this.handleItemDoubleClick}
//        onTimeChange={this.handleTimeChange}
        // moveResizeValidator={this.moveResizeValidator}
        /* rightSidebarWidth={150}
        rightSidebarContent={<div>Above The Right</div>} */
      >
       <TimelineHeaders className="sticky">
          <SidebarHeader>
            {({ getRootProps }:HeaderDataInput) => {
              return <div {...getRootProps()}>Left</div>;
            }}
          </SidebarHeader>
          <DateHeader unit="primaryHeader" />
          <DateHeader />
        </TimelineHeaders>
        <TimelineMarkers>
          <TodayMarker />
          <CustomMarker
            date={
              moment()
                .startOf('day')
                .valueOf() +
              1000 * 60 * 60 * 2
            }
          />
          <CustomMarker
            date={moment()
              .add(3, 'day')
              .valueOf()}
          >
            {({ styles }:CustomMakerStyleInput) => {
              const newStyles = { ...styles, backgroundColor: 'blue' }
              return <div style={newStyles} />
            }}
          </CustomMarker>
          <CursorMarker />
        </TimelineMarkers>
      </Timeline>
    

    );     
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(TimelineChart);

