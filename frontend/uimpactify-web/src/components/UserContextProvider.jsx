import React, { Component } from "react";
const { Provider, Consumer } = React.createContext();

class UserContextProvider extends Component {
  constructor(props) {
    super(props)
    const storedAuthenticatedUserId = localStorage.getItem('authenticatedUserId');
    this.state = {
      userId: storedAuthenticatedUserId
    };
  }
  signOut = () => {
    localStorage.removeItem('authenticatedUserId');
    this.setState({userId: null})
  }
  signIn = (authenticatedUserId) => {
    localStorage.setItem('authenticatedUserId', authenticatedUserId);   
    this.setState({userId:authenticatedUserId})
  }
  render() {
    return <Provider value={{userId: this.state.userId, signOut: this.signOut, signIn: this.signIn}}>{this.props.children}</Provider>;
  }
}

export { UserContextProvider, Consumer as UserContextConsumer };