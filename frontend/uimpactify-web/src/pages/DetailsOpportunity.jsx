import React, { Component } from 'react';
import "../stylesheets/css/Opportunities.css";
import { getApplicationsByPosting } from "../helpers/services/user-service";

class DetailsOpportunity extends Component {
    constructor(props) {
        super(props);
        const { uid, type, pid, title, description } = props.location.state;
        this.state = {
                uid: uid,
                type: type,
                pid: pid,
                title: title,
                description: description,
                applied: false
        }
    }

    componentDidMount() {
        const { pid, uid } = this.state;
        const int_uid = parseInt(uid);
        getApplicationsByPosting(pid).then(
            (r) => {
                var applied = false;
                for (var i = 0; i < r.data.length; i++) {
                    console.log(r.data[i].applicant.id);
                    if (r.data[i].applicant.id === int_uid) {
                        applied = true;
                        break;
                    }
                }
                this.setState({ applied: applied });
            }
        );
    }

    render() {
        return (
            <div className="opportunity-page-container">
                <div className="shadow p-3 mb-5 bg-white rounded">
                    <h3 className="opportunity-page-header">Managing Opportunity</h3>
                </div>

                <div className="opportunity-grid">
                    <div className="grid-pTitle">
                        <h5>Title</h5>
                        <p className="text-center pTitle-content">{this.state.title}</p>
                    </div>
                    <div className="grid-pType">
                        <h5>Type</h5>
                        <p className="text-center pType-content">{this.state.type}</p>
                    </div>
                    <div className="grid-pDescription">
                        <h5>Description</h5>
                        <text className="pDescription-content">{this.state.description}</text>
                    </div>
                </div>

                {this.state.applied &&
                <h4 className="text-danger">*Your application is currently being processed... Check your email for any updates.</h4>}
                <a href="javascript:history.back()"
                    className="btn btn-secondary opportunity-formButtons">
                    Back
                </a>
            </div>
        );
    }
}

export default DetailsOpportunity;