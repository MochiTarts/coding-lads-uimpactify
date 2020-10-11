import React from "react";
import { Navbar, Nav, NavbarBrand, NavItem, NavLink } from "shards-react";
import "../stylesheets/css/Sidebar.css";

class Sidebar extends React.Component {
  render() {
    return (
      <Nav className="sidebar" vertical={true}>
        <NavItem>
          <img
            id="sidebar-logo"
            src="https://wisxi.com/wp-content/uploads/2018/10/67226-1-e1539174955591.jpg"
          />
        </NavItem>
        <NavItem>
          <NavLink active href="#">
            Dashboard
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink href="#">Opportunities</NavLink>
        </NavItem>
        <NavItem>
          <NavLink href="#">Settings</NavLink>
        </NavItem>
      </Nav>
    );
  }
}
export default Sidebar;
