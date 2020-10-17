import React, { Component } from 'react';
import OpportunityCard from "../components/OpportunityCard";
import "../stylesheets/css/Opportunities.css";

class ExploreOpportunities extends Component {
    constructor(props) {
        super(props);
        // TODO: hard coded opportunities, remove this
        const volOpp = [
            {id: '10256', title: "Environmental Hike", description: "Want to join us on a hike to protect the environment? Apply now for a experience of a life time where we hike onto Mt. Richard while picking up litter."},
        ];
        const empOpp = [
            {id: '71935', title: "Software Developer", description: "Bulletin Corp. is looking for passionate developers to work on our latest project, Lunatic. Join a team of creatively minded individuals like yourself to bring our product to life."},
            {id: '75035', title: "UI Designer", description: "Bulletin Corp. is looking for passionate designers to create a post-modern design of our products. Join a team of creatively minded individuals like yourself to bring our product to life."}
        ];
        const conOpp = [
            {id: '87671', title: "Leading Psychologist", description: "Looking for a professional psychologist who have experience leading a team of indivduals into a successful mindset."},
        ];
        this.state = {
            volOpp: volOpp,
            empOpp: empOpp,
            conOpp: conOpp
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
        const { volOpp, empOpp, conOpp } = this.state;

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
                    <li className="nav-item">
                        <button className="nav-link tablinks"
                                id="Consulting"
                                onClick={() => this.handleTabSwitch("Consulting")}>
                            Consulting
                        </button>
                    </li>
                </ul>

                <div className="tabcontent row" id="VolunteerContent">
                    {volOpp.map((opp) => (
                        <OpportunityCard
                            key={opp.id}
                            title={opp.title}
                            description={opp.description}
                            type="volunteer"
                            button="Apply"
                        />
                    ))}
                </div>
                <div className="tabcontent row" id="EmploymentContent" style={{ display: "none" }}>
                    {empOpp.map((opp) => (
                        <OpportunityCard
                            key={opp.id}
                            title={opp.title}
                            description={opp.description}
                            type="employment"
                            button="Apply"
                        />
                    ))}
                </div>
                <div className="tabcontent row" id="ConsultingContent" style={{ display: "none" }}>
                    {conOpp.map((opp) => (
                        <OpportunityCard
                            key={opp.id}
                            title={opp.title}
                            description={opp.description}
                            type="consulting"
                            button="Apply"
                        />
                    ))}
                </div>

            </div>
        );
    }
}

export default ExploreOpportunities;