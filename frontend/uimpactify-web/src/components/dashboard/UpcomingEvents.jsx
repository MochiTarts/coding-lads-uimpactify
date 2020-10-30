import React, { Fragment } from "react";
import { Container, Row, Col, Button } from "shards-react";
import Calender from "./Calender.jsx";

class UpcomingEvents extends React.Component {
  constructor(props) {
    super(props);
  }
  render() {
    return (
      <Container>
        <Button>Open calendar</Button>
        <Col>
          <h4>Today</h4>
          <Row>{this.props.today && ? this.props.today : "Nothing!"}</Row>
          <h4>Today</h4>
          <Row>{this.props.tomorrow ? this.props.tomorrow : "Nothing!"}</Row>
        </Col>
      </Container>
    );
  }
}
export default UpcomingEvents;
