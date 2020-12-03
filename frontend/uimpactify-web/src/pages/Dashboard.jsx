import React, { Fragment } from "react";
import { Container, Row, Col, Button } from "shards-react";
import "../stylesheets/css/Dashboard.css";
import UpcomingEvents from "../components/dashboard/UpcomingEvents";
import SuggestedEvents from "../components/dashboard/SuggestedEvents";
import WorkShops from "../components/dashboard/WorkShops";
import YourCourses from "../components/dashboard/YourCourses";
import News from "../components/dashboard/News";
import { getStudentCourses } from "../helpers/services/course-service";

class Dashboard extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      courses: [],
    };
  }
  componentDidMount() {
    getStudentCourses(localStorage.getItem("authenticatedUserId")).then(
      (resp) => {
        this.setState({
          courses: resp.data.map((x) => {
            return {
              title: x.course.courseName,
              image: "/static/media/knowledge_clipart.e38938d0.png",
              id: x.course.id,
            };
          }),
        });
      }
    );
  }
  render() {
    return (
      <Container id="content-container" className="content-container">
        <Col className="col-1">
          <Row className="row-1-1">
            <div className="title-group">
              <h4>Upcoming Events</h4>
              <Button>Open Calendar</Button>
            </div>
            <div id="upcoming-events" className="dash-component">
              <UpcomingEvents />
            </div>
          </Row>
          <Row className="row-1-2">
            <div className="title-group">
              <h4>Your Courses</h4>
              <Button>See All</Button>
            </div>
            <YourCourses courses={this.state.courses} />
          </Row>
        </Col>
        <Col className="col-2">
          <Row className="row-1-1">
            <div className="title-group">
              <h4>Suggested Events</h4>
              <Button>See All</Button>
            </div>
            <div className="dash-component">
              <SuggestedEvents networking={[{}, {}, {}]} />
              <WorkShops workshops={[{}, {}, {}]} />
            </div>
          </Row>
          <Row className="row-1-2">
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
