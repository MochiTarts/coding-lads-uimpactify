import React from 'react';
import { Link } from 'react-router-dom';
import "../stylesheets/css/OpportunityCard.css";

function OpportunityCard(props) {
    var linkPath = "/";
    if (props.button === "Manage") {
        linkPath = "/myopportunities/manage";
    } else if (props.button === "Apply") {
        linkPath = "/explore-opportunities/apply";
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
                        uid: props.uid,
                        type: props.type,
                        pid: props.pid,
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