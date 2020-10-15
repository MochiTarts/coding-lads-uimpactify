import React from "react";
import '../stylesheets/css/SignUp.css';
import {Container, Row, Col,
    Card,
    CardTitle,
    CardBody,
    CardFooter,
    Button,
    FormInput,
    FormGroup
  } from "shards-react";
import accountTypeSocialInit from "../img/account-type-social-initiative.png";
import accountTypeStudent from "../img/account-type-student.png";
import accountTypeInstructor from "../img/account-type-instructor.png";
import checkMark from "../img/check-mark.png";
import {signUp} from "../helpers/services/user-service";

class SignUp extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      signedUp: false,
      signUpStep: 1,
      accountType: "",
      firstName: "",
      lastName: "",
      username: "",
      password: "",
      confirmPassword: ""
    }
  }

  movePrevPage = () => {
    this.setState({signUpStep: this.state.signUpStep-1});
  }

  moveNextPage = () => {
    this.setState({signUpStep: this.state.signUpStep+1});
  }

  finishSignUp = () => {
    signUp().then(
      () => {
        this.setState({signedUp: true});
      },
      (error) => {

      }
    )
  }

  handleChangeFirstName = (event) => {
    this.setState({firstName: event.target.value});
  }
  handleChangeLastName = (event) => {
    this.setState({lastName: event.target.value});
  }
  handleChangeUsername = (event) => {
    this.setState({username: event.target.value});
  }
  handleChangePassword = (event) => {
    this.setState({password: event.target.value});
  }
  handleChangeConfirmPassword = (event) => {
    this.setState({confirmPassword: event.target.value});
  }

  render() {
    const {firstName, lastName, username, password, confirmPassword} = this.state;
    const isFormValid = firstName && lastName && username && password && confirmPassword;
    return (
        <Container 
        className = "container" 
        >
                <Card>
                <CardBody>
                    <CardTitle>
                      <h4>Sign up</h4>
                      <h6>
                        {!this.state.signedUp && this.state.signUpStep===1 && "Account Type"}
                        {!this.state.signedUp && this.state.signUpStep===2 && "Account Information"}
                        {this.state.signedUp && "Finish"}
                      </h6>
                    </CardTitle>
                    {!this.state.signedUp && this.state.signUpStep ===1 && 
                    <Row >
                      <Col className = {this.state.accountType === "social-initiative" ? "accountTypeColSelected" : "accountTypeCol" } onClick={()=>{this.setState({accountType:"social-initiative"})}}>
                        <a href="#social-initiative">
                          <img src={accountTypeSocialInit} className="accountTypeImg" alt="social-initiative"></img>
                          <p>Social Initiative</p>
                        </a>
                      </Col>
                      <Col className = {this.state.accountType === "student" ? "accountTypeColSelected" : "accountTypeCol" } onClick={()=>{this.setState({accountType:"student"})}}>
                        <a href="#student">
                          <img src={accountTypeStudent} className="accountTypeImg" alt="student"></img>
                          <p>Student</p>
                        </a>
                      </Col>          
                      <Col className = {this.state.accountType === "instructor" ? "accountTypeColSelected" : "accountTypeCol" } onClick={()=>{this.setState({accountType:"instructor"})}}>
                        <a href="#instructor">
                          <img src={accountTypeInstructor} className="accountTypeImg" alt="instructor"></img>
                          <p>Instructor</p>
                        </a>
                      </Col>
                    </Row>}
                    {!this.state.signedUp && this.state.signUpStep ===2 &&
                    <Col>
                      <Row>
                        <Col>
                          <FormGroup>
                            <label>First Name</label>
                            <FormInput placeholder="First Name" onChange={this.handleChangeFirstName} />    
                          </FormGroup>                  
                        </Col>
                        <Col>   
                          <FormGroup>
                            <label>Last Name</label>
                            <FormInput placeholder="Last Name" onChange={this.handleChangeLastName} />    
                          </FormGroup>   
                        </Col>
                      </Row>
                      <Row>
                        <Col>
                          <FormGroup>
                            <label>Username</label>
                            <FormInput placeholder="Username" onChange={this.handleChangeUsername}/>    
                          </FormGroup>   
                        </Col>
                      </Row>
                      <Row>
                        <Col>
                          <FormGroup>
                            <label>Password</label>
                            <FormInput placeholder="Password" onChange={this.handleChangePassword}/>    
                          </FormGroup>   
                        </Col>
                      </Row>
                      <Row>
                        <Col>
                          <FormGroup>
                            <label>Confirm Password</label>
                            <FormInput placeholder="Confirm Password" onChange={this.handleChangeConfirmPassword}/>    
                          </FormGroup>   
                        </Col>
                      </Row>
                    </Col>
                    }
                    { this.state.signedUp &&
                      <Container  >
                        <Col >
                          <Row className="container">
                          <img src={checkMark} id="checkMarkImg" alt="check-mark"></img>
                          </Row>
                          <hr></hr>
                          <Row>
                            Registered successfully, you can now sign in with your new account!
                          </Row>
                        </Col>
                      </Container>
                    }
                </CardBody>
                <CardFooter>
                    <Row>
                      <Col>
                        {!this.state.signedUp && this.state.signUpStep > 1 && 
                          <Button onClick={this.movePrevPage}>&larr; Back</Button>
                        }
                      </Col>
                      <Col>
                        {!this.state.signedUp && this.state.signUpStep === 1 && 
                          <Button disabled={!this.state.accountType} onClick={this.moveNextPage}>Proceed &rarr;</Button>
                        }
                        {!this.state.signedUp && this.state.signUpStep === 2 && 
                          <Button disabled={!isFormValid} onClick={this.finishSignUp}>Proceed : Finish &rarr;</Button>                        
                        }
                      </Col>
                    </Row>
                </CardFooter>
                </Card>
        </Container>
    );
  }
}
export default SignUp;