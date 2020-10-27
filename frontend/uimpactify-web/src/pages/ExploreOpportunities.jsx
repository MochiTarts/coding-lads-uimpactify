import React, { Component } from 'react';
import OpportunityCard from "../components/OpportunityCard";
import "../stylesheets/css/Opportunities.css";
import { getAllPosting } from "../helpers/services/posting-service";

class ExploreOpportunities extends Component {
    constructor(props) {
        super(props);
        this.state = {
            volOpp: [],
            empOpp: [],
            conOpp: []
        }
    }

    componentDidMount() {
        var curr;
        var volOpp = [];
        var empOpp = [];
        var conOpp = [];
        getAllPosting(this.state.uid).then(
            (r) => {
                for (var i = 0; i < r.data.length; i++) {
                    curr = r.data[i];
                    if (curr.postingType === "VOLUNTEER") {
                        volOpp.push({id: curr.id, title: curr.name, description: curr.postingDesc});
                    } else if (curr.postingType === "EMPLOYMENT") {
                        empOpp.push({id: curr.id, title: curr.name, description: curr.postingDesc});
                    } else if (curr.postingType === "CONSULTING") {
                        conOpp.push({id: curr.id, title: curr.name, description: curr.postingDesc});
                    }
                }
                this.setState({ volOpp: volOpp });
                this.setState({ empOpp: empOpp });
                this.setState({ conOpp: conOpp });
            }
        );
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
                            type="VOLUNTEER"
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
                            type="EMPLOYMENT"
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
                            type="CONSULIING"
                            button="Apply"
                        />
                    ))}
                </div>

            </div>
        );
    }
}

export default ExploreOpportunities;