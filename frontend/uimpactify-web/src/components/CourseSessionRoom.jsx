import React, { Component } from 'react';
import Video from 'twilio-video';
import {getAccessToken} from '../helpers/services/twilio-service';
import VideoChat from './video/VideoChat.jsx';
class CourseSessionRoom extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        const storedAuthenticatedUser = JSON.parse(localStorage.getItem('authenticatedUser'));
        return(
            <VideoChat username={storedAuthenticatedUser.username} />
        );
    }

}

export default CourseSessionRoom;