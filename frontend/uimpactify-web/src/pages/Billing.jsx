import React from 'react';
import { Container, Card, CardBody, Row, Col, FormInput, FormGroup } from 'shards-react';
import '../stylesheets/css/Billing.css';
export default class Billing extends React.Component {
    render() {
        return(
            <div>
                <Container className="container">
                <Col xs="12">
                    <Row>
                    <Col>
                        <FormGroup>
                        <label>Username</label>
                        <FormInput placeholder="Username" />    
                        </FormGroup>   
                    </Col>
                    </Row>
                    <Row>
                    <Col>
                        <FormGroup>
                        <label>Password</label>
                        <FormInput type="password" placeholder="Password" />    
                        </FormGroup>   
                    </Col>
                    </Row>

                </Col>
                </Container>

            </div>
        )
    }
}