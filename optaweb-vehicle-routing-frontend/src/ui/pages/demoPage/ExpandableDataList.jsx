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

function textToHtml(html)
{
    let arr = html.split(/<br\s*\/?>/i);
    return arr.reduce((el, a) => el.concat(a, <br />), []);
}

export class ExpandableDataList extends React.Component {
  constructor(props) {
    super(props);
    var isOpenItemsVar = [{name: " ", isOpen: false}];
    props.demoProblems.map(demoProblem => {
      var isOpenItemVar = {name: demoProblem.name, isOpen: false};
      isOpenItemsVar.push(isOpenItemVar);
    });
    console.log(isOpenItemsVar);
    this.state = {
      expanded: ['ex-toggle1', 'ex-toggle3'],
      isOpen1: false,
      isOpen2: false,
      isOpen3: false,
      isOpenItems: isOpenItemsVar
    };

    this.onToggleItemList = (isOpenItem) => {
      var isOpenItemsVar = this.state.isOpenItems;
      this.setState(isOpenItemsVar);
    };

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

    this.handleClick= (demoName) => {

      this.props.handleDemoLoadClick(demoName);
    }

    function search(nameKey, myArray){
      for (var i=0; i < myArray.length; i++) {
          if (myArray[i].name === nameKey) {
              return myArray[i];
          }
      }
    }

    function togleItem(nameKey, myArray){
      for (var i=0; i < myArray.length; i++) {
          if (myArray[i].name === nameKey) {
              myArray[i] = [{name: nameKey, isOpen: !myArray[i].isOpen }]
          }
      }
      return myArray;
    }

    this.onSelect3 = event => {
      this.setState(prevState => ({
        isOpen3: !prevState.isOpen3
      }));
    };
    this.handleClick = this.handleClick.bind(this);

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


    function search(nameKey, myArray){
      for (var i=0; i < myArray.length; i++) {
          if (myArray[i].name === nameKey) {
              return myArray[i];
          }
      }
    }

    return (
      <DataList aria-label="Expandable data list example">
        {demoProblems.map(demoProblem => {
           return (
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
                <DataListAction aria-labelledby={demoProblem.name +"Action"} id={demoProblem.name +"Action"} aria-label={demoProblem.name +"Action"} >
                  {console.log("que loucura")}
                  <Button
                    id={demoProblem.name +"DropdownDemo"}
                    aria-label={demoProblem.name +"DropdownDemo"}
                    onClick={() => this.handleClick(demoProblem.name)}
                  >
                    Open
                  </Button>


                </DataListAction>
              </DataListItemRow>
              <DataListContent
                aria-label="Primary Content Details"
                id={demoProblem.name +"Expand"}
                isHidden={!this.state.expanded.includes(demoProblem.name +"Toggle")}
              >
                <p>
                  {textToHtml(demoProblem.routingProblemParameters.demoElucidation.replace(/\n/g,"<br />"))}
                </p>
                <p>
                  { "Initial Date: "+ demoProblem.routingProblemParameters.demoInitialDate }
                </p>
              </DataListContent>
            </DataListItem>
            )
          })}
      </DataList>
    );
  }
}

export default connect(mapStateToProps)(ExpandableDataList);
