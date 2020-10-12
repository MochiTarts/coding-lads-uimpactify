import React, { Component } from 'react';

function OpportunityCard(props) {
    return (
<<<<<<< HEAD
        <div class="col-sm-3 border rounded">
            <div class="card" />
=======
        <div class="col-sm-3 rounded">
            <div class="card" />
            <img class="card-img-top" src={props.imgsrc} alt="Opportunity Image" />
>>>>>>> 09c5e748b5aa348b2f113cdc4cfb5a99920784c9
            <div class="card-body">
                <h4 class="card-title">{props.title}</h4>
                <p class="card-text">{props.description}</p>
                <a href="#" class="btn btn-primary">Manage &rarr;</a>
            </div>
        </div>
    )
}
 
export default OpportunityCard;