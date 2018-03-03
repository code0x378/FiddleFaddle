#include "question.h"

Question::Question(QObject *parent) : QObject(parent)
{

}

QString Question::getQuestion() const
{
    return question;
}

void Question::setQuestion(const QString &value)
{
    question = value;
}

QStringList Question::getAnswers() const
{
    return answers;
}

void Question::setAnswers(const QStringList &value)
{
    answers = value;
}

int Question::getCorrectAnswerIndex() const
{
    return correctAnswerIndex;
}

void Question::setCorrectAnswerIndex(int value)
{
    correctAnswerIndex = value;
}
