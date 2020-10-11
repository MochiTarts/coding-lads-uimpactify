import React from 'react';
import { Navbar, Nav, NavbarBrand, NavItem, NavLink } from "shards-react";

const styles = {
    navText: {
        color: 'white'
    }
  };

class Landing extends React.Component {

    render() {
        return (
            <Navbar type="dark" theme="dark" expand="md">
                <NavbarBrand href="#">U-Impactify</NavbarBrand>
                <Nav>
                    <NavItem >
                        <NavLink active style={styles.navText} href="#">
                            About Us
                        </NavLink>
                    </NavItem>
                    <NavItem>
                        <NavLink style={styles.navText} href="#">Pricing</NavLink>
                    </NavItem>
                    <NavItem>
                        <NavLink style={styles.navText} href="/authenticated">DEBUG: Simulate authenticated</NavLink>
                    </NavItem>
                </Nav>
             </Navbar>
        )
    }
}
export default Landing;