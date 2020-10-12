import React, { Fragment } from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import Dashboard from "./pages/Dashboard.jsx";
import "bootstrap/dist/css/bootstrap.min.css";
import "shards-ui/dist/css/shards.min.css";
import bubbleBackground from "./img/double-bubble-outline.png";
import "./App.css";
import Sidebar from "./components/Sidebar";
import Landing from "./pages/Landing.jsx";
import  {UserContextProvider} from "./components/UserContextProvider";
import  PrivateRoute from "./components/PrivateRoute";

const styles = {
  main: {
    backgroundImage: `url(${bubbleBackground})`,
    backgroundRepeat: "repeat",
    height: "100vh",
  },
};

function App() {
  return (
    <div id="main-container" style={styles.main}>
      <UserContextProvider>
        <Sidebar />
        <BrowserRouter>
          <Switch>
            <Route path="/" exact component={Landing} />
            <Fragment>
              <PrivateRoute path="/dashboard" component={Dashboard} />
            </Fragment>
          </Switch>
        </BrowserRouter>
      </UserContextProvider>
    </div>
  );
}

export default App;
