import React, { Fragment } from "react";
import { Container, Row, Col, Button } from "shards-react";

class UpcomingEvents extends React.Component {
  constructor(props) {
    super(props);
  }
  render() {
    return (
      <Container>
        <Col>
          <h4>Today</h4>
          <Row>{this.props.today ? this.props.today : "Nothing!"}</Row>
          <h4>Tomorrow</h4>
          <Row>{this.props.tomorrow ? this.props.tomorrow : "Nothing!"}</Row>
        </Col>
      </Container>
    );
  }
}
export default UpcomingEvents;
