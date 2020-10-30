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
        getMyOpportunities(this.state.uid).then(
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

    render() { 
        const { uid, volOpp, empOpp, conOpp } = this.state;

        return (
            <div className="opportunity-page-container">
                <div className="shadow p-3 mb-5 bg-white rounded">
                    <h3 className="opportunity-page-header">My Opportunities</h3>
                </div>

                <h3 className="opportunity-page-subheader">Volunteer Opportunities</h3>
                <Link
                    className="btn btn-sm btn-outline-dark opportunity-newButton"
                    to={{
                        pathname: "/myopportunities/create",
                        state: {uid: uid, type: "VOLUNTEER"}
                    }}
                >
                    New
                </Link>
                <div className="row opportunityList">
                    {volOpp.map((opp) => (
                        <OpportunityCard
                            key={opp.id}
                            pid={opp.id}
                            uid={uid}
                            title={opp.title}
                            description={opp.description}
                            type="VOLUNTEER"
                            button="Manage"
                        />
                    ))}
                </div>


                <h3 className="opportunity-page-subheader">Employment Opportunities</h3>
                <Link
                    className="btn btn-sm btn-outline-dark opportunity-newButton"
                    to={{
                        pathname: "/myopportunities/create",
                        state: {uid: uid, type: "EMPLOYMENT"}
                    }}
                >
                    New
                </Link>
                <div className="row opportunityList">
                    {empOpp.map((opp) => (
                        <OpportunityCard
                            key={opp.id}
                            pid={opp.id}
                            uid={uid}
                            title={opp.title}
                            description={opp.description}
                            type="EMPLOYMENT"
                            button="Manage"
                        />
                    ))}
                </div>


                <h3 className="opportunity-page-subheader
                ">Consulting Opportunities</h3>
                <Link
                    className="btn btn-sm btn-outline-dark opportunity-newButton"
                    to={{
                        pathname: "/myopportunities/create",
                        state: {uid: uid, type: "CONSULTING"}
                    }}
                >
                    New
                </Link>
                <div className="row opportunityList">
                    {conOpp.map((opp) => (
                        <OpportunityCard
                            key={opp.id}
                            pid={opp.id}
                            uid={uid}
                            title={opp.title}
                            description={opp.description}
                            type="CONSULTING"
                            button="Manage"
                        />
                    ))}
                </div>
            </div>
        );
    }
}
 
export default MyOpportunities;