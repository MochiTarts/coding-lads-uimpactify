import React, { Fragment } from "react";
import { Container, Row, Col } from "shards-react";
import Calender from "./Calender.jsx";

class UpcomingEvents extends React.Component {
  constructor(props) {
    super(props);
  }
  render() {
    return (
      <Container>
        <Col>
          <Calender />
        </Col>
        <Col>
          <Row>Today</Row>
          <Row>{this.props.today ? this.props.today : "Nothing!"}</Row>
          <Row>Tomorrow</Row>
          <Row>{this.props.tomorrow ? this.props.tomorrow : "Nothing!"}</Row>
        </Col>
      </Container>
    );
  }
}
export default UpcomingEvents;
