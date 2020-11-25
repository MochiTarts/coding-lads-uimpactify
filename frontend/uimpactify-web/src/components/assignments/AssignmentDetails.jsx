import React, { Component } from 'react';
import "../../stylesheets/css/AssignmentsTab.css";
import file_icon from "../../img/file.png";

class AssignmentDetails extends Component {
    constructor(props) {
        super(props);
        this.state = {
            assign: props.curr_assign,
            submitFile: null,
            submittedFile: null,
            submissionList: []
        }
    }

    componentDidMount() {
        const type = this.props.role ? this.props.role.name : null;
        const isLearner = true ? type === "IMPACT_LEARNER" : false;
        const isConsultant = true ? type === "IMPACT_CONSULTANT" : false;

        if (isConsultant) {
            var submissionList = [];
            submissionList.push({sid: 100, submitedBy: "Ryan Lin", fileName: "assign.pdf"});
            submissionList.push({sid: 101, submitedBy: "Bob Li", fileName: "assign123.pdf"});
            this.setState({ submissionList: submissionList });
        } else if (isLearner) {
            this.setState({ submittedFile: "assign.pdf" });
        }
    }

    handleUploadSubmission = (event) => {
        const fileName = event.target.value.split("\\").pop();
        document.getElementById("submit-upload-text").innerHTML = fileName;
        this.setState({ submitFile: event.target.files[0] });
    }

    handleClickSubmit = () => {
        console.log(this.state.submitFile);
    }

    render() {
        const { assign, submitFile, submittedFile, submissionList } = this.state;
        const type = this.props.role ? this.props.role.name : null;
        const isLearner = true ? type === "IMPACT_LEARNER" : false;
        const isConsultant = true ? type === "IMPACT_CONSULTANT" : false;

        return(
            <div>
                <button className="btn btn-secondary float-right"
                    onClick={this.props.onBack}>
                    Back to Available Assignments
                </button>
                <h3>{assign.name}</h3>
                <p>Due {assign.due}</p>
                <a href="#">
                    <img src={file_icon} className="download-link-icon"/>
                    Download the assignment handout here
                </a><br/>

                {isLearner &&
                <div className="learner-submission-container">
                    <h4>Your Submission</h4>
                    {submittedFile && 
                    <div>
                        Submitted {submittedFile}
                    </div>}
                    {!submittedFile && 
                    <div>
                        Nothing submitted yet
                    </div>}
                    <div className="custom-file">
                        <input type="file" 
                            className="custom-file-input"
                            id="submission-upload"
                            onChange={(e) => this.handleUploadSubmission(e)} />
                        <label htmlFor="submission" className="custom-file-label" id="submit-upload-text">
                            Upload your assignment solution here
                        </label>
                    </div>
                    <button className="btn btn-primary assign-submit-button"
                            onClick={this.handleClickSubmit}
                            disabled={!submitFile}>
                        Submit
                    </button>
                </div>}

                {isConsultant &&
                <div className="consultant-submission-container">
                    <h4>Submissions</h4>
                    <div className="row">
                        {submissionList.map((sub) => (
                            <a href="#" className="sub-entry">
                                <p className="sub-entry-fname">{sub.fileName}</p>
                                <img className="sub-entry-link-icon" src={file_icon}/>
                                <p className="sub-entry-uname">Submitted by {sub.submitedBy}</p>
                            </a>
                        ))}
                    </div>
                </div>}
            </div>
        );
    }
}
 
export default AssignmentDetails;