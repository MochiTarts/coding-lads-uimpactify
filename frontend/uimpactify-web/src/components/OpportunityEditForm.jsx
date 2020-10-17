import React, { Component } from 'react';
import "../stylesheets/css/OpportunityEditForm.css";

class OpportunityEditForm extends Component {
    constructor(props) {
        super(props);
        if (props.new) {
            this.state = {
                isNew: true,
                type: props.type,
                title: "",
                description: ""
            }
        } else {
            this.state = {
                isNew: false,
                type: props.type,
                title: props.title,
                description: props.description
            }
        }
    }

    handleSave() {
        // TODO: make API call to write to DB
        console.log("Clicked Save!");
    }

    handleDelete() {
        // TODO: make API call to delete this opportunity
        console.log("Clicked Delete!");
    }

    render() {
        return (
            <form action="/myopportunities">
                <div className="form-group">
                    <label htmlFor="title">Title</label>
                    <input 
                        type="text" 
                        className="form-control"
                        id="title" 
                        value={this.state.title}
                        onChange={(event) => this.setState({title: event.target.value})} />
                </div>
                <div className="form-group">
                    <label htmlFor="type">Type</label>
                    <input 
                        type="text" 
                        className="form-control"
                        id="type" 
                        value={this.state.type}
                        disabled />
                </div>
                <div className="form-group">
                    <label htmlFor="description">Description</label>
                    <textarea
                        type="text"
                        className="form-control"
                        id="description"
                        rows='10'
                        value={this.state.description}
                        onChange={(event) => this.setState({description: event.target.value})}/>
                </div>
                
                <button type="submit" className="btn btn-primary formButtons" onClick={this.handleSave}>Save</button>
                {this.state.isNew && 
                    <a href="/myopportunities" className="btn btn-secondary formButtons">
                        Cancel
                    </a>}   
                {!this.state.isNew && 
                    <button type="submit" className="btn btn-secondary formButtons" onClick={this.handleDelete}>
                        Remove
                    </button>}        
            </form>
        );
    }
}
export default OpportunityEditForm;