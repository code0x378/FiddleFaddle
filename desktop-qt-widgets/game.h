#ifndef GAME_H
#define GAME_H

#include "question.h"

#include <QObject>

class Game : public QObject
{
    Q_OBJECT

public:
    explicit Game(QObject *parent = nullptr);

    QList<Question *> *getQuestions() const;

    void setQuestions(QList<Question *> *value);

    int getCorrect() const;

    int getTotal() const;

    bool isCorrect(int value);

    Question *newQuestion() const;

    int getLevel() const;

    Question *getNextQuestion();

    int getCurrentIndex() const;

    bool isCorrectAnswer(int value);

    Question *getQuestion();

private:
    QList<Question *> *questions = new QList<Question *>();

    int total = 1;

    int correct = 0;

    int level = 1;

    int currentIndex = 0;

    void generateSampleQuestions();
};

#endif // GAME_H
