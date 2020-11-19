import React, {useState,useEffect } from 'react';
import { FormInput, Button,Row,Col,ListGroup,ListGroupItem, ListGroupItemHeading } from 'shards-react';

const Lobby = ({
  roomName,
  handleRoomNameChange,
  handleSubmit
}) => {
  const [sessionList, setSessionList] = useState([]);
  const [creationOpen, setCreationOpen] = useState(false);
  const [newSessionName, setNewSessionName] = useState('');
  const [newSessionTime, setNewSessionTime] = useState('');
  const [newSessionDesc, setNewSessionDesc] = useState('');
  const handleCreate = () => {
    if(!creationOpen) {
      setCreationOpen(true);
    } else {
      setSessionList([...sessionList, {name: newSessionName, time: newSessionTime, description: newSessionDesc}]);
      setNewSessionName('');
      setNewSessionTime('');
      setNewSessionDesc('');
      setCreationOpen(false);
    }
  }
  const selectRoom = (name) => {
    handleRoomNameChange(name);
    handleSubmit();
  }
  useEffect(() => {
    const sessions = [{name:"Lecture 0001",time:"5pm-7pm", description:"lecture live session #1"}, {name:"Tutorial 0001", time:"10am-11am", description:"tutorial live session #1"}]
    setSessionList(sessions);
  }, []);
  const sessionItems = sessionList.map((session) =>(
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
          <ListGroupItem >
      <Row>
        <Col xs="1">
          <Button pill theme="light" size="small" onClick={handleCreate}>
            {creationOpen ? "Confirm" : "Create"}
          </Button>
        </Col>
        <Col xs="2">
        {creationOpen && <FormInput placeholder="Session Name" value={newSessionName} onChange={(event)=>{setNewSessionName(event.target.value)}}/>}
        </Col>
        <Col xs="2">
        {creationOpen && <FormInput placeholder="Session Time" value={newSessionTime} onChange={(event)=>{setNewSessionTime(event.target.value)}}/>}
        </Col>
        <Col xs="4">
        {creationOpen && <FormInput placeholder="Session Description" value={newSessionDesc} onChange={(event)=>{setNewSessionDesc(event.target.value)}}/>}
        </Col>
      </Row>

    </ListGroupItem>
    </ListGroup>
  );
};

export default Lobby;