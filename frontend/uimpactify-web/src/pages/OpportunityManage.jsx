import React, { Component } from 'react';
import OpportunityEditForm from '../components/OpportunityEditForm.jsx';

const styles = {
    pageStyle: {
        marginRight: 25,
        marginLeft: 25,
        marginTop: 25
    },
    headStyle: {
        color: `rgb(172, 119, 206)`
    }
}

function OpportunityManage(props) {
    const { title, description } = props.location.state

    return (
        <div style={styles.pageStyle}>
            <div className="shadow p-3 mb-5 bg-white rounded">
                <h3 style={styles.headStyle}>Managing Opportunity</h3>
            </div>

            <OpportunityEditForm new={false} title={title} description={description}/>
        </div>
    );
}

export default OpportunityManage