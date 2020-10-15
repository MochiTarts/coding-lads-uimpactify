import React, { Fragment } from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import Dashboard from "./pages/Dashboard.jsx";
import MyOpportunities from "./pages/MyOpportunities.jsx";
import ExploreOpportunities from "./pages/ExploreOpportunities.jsx";
import OpportunityNew from "./pages/OpportunityNew.jsx";
import OpportunityManage from "./pages/OpportunityManage.jsx";
import "bootstrap/dist/css/bootstrap.min.css";
import "shards-ui/dist/css/shards.min.css";
import bubbleBackground from "./img/double-bubble-outline.png";
import "./App.css";
import Sidebar from "./components/Sidebar";
import Landing from "./pages/Landing.jsx";
import SignUp from "./pages/SignUp.jsx";
import  {UserContextProvider} from "./components/UserContextProvider";
import  PrivateRoute from "./components/PrivateRoute";

const styles = {
  main: {
    backgroundImage: `url(${bubbleBackground})`,
    backgroundRepeat: "repeat",
    minHeight: "100vh",
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
            <Route path="/signup" exact component={SignUp} />
            <Fragment>
              <PrivateRoute path="/dashboard" component={Dashboard} />
              <PrivateRoute path="/explore-opportunities" component={ExploreOpportunities} />
              <PrivateRoute path="/myopportunities" exact component={MyOpportunities} />
              <PrivateRoute path="/myopportunities/new" component={OpportunityNew} />
              <PrivateRoute path="/myopportunities/manage" component={OpportunityManage} />
            </Fragment>
          </Switch>
        </BrowserRouter>
      </UserContextProvider>
    </div>
  );
}

export default App;
