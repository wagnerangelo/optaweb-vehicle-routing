import React from 'react';
import { connect } from 'react-redux';
import { FilterSidePanel, FilterSidePanelCategory, FilterSidePanelCategoryItem } from '@patternfly/react-catalog-view-extension';
import { StarIcon, CcPaypalIcon, CcAmexIcon, CcDiscoverIcon, CcVisaIcon, CcMastercardIcon, CcDinersClubIcon } from '@patternfly/react-icons';
import { TextInput } from '@patternfly/react-core';
import { uniqBy, groupBy ,map ,reduce, keys } from 'lodash';

function mapStateToProps(state) {
  const { serverInfo } = state
  return { demoProblems: serverInfo.demos }
}

export class MockFilterSidePanelExample extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      activeFilters: {
        contextPLSV: false,
        contextRSV: false,
        complexityBasic: false,
        complexityLow: false,
        complexityMedium: false,
        complexityHigh: false,
        typePickup: false,
        typeSports: false,
        makeChevy: false,
        makeFord: false,
        makeDodge: false,
        makeVolkswagen: false,
        makeHyundai: false,
        makeHonda: false,
        makeToyota: false,
        makeMercedes: false,
        makeBMW: false,
        makeInfiniti: false,
        makeLexus: false,
        makeAcura: false,
        paymentPaypal: false,
        paymentDiscover: false,
        paymentVisa: false,
        paymentMastercard: false,
        paymentAmex: false,
        paymentDinersClub: false,
        mileage50: false,
        mileage40: false,
        mileage30: false,
        mileage20: false,
        mileage10: false,
        rating5: false,
        rating4: false,
        rating3: false,
        rating2: false,
        rating1: false
      },
      showAllCategories: {
        context: false,
        complexity: false,
        make: false,
        paymentOptions: false,
        mileage: false,
        rating: false
      }
    };

    this.onShowAllToggle = id => {
      const showAllCategories = { ...this.state.showAllCategories };
      showAllCategories[id] = !showAllCategories[id];
      this.setState({ showAllCategories });
    };

    this.onFilterChange = (id, value) => {
      const activeFilters = { ...this.state.activeFilters };
      activeFilters[id] = value;
      this.setState({ activeFilters });
    };

    this.getStars = count => {
      const stars = [];

      for (let i = 0; i < count; i++) {
        stars.push(<StarIcon key={i} />);
      }

      return (
        <span>
          <span className="sr-only">{`${count} stars`}</span>
          {stars}
        </span>
      );

    };

  }

  render() {
    const { activeFilters, showAllCategories } = this.state;
    const { demoProblems} = this.props;

    function isEqualValue(a,b) {
      if (a == b) return true;

      return false;
    }


    const filterStatus = (field) => {
      switch(field) {
        case 'contextPLSV':
          return this.state.activeFilters.contextPLSV;
        case 'contextRSV':
          return this.state.activeFilters.contextRSV;
        case 'complexityBasic':
          return this.state.activeFilters.complexityBasic;
        case 'complexityLow':
          return this.state.activeFilters.complexityLow;
        case 'complexityMedium':
          return this.state.activeFilters.complexityMedium;
        case 'complexityHigh':
          return this.state.activeFilters.complexityHigh;
        default:
          return undefined;
      }
    }

    function setActiveFilter(a,b) {
      if (a == b) return true;

      return false;
    }

    const maxShowCount = 5;
    const leeway = 2;

    //Context
    const demoContextUniqs = uniqBy(demoProblems,'routingProblemParameters.demoContext');
    var group = groupBy(demoProblems,'routingProblemParameters.demoContext')
   // console.log("resultado do group: "+ JSON.stringify(group));
    var demoContextGroupeds = map(keys(group), function(e) {
      return reduce(group[e], function(r, o) {
        return r.count += +o.price, r }, {Group: e, count: 0, sum: group[e].length})
    })
    console.log("resultado do result group: "+ JSON.stringify(demoContextGroupeds));
    //console.log("demoProblems: "+ JSON.stringify(demoProblems));
    //console.log("demoContextUniqs"+ JSON.stringify(demoContextUniqs));

    //Complexity
    const demoComplexityUniqs = uniqBy(demoProblems,'routingProblemParameters.demoComplexity');
    var group = groupBy(demoProblems,'routingProblemParameters.demoComplexity')
   // console.log("resultado do group: "+ JSON.stringify(group));
    var demoComplexityGroupeds = map(keys(group), function(e) {
      return reduce(group[e], function(r, o) {
        return r.count += +o.price, r }, {Group: e, count: 0, sum: group[e].length})
    })
    console.log("resultado do result group: "+ JSON.stringify(demoComplexityGroupeds));
    //console.log("demoProblems: "+ JSON.stringify(demoProblems));
    //console.log("demoComplexityUniqs"+ JSON.stringify(demoComplexityUniqs));



    return (
     <div style={{ width: '205px', border: '1px solid grey', paddingTop: '20px' }}>
      <FilterSidePanel id="filter-panel">
        <FilterSidePanelCategory key="contextCategory">
          <TextInput type="text" id="filter-text-input" placeholder="Filter by name" aria-label="filter text input" />
        </FilterSidePanelCategory>
          <FilterSidePanelCategory
            key="context"
            title="Context"
            maxShowCount={maxShowCount}
            leeway={leeway}
            showAll={showAllCategories.context}
            onShowAllToggle={() => this.onShowAllToggle('context')}
          >
            {demoContextUniqs.map(demoContextElement =>
              <FilterSidePanelCategoryItem
                key={'context'+ demoContextElement.routingProblemParameters.demoContext}
                count={demoContextGroupeds.find(demoContextGroup => isEqualValue(demoContextGroup.Group, demoContextElement.routingProblemParameters.demoContext)).sum}
                checked={filterStatus('context'+ demoContextElement.routingProblemParameters.demoContext)}
                onClick={e => this.onFilterChange('context'+ demoContextElement.routingProblemParameters.demoContext, e.target.checked)}
              >
              {demoContextElement.routingProblemParameters.demoContext}
              </FilterSidePanelCategoryItem>
            )}
            {(demoContextUniqs === []) ? <FilterSidePanelCategoryItem>No Options</FilterSidePanelCategoryItem>: null}
        </FilterSidePanelCategory>
        <FilterSidePanelCategory
          key="complexity"
          title="Complexity"
          maxShowCount={maxShowCount}
          leeway={leeway}
          showAll={showAllCategories.complexity}
          onShowAllToggle={() => this.onShowAllToggle('complexity')}
        >
           {demoComplexityUniqs.map(demoComplexityElement =>
              <FilterSidePanelCategoryItem
                key={'complexity'+ demoComplexityElement.routingProblemParameters.demoComplexity}
                count={demoComplexityGroupeds.find(demoComplexityGroup => isEqualValue(demoComplexityGroup.Group, demoComplexityElement.routingProblemParameters.demoComplexity)).sum}
                checked={filterStatus('complexity'+ demoComplexityElement.routingProblemParameters.demoComplexity)}
                onClick={e => this.onFilterChange('complexity'+ demoComplexityElement.routingProblemParameters.demoComplexity, e.target.checked)}
              >
              {demoComplexityElement.routingProblemParameters.demoComplexity}
              </FilterSidePanelCategoryItem>
            )}
            {(demoComplexityUniqs === []) ? <FilterSidePanelCategoryItem>No Options</FilterSidePanelCategoryItem>: null}
        </FilterSidePanelCategory>
        <FilterSidePanelCategory
          key="cat5"
          title="Number of Operations"
          maxShowCount={maxShowCount}
          leeway={leeway}
          showAll={showAllCategories.mileage}
          onShowAllToggle={() => this.onShowAllToggle('mileage')}
        >
          <FilterSidePanelCategoryItem
            key="gt50"
            count={10}
            checked={activeFilters.mileage50}
            onClick={e => this.onFilterChange('mileage50', e.target.checked)}
          >
            0 - 6
          </FilterSidePanelCategoryItem>
          <FilterSidePanelCategoryItem
            key="1230"
            count={5}
            checked={activeFilters.mileage40}
            onClick={e => this.onFilterChange('mileage40', e.target.checked)}
          >
            12-30
          </FilterSidePanelCategoryItem>
          <FilterSidePanelCategoryItem
            key="30100"
            count={9}
            checked={activeFilters.mileage30}
            onClick={e => this.onFilterChange('mileage30', e.target.checked)}
          >
            30-100
          </FilterSidePanelCategoryItem>
          <FilterSidePanelCategoryItem
            key="2030"
            count={12}
            checked={activeFilters.mileage20}
            onClick={e => this.onFilterChange('mileage20', e.target.checked)}
          >
            101-999
          </FilterSidePanelCategoryItem>
          <FilterSidePanelCategoryItem
            key="gt20"
            count={8}
            checked={activeFilters.mileage10}
            onClick={e => this.onFilterChange('mileage10', e.target.checked)}
          >
            1000 - 10000
          </FilterSidePanelCategoryItem>
        </FilterSidePanelCategory>
        <FilterSidePanelCategory
          key="cat6"
          title="Rating"
          maxShowCount={maxShowCount}
          leeway={leeway}
          showAll={showAllCategories.rating}
          onShowAllToggle={() => this.onShowAllToggle('rating')}
        >
          <FilterSidePanelCategoryItem
            key="5star"
            count={2}
            icon={this.getStars(5)}
            checked={activeFilters.rating5}
            onClick={e => this.onFilterChange('rating5', e.target.checked)}
          />
          <FilterSidePanelCategoryItem
            key="4star"
            count={12}
            icon={this.getStars(4)}
            checked={activeFilters.rating4}
            onClick={e => this.onFilterChange('rating4', e.target.checked)}
          />
          <FilterSidePanelCategoryItem
            key="3star"
            count={8}
            icon={this.getStars(3)}
            checked={activeFilters.rating3}
            onClick={e => this.onFilterChange('rating3', e.target.checked)}
          />
          <FilterSidePanelCategoryItem
            key="2star"
            count={5}
            icon={this.getStars(2)}
            checked={activeFilters.rating2}
            onClick={e => this.onFilterChange('rating2', e.target.checked)}
          />
          <FilterSidePanelCategoryItem
            key="1star"
            count={3}
            icon={this.getStars(1)}
            checked={activeFilters.rating1}
            onClick={e => this.onFilterChange('rating1', e.target.checked)}
          />
        </FilterSidePanelCategory>
      </FilterSidePanel>
    </div>
    );
  }
}
export default connect(mapStateToProps)(MockFilterSidePanelExample)
