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
        <PropertyItem label="Operator Version" value="0.9.8 (latest)" />
        <PropertyItem
          label="Certified Level"
          value={
            <span>
              <OkIcon style={{color: '#3f9c35'}} /> 
              {demoParameters.demoContext}
            </span>
          }
        />
        <PropertyItem label="Provider" value="Red Hat, Inc" />
        <PropertyItem label="Health Index" value="A" />
        <PropertyItem
          label="Repository"
          value={
            <a href="https://quay.io/repository/redhat/prometheus-operator">
              https://quay.io/repository
              /redhat/prometheus-operator
            </a>
          }
        />
        <PropertyItem
          label="Container Image"
          value={
            <a href="#">
              0.22.2 <ExternalLinkAltIcon />
            </a>
          }
        />
        <PropertyItem
          label="Created At"
          value={
            <span>
              <GlobeIcon /> Aug 23, 1:58pm
            </span>
          }
        />
        <PropertyItem label="Support" value={<a href="#">Red Hat</a>} />
      </PropertiesSidePanel>
    </div>
)};
