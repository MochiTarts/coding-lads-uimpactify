import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import "../stylesheets/css/OpportunityCard.css";

function OpportunityCard(props) {
    var linkPath = "/";
    if (props.button == "Manage") {
        linkPath = "/myopportunities/manage";
    } else if (props.button == "Apply") {
        linkPath = "/myopportunities";
    }

    return (
        <div className="col-sm-3 border rounded cardContainer">
            <div className="card" />
            <div className="card-body">
                <h4 className="card-title">{props.title}</h4>
                <p className="card-text cardDescription">{props.description}</p>
                <Link 
                className="btn btn-primary"
                to={{
                    pathname: linkPath,
                    state: {
                        isNew: false,
                        uid: props.uid,
                        socialInit: props.socialInit,
                        type: props.type,
                        pid: props.key,
                        title: props.title,
                        description: props.description,
                    }
                }}>
                    {props.button} &rarr;
                </Link>;
            </div>
        </div>
    )
}
 
export default OpportunityCard;