import React, { Fragment } from "react";
import { Container, Row, Col, Button } from "shards-react";
import "../stylesheets/css/Dashboard.css";
import UpcomingEvents from "../components/dashboard/UpcomingEvents";
import SuggestedEvents from "../components/dashboard/SuggestedEvents";
import WorkShops from "../components/dashboard/WorkShops";
import YourCourses from "../components/dashboard/YourCourses";
import News from "../components/dashboard/News";

class Dashboard extends React.Component {
  render() {
    return (
      <Container className="content-container">
        <Col>
          <Row>
            <h4>Upcoming Events</h4>
            <div id="upcoming-events" className="dash-component">
              <UpcomingEvents />
            </div>
          </Row>
          <Row>
            <div className="title-group">
              <h4>Your Courses</h4>
              <Button>See All</Button>
            </div>
            <YourCourses courses={[{}, {}, {}, {}]} />
          </Row>
        </Col>
        <Col>
          <Row>
            <div className="title-group">
              <h4>Suggested Events</h4>
              <Button>See All</Button>
            </div>
            <div className="dash-component">
              <SuggestedEvents networking={[{}, {}, {}]} />
              <WorkShops workshops={[{}, {}, {}]} />
            </div>
          </Row>
          <Row>
            <h4>News</h4>
            <div className="dash-component news">
              <News news={[{}, {}, {}, {}]} />
            </div>
          </Row>
        </Col>
      </Container>
    );
  }
}
export default Dashboard;
