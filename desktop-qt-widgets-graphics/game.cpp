#include "game.h"

Game::Game(QObject *parent) : QObject(parent)
{
    generateSampleQuestions();
}

void Game:: generateSampleQuestions()
{
    Question *q1 = new Question();
    q1->setQuestion("What is the job title of the person in charge of the camera and lighting crews working on a film?");
    q1->setAnswers( QStringList() << "Production Manager" << "Cinematographer" << "Unit Manager" <<
                    "Gaffer");
    q1->setCorrectAnswerIndex(1);
    questions->push_back(q1);

    Question *q2  = new Question();
    q2->setQuestion("What is a baby rabbit called?");
    q2->setAnswers( QStringList() << "Kitten" << "Pup" << "Rabee" << "Tuber");
    q2->setCorrectAnswerIndex(0);
    questions->push_back(q2);

    Question *q3  = new Question();
    q3->setQuestion("In January 1996 Bill Clinton challenged congress to \"Never, ever\" do this again");
    q3->setAnswers( QStringList() << "Kiss Monica" << "Kiss Hilary" << "Shutdown government" <<
                    "Run for re-election");
    q3->setCorrectAnswerIndex(2);
    questions->push_back(q3);

    Question *q4  = new Question();
    q4->setQuestion("This Mississippi capital was named for a general who later became president");
    q4->setAnswers( QStringList() << "Topeka" << "Jackson" << "Richmond" << "Grant");
    q4->setCorrectAnswerIndex(2);
    questions->push_back(q4);

    Question *q5  = new Question();
    q5->setQuestion("This Honda minivan has a Homeric name");
    q5->setAnswers( QStringList() << "Ulysses" << "Troy" << "Spartan" << "Odyssey");
    q5->setCorrectAnswerIndex(3);
    questions->push_back(q5);
}

QList<Question *> *Game::getQuestions() const
{
    return questions;
}

void Game::setQuestions(QList<Question *> *value)
{
    questions = value;
}

int Game::getCorrect() const
{
    return correct;
}

int Game::getTotal() const
{
    return total;
}

bool Game::isCorrect(int value)
{
    return false;
}

Question *Game::newQuestion() const
{
    return questions->at(1);
}

int Game::getLevel() const
{
    return level;
}

Question *Game::getNextQuestion()
{
    total++;
    if (questions->size() <= ++currentIndex) {
        currentIndex = 0;
    }

    return questions->at(currentIndex);
}

Question *Game::getQuestion()
{
    return questions->at(currentIndex);
}


int Game::getCurrentIndex() const
{
    return currentIndex;
}

bool Game::isCorrectAnswer(int value)
{
    if (value == getQuestion()->getCorrectAnswerIndex()) {
        correct++;
        return true;
    } else {
        return false;
    }
}

