import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import OpportunityCard from '../components/OpportunityCard.jsx';
import "../stylesheets/css/Opportunities.css";
import { getMyOpportunities } from "../helpers/services/user-service";

class MyOpportunities extends Component {
    constructor(props) {
        super(props);
        this.state = {
            uid: props.uid,
            role: props.uinfo.role,
            socialInit: props.uinfo.socialInit,
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
        const isEmployee = true ? this.state.socialInit : false;
        getMyOpportunities(this.state.uid, isEmployee).then(
            (r) => {
                for (var i = 0; i < r.data.length; i++) {
                    if (isEmployee) {
                        curr = r.data[i];
                    } else {
                        curr = r.data[i].posting;
                    }
                    if (curr.postingType === "VOLUNTEERING") {
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

    render() { 
        const { uid, volOpp, empOpp, conOpp, role, socialInit } = this.state;
        const type = role ? role.name : null;
        const isConsultant = true ? type === "IMPACT_CONSULTANT" : false;
        const isLearner = true ? type === "IMPACT_LEARNER" : false;
        var buttonText;
        if (socialInit) {
            buttonText = "Manage";
        } else {
            buttonText = "Details";
        }

        return (
            <div className="opportunity-page-container">
                <div className="shadow p-3 mb-5 bg-white rounded">
                    <h3 className="opportunity-page-header">My Opportunities</h3>
                </div>

                {(socialInit || isLearner) &&
                <h3 className="opportunity-page-subheader">
                    Volunteer Opportunities
                </h3>}
                {socialInit &&
                <Link
                    className="btn btn-sm btn-outline-dark opportunity-newButton"
                    to={{
                        pathname: "/opportunity/create",
                        state: {uid: uid, type: "VOLUNTEERING"}
                    }}>
                    New
                </Link>}
                {(socialInit || isLearner) &&
                <div className="row opportunityList">
                    {volOpp.map((opp) => (
                        <OpportunityCard
                            key={opp.id}
                            pid={opp.id}
                            uid={uid}
                            title={opp.title}
                            description={opp.description}
                            type="VOLUNTEERING"
                            button={buttonText}
                        />
                    ))}
                </div>}


                {(socialInit || isLearner) &&
                <h3 className="opportunity-page-subheader">
                    Employment Opportunities
                </h3>}
                {socialInit &&
                <Link
                    className="btn btn-sm btn-outline-dark opportunity-newButton"
                    to={{
                        pathname: "/opportunity/create",
                        state: {uid: uid, type: "EMPLOYMENT"}
                    }}>
                    New
                </Link>}
                {(socialInit || isLearner) &&
                <div className="row opportunityList">
                    {empOpp.map((opp) => (
                        <OpportunityCard
                            key={opp.id}
                            pid={opp.id}
                            uid={uid}
                            title={opp.title}
                            description={opp.description}
                            type="EMPLOYMENT"
                            button={buttonText}
                        />
                    ))}
                </div>}


                {(socialInit || isConsultant) &&
                <h3 className="opportunity-page-subheader">
                    Consulting Opportunities
                </h3>}
                {socialInit &&
                <Link
                    className="btn btn-sm btn-outline-dark opportunity-newButton"
                    to={{
                        pathname: "/opportunity/create",
                        state: {uid: uid, type: "CONSULTING"}
                    }}>
                    New
                </Link>}
                {(socialInit || isConsultant) &&
                <div className="row opportunityList">
                    {conOpp.map((opp) => (
                        <OpportunityCard
                            key={opp.id}
                            pid={opp.id}
                            uid={uid}
                            title={opp.title}
                            description={opp.description}
                            type="CONSULTING"
                            button={buttonText}
                        />
                    ))}
                </div>}
            </div>
        );
    }
}
 
export default MyOpportunities;