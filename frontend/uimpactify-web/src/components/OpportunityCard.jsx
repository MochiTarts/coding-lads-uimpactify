import React, { Component } from 'react';
import { Link } from 'react-router-dom';

function OpportunityCard(props) {
    return (
        <div className="col-sm-3 border rounded">
            <div className="card" />
            <div className="card-body">
                <h4 className="card-title">{props.title}</h4>
                <p className="card-text">{props.description}</p>
                <Link 
                className="btn btn-primary"
                to={{
                    pathname: "/oppurtunities/manage",
                    state: {
                        title: props.title,
                        description: props.description,
                    }
                }}
                >
                    Manage &rarr;
                </Link>
            </div>
        </div>
    )
}
 
export default OpportunityCard;