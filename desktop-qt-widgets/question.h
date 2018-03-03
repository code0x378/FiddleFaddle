#ifndef QUESTION_H
#define QUESTION_H

#include <QObject>

class Question : public QObject
{
    Q_OBJECT

public:
    explicit Question(QObject *parent = nullptr);

    QString getQuestion() const;

    void setQuestion(const QString &value);

    QStringList getAnswers() const;

    void setAnswers(const QStringList &value);

    int getCorrectAnswerIndex() const;

    void setCorrectAnswerIndex(int value);

private:

    QString question = "";

    QStringList answers;

    int correctAnswerIndex = 0;
};

#endif // QUESTION_H
