import React from "react";
import { Container, Row, Col } from "shards-react";
import EventItem from "./EventItem.jsx";

class YourCourses extends React.Component {
  constructor(props) {
    super(props);
  }
  render() {
    return (
      <div className="dash-component">
        <Container id="courses">
          <Row className="item-row">
            {this.props.courses.slice(0, 4).map((x) => {
              const isEmpty = (obj) => {
                for (let key in obj) {
                  if (obj.hasOwnProperty(key)) return false;
                }
                return true;
              };
              if (!isEmpty(x))
                return (
                  <Col>
                    <EventItem image={x.image} title={x.title} omittime />
                  </Col>
                );
            })}
            <Col>
              <EventItem
                className="add-new-courses"
                image="https://image.flaticon.com/icons/png/512/32/32339.png"
                title="Add a New Course"
                omittime
                onClick={() => {
                  window.location.href = "/courses/mycourses";
                }}
              />
            </Col>
          </Row>
        </Container>
      </div>
    );
  }
}
export default YourCourses;
