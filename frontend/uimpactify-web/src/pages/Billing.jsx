import React from 'react';
import { Container, Card, CardBody, Row, Col, FormInput, FormGroup, CardTitle } from 'shards-react';
import '../stylesheets/css/Billing.css';
import Chart from 'react-apexcharts';
import { ListGroup, ListGroupItem, ListGroupItemHeading, ListGroupItemText } from "shards-react";
export default class Billing extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
        
          invoiceSeries: [],
          earningSeries: [],
          invoiceOptions: {
            labels: [],
            chart: {
              type: 'donut',
            },
            plotOptions: {
                pie: {
                    expandOnClick: false,
                    donut: {
                        size: '40%',
                        labels: {
                            show: true,
                            name: {
                              show: true,
                              fontSize: '12px',
                              fontFamily: 'Helvetica, Arial, sans-serif',
                              fontWeight: 600,
                              color: undefined,
                              offsetY: -10,
                              formatter: function (val) {
                                return val.length > 20 ? val.substr(0, 19) + "..." : val
                              }
                            },
                            value: {
                              formatter: function (val) {
                                return "$" + val
                              }
                            }
                        }
                    }
                }
            },
            legend: {
              show: false,              
            },
            tooltip: {
              y: {
                formatter: function(value) {
                  return "$"+value;
                }
              }
            }

          },
          earningOptions: {
            labels: [],
            chart: {
              type: 'donut',
            },
            plotOptions: {
                pie: {
                  expandOnClick: false,
                    donut: {
                        size: '40%',
                        labels: {
                            show: true,
                            name: {
                              show: true,
                              fontSize: '12px',
                              fontFamily: 'Helvetica, Arial, sans-serif',
                              fontWeight: 600,
                              color: undefined,
                              offsetY: -10,
                              formatter: function (val) {
                                return val.length > 25 ? val.substr(0, 24) + "..." : val
                              }
                            },
                            value: {
                              formatter: function (val) {
                                return "$" + val
                              }
                            }
                        }
                    }
                }
            },
            legend: {
              show: false,              
            },
            tooltip: {
              y: {
                formatter: function(value) {
                  return "$"+value;
                }
              }
            }
          },
        
        };
    }

    componentDidMount() {
        this.setState({earningSeries: [4, 5, 1, 7], earningOptions: {...this.state.earningOptions, labels: ["Intro to Computer Science","Course B","Course C","Course D"]}, invoiceSeries: [44, 55, 41, 17, 15,10,10], invoiceOptions: {...this.state.invoiceOptions, labels: [41, 17, 15,10,10,10,10]}})
    }
    
    render() {

        return(

                <Container className="billing-container">
                    <Col>
                        <Row className="billing-header">
                            <h1>Billing</h1>
                        </Row>
                        <Row>
                          <Col xs="12">
                          <ListGroup>
                          <ListGroupItemHeading className="billing-li">
                            <Row>
                                <Col xs="2">
                                Date
                                </Col>
                                <Col xs="2">
                                Cost
                                </Col>
                                <Col xs="8">
                                  Details
                                </Col>
                            </Row>
                          </ListGroupItemHeading>
                            <ListGroupItem className="billing-li">
                              <Row>
                                <Col xs="2">
                                  123
                                </Col>
                                <Col xs="2">
                                  456
                                </Col>
                                <Col xs="8">
                                  789
                                </Col>
                              </Row>

                            </ListGroupItem>
                            <ListGroupItem>Dapibus ac facilisis in</ListGroupItem>
                            <ListGroupItem>Morbi leo risus</ListGroupItem>
                            <ListGroupItem>Porta ac consectetur ac</ListGroupItem>
                            <ListGroupItem>Vestibulum at eros</ListGroupItem>
                          </ListGroup>
                          </Col>

                        </Row>
                        <hr></hr>
                        <Row>
                            <Col >
                            <Card className="billing-card" >
                                <CardTitle>{`Invoices (Total: ${this.state.invoiceSeries.length})`}</CardTitle>
                                <CardBody>
                                <Chart options={this.state.invoiceOptions} series={this.state.invoiceSeries} type="donut" />
                                </CardBody>

                            </Card>
                            </Col>
                            <Col>
                            <Card  className="billing-card">
                                <CardTitle>{`Earnings (Total: ${this.state.earningSeries.length})`}</CardTitle>
                                <CardBody>
                                <Chart options={this.state.earningOptions} series={this.state.earningSeries} type="donut" />

                                </CardBody>
                            </Card>
                            </Col>

                        </Row>


                    </Col>
                </Container>


        )
    }
}