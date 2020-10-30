import React from "react";
import { Calendar, momentLocalizer } from "react-big-calendar";
import moment from "moment";
import "react-big-calendar/lib/css/react-big-calendar.css";
import { getEvents } from "../helpers/services/event-service";

const localizer = momentLocalizer(moment);

const getAndConvertEvents = () => {
  const storedAuthenticatedUserId = localStorage.getItem("authenticatedUserId");
  getEvents(storedAuthenticatedUserId).then((result) => {
    console.log(
      result.data.map((x) => {
        return { title: x.eventName, start: x.eventDate, end: x.eventDate };
      })
    );
  });
};

class CalendarPlanner extends React.Component {
  constructor(props) {
    super(props);
  }
  render() {
    return (
      <div>
        <Calendar
          localizer={localizer}
          events={[
            {
              title: "ass-FUCK MY NEIBOUR",
              start: "2020-10-28",
              end: "2020-10-28",
            },
          ]}
          startAccessor="start"
          endAccessor="end"
          style={{ height: 500 }}
        />
      </div>
    );
  }
}
export default CalendarPlanner;
