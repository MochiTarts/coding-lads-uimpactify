import React, { Component } from 'react';
import OpportunityEditForm from '../components/OpportunityEditForm.jsx';
import "../stylesheets/css/Opportunities.css";

function EditOpportunity(props) {
    const { isNew, type, title, description } = props.location.state
    var headerText;
    if (isNew) {
        headerText = "Creating New Opportunity";
    } else {
        headerText = "Managing Opportunity";
    }

    return (
        <div className="pageContainer">
            <div className="shadow p-3 mb-5 bg-white rounded">
                <h3 className="pageHeader">{headerText}</h3>
            </div>

            <OpportunityEditForm new={isNew} type={type} title={title} description={description}/>
        </div>
    );
}

export default EditOpportunity