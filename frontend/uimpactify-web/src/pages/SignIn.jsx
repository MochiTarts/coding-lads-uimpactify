import React from "react";
import '../stylesheets/css/SignIn.css';
import {Container, Row, Col,
    Card,
    CardTitle,
    CardSubtitle,
    CardBody,
    CardFooter,
    Button,
    FormInput,
    FormGroup
  } from "shards-react";
import {signIn} from "../helpers/services/user-service";
import  {UserContextConsumer} from "../components/UserContextProvider";

class SignIn extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      username: "",
      password: "",
    }
  }

  movePrevPage = () => {
    this.setState({signUpStep: this.state.signUpStep-1});
  }

  moveNextPage = () => {
    this.setState({signUpStep: this.state.signUpStep+1});
  }

  finishSignIn = (signInCallBack) => {
    const {username, password} = this.state;
    signIn(username, password).then(
      () => {
        signInCallBack({username});
        this.props.history.push('/dashboard');
      },
      (error) => {

      }
    )
  }

  handleChangeUsername = (event) => {
    this.setState({username: event.target.value});
  }
  handleChangePassword = (event) => {
    this.setState({password: event.target.value});
  }

  render() {
    const {username, password} = this.state;
    const isFormValid = username && password;
    return (
        <UserContextConsumer>
            {(context) => (
                
                <Container 
                className = "container" 
                >
                        <Card id="signin-card">
                        <CardBody>
                            <CardTitle>Sign In</CardTitle>
                            <CardSubtitle>Please enter your credentials</CardSubtitle>
                            <Col>
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
                                    <FormInput type="password" placeholder="Password" onChange={this.handleChangePassword}/>    
                                    </FormGroup>   
                                </Col>
                                </Row>
                            </Col>
                        </CardBody>
                        <CardFooter>
                            <Row>
                                <Col className="container">
                                    <Button disabled={!isFormValid} onClick={()=>{this.finishSignIn(context.signIn)}}>Sign In</Button>                        
                                </Col>
                            </Row>
                        </CardFooter>
                        </Card>
                </Container>
            )}
        </UserContextConsumer>

    );
  }
}
export default SignIn;