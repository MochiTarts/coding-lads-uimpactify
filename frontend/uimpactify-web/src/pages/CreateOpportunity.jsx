import React, { Component } from 'react';
import { createPosting, getUser } from "../helpers/services/user-service";
import "../stylesheets/css/Opportunities.css";

class CreateOpportunity extends Component {
    constructor(props) {
        super(props);
        const { uid, type } = props.location.state;
        this.state = {
                uid: uid,
                type: type,
                title: "",
                description: ""
        }
    }

    handleSave = (event) => {
        event.preventDefault();
        const { title, description, uid, type } = this.state;
        getUser(uid).then(
            (r1) => {
                const socialInit = r1.data.socialInit;
                createPosting(title, description, uid, type, socialInit.name).then(
                    (r2) => {
                        this.props.history.push("/myopportunities");
                    }
                );
            }
        );
    }

    render() {
        const { title, description } = this.state;
        var isValid = title && description;

        return (
            <div className="opportunity-page-container">
                <div className="shadow p-3 mb-5 bg-white rounded">
                    <h3 className="opportunity-page-header">Creating New Opportunity</h3>
                </div>

                <form>
                    <div className="opportunity-formGroup">
                        <label htmlFor="title">Title</label>
                        <input 
                            type="text" 
                            className="form-control"
                            id="title" 
                            value={this.state.title}
                            onChange={(event) => this.setState({title: event.target.value})} />
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
                            onChange={(event) => this.setState({description: event.target.value})}/>
                    </div>
                    
                    <button type="submit" 
                            className="btn btn-primary opportunity-formButtons" 
                            disabled={!isValid}
                            onClick={(event) => this.handleSave(event)}>
                        Save
                    </button>
                    <a href="/myopportunities"
                       className="btn btn-secondary opportunity-formButtons">
                            Cancel
                    </a>
                </form>
            </div>
        );
    }
}

export default CreateOpportunity