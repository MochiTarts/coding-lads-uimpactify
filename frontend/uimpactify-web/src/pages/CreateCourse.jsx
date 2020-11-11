import React, { Component } from 'react';
import "../stylesheets/css/Courses.css";

class CreateCourse extends Component {
    consturctor(props) {
        
    }
    
    render() { 
        return (
            <div className="courses-page-container">
                <div className="shadow p-3 mb-5 bg-white rounded">
                    <h3 className="courses-page-header">New Course</h3>
                </div>

                <form className="course-formGroup">
                    <div class="form-group row">
                        <label htmlFor="title" className="col-sm-2 col-form-label">Course Name</label>
                        <div class="col-sm-10">
                            <input type="text" id="title" className="form-control"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label htmlFor="cost" className="col-sm-2 col-form-label">Course Cost</label>
                        <div class="col-sm-2">
                            <input type="number" min="0.00" max="999.99" step="0.10" id="cost" className="form-control" placeholder="$"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label htmlFor="description" className="col-sm-2 col-form-label">Course Description</label>
                        <div class="col-sm-10">
                            <textarea type="text" id="description" className="form-control" rows="10"/>
                        </div>
                    </div>

                    <a href="javascript:history.back()"
                       className="btn btn-secondary course-formButton">
                        Cancel
                    </a>
                    <button type="submit" 
                            className="btn btn-primary course-formButton">
                        Create
                    </button>
                </form>
            </div>
        );
    }
}
 
export default CreateCourse;