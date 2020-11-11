import React from 'react';
import { Link } from 'react-router-dom';
import clipart from "../img/knowledge_clipart.png";
import "../stylesheets/css/CourseCard.css";

function CourseCard(props) {
    return(
        <div className="card w-25 border-info text-center">
            <div className="card-header">Enrolled</div>
            <img className="card-img-top course-card-img" src={clipart} />
            <div className="card-body course-card-title">
                <h5 className="card-title">
                    <Link to={{
                        pathname: "/courses/mycourses/coursepage",
                        state: {
                            cid: props.cid,
                            title: props.title,
                            description: props.description,
                            instructor: props.instructor
                        }
                    }}> 
                        {props.title}
                    </Link>
                </h5>
            </div>
            <div className="card-footer text-muted">
                Instructed by {props.instructor}
            </div>
        </div>
    );
}

export default CourseCard;