import React, { Component } from 'react';
import { updatePosting, getUser, deletePosting } from "../helpers/services/user-service";
import "../stylesheets/css/Opportunities.css";

class ManageOpportunity extends Component {
    constructor(props) {
        super(props);
        const { uid, type, pid, title, description } = props.location.state;
        const socialInit = props.uinfo.socialInit;
        this.state = {
                uid: uid,
                socialInit: socialInit,
                type: type,
                pid: pid,
                title: title,
                description: description
        }
    }

    handleSave = (event) => {
        event.preventDefault();
        const { pid, title, description, uid, type, socialInit } = this.state;
        updatePosting(pid, title, description, uid, type, socialInit.name).then(
            (r) => {
                this.props.history.push("/opportunity/myopportunities");
            }
        );
    }

    handleDelete = (event) => {
        event.preventDefault();
        deletePosting(this.state.pid).then(
            (r) => {
                this.props.history.push("/opportunity/myopportunities");
            }
        );
    }

    render() {
        const { title, description } = this.state;
        var isValid = title && description;

        return (
            <div className="opportunity-page-container">
                <div className="shadow p-3 mb-5 bg-white rounded">
                    <h3 className="opportunity-page-header">Managing Opportunity</h3>
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
                    <button type="submit" 
                            className="btn btn-secondary opportunity-formButtons" 
                            onClick={(event) => this.handleDelete(event)}>
                        Delete
                    </button>
                </form>
            </div>
        );
    }
}

export default ManageOpportunity