import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import "../stylesheets/css/Courses.css";
import CourseCard from "../components/CourseCard.jsx";
import { getStudentCourses, getInstructorCourses } from "../helpers/services/course-service";

class MyCourses extends Component {
    constructor(props) {
        super(props);
        this.state = {
            uid: props.uid,
            role: props.uinfo.role,
            courseList: []
        }
    }

    componentDidMount() {
        // var courseList = [
        //     {cid: 1, title: "Hard-Coding 101: Spaghetti Edition", description: "Do you like hard coding because thinking is too difficult, well then enjoy our course on some tasty spaghetti and meatballs.", instructor: "Poytel Lias"},
        //     {cid: 2, title: "Unmangling the Mangler: Complete Course on Making PoST", description: "Have you heard of the infamous Code Mangler? If not, then you are hearing about it now. This slimy mangly coder will take your precious spaghetti and mangle it with its fluids, making your code unrecognizable.", instructor: "Deliao Puyat"}
        // ];
        // this.setState({ courseList: courseList })
        const { uid, role } = this.state;
        if (role !== null && role.name === "IMPACT_LEARNER") {
            getStudentCourses(uid).then(
                (r) => {
                    var courseList = [];
                    for (var i = 0; i < r.data.length; i++) {
                        const curr = r.data[i].course;
                        const { firstName, lastName } = curr.instructor.user;
                        courseList.push({
                            cid: curr.id,
                            title: curr.courseName,
                            instructor: firstName + " " + lastName,
                            description: curr.courseDesc
                        });
                    }
                    this.setState({ courseList: courseList });
                }
            );
        } else if (role !== null && role.name === "IMPACT_CONSULTANT") {
            getInstructorCourses(uid).then(
                (r) => {
                    var courseList = [];
                    for (var i = 0; i < r.data.length; i++) {
                        const curr = r.data[i];
                        const { firstName, lastName } = curr.instructor.user;
                        courseList.push({
                            cid: curr.id,
                            title: curr.courseName,
                            instructor: firstName + " " + lastName,
                            description: curr.courseDesc
                        });
                    }
                    this.setState({ courseList: courseList });
                }
            );
        }
        
    }

    render() {
        const { uid, role, socialInit, courseList } = this.state;
        const type = role ? role.name : null;
        const isConsultant = true ? type === "IMPACT_CONSULTANT" : false;
        const isLearner = true ? type === "IMPACT_LEARNER" : false;
        const isEmployee = true ? !role : false;

        var subheader;
        var buttonText;
        var buttonLink;
        if (isLearner || isEmployee) {
            subheader = "Enrolled Courses";
            buttonText = "Explore";
            buttonLink = "/courses/explore";
        } else if (isConsultant) {
            subheader = "Instructing Courses";
            buttonText = "New"
            buttonLink = "/courses/new"
        }

        return(
            <div className="courses-page-container">
                <div className="shadow p-3 mb-5 bg-white rounded">
                    <h3 className="courses-page-header">My Courses</h3>
                </div>

                <h3 className="courses-page-subheader">
                    {subheader}
                </h3>
                <Link
                    className="btn btn-sm btn-outline-dark courses-button"
                    to={{
                        pathname: buttonLink,
                        state: {}
                    }}>
                    {buttonText}
                </Link>
                <div className="row courses-list">
                    {courseList.map((c) => (
                        <CourseCard 
                            key={c.cid}
                            cid={c.cid}
                            title={c.title}
                            description={c.description}
                            instructor={c.instructor} />
                    ))}
                </div>

            </div>
        );
    }
}
 
export default MyCourses;