import React, { Component } from 'react';
import "../stylesheets/css/Opportunities.css";
import { createApplication } from "../helpers/services/user-service";

class ApplyOpportunity extends Component {
    constructor(props) {
        super(props);
        const { pid, type, title, description } = props.location.state;
        const uid = props.uid;
        this.state = {
                uid: uid,
                type: type,
                pid: pid,
                title: title,
                description: description,
                email: null
        }
    }

    handleChange(event) {
        this.setState({email: event.target.value});
    }

    handleApply(event) {
        event.preventDefault();
        const { uid, pid, email } = this.state;
        createApplication(uid, pid, email).then(
            (r) => {
                this.props.history.push("/opportunity/myopportunities");
            }
        );
    }

    render() { 
        var isValid = this.state.email;
        return (
            <div className="opportunity-page-container">
                <div className="shadow p-3 mb-5 bg-white rounded">
                    <h3 className="opportunity-page-header">Applying for Opportunity</h3>
                </div>

                <form>
                    <div className="opportunity-formGroup">
                        <label htmlFor="title">Title</label>
                        <input 
                            type="text" 
                            className="form-control"
                            id="title" 
                            value={this.state.title} 
                            disabled />
                    </div>
                    <div className="opportunity-formGroup">
                        <label htmlFor="type">Type</label>
                        <input 
                            type="text" 
                            className="form-control"
                            id="type" 
                            value={this.state.type}
                            disabled />
                    </div>
                    <div className="opportunity-formGroup">
                        <label htmlFor="description">Description</label>
                        <textarea
                            type="text"
                            className="form-control"
                            id="description"
                            rows='10'
                            value={this.state.description}
                            disabled/>
                    </div>
                    <div className="opportunity-formGroup files">
                        <label htmlFor="email">Your Email</label>
                        <input type="email"
                               id="email"
                               className="form-control"
                               onChange={(event) => this.setState({email: event.target.value})} />
                    </div>
                    
                    
                    <button type="submit" 
                            className="btn btn-primary opportunity-formButtons" 
                            disabled={!isValid}
                            onClick={(event) => this.handleApply(event)}>
                        Apply
                    </button>
                    <a href="javascript:history.back()"
                       className="btn btn-secondary opportunity-formButtons">
                        Cancel
                    </a>
                </form>
            </div>
        );
    }
}
 
export default ApplyOpportunity;