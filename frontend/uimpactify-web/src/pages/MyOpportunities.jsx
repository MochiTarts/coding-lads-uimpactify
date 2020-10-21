import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import OpportunityCard from '../components/OpportunityCard.jsx';
import "../stylesheets/css/Opportunities.css";
import { mountMyOpportunities } from "../helpers/services/user-service";

class MyOpportunities extends Component {
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
        var curr;
        var volOpp = [];
        var empOpp = [];
        var conOpp = [];
        mountMyOpportunities().then(
            (r) => {
                const response = JSON.parse(r);
                for (var i = 0; i < response.length; i++) {
                    curr = response[i];
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
            },
            (e) => {}
        );
    }

    render() { 
        const { volOpp, empOpp, conOpp } = this.state;

        return (
            <div className="pageContainer">
                <div className="shadow p-3 mb-5 bg-white rounded">
                    <h3 className="pageHeader">My Opportunities</h3>
                </div>

                <h3 className="pageSubheader">Volunteer Opportunities</h3>
                <Link
                    className="btn btn-sm btn-outline-dark newButton"
                    to={{
                        pathname: "/myopportunities/manage",
                        state: {
                            isNew: true,
                            uid: null,
                            socialInit: null,
                            type: "volunteer"
                        }
                    }}
                >
                    New
                </Link>
                <div className="row opportunityList">
                    {volOpp.map((opp) => (
                        <OpportunityCard
                            key={opp.id}
                            uid={null}
                            socialInit={null}
                            title={opp.title}
                            description={opp.description}
                            type="volunteer"
                            button="Manage"
                        />
                    ))}
                </div>


                <h3 className="pageSubheader">Employment Opportunities</h3>
                <Link
                    className="btn btn-sm btn-outline-dark newButton"
                    to={{
                        pathname: "/myopportunities/manage",
                        state: {
                            isNew: true,
                            uid: null,
                            socialInit: null,
                            type: "employment"
                        }
                    }}
                >
                    New
                </Link>
                <div className="row opportunityList">
                    {empOpp.map((opp) => (
                        <OpportunityCard
                            key={opp.id}
                            uid={null}
                            socialInit={null}
                            title={opp.title}
                            description={opp.description}
                            type="employment"
                            button="Manage"
                        />
                    ))}
                </div>


                <h3 className="pageSubheader">Consulting Opportunities</h3>
                <Link
                    className="btn btn-sm btn-outline-dark newButton"
                    to={{
                        pathname: "/myopportunities/manage",
                        state: {
                            isNew: true,
                            uid: null,
                            socialInit: null,
                            type: "consulting"
                        }
                    }}
                >
                    New
                </Link>
                <div className="row opportunityList">
                    {conOpp.map((opp) => (
                        <OpportunityCard
                            key={opp.id}
                            uid={null}
                            socialInit={null}
                            title={opp.title}
                            description={opp.description}
                            type="consulting"
                            button="Manage"
                        />
                    ))}
                </div>
            </div>
        );
    }
}
 
export default MyOpportunities;