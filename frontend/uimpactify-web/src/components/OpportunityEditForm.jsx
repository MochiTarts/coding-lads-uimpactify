import React, { Component } from 'react';
import { FormTextarea } from 'shards-react';

class OpportunityEditForm extends Component {
    constructor(props) {
        super(props);
        if (props.new) {
            this.state = {
                title: "",
                description: ""
            }
        } else {
            this.state = {
                title: props.title,
                description: props.description
            }
        }
    }

    handleSave() {
        // make API call to write to DB
        console.log("Clicked Save!");
    }

    render() {
        return (
            <form action="/opportunities">
                <div className="form-group">
                    <label for="title">Title</label>
                    <input 
                        type="text" 
                        className="form-control" 
                        id="title" 
                        value={this.state.title}
                        onChange={(event) => this.setState({title: event.target.value})} />
                </div>
                <div className="form-group">
                    <label for="description">Description</label>
                    <textarea
                        type="text"
                        className="form-control"
                        id="description"
                        rows='10'
                        value={this.state.description}
                        onChange={(event) => this.setState({description: event.target.value})}/>
                </div>
                <button type="submit" className="btn btn-primary" onClick={this.handleSave}>Save</button>
            </form>
        );
    }
}
export default OpportunityEditForm;