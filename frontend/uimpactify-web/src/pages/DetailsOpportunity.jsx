import React, { Component } from 'react';
import "../stylesheets/css/Opportunities.css";

class DetailsOpportunity extends Component {
    constructor(props) {
        super(props);
        const { uid, type, pid, title, description } = props.location.state;
        this.state = {
                uid: uid,
                type: type,
                pid: pid,
                title: title,
                description: description
        }
    }

    render() {
        return (
            <div className="opportunity-page-container">
                <div className="shadow p-3 mb-5 bg-white rounded">
                    <h3 className="opportunity-page-header">Managing Opportunity</h3>
                </div>

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
                
                <a href="javascript:history.back()"
                   className="btn btn-secondary opportunity-formButtons">
                    Back
                </a>
            </div>
        );
    }
}

export default DetailsOpportunity;