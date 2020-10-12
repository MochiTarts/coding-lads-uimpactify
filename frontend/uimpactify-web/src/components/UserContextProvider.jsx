import React, { Component } from "react";
import UserModel from "../models/UserModel";
const { Provider, Consumer } = React.createContext();

class UserContextProvider extends Component {
  constructor(props) {
    super(props)
    const storedAuthenticatedUser = JSON.parse(localStorage.getItem('authenticatedUser'));
    this.state = {
      user: storedAuthenticatedUser
    };
  }
  signOut = () => {
    localStorage.removeItem('authenticatedUser');
    this.setState({user: null})
  }
  signIn = () => {
    // TODO: perform actual authentication
    var authenticatedUser  = new UserModel("Alex")
    localStorage.setItem('authenticatedUser', JSON.stringify(authenticatedUser));   
    this.setState({user:authenticatedUser})
  }
  render() {
    return <Provider value={{user: this.state.user, signOut: this.signOut, signIn: this.signIn}}>{this.props.children}</Provider>;
  }
}

export { UserContextProvider, Consumer as UserContextConsumer };