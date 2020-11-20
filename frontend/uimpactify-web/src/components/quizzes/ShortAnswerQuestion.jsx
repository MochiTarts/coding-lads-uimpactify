import React, { Component } from "react";
import { createQuiz } from "../../helpers/services/quizzes-service";

class ShortAnswerQuestion extends Component {
  constructor(props) {
    super(props);
    // this.props.question
    // this.props.choices
    this.state = {
      selected: [],
    };
  }

  render() {
    return <div id="someRandomID">FUCK iu</div>;
  }
}

export default ShortAnswerQuestion;
