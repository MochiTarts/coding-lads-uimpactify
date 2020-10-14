import React, { Component } from 'react';
import OpportunityCard from "../components/OpportunityCard";
import "../stylesheets/css/Opportunities.css";

class ExploreOpportunities extends Component {
    constructor(props) {
        super(props);
        // TODO: hard coded opportunities, remove this
        const volOpp = [
            {id: '00125', title: "Reg in the Abyss", description: "The hole in front; a mere gap on the planet compares not to the opening within her mind."},
            {id: '12981', title: "Billy's Wish", description: "On an autumn day, Billy walks down where none have passed, as a shadow of longing swiftly eludes through the crevices of dream and reality."}
        ];
        const empOpp = [
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

    handleTabSwitch(tab) {
        var i;

        var tabcontents = document.getElementsByClassName("tabcontent");
        for (i = 0; i < tabcontents.length; i++) {
            tabcontents[i].style.display = "none";
        }
        
        var tablinks = document.getElementsByClassName("tablinks");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "")
        }
        
        document.getElementById(tab + "Content").style.display = "";
        document.getElementById(tab).className += " active";
    }

    render() {
        const { volOpp, empOpp } = this.state;

        return(
            <div className="pageContainer">
                <div className="shadow p-3 mb-5 bg-white rounded">
                    <h3 className="pageHeader">Explore Opportunities</h3>
                </div>

                <ul className="nav nav-tabs">
                    <li className="nav-item">
                        <button className="nav-link tablinks active"
                                id="Volunteer"
                                onClick={() => this.handleTabSwitch("Volunteer")}>
                            Volunteer
                        </button>
                    </li>
                    <li className="nav-item">
                        <button className="nav-link tablinks"
                                id="Employment"
                                onClick={() => this.handleTabSwitch("Employment")}>
                            Employment
                        </button>
                    </li>
                </ul>

                <div className="tabcontent row" id="VolunteerContent">
                    {volOpp.map((opp) => (
                        <OpportunityCard
                            title={opp.title}
                            description={opp.description}
                            button="Apply"
                        />
                    ))}
                </div>
                <div className="tabcontent row" id="EmploymentContent" style={{ display: "none" }}>
                    {empOpp.map((opp) => (
                        <OpportunityCard
                            title={opp.title}
                            description={opp.description}
                            button="Apply"
                        />
                    ))}
                </div>

            </div>
        );
    }
}

export default ExploreOpportunities;