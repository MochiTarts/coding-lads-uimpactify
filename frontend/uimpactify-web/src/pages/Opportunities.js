import React, { Component } from 'react';
import OpportunityCard from '../components/OpportunityCard';

const styles = {
<<<<<<< HEAD
    pageStyle: {
        marginRight: 25,
        marginLeft: 25,
        marginTop: 25
    },
=======
>>>>>>> 09c5e748b5aa348b2f113cdc4cfb5a99920784c9
    headStyle: {
        color: `rgb(172, 119, 206)`
    },
    subheadStyle: {
        color: `rgb(79, 79, 79)`,
        display: 'inline',
        marginRight: 10
<<<<<<< HEAD
    },
    oppListStyle: {
        marginBottom: 20
    },
    newButtonStyle: {
        marginBottom: 5
=======
>>>>>>> 09c5e748b5aa348b2f113cdc4cfb5a99920784c9
    }
}

class Opportunities extends Component {
    constructor(props) {
        super(props);
<<<<<<< HEAD
        // hard coded opportunities, will be deleted
=======
>>>>>>> 09c5e748b5aa348b2f113cdc4cfb5a99920784c9
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

<<<<<<< HEAD
    componentDidMount() {
        // fetch opportunities from DB and update state
        console.log("mounted");
=======
    handleNewOpportunity = () => {
        console.log("New clicked!");
>>>>>>> 09c5e748b5aa348b2f113cdc4cfb5a99920784c9
    }

    render() { 
        const { volOpp, empOpp } = this.state;

        return (
<<<<<<< HEAD
            <div style={styles.pageStyle}>
=======
            <div style={{ marginRight: 25, marginLeft: 25 }}>
>>>>>>> 09c5e748b5aa348b2f113cdc4cfb5a99920784c9
                <div className="shadow p-3 mb-5 bg-white rounded">
                    <h3 style={styles.headStyle}>Opportunities</h3>
                </div>

                <h3 style={styles.subheadStyle}>Volunteer Opportunities</h3>
<<<<<<< HEAD
                <a href="/oppurtunities/new" 
                   class="btn btn-sm btn-outline-dark" 
                   style={styles.newButtonStyle}>
                    New
                </a>
                <div className="row" style={styles.oppListStyle}>
                    {volOpp.map((opp) => (
                        <OpportunityCard
=======
                <button onClick={this.handleNewOpportunity} class="btn btn-sm btn-outline-dark">New</button>
                <div className="row">
                    {volOpp.map((opp) => (
                        <OpportunityCard
                            imgsrc="#"
>>>>>>> 09c5e748b5aa348b2f113cdc4cfb5a99920784c9
                            title={opp.title}
                            description={opp.description}
                        />
                    ))}
                </div>

                <h3 style={styles.subheadStyle}>Employment Opportunities</h3>
<<<<<<< HEAD
                <a href="/oppurtunities/new" 
                   class="btn btn-sm btn-outline-dark" 
                   style={styles.newButtonStyle}>
                    New
                </a>
                <div className="row" style={styles.oppListStyle}>
                    {empOpp.map((opp) => (
                        <OpportunityCard
=======
                <button onClick={this.handleNewOpportunity} class="btn btn-sm btn-outline-dark">New</button>
                <div className="row">
                    {empOpp.map((opp) => (
                        <OpportunityCard
                            imgsrc="#"
>>>>>>> 09c5e748b5aa348b2f113cdc4cfb5a99920784c9
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