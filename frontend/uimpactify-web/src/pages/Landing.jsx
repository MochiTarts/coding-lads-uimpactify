import React from "react";
import "../stylesheets/css/Landing.css";

class Landing extends React.Component {
  render() {
    return (
      <div className="about-section">
        <div className="about-text">
          <h5>What is Uimpactify?</h5>
          <h2>
            An online learning platform for scoial entrepreneurs and
            intrapreneurs
          </h2>
          <div
            className="get-started"
            onClick={() => {
              window.location.href = "/signup";
            }}
          >
            GET STARTED
          </div>
        </div>
        <img
          className="about-image"
          src="https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fimages.clipartpanda.com%2Feducation-clip-art-yikL9r6iE.jpeg&f=1&nofb=1"
          alt="land page pic"
        />
      </div>
    );
  }
}
export default Landing;
