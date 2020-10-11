import React from 'react';
import { BrowserRouter, Route } from 'react-router-dom';
import Landing from './pages/Landing.jsx';
import LandingAuthenticated from './pages/LandingAuthenticated.jsx';
import logo from './logo.svg';
import './App.css';
import "bootstrap/dist/css/bootstrap.min.css";
import "shards-ui/dist/css/shards.min.css"
import bubbleBackground from './img/double-bubble-outline.png';

const styles = {
  main: {
      backgroundImage: `url(${bubbleBackground})`,
      backgroundRepeat:  "repeat",
      height: '100vh'
  }
};

function App() {
  return (
    <div style={styles.main}>
      <BrowserRouter>
        <Route path="/" exact component={Landing} /> 
        <Route path="/authenticated" component={LandingAuthenticated} /> 
      </BrowserRouter>
    </div>

    // <div className="App">
    //   <header className="App-header">
    //     <img src={logo} className="App-logo" alt="logo" />
    //     <p>
    //       Edit <code>src/App.js</code> and save to reload.
    //     </p>
    //     <a
    //       className="App-link"
    //       href="https://reactjs.org"
    //       target="_blank"
    //       rel="noopener noreferrer"
    //     >
    //       Learn React
    //     </a>
    //   </header>
    // </div>
  );
}

export default App;
