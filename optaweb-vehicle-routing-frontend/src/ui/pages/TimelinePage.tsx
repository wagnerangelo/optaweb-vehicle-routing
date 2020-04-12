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

/* import {
  DataList,
  GutterSize,
  Split,
  SplitItem,
  TextVariants,
} from '@patternfly/react-core'; */
import * as React from 'react';
import { connect } from 'react-redux';
import { routeOperations } from 'store/route';
import { Vehicle, RoutingPlan } from 'store/route/types';
import { AppState } from 'store/types';
import TimelineChart from 'ui/components/TimelineChart';
//import "./timelineCss/styless.scss";
//import "react-calendar-timeline/lib/Timeline.css";


import "@patternfly/react-core/dist/styles/base.css";
import './fonts.css';

import "./timelineCss/Timeline.css"
import "./chartStyleSticky/chartStyleSticky.css";

import {
  Avatar,
  Brand,
  Breadcrumb,
  BreadcrumbItem,
  Button,
  ButtonVariant,
  Dropdown,
  Card,
  CardBody,
  DropdownToggle,
  DropdownItem,
  DropdownSeparator,
  Gallery,
  GalleryItem,
  KebabToggle,
  Nav,
  NavItem,
  NavList,
  Page,
  PageHeader,
  PageSection,
  PageSectionVariants,
  PageSidebar,
  SkipToContent,
  TextContent,
  Text,
  Toolbar,
  ToolbarGroup,
  ToolbarItem,
  Split, 
  SplitItem
} from '@patternfly/react-core';
// make sure you've installed @patternfly/patternfly
import accessibleStyles from '@patternfly/react-styles/css/utilities/Accessibility/accessibility';
import spacingStyles from '@patternfly/react-styles/css/utilities/Spacing/spacing';
import { css } from '@patternfly/react-styles';
import { BellIcon, CogIcon } from '@patternfly/react-icons';
import { KpiNavigation } from './timelinePage/kpiNavigation';
const imgBrand = "data:image/svg+xml;base64,PHN2ZyBlbmFibGUtYmFja2dyb3VuZD0ibmV3IDAgMCAyMjUgMzUiIHZpZXdCb3g9IjAgMCAyMjUgMzUiIHdpZHRoPSIyMjUiIGhlaWdodD0iMzUiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiPjxsaW5lYXJHcmFkaWVudCBpZD0iYSIgZ3JhZGllbnRVbml0cz0idXNlclNwYWNlT25Vc2UiIHgxPSIxNy40Nzk5IiB4Mj0iMTcuNDc5OSIgeTE9IjIuMDM2OSIgeTI9IjM0LjExMTMiPjxzdG9wIG9mZnNldD0iMCIgc3RvcC1jb2xvcj0iIzAwZjFmZiIvPjxzdG9wIG9mZnNldD0iMSIgc3RvcC1jb2xvcj0iIzAwNzZjMSIvPjwvbGluZWFyR3JhZGllbnQ+PHBhdGggZD0ibTEyLjMgMjYuNy01LjIgMS4yLTctOS45IDE3LTE2LjYuNC0uNC40LjQgMTcgMTYuNi03IDkuOS01LjItMS4yLTUuMiA3LjN6bTUuMiA1LjYgNC4yLTUuOC0xLjMtLjMtMi44IDQuMS0zLTQuMS0xLjMuM3ptLjEtMy44IDItMy0yLjEtMjAuMy0yLjEgMjAuM3ptOS44LTEuOCA2LjEtOC42LTEwLjMtMTAuMSA1LjUgNi43LTMuMSA0LjMgMSAyLjMtMy4yIDQuNXptLTE5LjkgMCA0LjEtLjktMy4yLTQuNSAxLTIuMy0zLjEtNC4zIDUuNC02LjctMTAuMyAxMC4xem01LjItMS4yIDEuMy0uMy0zLjktNS4zLS41IDEuMnptOS42IDAgMy4xLTQuNC0uNS0xLjItMy45IDUuM3ptLTEuOC0xLjQgMy45LTUuMy01LjgtMTMuMnptLTYuMSAwIDItMTguNS01LjggMTMuM3ptLTQuNS02LjIgNS44LTEzLjEtOC4xIDkuOXptMTUuMiAwIDIuMy0zLjItOC4xLTkuOXoiIGZpbGw9InVybCgjYSkiLz48ZyBmaWxsPSIjZmZmIj48cGF0aCBkPSJtNTAuOCAyNHYtMTIuMWg1LjJjLjggMCAxLjQuMSAxLjkuM3MuOS41IDEuMy45Yy4zLjQuNi44LjcgMS4yLjEuNS4yLjkuMiAxLjQgMCAuMyAwIC42LS4xLjlzLS4yLjYtLjMuOS0uMy41LS41LjhjLS4yLjItLjUuNS0uOC43cy0uNy4zLTEuMS40LS44LjItMS4zLjJoLTIuOXY0LjR6bTUuMy02LjdjLjMgMCAuNSAwIC44LS4xLjItLjEuNC0uMi41LS40cy4yLS4zLjMtLjUuMS0uNC4xLS42IDAtLjQtLjEtLjVjMC0uMi0uMS0uNC0uMy0uNS0uMS0uMi0uMy0uMy0uNS0uNHMtLjUtLjItLjgtLjJoLTN2My4yeiIvPjxwYXRoIGQ9Im03Ni4yIDI0LS45LTIuNWgtNC43bC0uOSAyLjVoLTIuNWw0LjYtMTIuMWgyLjNsNC42IDEyLjF6bS0yLjktNy44Yy0uMS0uMS0uMS0uMy0uMi0uNXMtLjEtLjQtLjItLjZjMCAuMi0uMS40LS4yLjZzLS4xLjQtLjIuNWwtMS4yIDMuMWgzLjJ6Ii8+PHBhdGggZD0ibTkxLjkgMTQuMnY5LjhoLTIuM3YtOS44aC0zLjV2LTIuM2g5LjN2Mi4zeiIvPjxwYXRoIGQ9Im0xMTAgMTQuMnY5LjhoLTIuM3YtOS44aC0zLjV2LTIuM2g5LjN2Mi4zeiIvPjxwYXRoIGQ9Im0xMjMuMSAyNHYtMTIuMWg4LjN2Mi4yaC01Ljl2Mi41aDMuNXYyLjJoLTMuNXYyLjloNi4zdjIuM3oiLz48cGF0aCBkPSJtMTQxLjUgMjR2LTEyLjFoNS42Yy44IDAgMS40LjEgMS45LjNzLjkuNSAxLjIuOC41LjguNyAxLjJjLjEuNS4yLjkuMiAxLjQgMCAuMy0uMS43LS4xIDEtLjEuNC0uMi43LS40IDFzLS40LjYtLjcuOS0uNi41LTEgLjZsMi4zIDQuOGgtMi42bC0yLjMtNC41aC0yLjR2NC42em01LjctNi43Yy4zIDAgLjUgMCAuOC0uMS4yLS4xLjQtLjIuNS0uNC4xLS4xLjItLjMuMy0uNXMuMS0uNC4xLS42IDAtLjQtLjEtLjZjMC0uMi0uMS0uNC0uMi0uNXMtLjMtLjMtLjUtLjQtLjUtLjEtLjgtLjFoLTMuM3YzLjJ6Ii8+PHBhdGggZD0ibTE2OC44IDI0LTQuOC03LjFjLS4xLS4xLS4yLS4zLS4zLS40LS4xLS4yLS4yLS4zLS4yLS41di41LjUgN2gtMi4zdi0xMi4xaDIuMmw0LjcgN2MuMS4xLjIuMy4zLjQuMS4yLjIuMy4zLjUgMC0uMiAwLS4zIDAtLjVzMC0uMyAwLS40di03aDIuM3YxMi4xeiIvPjxwYXRoIGQ9Im0xODEuMyAyNHYtMTIuMWg4LjF2Mi4yaC01Ljh2Mi41aDMuN3YyLjJoLTMuN3Y1LjJ6Ii8+PHBhdGggZD0ibTE5OC44IDI0di0xMi4xaDIuM3Y5LjloNS44djIuMnoiLz48cGF0aCBkPSJtMjE4LjIgMjR2LTQuN2wtNC41LTcuNGgyLjZsMyA0LjkgMy00LjloMi43bC00LjUgNy40djQuN3oiLz48L2c+PC9zdmc+Cg==";
const imgAvatar = "data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHN2ZyBlbmFibGUtYmFja2dyb3VuZD0ibmV3IDAgMCAzNiAzNiIgdmVyc2lvbj0iMS4xIiB2aWV3Qm94PSIwIDAgMzYgMzYiIHhtbDpzcGFjZT0icHJlc2VydmUiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CjxzdHlsZSB0eXBlPSJ0ZXh0L2NzcyI+CgkvKnN0eWxlbGludC1kaXNhYmxlKi8KCS5zdDB7ZmlsbC1ydWxlOmV2ZW5vZGQ7Y2xpcC1ydWxlOmV2ZW5vZGQ7ZmlsbDojRkZGRkZGO30KCS5zdDF7ZmlsdGVyOnVybCgjYik7fQoJLnN0MnttYXNrOnVybCgjYSk7fQoJLnN0M3tmaWxsLXJ1bGU6ZXZlbm9kZDtjbGlwLXJ1bGU6ZXZlbm9kZDtmaWxsOiNCQkJCQkI7fQoJLnN0NHtvcGFjaXR5OjAuMTtmaWxsLXJ1bGU6ZXZlbm9kZDtjbGlwLXJ1bGU6ZXZlbm9kZDtlbmFibGUtYmFja2dyb3VuZDpuZXcgICAgO30KCS5zdDV7b3BhY2l0eTo4LjAwMDAwMGUtMDI7ZmlsbC1ydWxlOmV2ZW5vZGQ7Y2xpcC1ydWxlOmV2ZW5vZGQ7ZmlsbDojMjMxRjIwO2VuYWJsZS1iYWNrZ3JvdW5kOm5ldyAgICA7fQoJLypzdHlsZWxpbnQtZW5hYmxlKi8KPC9zdHlsZT4KCQkJPGNpcmNsZSBjbGFzcz0ic3QwIiBjeD0iMTgiIGN5PSIxOC41IiByPSIxOCIvPgoJCTxkZWZzPgoJCQk8ZmlsdGVyIGlkPSJiIiB4PSI1LjIiIHk9IjcuMiIgd2lkdGg9IjI1LjYiIGhlaWdodD0iNTMuNiIgZmlsdGVyVW5pdHM9InVzZXJTcGFjZU9uVXNlIj4KCQkJCTxmZUNvbG9yTWF0cml4IHZhbHVlcz0iMSAwIDAgMCAwICAwIDEgMCAwIDAgIDAgMCAxIDAgMCAgMCAwIDAgMSAwIi8+CgkJCTwvZmlsdGVyPgoJCTwvZGVmcz4KCQk8bWFzayBpZD0iYSIgeD0iNS4yIiB5PSI3LjIiIHdpZHRoPSIyNS42IiBoZWlnaHQ9IjUzLjYiIG1hc2tVbml0cz0idXNlclNwYWNlT25Vc2UiPgoJCQk8ZyBjbGFzcz0ic3QxIj4KCQkJCTxjaXJjbGUgY2xhc3M9InN0MCIgY3g9IjE4IiBjeT0iMTguNSIgcj0iMTgiLz4KCQkJPC9nPgoJCTwvbWFzaz4KCQk8ZyBjbGFzcz0ic3QyIj4KCQkJPGcgdHJhbnNmb3JtPSJ0cmFuc2xhdGUoNS4wNCA2Ljg4KSI+CgkJCQk8cGF0aCBjbGFzcz0ic3QzIiBkPSJtMjIuNiAxOC4xYy0xLjEtMS40LTIuMy0yLjItMy41LTIuNnMtMS44LTAuNi02LjMtMC42LTYuMSAwLjctNi4xIDAuNyAwIDAgMCAwYy0xLjIgMC40LTIuNCAxLjItMy40IDIuNi0yLjMgMi44LTMuMiAxMi4zLTMuMiAxNC44IDAgMy4yIDAuNCAxMi4zIDAuNiAxNS40IDAgMC0wLjQgNS41IDQgNS41bC0wLjMtNi4zLTAuNC0zLjUgMC4yLTAuOWMwLjkgMC40IDMuNiAxLjIgOC42IDEuMiA1LjMgMCA4LTAuOSA4LjgtMS4zbDAuMiAxLTAuMiAzLjYtMC4zIDYuM2MzIDAuMSAzLjctMyAzLjgtNC40czAuNi0xMi42IDAuNi0xNi41YzAuMS0yLjYtMC44LTEyLjEtMy4xLTE1eiIvPgoJCQkJPHBhdGggY2xhc3M9InN0NCIgZD0ibTIyLjUgMjZjLTAuMS0yLjEtMS41LTIuOC00LjgtMi44bDIuMiA5LjZzMS44LTEuNyAzLTEuOGMwIDAtMC40LTQuNi0wLjQtNXoiLz4KCQkJCTxwYXRoIGNsYXNzPSJzdDMiIGQ9Im0xMi43IDEzLjJjLTMuNSAwLTYuNC0yLjktNi40LTYuNHMyLjktNi40IDYuNC02LjQgNi40IDIuOSA2LjQgNi40LTIuOCA2LjQtNi40IDYuNHoiLz4KCQkJCTxwYXRoIGNsYXNzPSJzdDUiIGQ9Im05LjQgNi44YzAtMyAyLjEtNS41IDQuOS02LjMtMC41LTAuMS0xLTAuMi0xLjYtMC4yLTMuNSAwLTYuNCAyLjktNi40IDYuNHMyLjkgNi40IDYuNCA2LjRjMC42IDAgMS4xLTAuMSAxLjYtMC4yLTIuOC0wLjYtNC45LTMuMS00LjktNi4xeiIvPgoJCQkJPHBhdGggY2xhc3M9InN0NCIgZD0ibTguMyAyMi40Yy0yIDAuNC0yLjkgMS40LTMuMSAzLjVsLTAuNiAxOC42czEuNyAwLjcgMy42IDAuOWwwLjEtMjN6Ii8+CgkJCTwvZz4KCQk8L2c+Cjwvc3ZnPgo=";


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

export interface timelineStateInterface {
  isDropdownOpen: boolean,
  
  activeItem: number
}

class TimelinePage extends React.Component<Props, timelineStateInterface> {
  onDropdownToggle: (isDropdownOpen: any) => void;
  onDropdownSelect: (event: any) => void;
  onNavSelect: (result: any) => void;
  onNavToggle: (event: any) => void;
  constructor(props: Props) {
    super(props)
    
    this.state = {
      isDropdownOpen: false,
      activeItem: 0
    };

    this.onDropdownToggle = isDropdownOpen => {
      this.setState({
        isDropdownOpen
      });
    };

    this.onDropdownSelect = event => {
      this.setState({
        isDropdownOpen: !this.state.isDropdownOpen
      });
    };

    this.onNavSelect = result => {
      this.setState({
        activeItem: result.itemId
      });
    };

    this.onNavToggle = event => {
      console.log("navtoggle clicked!");
      this.setState({
        isDropdownOpen: !this.state.isDropdownOpen
    });
      console.log("isDropdownOpen State:"+ this.state.isDropdownOpen);
    }
  }
  render() {
    const { isDropdownOpen, activeItem } = this.state;
    
    const PageNav = (
      <Nav onSelect={this.onNavSelect} aria-label="Nav" >
        <NavList>
          <NavItem itemId={0} isActive={activeItem === 0}>
            System Panel
          </NavItem>
          <NavItem itemId={1} isActive={activeItem === 1}>
            Policy
          </NavItem>
          <NavItem itemId={2} isActive={activeItem === 2}>
            Authentication
          </NavItem>
          <NavItem itemId={3} isActive={activeItem === 3}>
            Network Services
          </NavItem>
          <NavItem itemId={4} isActive={activeItem === 4}>
            Server
          </NavItem>
        </NavList>
      </Nav>
    );
    
    

    const Header = (
      <PageHeader
       /*  logo={<Brand src={imgBrand} alt="Patternfly Logo" />}
        toolbar={PageToolbar}
        avatar={<Avatar src={imgAvatar} alt="Avatar image" />} */
        showNavToggle
      />
    );
    const Sidebar = <PageSidebar nav={PageNav} isNavOpen={this.state.isDropdownOpen}  />;
    const pageId = 'main-content-page-layout-default-nav';
    const PageSkipToContent = <SkipToContent href={`#${pageId}`}>Skip to content</SkipToContent>;

    

    return (
      <div>
        <Split>          
          <SplitItem isFilled>pf-m-fill
            <React.Fragment>
              <Page skipToContent={PageSkipToContent}>
                <PageSection variant={PageSectionVariants.light}>
                  <div style={{
                    overflowY: 'auto',
                    overflowX: 'auto',
                    height: '100%',
                    width: '100%'
                  }}>
                    <TimelineChart/>
                  </div>
                </PageSection>
                <PageSection>
                  <Gallery gutter="md">
                    {Array.apply(0, Array(10)).map((x, i) => (
                      <GalleryItem key={i}>
                        <Card>
                          <CardBody>This is a card</CardBody>
                        </Card>
                      </GalleryItem>
                    ))}
                  </Gallery>
                </PageSection>
              </Page>
            </React.Fragment>
          </SplitItem>

          <SplitItem isFilled={false}>            
            <button id="nav-toggle2"
                aria-controls="page-sidebar" 
                aria-expanded="true" 
                aria-label="Global navigation" 
                className="pf-c-button pf-m-plain" 
                type="button"
                onClick={this.onNavToggle}>
                <svg fill="currentColor" height="1em" width="1em" viewBox="0 0 448 512" aria-hidden="true" role="img" >
                  <path d="M16 132h416c8.837 0 16-7.163 16-16V76c0-8.837-7.163-16-16-16H16C7.163 60 0 67.163 0 76v40c0 8.837 7.163 16 16 16zm0 160h416c8.837 0 16-7.163 16-16v-40c0-8.837-7.163-16-16-16H16c-8.837 0-16 7.163-16 16v40c0 8.837 7.163 16 16 16zm0 160h416c8.837 0 16-7.163 16-16v-40c0-8.837-7.163-16-16-16H16c-8.837 0-16 7.163-16 16v40c0 8.837 7.163 16 16 16z" transform=""></path>
                </svg>          
            </button>
            <KpiNavigation/>
            {/* <PageSidebar nav={PageNav} isNavOpen={this.state.isDropdownOpen}  /> */}
          </SplitItem>
        </Split>
      </div>
      

/* 
      <div style={{
        overflowY: 'auto',
        overflowX: 'auto',
        height: '100%',
        width: '100%'
      }}>
                <TimelineChart/>
          </div>
 */   
    );    
  } 
} 

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