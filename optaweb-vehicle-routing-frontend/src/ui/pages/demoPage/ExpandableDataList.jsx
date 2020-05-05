import React from 'react';
import { connect } from 'react-redux';
import Background from '../../components/Background';
import {
  Button,
  DataList,
  DataListItem,
  DataListItemRow,
  DataListCell,
  DataListCheck,
  DataListAction,
  DataListToggle,
  DataListContent,
  DataListItemCells,
  Dropdown,
  DropdownItem,
  DropdownPosition,
  KebabToggle,
  Badge
} from '@patternfly/react-core';
import { CodeBranchIcon } from '@patternfly/react-icons';

function mapStateToProps(state) {
    const { serverInfo } = state  
    
    return { demoProblems: serverInfo.demos }
}

export class ExpandableDataList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      expanded: ['ex-toggle1', 'ex-toggle3'],
      isOpen1: false,
      isOpen2: false,
      isOpen3: false
    };
   
    this.props.loadHandler(demoProblem.name)


    this.onToggle1 = isOpen1 => {
      this.setState({ isOpen1 });
    };

    this.onToggle2 = isOpen2 => {
      this.setState({ isOpen2 });
    };

    this.onToggle3 = isOpen3 => {
      this.setState({ isOpen3 });
    };

    this.onSelect1 = event => {
      this.setState(prevState => ({
        isOpen1: !prevState.isOpen1
      }));
    };

    this.onSelect2 = event => {
      this.setState(prevState => ({
        isOpen2: !prevState.isOpen2
      }));
    };

    this.onSelect3 = event => {
      this.setState(prevState => ({
        isOpen3: !prevState.isOpen3
      }));
    };
  }

  render() {
    const { demoProblems} = this.props;
    const previewPlaceholder = {
        display: 'block',
        width: '100%',
        padding: '.25rem .5rem',
        color: '#004e8a',
        backgroundColor: '#def3ff',
        border: '1px solid rgba(0,0,0,.1)',
        borderRadius: '4px'
      };

    const toggle = id => {
      const expanded = this.state.expanded;
      const index = expanded.indexOf(id);
      const newExpanded =
        index >= 0 ? [...expanded.slice(0, index), ...expanded.slice(index + 1, expanded.length)] : [...expanded, id];
      this.setState(() => ({ expanded: newExpanded }));
    };
    return (
       
      <DataList aria-label="Expandable data list example">
        {demoProblems.map(demoProblem =>
                  <DataListItem aria-labelledby={demoProblem.name +"Item"} isExpanded={this.state.expanded.includes(demoProblem.name + "Toggle")}>
                  <DataListItemRow>
                    <DataListToggle
                      onClick={() => toggle(demoProblem.name + "Toggle")}
                      isExpanded={this.state.expanded.includes(demoProblem.name + "Toggle")}
                      id={demoProblem.name + "Toggle"}
                      aria-controls={demoProblem.name +"Expand"}
                    />
                    <DataListItemCells
                      dataListCells={[
                        <DataListCell isIcon key="icon">
                          <CodeBranchIcon />
                        </DataListCell>,
                        <DataListCell width={5} key="primary content">
                          <div style={previewPlaceholder} id="ex-item1">
                            <b>
                              {demoProblem.name} 
                            </b>  
                          </div>
                          
                          <Badge key={1} isRead>{demoProblem.routingProblemParameters.demoContext}</Badge>
                          {' '}
                          <Badge key={2} isRead>{demoProblem.routingProblemParameters.demoComplexity}</Badge>
                          {' '}
                          <Badge key={3} isRead>{"Hozizon:"+ demoProblem.routingProblemParameters.demoHorizon + " days"}</Badge>
                       </DataListCell>,
                        <DataListCell key="secondary content">
                          <div>
                            <h7>
                              <p>Wagner</p>
                              <p>Carrijo</p>
                            </h7>                            
                          </div>
                        </DataListCell>,
                       
                      ]}
                    />
                    <Button
                      id={demoProblem.name +"Open"}                      
                      style={{ marginBottom: 16, marginLeft: 16 }}
                      onClick={}
                    >
                      Export
                    </Button>
                  </DataListItemRow>
                  <DataListContent
                    aria-label="Primary Content Details"
                    id={demoProblem.name +"Expand"}
                    isHidden={!this.state.expanded.includes(demoProblem.name +"Toggle")}
                  >
                    <p>
                      { demoProblem.routingProblemParameters.demoElucidation }                      
                    </p>
                    <p>
                      { "Initial Date: "+ demoProblem.routingProblemParameters.demoInitialDate }
                    </p>
                  </DataListContent>
                </DataListItem>
        )} 

        <DataListItem aria-labelledby="ex-item2" isExpanded={this.state.expanded.includes('ex-toggle2')}>
          <DataListItemRow>
            <DataListToggle
              onClick={() => toggle('ex-toggle2')}
              isExpanded={this.state.expanded.includes('ex-toggle2')}
              id="ex-toggle2"
              aria-controls="ex-expand2"
            />
            <DataListItemCells
              dataListCells={[
                <DataListCell isIcon key="icon">
                  <CodeBranchIcon />
                </DataListCell>,
                
                <DataListCell width={5} key="secondary content">
                     <div style={previewPlaceholder} >
                      <b id="width-ex3-item1">width 5</b>
                      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                    </div>
                </DataListCell>,
                <DataListCell width={1}  key="secondary content 2" >
                  <span>Lorem ipsum dolor sit amet.</span>
                </DataListCell>,
                
              ]}
            />
            <DataListAction aria-labelledby="ex-item2 ex-action2" id="ex-action2" aria-label="Actions">
              <Dropdown
                isPlain
                position={DropdownPosition.right}
                isOpen={this.state.isOpen2}
                onSelect={this.onSelect2}
                toggle={<KebabToggle onToggle={this.onToggle2} />}
                dropdownItems={[
                  <DropdownItem key="link">Link</DropdownItem>,
                  <DropdownItem key="action" component="button">
                    Action
                  </DropdownItem>,
                  <DropdownItem key="disabled link" isDisabled>
                    Disabled Link
                  </DropdownItem>
                ]}
              />
            </DataListAction>
          </DataListItemRow>
          <DataListContent
            aria-label="Primary Content Details"
            id="ex-expand2"
            isHidden={!this.state.expanded.includes('ex-toggle2')}
          >
            <p>
              Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
              dolore magna aliqua.
            </p>
          </DataListContent>
        </DataListItem>
        <DataListItem aria-labelledby="ex-item3" isExpanded={this.state.expanded.includes('ex-toggle3')}>
          <DataListItemRow>
            <DataListToggle
              onClick={() => toggle('ex-toggle3')}
              isExpanded={this.state.expanded.includes('ex-toggle3')}
              id="ex-toggle3"
              aria-controls="ex-expand3"
            />
            <DataListItemCells 
              dataListCells={[
                <DataListCell isIcon key="icon">
                  <CodeBranchIcon />
                </DataListCell>,
                <DataListCell key="tertiary content" pfe-img-src>
                  
                  <div id="ex-item3">Tertiary content</div>
                  <span>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</span>
                </DataListCell>,
                <DataListCell key="secondary content">
                  <span>Lorem ipsum dolor sit amet.</span>
                </DataListCell>,
                <DataListCell key="secondary content 2">
                     
                  <span>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</span>
                </DataListCell>
              ]}
            />
            <DataListAction aria-labelledby="ex-item3 ex-action3" id="ex-action3" aria-label="Actions">
              <Dropdown
                isPlain
                position={DropdownPosition.right}
                isOpen={this.state.isOpen3}
                onSelect={this.onSelect3}
                toggle={<KebabToggle onToggle={this.onToggle3} />}
                dropdownItems={[
                  <DropdownItem key="link">Link</DropdownItem>,
                  <DropdownItem key="action" component="button">
                    Action
                  </DropdownItem>,
                  <DropdownItem key="disabled link" isDisabled>
                    Disabled Link
                  </DropdownItem>
                ]}
              />
            </DataListAction>
          </DataListItemRow>
          <DataListContent
            aria-label="Primary Content Details"
            id="ex-expand3"
            isHidden={!this.state.expanded.includes('ex-toggle3')}
            noPadding
          >
            This expanded section has no padding.
          </DataListContent>
        </DataListItem>
      </DataList>
    );
  }
}

export default connect(mapStateToProps)(ExpandableDataList);
