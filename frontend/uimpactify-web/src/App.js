import React, { Fragment } from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import Dashboard from "./pages/Dashboard.jsx";
import "bootstrap/dist/css/bootstrap.min.css";
import "shards-ui/dist/css/shards.min.css";
import bubbleBackground from "./img/double-bubble-outline.png";
import "./App.css";
import Sidebar from "./components/Sidebar";
import Landing from "./pages/Landing.jsx";

const styles = {
  main: {
    backgroundImage: `url(${bubbleBackground})`,
    backgroundRepeat: "repeat",
    height: "100vh",
  },
};

function App() {
  return (
    <BrowserRouter>
      <Switch>
        <Route path="/" exact component={Landing} />
        <Fragment>
          <div id="main-container" style={styles.main}>
            <Sidebar />
            <Route path="/dashboard" component={Dashboard} />
          </div>
        </Fragment>
      </Switch>
    </BrowserRouter>

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
