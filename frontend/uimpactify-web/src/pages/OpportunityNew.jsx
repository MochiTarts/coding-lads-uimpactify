import React, { Component } from 'react';
import OpportunityEditForm from '../components/OpportunityEditForm.jsx';
import "../stylesheets/css/Opportunities.css";

function OpportunityNew() {
    return (
        <div className="pageContainer">
            <div className="shadow p-3 mb-5 bg-white rounded">
                <h3 className="pageHeader">Creating New Opportunity</h3>
            </div>

            <OpportunityEditForm new={true} />
        </div>
    );
}

export default OpportunityNew