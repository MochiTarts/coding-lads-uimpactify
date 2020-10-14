import React, { Component } from 'react';
import { Link } from 'react-router-dom';

const styles = {
    cardStyle: {
        backgroundColor: `rgb(239, 239, 242)`,
        marginLeft: 5,
        marginRight: 5
    },
    descripStyle: {
        display: "-webkit-box",
        WebkitLineClamp: 3,
        WebkitBoxOrient: "vertical",
        overflow: "hidden",
        textOverflow: "ellipsis"
    }
}

function OpportunityCard(props) {
    return (
        <div className="col-sm-3 border rounded" style={styles.cardStyle}>
            <div className="card" />
            <div className="card-body">
                <h4 className="card-title">{props.title}</h4>
                <p className="card-text" style={styles.descripStyle}>{props.description}</p>
                <Link 
                className="btn btn-primary"
                to={{
                    pathname: "/opportunities/manage",
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