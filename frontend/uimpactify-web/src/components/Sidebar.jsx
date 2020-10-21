import React from "react";
import { Navbar, Nav, NavbarBrand, NavItem, NavLink } from "shards-react";
import "../stylesheets/css/Sidebar.css";
import  {UserContextConsumer} from "../components/UserContextProvider";

class Sidebar extends React.Component {
  render() {
    return (
      <UserContextConsumer>
        {userContext => (
          <Nav className="sidebar" vertical={true}>
            <NavItem>
              <img
                id="sidebar-logo"
                src="https://wisxi.com/wp-content/uploads/2018/10/67226-1-e1539174955591.jpg"
              />
            </NavItem>
            {userContext.userId && 
            <div>
              <NavItem>
                <NavLink href="/dashboard">Dashboard</NavLink>
              </NavItem>,
              <NavItem>
                <NavLink href="/oppurtunities">Opportunities</NavLink>
              </NavItem>,
              <NavItem>
                <NavLink href="/settings">Settings</NavLink>
              </NavItem>,
              <NavItem>
                <NavLink href="#" onClick= {()=>{userContext.signOut()}}>Sign Out</NavLink>
              </NavItem>
            </div>}
            {!userContext.userId && 
            <div>
              <NavItem>
                <NavLink href="/aboutus">About Us</NavLink>
              </NavItem>,
              <NavItem>
                <NavLink href="/signin">Sign In</NavLink>
              </NavItem >,
              <NavItem>
                <NavLink href="/signup">Sign Up</NavLink>
              </NavItem>
            </div>}
            
          </Nav>
        )}
      </UserContextConsumer>

    );
  }
}
export default Sidebar;
