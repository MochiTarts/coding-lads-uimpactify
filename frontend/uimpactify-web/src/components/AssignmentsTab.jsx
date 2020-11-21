import React, { Component } from 'react';
import "../stylesheets/css/AssignmentsTab.css";

class AssignmentsTab extends Component {
    constructor(props) {
        super(props);
        this.state = {
            assignList: []
        }
    }

    componentDidMount() {
        var assignList = [
            {aid: 1, name: "Assignment 1", due: Date.now()},
            {aid: 2, name: "Assignment 2", due: Date.now()}
        ];
        this.setState({ assignList: assignList });
    }

    render() { 
        const { assignList } = this.state;
        const dateRegex = "[A-Za-z]+ [A-Za-z]+ [0-9]{1,2} [0-9]{4} [0-9]{2}:[0-9]{2}";

        return (
            <div>
                <h3>Available Assignments</h3>
                <div className="assign-list">
                    {assignList.map((assign) => (
                        <div className="card assign-card">
                            <div className="card-body assign-card-body">
                                <h5 className="assign-title">{assign.name}</h5>
                                <p className="assign-due-date float-right">
                                    Due {Date(assign.due).toString().match(dateRegex)[0]}
                                </p>
                            </div>
                        </div>
                    ))}
                </div> 
                
            </div>
        );
    }
}

export default AssignmentsTab;