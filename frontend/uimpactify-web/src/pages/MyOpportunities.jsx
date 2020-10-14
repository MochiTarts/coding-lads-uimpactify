import React, { Component } from 'react';
import OpportunityCard from '../components/OpportunityCard.jsx';
import "../stylesheets/css/Opportunities.css";

class MyOpportunities extends Component {
    constructor(props) {
        super(props);
        // TODO: hard coded opportunities, remove this
        const volOpp = [
            {id: '00125', title: "Reg in the Abyss", description: "The hole in front; a mere gap on the planet compares not to the opening within her mind."},
            {id: '12981', title: "Billy's Wish", description: "On an autumn day, Billy walks down where none have passed, as a shadow of longing swiftly eludes through the crevices of dream and reality."}
        ];
        const empOpp = [
            {id: '00125', title: "Reg in the Abyss", description: "The hole in front; a mere gap on the planet compares not to the opening within her mind."},
            {id: '12981', title: "Billy's Wish", description: "On an autumn day, Billy walks down where none have passed, as a shadow of longing swiftly eludes through the crevices of dream and reality."}
        ];
        this.state = {
            volOpp: volOpp,
            empOpp: empOpp
        }
    }

    componentDidMount() {
        // TODO: fetch opportunities from DB and update state
        console.log("mounted");
    }

    render() { 
        const { volOpp, empOpp } = this.state;

        return (
            <div className="pageContainer">
                <div className="shadow p-3 mb-5 bg-white rounded">
                    <h3 className="pageHeader">My Opportunities</h3>
                </div>

                <h3 className="pageSubheader">Volunteer Opportunities</h3>
                <a href="/myopportunities/new" 
                   className="btn btn-sm btn-outline-dark newButton">
                    New
                </a>
                <div className="row opportunityList">
                    {volOpp.map((opp) => (
                        <OpportunityCard
                            title={opp.title}
                            description={opp.description}
                            button="Manage"
                        />
                    ))}
                </div>

                <h3 className="pageSubheader">Employment Opportunities</h3>
                <a href="/myopportunities/new" 
                   className="btn btn-sm btn-outline-dark newButton">
                    New
                </a>
                <div className="row opportunityList">
                    {empOpp.map((opp) => (
                        <OpportunityCard
                            title={opp.title}
                            description={opp.description}
                            button="Manage"
                        />
                    ))}
                </div>
            </div>
        );
    }
}
 
export default MyOpportunities;