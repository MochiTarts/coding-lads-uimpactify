import React from "react";
import { Navbar, Nav, NavbarBrand, NavItem, NavLink, Dropdown, DropdownToggle, DropdownMenu, DropdownItem } from "shards-react";
import "../stylesheets/css/Sidebar.css";
import  {UserContextConsumer} from "../components/UserContextProvider";

class Sidebar extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      dropdownOpen: false,
    }
    this.toggleDropdown = this.toggleDropdown.bind(this)
  }

  toggleDropdown() {
    this.setState({
      ...this.state,
      ...{
        dropdownOpen: !this.state.dropdownOpen
      }
    });
  }

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
            {userContext.user && 
            <div>
              <NavItem>
                <NavLink href="/dashboard">Dashboard</NavLink>
              </NavItem>,
              <Dropdown open={this.state.dropdownOpen} toggle={this.toggleDropdown} direction="right">
                <DropdownToggle nav caret>Opportunities</DropdownToggle>
                <DropdownMenu>
                  <DropdownItem href="/opportunities">My Opportunities</DropdownItem>
                  <DropdownItem>Explore</DropdownItem>
                </DropdownMenu>
              </Dropdown>,
              <NavItem>
                <NavLink href="/settings">Settings</NavLink>
              </NavItem>,
              <NavItem>
                <NavLink href="#" onClick= {()=>{userContext.signOut()}}>Sign Out</NavLink>
              </NavItem>
            </div>}
            {!userContext.user && 
            <div>
              <NavItem>
                <NavLink href="/aboutus">About Us</NavLink>
              </NavItem>,
              <NavItem>
                <NavLink href="/dashboard" onClick=  {()=>{userContext.signIn()}}>Sign In</NavLink>
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
