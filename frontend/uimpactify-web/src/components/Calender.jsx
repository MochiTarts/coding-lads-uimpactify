import React from "react";
import { Navbar, Nav, NavbarBrand, NavItem, NavLink } from "shards-react";
import InfiniteCalendar from "react-infinite-calendar";
import "react-infinite-calendar/styles.css";

const today = new Date();
const lastWeek = new Date(
  today.getFullYear(),
  today.getMonth(),
  today.getDate() - 7
);
class Calender extends React.Component {
  render() {
    return (
      <InfiniteCalendar
        width={400}
        height={200}
        selected={today}
        disabledDays={[]}
        minDate={lastWeek}
        displayOptions={{ showHeader: false }}
      />
    );
  }
}
export default Calender;
