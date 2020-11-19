import React from 'react';
import { Button,Row,Col,ListGroup,ListGroupItem, ListGroupItemHeading } from 'shards-react';

const Lobby = ({
  roomName,
  handleRoomNameChange,
  handleSubmit
}) => {
  const selectRoom = (name) => {
    handleRoomNameChange(name);
    handleSubmit();
  }
  const sessions = [{name:"Lecture 0001",time:"5pm-7pm", description:"lecture live session #1"}, {name:"Tutorial 0001", time:"10am-11am", description:"tutorial live session #1"}]
  const sessionItems = sessions.map((session) =>(
    <ListGroupItem key={session.name}>
      <Row>
        <Col xs="1">
          <Button pill theme="success" size="small" onClick={()=>{selectRoom(session.name)}}>
            Enter
          </Button>
        </Col>
        <Col xs="2">
        {session.name}
        </Col>
        <Col xs="2">
        {session.time}
        </Col>
      </Row>

    </ListGroupItem>))
  return (
    <ListGroup>
          <ListGroupItemHeading>
      <Row>
        <Col xs="1">

        </Col>
        <Col xs="2">
        Session Name
        </Col>
        <Col xs="2">
        Time
        </Col>
        <Col xs="4">
        Description
        </Col>
      </Row>

    </ListGroupItemHeading>
          {sessionItems}
    </ListGroup>
  );
};

export default Lobby;