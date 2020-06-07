import React from 'react';
import { PropertiesSidePanel, PropertyItem } from '@patternfly/react-catalog-view-extension';
import { OkIcon, ExternalLinkAltIcon, GlobeIcon } from '@patternfly/react-icons';
import { Demo as DemoProblem, RoutingProblemParameters} from 'store/server/types';

interface DemoParameterProps {
  demoSelectedName: string| null,
  demoProblems:DemoProblem[]
}
export const PropertiesSidePanelWithItemsTsx: React.FC<DemoParameterProps>  = ({demoProblems, demoSelectedName}) => {
  const demoProblem = demoProblems.find(demo => demo.name === demoSelectedName);
  let demoParameters:RoutingProblemParameters = {
    demoContext: "",
    demoComplexity: "",
    demoHorizon: 0,
    demoInitialDate: "",
    demoElucidation: ""
  };
  if (demoProblem) {
    demoParameters = demoProblem.routingProblemParameters;
  }
  return (
      <div style={{ display: 'flex', padding: '15px', border: '1px solid grey' }}>
      <PropertiesSidePanel>
        <PropertyItem label="Persistence Data Version" value="0.9.8 (latest)" />
        <PropertyItem
          label="Homologation Status"
          value={
            <span>
              <OkIcon style={{color: '#3f9c35'}} />
              Aproved
            </span>
          }
        />
        <PropertyItem label="Context" value={demoParameters.demoContext} />
        <PropertyItem label="Complexity" value={demoParameters.demoComplexity} />
        <PropertyItem
          label="Repository"
          value={
            <a href="http:....">
            </a>
          }
        />
        <PropertyItem
          label="Operation Number"
          value={
            <a href="#">
              6 <ExternalLinkAltIcon />
            </a>
          }
        />
        <PropertyItem
          label="Created At"
          value={
            <span>
              <GlobeIcon /> dez/2018
            </span>
          }
        />
        <PropertyItem label="Iniciative" value={<a href="#">TELB</a>} />
      </PropertiesSidePanel>
    </div>
)};
