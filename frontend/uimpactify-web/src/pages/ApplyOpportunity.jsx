import React, { Component } from 'react';
import "../stylesheets/css/Opportunities.css";

class ApplyOpportunity extends Component {
    constructor(props) {
        super(props);
        const { uid, type, title, description } = props.location.state;
        this.state = {
                uid: uid,
                type: type,
                title: title,
                description: description,
                resume: null
        }
    }

    handleChange(event) {
        this.setState({
            resume: event.target.files[0]
        });
    }

    handleApply(event) {
        event.preventDefault();
        // TODO: hook up API here
        console.log(this.state.resume);
    }

    render() { 
        var isValid = this.state.resume;
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
                        <label htmlFor="resume">Upload Resume Here</label>
                        <input type="file"
                               id="resume"
                               className="form-control"
                               onChange={(event) => this.handleChange(event)} />
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