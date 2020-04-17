import React from 'react';
import { Expandable } from '@patternfly/react-core';
import { KpiParametersOil } from 'ui/components/kpi/oil/KpiParametersOil';

export class ParameterExpandableOil extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      isExpanded: false
    };
    this.onToggle = () => {
      this.setState({
        isExpanded: !this.state.isExpanded
      });
    };
  }

  render() {
    const { isExpanded } = this.state;
    return (
      <Expandable toggleText={isExpanded ? 'Production Estimate Parameters' : 'PO Parameters'} onToggle={this.onToggle} isExpanded={isExpanded}>
        <KpiParametersOil/>
      </Expandable>
    );
  }
}
