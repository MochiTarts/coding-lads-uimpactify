import React, { Component } from 'react';
import "../stylesheets/css/Courses.css";

class ExploreCourse extends Component {
    constructor(props) {
        super(props);
        this.state = {
            courseList: []
        }
        
    }

    componentDidMount() {
        // TODO: remove hard-coded list
        var courseList = [
            {cid: 1, title: "Hard-Coding 101: Spaghetti Edition", description: "Do you like hard coding because thinking is too difficult, well then enjoy our course on some tasty spaghetti and meatballs.", instructor: "Poytel Lias", cost: 50.50},
            {cid: 2, title: "Unmangling the Mangler: Complete Course on Making PoST", description: "Have you heard of the infamous Code Mangler? If not, then you are hearing about it now. This slimy mangly coder will take your precious spaghetti and mangle it with its fluids, making your code unrecognizable.", instructor: "Deliao Puyat", cost: 100}
        ];
        this.setState({ courseList: courseList })
    }

    handleClickCourse(id) {
        var courseButton = document.getElementById(id + "_button");
        var courseDetail = document.getElementById(id + "_details");
        if (courseDetail.style.display === "none") {
            courseDetail.style.display = "";
            courseButton.className += " c-expanded";    
        } else {
            courseDetail.style.display = "none";
            courseButton.className = courseButton.className.replace(" c-expanded", "");
        }
    }

    render() { 
        const { courseList } = this.state;

        return (
            <div className="courses-page-container">
                <div className="shadow p-3 mb-5 bg-white rounded">
                    <h3 className="courses-page-header">Explore Courses</h3>
                </div>

                <div className="courses-listing-available justify-content-center align-items-center">
                    <h3>Available Courses</h3>
                    {courseList.map((course) => (
                        <div key={course.cid}>
                            <button onClick={() => this.handleClickCourse(course.cid)}
                                    className="courses-listing-button"
                                    id={course.cid + "_button"}>
                                {course.title}
                            </button>
                            <div className="courses-listing-detail"
                                    id={course.cid + "_details"}
                                    style={{ display: "none" }}>
                                Name:
                                <h6>{course.title}</h6>                                
                                Instructor:
                                <h6>{course.instructor}</h6>
                                Cost:
                                <h6>${course.cost}</h6>
                                Description:
                                <h6>{course.description}</h6>
                                <button className="btn btn-danger btn-sm float-right">
                                    Enroll
                                </button>
                            </div>
                        </div>
                    ))}
                </div>

            </div>
        );
    }
}
 
export default ExploreCourse;