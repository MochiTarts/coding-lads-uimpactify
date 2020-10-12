import React, { Component } from 'react';
import OpportunityCard from '../components/OpportunityCard';

const styles = {
    headStyle: {
        color: `rgb(172, 119, 206)`
    },
    subheadStyle: {
        color: `rgb(79, 79, 79)`,
        display: 'inline',
        marginRight: 10
    }
}

class Opportunities extends Component {
    constructor(props) {
        super(props);
        const volOpp = [
            {id: '00125', title: "Reg in the Abyss", description: "The hole in front; a mere gap on the planet compares not to the opening within..."},
            {id: '12981', title: "Billy's Wish", description: "On an autumn day, Billy walks down where none have passed, as a shadow of longing swiftly eludes through the crevices..."}
        ];
        const empOpp = [
            {id: '00125', title: "Reg in the Abyss", description: "The hole in front; a mere gap on the planet compares not to the opening within..."},
            {id: '12981', title: "Billy's Wish", description: "On an autumn day, Billy walks down where none have passed, as a shadow of longing swiftly eludes through the crevices..."}
        ];
        this.state = {
            volOpp: volOpp,
            empOpp: empOpp
        }
    }

    handleNewOpportunity = () => {
        console.log("New clicked!");
    }

    render() { 
        const { volOpp, empOpp } = this.state;

        return (
            <div style={{ marginRight: 25, marginLeft: 25 }}>
                <div className="shadow p-3 mb-5 bg-white rounded">
                    <h3 style={styles.headStyle}>Opportunities</h3>
                </div>

                <h3 style={styles.subheadStyle}>Volunteer Opportunities</h3>
                <button onClick={this.handleNewOpportunity} class="btn btn-sm btn-outline-dark">New</button>
                <div className="row">
                    {volOpp.map((opp) => (
                        <OpportunityCard
                            imgsrc="#"
                            title={opp.title}
                            description={opp.description}
                        />
                    ))}
                </div>

                <h3 style={styles.subheadStyle}>Employment Opportunities</h3>
                <button onClick={this.handleNewOpportunity} class="btn btn-sm btn-outline-dark">New</button>
                <div className="row">
                    {empOpp.map((opp) => (
                        <OpportunityCard
                            imgsrc="#"
                            title={opp.title}
                            description={opp.description}
                        />
                    ))}
                </div>
            </div>
        );
    }
}
 
export default Opportunities;