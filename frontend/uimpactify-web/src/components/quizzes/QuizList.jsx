import React, { Component } from "react";
import { Button, Col, ListGroup, ListGroupItem, Row } from "shards-react";
import {getAllQuizzesByCourseId, deleteQuiz} from "../../helpers/services/quizzes-service";
import TakeQuiz from "./TakeQuiz";
class QuizList extends Component {
  constructor(props) {
    super(props);
    this.state={
        quizzes: [],
        selectedQuiz: null
    }
  }

  componentDidMount() {
      this.fetchQuizzes();
  }

  fetchQuizzes = () => {
    getAllQuizzesByCourseId(this.props.cid).then(
        (res) => {
            this.setState({quizzes: res.data})
        }
    )
  }

  handleDeleteQuiz = (quizId) => {
      deleteQuiz(quizId).then(
          ()=>this.fetchQuizzes()
      )
  }

  render() {
    const quizItems = this.state.quizzes.map(
        (quiz, i)=>{
            return(
            <ListGroupItem key={quiz.id}>
                <Row>
                    <Col xs="3">
                        {`Quiz #${quiz.id}`}
                    </Col>
                    <Col xs="6">
                        
                    </Col>
                    <Col xs="3">
                        {this.props.user.role.name==="IMPACT_CONSULTANT" 
                        ? <Button pill theme="danger" onClick={()=>this.handleDeleteQuiz(quiz.id)}>Delete</Button>
                        : <Button pill theme="success" onClick={()=>this.setState({selectedQuiz: quiz})}>Take Quiz</Button>}
                        
                    </Col>
                </Row>

            </ListGroupItem>
            )
        });
    return (
        <div>
            {!this.state.selectedQuiz ? <ListGroup>{quizItems}</ListGroup> : <TakeQuiz uid={this.props.user.id} quiz={this.state.selectedQuiz}></TakeQuiz>}
        </div>
    );
  }
}

export default QuizList;