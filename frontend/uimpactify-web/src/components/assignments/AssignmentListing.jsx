import React, { Component } from 'react';
import "../../stylesheets/css/AssignmentsTab.css";
import assign_plus from "../../img/assign_plus.png";

class AssignmentListing extends Component {
    constructor(props) {
        super(props);
        this.state = {
            assignList: []
        }
    }

    componentDidMount() {
        var assignList = [
            {aid: 1, name: "Assignment 1", due: Date()},
            {aid: 2, name: "Assignment 2", due: Date()}
        ];
        this.setState({ assignList: assignList });
    }


    render() {
        const { onCreate, onGoDetails } = this.props;
        const { assignList } = this.state;
        const dateRegex = "[A-Za-z]+ [A-Za-z]+ [0-9]{1,2} [0-9]{4} [0-9]{2}:[0-9]{2}";
        const type = this.props.role ? this.props.role.name : null;
        const isConsultant = true ? type === "IMPACT_CONSULTANT" : false;
        
        return(
            <div>
                <h3>Available Assignments</h3>
                <div>
                    {assignList.map((assign) => (
                        <div className="card assign-card">
                            <div className="card-body assign-card-body"
                                onClick={() => onGoDetails(assign)}>
                                <h5 className="assign-title">{assign.name}</h5>
                                <p className="assign-due-date float-right">
                                    Due {Date(assign.due).toString().match(dateRegex)[0]}
                                </p>
                            </div>
                        </div>
                    ))}
                </div>
                {isConsultant &&
                <div className="new-assign-card text-center"
                    onClick={onCreate}>
                    <h5 className="assign-title">Create New Assignment</h5>
                    <img className="assign-plus-button" src={assign_plus}/>
                </div>}
            </div>
        );
    }
}
 
export default AssignmentListing;