import React, { Component } from 'react';

function OpportunityCard(props) {
    return (
        <div class="col-sm-3 rounded">
            <div class="card" />
            <img class="card-img-top" src={props.imgsrc} alt="Opportunity Image" />
            <div class="card-body">
                <h4 class="card-title">{props.title}</h4>
                <p class="card-text">{props.description}</p>
                <a href="#" class="btn btn-primary">Manage &rarr;</a>
            </div>
        </div>
    )
}
 
export default OpportunityCard;