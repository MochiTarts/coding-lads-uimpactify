import React from "react";
import { Container, Row, Col } from "shards-react";
import "../stylesheets/css/Dashboard.css";
import Calender from "../components/Calender";

class Dashboard extends React.Component {
  render() {
    return (
      <Container className="content-container">
        <Col>
          <Row>
            <h4>Upcoming Events</h4>
            <div className="dash-component">
              <Calender />
            </div>
          </Row>
          <Row>
            <h4>Suggested Events</h4>
          </Row>
        </Col>
        <Col>
          <Row>
            <h4>Your Courses</h4>
          </Row>
          <Row>
            <h4>News</h4>
          </Row>
        </Col>
      </Container>
    );
  }
}
export default Dashboard;
